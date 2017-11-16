package net.albertogarrido.dawandalite.utilities;


import android.support.annotation.NonNull;
import android.text.TextUtils;

public class StringUtils {

    private StringUtils() {
        throw new AssertionError("No instances of " + StringUtils.class.getSimpleName());
    }

    @NonNull
    public static String capitalizeFirstWord(@NonNull String word) {
        if (TextUtils.isEmpty(word)) return word;
        word = word.toLowerCase();
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
