package net.albertogarrido.dawandalite.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;

abstract class $AutoValue_Seller extends $$AutoValue_Seller {
  $AutoValue_Seller(long id, String username, String country, int rating) {
    super(id, username, country, rating);
  }

  public static final class GsonTypeAdapter extends TypeAdapter<Seller> {
    private final TypeAdapter<Long> idAdapter;
    private final TypeAdapter<String> usernameAdapter;
    private final TypeAdapter<String> countryAdapter;
    private final TypeAdapter<Integer> ratingAdapter;
    public GsonTypeAdapter(Gson gson) {
      this.idAdapter = gson.getAdapter(Long.class);
      this.usernameAdapter = gson.getAdapter(String.class);
      this.countryAdapter = gson.getAdapter(String.class);
      this.ratingAdapter = gson.getAdapter(Integer.class);
    }
    @Override
    public void write(JsonWriter jsonWriter, Seller object) throws IOException {
      if (object == null) {
        jsonWriter.nullValue();
        return;
      }
      jsonWriter.beginObject();
      jsonWriter.name("id");
      idAdapter.write(jsonWriter, object.id());
      jsonWriter.name("username");
      usernameAdapter.write(jsonWriter, object.username());
      jsonWriter.name("country");
      countryAdapter.write(jsonWriter, object.country());
      jsonWriter.name("rating");
      ratingAdapter.write(jsonWriter, object.rating());
      jsonWriter.endObject();
    }
    @Override
    public Seller read(JsonReader jsonReader) throws IOException {
      if (jsonReader.peek() == JsonToken.NULL) {
        jsonReader.nextNull();
        return null;
      }
      jsonReader.beginObject();
      long id = 0L;
      String username = null;
      String country = null;
      int rating = 0;
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
          case "username": {
            username = usernameAdapter.read(jsonReader);
            break;
          }
          case "country": {
            country = countryAdapter.read(jsonReader);
            break;
          }
          case "rating": {
            rating = ratingAdapter.read(jsonReader);
            break;
          }
          default: {
            jsonReader.skipValue();
          }
        }
      }
      jsonReader.endObject();
      return new AutoValue_Seller(id, username, country, rating);
    }
  }
}
