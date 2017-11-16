package net.albertogarrido.dawandalite.network;

import android.arch.lifecycle.MutableLiveData;

import net.albertogarrido.dawandalite.di.DI;
import net.albertogarrido.dawandalite.model.Category;
import net.albertogarrido.dawandalite.model.Product;
import net.albertogarrido.dawandalite.model.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// For reviewers: improvements could be done here by using Dagger2 instead of manual DI
public class CategoriesNetworkRepository {

    private static CategoriesNetworkRepository categoriesRepository;
    private CategoriesApiService categoriesApiService;

    private CategoriesNetworkRepository(Retrofit retrofit) {
        categoriesApiService = retrofit.create(CategoriesApiService.class);
    }

    public synchronized static CategoriesNetworkRepository instance() {
        if (categoriesRepository == null) {
            categoriesRepository = new CategoriesNetworkRepository(DI.getRetrofitClient());
        }
        return categoriesRepository;
    }

    public MutableLiveData<Resource<List<Category>>> getCategoriesList() {
        final MutableLiveData<Resource<List<Category>>> liveData = new MutableLiveData<>();

        liveData.postValue(Resource.loading(Category.emptyList()));

        categoriesApiService.categories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.body() == null) {
                    liveData.setValue(Resource.emptyData(response.message(), response.body()));
                } else if (response.isSuccessful()) {
                    if (response.body().size() == 0) {
                        liveData.setValue(Resource.emptyData(response.message(), response.body()));
                    }
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getMessage(), Category.emptyList()));
            }
        });

        return liveData;
    }

    public MutableLiveData<Resource<List<Product>>> getProductsFromCategory(int categoryId) {
        final MutableLiveData<Resource<List<Product>>> liveData = new MutableLiveData<>();

        liveData.postValue(Resource.loading(Product.emptyList()));

        categoriesApiService.products(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() == null) {
                    liveData.setValue(Resource.emptyData(response.message(), response.body()));
                } else if (response.isSuccessful()) {
                    if (response.body().size() == 0) {
                        liveData.setValue(Resource.emptyData(response.message(), response.body()));
                    }
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                liveData.setValue(Resource.error(t.getMessage(), Product.emptyList()));
            }
        });

        return liveData;
    }
}
