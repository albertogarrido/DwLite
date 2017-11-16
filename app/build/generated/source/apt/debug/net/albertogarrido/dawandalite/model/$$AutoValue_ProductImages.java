
package net.albertogarrido.dawandalite.model;

import com.google.gson.annotations.SerializedName;

 abstract class $$AutoValue_ProductImages extends ProductImages {

  private final String mainImg;

  $$AutoValue_ProductImages(
      String mainImg) {
    if (mainImg == null) {
      throw new NullPointerException("Null mainImg");
    }
    this.mainImg = mainImg;
  }

  @SerializedName(value = "product_l")
  @Override
  public String mainImg() {
    return mainImg;
  }

  @Override
  public String toString() {
    return "ProductImages{"
        + "mainImg=" + mainImg
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ProductImages) {
      ProductImages that = (ProductImages) o;
      return (this.mainImg.equals(that.mainImg()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.mainImg.hashCode();
    return h;
  }

}
