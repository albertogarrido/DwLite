package net.albertogarrido.dawandalite.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class ProductImages implements Parcelable {
    @SerializedName("product_l")
    public abstract String mainImg();

    public static ProductImages create(String mainImg) {
        return new AutoValue_ProductImages(mainImg);
    }

    public static TypeAdapter<ProductImages> typeAdapter(Gson gson) {
        return new AutoValue_ProductImages.GsonTypeAdapter(gson);
    }
}