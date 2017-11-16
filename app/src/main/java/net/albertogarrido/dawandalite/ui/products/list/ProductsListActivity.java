package net.albertogarrido.dawandalite.ui.products.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import net.albertogarrido.dawandalite.R;
import net.albertogarrido.dawandalite.di.DI;
import net.albertogarrido.dawandalite.model.Product;
import net.albertogarrido.dawandalite.model.Resource;
import net.albertogarrido.dawandalite.model.Status;
import net.albertogarrido.dawandalite.network.NetworkStatusLiveData;
import net.albertogarrido.dawandalite.ui.categories.CategoriesActivity;
import net.albertogarrido.dawandalite.ui.common.AppCompatLifecycleActivity;
import net.albertogarrido.dawandalite.ui.common.ErrorView;
import net.albertogarrido.dawandalite.ui.common.LoadingView;
import net.albertogarrido.dawandalite.ui.common.RecyclerItemClickListener;
import net.albertogarrido.dawandalite.ui.products.detail.ProductDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListActivity extends AppCompatLifecycleActivity
        implements ProductsPresenter.ProductsView, ErrorView.RetryClickListener {

    private static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.error_view)
    ErrorView errorView;

    @BindView(R.id.loading_view)
    LoadingView loadingView;

    @BindView(R.id.products_list)
    RecyclerView productsRecycler;

    private ProductsPresenter presenter;
    private LiveData<Resource<List<Product>>> productsListLiveData;
    private NetworkStatusLiveData networkStatusLiveData;
    private int categoryId;
    private String categoryName;

    public static Intent intent(CategoriesActivity categoriesActivity, int categoryId, String categoryName) {
        return new Intent(categoriesActivity, ProductsListActivity.class)
                .putExtra(CATEGORY_ID, categoryId)
                .putExtra(CATEGORY_NAME, categoryName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getExtras();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(categoryName != null ? categoryName : getResources().getString(R.string.products));
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
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void getExtras() {
        categoryId = getIntent().getExtras().getInt(CATEGORY_ID);
        categoryName = getIntent().getExtras().getString(CATEGORY_NAME);
    }

    private void init() {
        Resources res = getResources();
        loadingView.bind(String.format(res.getString(R.string.loading), categoryName));
        presenter = new ProductsPresenter(this, createViewModel());
        networkStatusLiveData = DI.getNetworkStatusLiveData(this);
        observeConnectivityChanges();
        if (NetworkStatusLiveData.isConnected(this)) {
            observeLiveData(false);
        } else {
            showError(ErrorView.ErrorType.NETWORK, null);
        }
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
        productsRecycler.setVisibility(show ? View.VISIBLE : View.GONE);
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
    public void populateProducts(final List<Product> products) {
        toggleLoading(false);
        toggleMainContent(true);
        configureRecyclerView();
        ProductsAdapter adapter = new ProductsAdapter(products, this);
        adapter.addRecyclerItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, ImageView imageShared, int position) {
                Product product = products.get(position);

                String sharedImageName = ViewCompat.getTransitionName(imageShared);
                Intent productDetailIntent = ProductDetailActivity.intent(ProductsListActivity.this, product, sharedImageName, categoryName);

                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(ProductsListActivity.this, imageShared, sharedImageName);
                    startActivity(productDetailIntent, options.toBundle());
                } else {
                    startActivity(productDetailIntent);
                }
            }
        });
        productsRecycler.setAdapter(adapter);
    }

    private void configureRecyclerView() {
        productsRecycler.setHasFixedSize(true);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        productsRecycler.setLayoutManager(gridLayoutManager);
    }

    private ProductsViewModel createViewModel() {
        ProductsViewModel.ProductsViewModelFactory productsViewModelFactory =
                new ProductsViewModel.ProductsViewModelFactory(getApplication(), categoryId);
        return ViewModelProviders.of(this, productsViewModelFactory).get(ProductsViewModel.class);
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
        return productsListLiveData != null &&
                productsListLiveData.getValue() != null &&
                productsListLiveData.getValue().status == Status.SUCCESS;
    }

    private void observeLiveData(boolean refresh) {
        toggleLoading(true);
        hideError();
        toggleMainContent(false);
        productsListLiveData = presenter.getProductsLiveData(categoryId, refresh);
        productsListLiveData.observe(this, new Observer<Resource<List<Product>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Product>> resource) {
                presenter.handleResponse(resource);
            }
        });
    }

    private void onRefresh() {
        if (NetworkStatusLiveData.isConnected(this)) {
            observeLiveData(true);
        } else if (!viewModelContainsData()) {
            showError(ErrorView.ErrorType.NETWORK, null);
        }
    }
}
