package net.albertogarrido.dawandalite.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;

abstract class $AutoValue_Product extends $$AutoValue_Product {
  $AutoValue_Product(int id, String title, Price price, String badge, Seller seller,
      ProductImages images) {
    super(id, title, price, badge, seller, images);
  }

  public static final class GsonTypeAdapter extends TypeAdapter<Product> {
    private final TypeAdapter<Integer> idAdapter;
    private final TypeAdapter<String> titleAdapter;
    private final TypeAdapter<Price> priceAdapter;
    private final TypeAdapter<String> badgeAdapter;
    private final TypeAdapter<Seller> sellerAdapter;
    private final TypeAdapter<ProductImages> imagesAdapter;
    public GsonTypeAdapter(Gson gson) {
      this.idAdapter = gson.getAdapter(Integer.class);
      this.titleAdapter = gson.getAdapter(String.class);
      this.priceAdapter = gson.getAdapter(Price.class);
      this.badgeAdapter = gson.getAdapter(String.class);
      this.sellerAdapter = gson.getAdapter(Seller.class);
      this.imagesAdapter = gson.getAdapter(ProductImages.class);
    }
    @Override
    public void write(JsonWriter jsonWriter, Product object) throws IOException {
      if (object == null) {
        jsonWriter.nullValue();
        return;
      }
      jsonWriter.beginObject();
      jsonWriter.name("id");
      idAdapter.write(jsonWriter, object.id());
      jsonWriter.name("title");
      titleAdapter.write(jsonWriter, object.title());
      jsonWriter.name("price");
      priceAdapter.write(jsonWriter, object.price());
      jsonWriter.name("badge");
      badgeAdapter.write(jsonWriter, object.badge());
      jsonWriter.name("seller");
      sellerAdapter.write(jsonWriter, object.seller());
      jsonWriter.name("default_image");
      imagesAdapter.write(jsonWriter, object.images());
      jsonWriter.endObject();
    }
    @Override
    public Product read(JsonReader jsonReader) throws IOException {
      if (jsonReader.peek() == JsonToken.NULL) {
        jsonReader.nextNull();
        return null;
      }
      jsonReader.beginObject();
      int id = 0;
      String title = null;
      Price price = null;
      String badge = null;
      Seller seller = null;
      ProductImages images = null;
      while (jsonReader.hasNext()) {
        String _name = jsonReader.nextName();
        if (jsonReader.peek() == JsonToken.NULL) {
          jsonReader.nextNull();
          continue;
        }
        switch (_name) {
          case "id": {
            id = idAdapter.read(jsonReader);
            break;
          }
          case "title": {
            title = titleAdapter.read(jsonReader);
            break;
          }
          case "price": {
            price = priceAdapter.read(jsonReader);
            break;
          }
          case "badge": {
            badge = badgeAdapter.read(jsonReader);
            break;
          }
          case "seller": {
            seller = sellerAdapter.read(jsonReader);
            break;
          }
          case "default_image": {
            images = imagesAdapter.read(jsonReader);
            break;
          }
          default: {
            jsonReader.skipValue();
          }
        }
      }
      jsonReader.endObject();
      return new AutoValue_Product(id, title, price, badge, seller, images);
    }
  }
}
