package net.albertogarrido.dawandalite.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


@AutoValue
public abstract class Category {

    public abstract int id();

    public abstract String name();

    @SerializedName("image_url")
    public abstract String imgUrl();


    public static Category create(int id, String name, String imgUrl) {
        return new AutoValue_Category(id, name, imgUrl);
    }

    public static TypeAdapter<Category> typeAdapter(Gson gson) {
        return new AutoValue_Category.GsonTypeAdapter(gson);
    }

    public static List<Category> emptyList() {
        return new ArrayList<>(0);
    }
}
