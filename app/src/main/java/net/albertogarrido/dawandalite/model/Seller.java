package net.albertogarrido.dawandalite.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Seller implements Parcelable {

    public abstract long id();

    public abstract String username();

    public abstract String country();

    public abstract int rating();

    public static Seller create(long id, String username, String country, int rating) {
        return new AutoValue_Seller(id, username, country, rating);
    }

    public static TypeAdapter<Seller> typeAdapter(Gson gson) {
        return new AutoValue_Seller.GsonTypeAdapter(gson);
    }
}
