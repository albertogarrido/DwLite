package net.albertogarrido.dawandalite.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.Override;
import java.lang.String;

final class AutoValue_Product extends $AutoValue_Product {
  public static final Parcelable.Creator<AutoValue_Product> CREATOR = new Parcelable.Creator<AutoValue_Product>() {
    @Override
    public AutoValue_Product createFromParcel(Parcel in) {
      return new AutoValue_Product(
          in.readInt(),
          in.readString(),
          (Price) in.readParcelable(Price.class.getClassLoader()),
          in.readInt() == 0 ? in.readString() : null,
          (Seller) in.readParcelable(Seller.class.getClassLoader()),
          (ProductImages) in.readParcelable(ProductImages.class.getClassLoader())
      );
    }
    @Override
    public AutoValue_Product[] newArray(int size) {
      return new AutoValue_Product[size];
    }
  };

  AutoValue_Product(int id, String title, Price price, String badge, Seller seller,
      ProductImages images) {
    super(id, title, price, badge, seller, images);
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id());
    dest.writeString(title());
    dest.writeParcelable(price(), flags);
    if (badge() == null) {
      dest.writeInt(1);
    } else {
      dest.writeInt(0);
      dest.writeString(badge());
    }
    dest.writeParcelable(seller(), flags);
    dest.writeParcelable(images(), flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }
}
