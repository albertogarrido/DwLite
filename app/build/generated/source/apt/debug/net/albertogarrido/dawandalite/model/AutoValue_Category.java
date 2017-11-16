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

final class AutoValue_Category extends $AutoValue_Category {
  AutoValue_Category(int id, String name, String imgUrl) {
    super(id, name, imgUrl);
  }

  public static final class GsonTypeAdapter extends TypeAdapter<Category> {
    private final TypeAdapter<Integer> idAdapter;
    private final TypeAdapter<String> nameAdapter;
    private final TypeAdapter<String> imgUrlAdapter;
    public GsonTypeAdapter(Gson gson) {
      this.idAdapter = gson.getAdapter(Integer.class);
      this.nameAdapter = gson.getAdapter(String.class);
      this.imgUrlAdapter = gson.getAdapter(String.class);
    }
    @Override
    public void write(JsonWriter jsonWriter, Category object) throws IOException {
      if (object == null) {
        jsonWriter.nullValue();
        return;
      }
      jsonWriter.beginObject();
      jsonWriter.name("id");
      idAdapter.write(jsonWriter, object.id());
      jsonWriter.name("name");
      nameAdapter.write(jsonWriter, object.name());
      jsonWriter.name("image_url");
      imgUrlAdapter.write(jsonWriter, object.imgUrl());
      jsonWriter.endObject();
    }
    @Override
    public Category read(JsonReader jsonReader) throws IOException {
      if (jsonReader.peek() == JsonToken.NULL) {
        jsonReader.nextNull();
        return null;
      }
      jsonReader.beginObject();
      int id = 0;
      String name = null;
      String imgUrl = null;
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
          case "name": {
            name = nameAdapter.read(jsonReader);
            break;
          }
          case "image_url": {
            imgUrl = imgUrlAdapter.read(jsonReader);
            break;
          }
          default: {
            jsonReader.skipValue();
          }
        }
      }
      jsonReader.endObject();
      return new AutoValue_Category(id, name, imgUrl);
    }
  }
}
