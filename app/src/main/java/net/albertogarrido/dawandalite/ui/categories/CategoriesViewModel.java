package net.albertogarrido.dawandalite.ui.categories;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import net.albertogarrido.dawandalite.model.Category;
import net.albertogarrido.dawandalite.model.Resource;
import net.albertogarrido.dawandalite.network.CategoriesNetworkRepository;

import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {

    private LiveData<Resource<List<Category>>> categoriesListLiveData;

    public CategoriesViewModel(Application application) {
        super(application);
        categoriesListLiveData = CategoriesNetworkRepository.instance().getCategoriesList();
    }

    public LiveData<Resource<List<Category>>> getCategoriesListLiveData() {
        return categoriesListLiveData;
    }

    public LiveData<Resource<List<Category>>> refreshLiveData() {
        return CategoriesNetworkRepository.instance().getCategoriesList();
    }
}
