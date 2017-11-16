package net.albertogarrido.dawandalite.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.text.DecimalFormat;

@AutoValue
public abstract class Price implements Parcelable {

    public abstract String currency();

    public abstract long cents();

    public static Price create(String currency, long cents) {
        return new AutoValue_Price(currency, cents);
    }

    public static TypeAdapter<Price> typeAdapter(Gson gson) {
        return new AutoValue_Price.GsonTypeAdapter(gson);
    }

    public String formatPrice() {
        return new DecimalFormat("#0.00").format(cents() / 100) +
                " " +
                currency();
    }
}
