package net.albertogarrido.dawandalite.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.Override;
import java.lang.String;

final class AutoValue_Seller extends $AutoValue_Seller {
  public static final Parcelable.Creator<AutoValue_Seller> CREATOR = new Parcelable.Creator<AutoValue_Seller>() {
    @Override
    public AutoValue_Seller createFromParcel(Parcel in) {
      return new AutoValue_Seller(
          in.readLong(),
          in.readString(),
          in.readString(),
          in.readInt()
      );
    }
    @Override
    public AutoValue_Seller[] newArray(int size) {
      return new AutoValue_Seller[size];
    }
  };

  AutoValue_Seller(long id, String username, String country, int rating) {
    super(id, username, country, rating);
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(id());
    dest.writeString(username());
    dest.writeString(country());
    dest.writeInt(rating());
  }

  @Override
  public int describeContents() {
    return 0;
  }
}
