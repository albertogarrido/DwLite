package net.albertogarrido.dawandalite.ui.categories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.albertogarrido.dawandalite.R;
import net.albertogarrido.dawandalite.di.DI;
import net.albertogarrido.dawandalite.model.Category;
import net.albertogarrido.dawandalite.model.Resource;
import net.albertogarrido.dawandalite.model.Status;
import net.albertogarrido.dawandalite.network.NetworkStatusLiveData;
import net.albertogarrido.dawandalite.ui.common.AppCompatLifecycleActivity;
import net.albertogarrido.dawandalite.ui.common.ErrorView;
import net.albertogarrido.dawandalite.ui.common.LoadingView;
import net.albertogarrido.dawandalite.ui.common.RecyclerItemClickListener;
import net.albertogarrido.dawandalite.ui.products.list.ProductsListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatLifecycleActivity
        implements CategoriesPresenter.CategoriesView, ErrorView.RetryClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.error_view)
    ErrorView errorView;

    @BindView(R.id.loading_view)
    LoadingView loadingView;

    @BindView(R.id.categories_list)
    RecyclerView categoriesRecycler;

    private CategoriesPresenter presenter;
    private LiveData<Resource<List<Category>>> categoriesListLiveData;
    private NetworkStatusLiveData networkStatusLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(getResources().getString(R.string.categories));
    }

    private void init() {
        Resources res = getResources();
        loadingView.bind(String.format(res.getString(R.string.loading), res.getString(R.string.categories)));
        presenter = new CategoriesPresenter(this, createViewModel());
        networkStatusLiveData = DI.getNetworkStatusLiveData(this);
        observeConnectivityChanges();
        if (NetworkStatusLiveData.isConnected(this)) {
            observeLiveData(false);
        } else {
            showError(ErrorView.ErrorType.NETWORK, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                onRefresh();
                break;
        }
        return true;
    }

    @Override
    public void showError(ErrorView.ErrorType errorType, @Nullable String optionalMessage) {
        errorView.setVisibility(View.VISIBLE);
        toggleLoading(false);
        toggleMainContent(false);
        errorView.bind(errorType, optionalMessage, this);
    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void toggleLoading(boolean show) {
        loadingView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void toggleMainContent(boolean show) {
        categoriesRecycler.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRetry() {
        if (viewModelContainsData()) return;
        if (NetworkStatusLiveData.isConnected(this)) {
            observeLiveData(true);
        } else {
            showError(ErrorView.ErrorType.NETWORK, null);
        }
    }

    @Override
    public void populateCategories(final List<Category> categories) {
        toggleLoading(false);
        toggleMainContent(true);
        configureRecyclerView();
        CategoriesAdapter adapter = new CategoriesAdapter(categories, this);
        adapter.addRecyclerItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, ImageView categoryImage, int position) {
                int categoryId = categories.get(position).id();
                String categoryName = categories.get(position).name();
                Intent productsListIntent = ProductsListActivity.intent(CategoriesActivity.this, categoryId, categoryName);
                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(CategoriesActivity.this, categoryImage, "catImage");
                    startActivity(productsListIntent, options.toBundle());
                } else {
                    startActivity(productsListIntent);
                }
            }
        });
        categoriesRecycler.setAdapter(adapter);
    }

    private void configureRecyclerView() {
        categoriesRecycler.setHasFixedSize(true);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        categoriesRecycler.setLayoutManager(gridLayoutManager);
    }

    private void onRefresh() {
        if (NetworkStatusLiveData.isConnected(this)) {
            observeLiveData(true);
        } else if (!viewModelContainsData()) {
            showError(ErrorView.ErrorType.NETWORK, null);
        }
        //TODO consider using Snackbar msg in case those 2 are false
    }

    private CategoriesViewModel createViewModel() {
        return ViewModelProviders.of(this).get(CategoriesViewModel.class);
    }

    private void observeLiveData(boolean refresh) {
        toggleLoading(true);
        hideError();
        toggleMainContent(false);
        categoriesListLiveData = presenter.getCategoriesLiveData(refresh);
        categoriesListLiveData.observe(this, new Observer<Resource<List<Category>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Category>> resource) {
                presenter.handleResponse(resource);
            }
        });
    }

    private void observeConnectivityChanges() {
        networkStatusLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isConnected) {
                if (isConnected == null || !isConnected) {
                    if (!viewModelContainsData()) {
                        showError(ErrorView.ErrorType.NETWORK, null);
                    }
                }
            }
        });
    }

    private boolean viewModelContainsData() {
        return categoriesListLiveData != null &&
                categoriesListLiveData.getValue() != null &&
                categoriesListLiveData.getValue().status == Status.SUCCESS;
    }
}
