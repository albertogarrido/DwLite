package net.albertogarrido.dawandalite.ui.products.list;


import android.arch.lifecycle.LiveData;
import android.util.Log;

import net.albertogarrido.dawandalite.model.Product;
import net.albertogarrido.dawandalite.model.Resource;
import net.albertogarrido.dawandalite.model.Status;
import net.albertogarrido.dawandalite.ui.DaWandaLiteView;
import net.albertogarrido.dawandalite.ui.common.ErrorView;

import java.util.List;

public class ProductsPresenter {

    private final ProductsPresenter.ProductsView view;
    private ProductsViewModel viewModel;

    interface ProductsView extends DaWandaLiteView {

        void populateProducts(List<Product> categories);
    }

    public ProductsPresenter(ProductsPresenter.ProductsView view, ProductsViewModel viewModel) {
        this.view = view;
        setViewModel(viewModel);
    }

    public void setViewModel(ProductsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public LiveData<Resource<List<Product>>> getProductsLiveData(int categoryId, boolean refresh) {
        if (refresh) {
            return viewModel.refreshLiveData();
        }
        return viewModel.getProductsListLiveData();
    }

    public void handleResponse(Resource<List<Product>> resource) {
        if (resource.status.equals(Status.SUCCESS)) {
            populateData(resource.data);
        } else if (resource.status.equals(Status.EMPTY_DATA)) {
            view.toggleLoading(false);
            view.showError(ErrorView.ErrorType.NOT_FOUND, null);
        } else if (resource.status.equals(Status.LOADING)) {
            view.hideError();
            view.toggleMainContent(false);
            view.toggleLoading(true);
        } else if (resource.status.equals(Status.ERROR)) {
            view.toggleLoading(false);
            view.showError(ErrorView.ErrorType.OTHER, resource.message);
        }
    }

    private void populateData(List<Product> products) {
        view.populateProducts(products);
        for (Product p :
                products) {
            Log.e("TAG", p.toString());
        }
    }

}
