package net.albertogarrido.dawandalite.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;

abstract class $AutoValue_ProductImages extends $$AutoValue_ProductImages {
  $AutoValue_ProductImages(String mainImg) {
    super(mainImg);
  }

  public static final class GsonTypeAdapter extends TypeAdapter<ProductImages> {
    private final TypeAdapter<String> mainImgAdapter;
    public GsonTypeAdapter(Gson gson) {
      this.mainImgAdapter = gson.getAdapter(String.class);
    }
    @Override
    public void write(JsonWriter jsonWriter, ProductImages object) throws IOException {
      if (object == null) {
        jsonWriter.nullValue();
        return;
      }
      jsonWriter.beginObject();
      jsonWriter.name("product_l");
      mainImgAdapter.write(jsonWriter, object.mainImg());
      jsonWriter.endObject();
    }
    @Override
    public ProductImages read(JsonReader jsonReader) throws IOException {
      if (jsonReader.peek() == JsonToken.NULL) {
        jsonReader.nextNull();
        return null;
      }
      jsonReader.beginObject();
      String mainImg = null;
      while (jsonReader.hasNext()) {
        String _name = jsonReader.nextName();
        if (jsonReader.peek() == JsonToken.NULL) {
          jsonReader.nextNull();
          continue;
        }
        switch (_name) {
          case "product_l": {
            mainImg = mainImgAdapter.read(jsonReader);
            break;
          }
          default: {
            jsonReader.skipValue();
          }
        }
      }
      jsonReader.endObject();
      return new AutoValue_ProductImages(mainImg);
    }
  }
}
