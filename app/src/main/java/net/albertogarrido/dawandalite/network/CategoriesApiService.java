package net.albertogarrido.dawandalite.network;


import net.albertogarrido.dawandalite.model.Category;
import net.albertogarrido.dawandalite.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriesApiService {

    String ENDPOINT_CATEGORIES = "categories.json";
    String ENDPOINT_PRODUCTS_LIST = "categories/{categoryId}.json";

    @GET(ENDPOINT_CATEGORIES)
    Call<List<Category>> categories();

    @GET(ENDPOINT_PRODUCTS_LIST)
    Call<List<Product>> products(@Path("categoryId") int categoryId);

}
