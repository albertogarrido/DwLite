package net.albertogarrido.dawandalite.ui.products.list;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import net.albertogarrido.dawandalite.model.Product;
import net.albertogarrido.dawandalite.model.Resource;
import net.albertogarrido.dawandalite.network.CategoriesNetworkRepository;

import java.util.List;

public class ProductsViewModel extends AndroidViewModel {

    private LiveData<Resource<List<Product>>> productsListLiveData;
    private final int categoryId;

    public ProductsViewModel(Application application, int categoryId) {
        super(application);
        this.categoryId = categoryId;
        productsListLiveData = CategoriesNetworkRepository.instance().getProductsFromCategory(categoryId);
    }

    public LiveData<Resource<List<Product>>> getProductsListLiveData() {
        return productsListLiveData;
    }

    public LiveData<Resource<List<Product>>> refreshLiveData() {
        return CategoriesNetworkRepository.instance().getProductsFromCategory(categoryId);
    }

    public static class ProductsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final int categoryId;

        public ProductsViewModelFactory(@NonNull Application application, int categoryId) {
            this.application = application;
            this.categoryId = categoryId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ProductsViewModel(application, categoryId);
        }
    }
}
