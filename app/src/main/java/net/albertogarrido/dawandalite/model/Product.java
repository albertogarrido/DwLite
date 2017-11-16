package net.albertogarrido.dawandalite.model;


import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@AutoValue
public abstract class Product implements Parcelable {

    public static final String BADGE_SALE = "sale";
    public static final String BADGE_EXPRESS = "express";
    public static final String BADGE_NEW = "new";

    public abstract int id();

    public abstract String title();

    public abstract Price price();

    @Nullable
    public abstract String badge();

    public abstract Seller seller();

    @SerializedName("default_image")
    public abstract ProductImages images();

    public static List<Product> emptyList() {
        return new ArrayList<>(0);
    }

    public static TypeAdapter<Product> typeAdapter(Gson gson) {
        return new AutoValue_Product.GsonTypeAdapter(gson);
    }
}
