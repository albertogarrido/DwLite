package net.albertogarrido.dawandalite.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.Override;
import java.lang.String;

final class AutoValue_ProductImages extends $AutoValue_ProductImages {
  public static final Parcelable.Creator<AutoValue_ProductImages> CREATOR = new Parcelable.Creator<AutoValue_ProductImages>() {
    @Override
    public AutoValue_ProductImages createFromParcel(Parcel in) {
      return new AutoValue_ProductImages(
          in.readString()
      );
    }
    @Override
    public AutoValue_ProductImages[] newArray(int size) {
      return new AutoValue_ProductImages[size];
    }
  };

  AutoValue_ProductImages(String mainImg) {
    super(mainImg);
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mainImg());
  }

  @Override
  public int describeContents() {
    return 0;
  }
}
