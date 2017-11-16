package net.albertogarrido.dawandalite.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;

abstract class $AutoValue_Price extends $$AutoValue_Price {
  $AutoValue_Price(String currency, long cents) {
    super(currency, cents);
  }

  public static final class GsonTypeAdapter extends TypeAdapter<Price> {
    private final TypeAdapter<String> currencyAdapter;
    private final TypeAdapter<Long> centsAdapter;
    public GsonTypeAdapter(Gson gson) {
      this.currencyAdapter = gson.getAdapter(String.class);
      this.centsAdapter = gson.getAdapter(Long.class);
    }
    @Override
    public void write(JsonWriter jsonWriter, Price object) throws IOException {
      if (object == null) {
        jsonWriter.nullValue();
        return;
      }
      jsonWriter.beginObject();
      jsonWriter.name("currency");
      currencyAdapter.write(jsonWriter, object.currency());
      jsonWriter.name("cents");
      centsAdapter.write(jsonWriter, object.cents());
      jsonWriter.endObject();
    }
    @Override
    public Price read(JsonReader jsonReader) throws IOException {
      if (jsonReader.peek() == JsonToken.NULL) {
        jsonReader.nextNull();
        return null;
      }
      jsonReader.beginObject();
      String currency = null;
      long cents = 0L;
      while (jsonReader.hasNext()) {
        String _name = jsonReader.nextName();
        if (jsonReader.peek() == JsonToken.NULL) {
          jsonReader.nextNull();
          continue;
        }
        switch (_name) {
          case "currency": {
            currency = currencyAdapter.read(jsonReader);
            break;
          }
          case "cents": {
            cents = centsAdapter.read(jsonReader);
            break;
          }
          default: {
            jsonReader.skipValue();
          }
        }
      }
      jsonReader.endObject();
      return new AutoValue_Price(currency, cents);
    }
  }
}
