package net.albertogarrido.dawandalite.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.Override;
import java.lang.String;

final class AutoValue_Price extends $AutoValue_Price {
  public static final Parcelable.Creator<AutoValue_Price> CREATOR = new Parcelable.Creator<AutoValue_Price>() {
    @Override
    public AutoValue_Price createFromParcel(Parcel in) {
      return new AutoValue_Price(
          in.readString(),
          in.readLong()
      );
    }
    @Override
    public AutoValue_Price[] newArray(int size) {
      return new AutoValue_Price[size];
    }
  };

  AutoValue_Price(String currency, long cents) {
    super(currency, cents);
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(currency());
    dest.writeLong(cents());
  }

  @Override
  public int describeContents() {
    return 0;
  }
}
