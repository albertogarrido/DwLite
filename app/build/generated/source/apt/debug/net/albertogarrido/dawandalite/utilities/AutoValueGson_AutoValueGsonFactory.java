package net.albertogarrido.dawandalite.utilities;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.Override;
import java.lang.SuppressWarnings;
import net.albertogarrido.dawandalite.model.Category;
import net.albertogarrido.dawandalite.model.Price;
import net.albertogarrido.dawandalite.model.Product;
import net.albertogarrido.dawandalite.model.ProductImages;
import net.albertogarrido.dawandalite.model.Seller;

public final class AutoValueGson_AutoValueGsonFactory extends AutoValueGsonFactory {
  @Override
  @SuppressWarnings("unchecked")
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    Class<T> rawType = (Class<T>) type.getRawType();
    if (Category.class.isAssignableFrom(rawType)) {
      return (TypeAdapter<T>) Category.typeAdapter(gson);
    } else if (Price.class.isAssignableFrom(rawType)) {
      return (TypeAdapter<T>) Price.typeAdapter(gson);
    } else if (Product.class.isAssignableFrom(rawType)) {
      return (TypeAdapter<T>) Product.typeAdapter(gson);
    } else if (ProductImages.class.isAssignableFrom(rawType)) {
      return (TypeAdapter<T>) ProductImages.typeAdapter(gson);
    } else if (Seller.class.isAssignableFrom(rawType)) {
      return (TypeAdapter<T>) Seller.typeAdapter(gson);
    } else {
      return null;
    }
  }
}
