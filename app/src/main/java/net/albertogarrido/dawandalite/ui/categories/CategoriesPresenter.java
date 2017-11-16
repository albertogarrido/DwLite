package net.albertogarrido.dawandalite.ui.categories;


import android.arch.lifecycle.LiveData;

import net.albertogarrido.dawandalite.model.Category;
import net.albertogarrido.dawandalite.model.Resource;
import net.albertogarrido.dawandalite.model.Status;
import net.albertogarrido.dawandalite.ui.DaWandaLiteView;
import net.albertogarrido.dawandalite.ui.common.ErrorView;

import java.util.List;

class CategoriesPresenter {

    private final CategoriesView view;
    private CategoriesViewModel viewModel;

    interface CategoriesView extends DaWandaLiteView {

        void populateCategories(List<Category> categories);
    }

    public CategoriesPresenter(CategoriesView view, CategoriesViewModel viewModel) {
        this.view = view;
        setViewModel(viewModel);
    }

    public void setViewModel(CategoriesViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public LiveData<Resource<List<Category>>> getCategoriesLiveData(boolean refresh) {
        if (refresh) {
            return viewModel.refreshLiveData();
        }
        return viewModel.getCategoriesListLiveData();
    }

    public void handleResponse(Resource<List<Category>> resource) {
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

    private void populateData(List<Category> categories) {
        view.populateCategories(categories);
    }
}

