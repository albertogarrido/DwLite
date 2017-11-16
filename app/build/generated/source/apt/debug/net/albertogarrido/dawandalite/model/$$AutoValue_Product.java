
package net.albertogarrido.dawandalite.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

 abstract class $$AutoValue_Product extends Product {

  private final int id;
  private final String title;
  private final Price price;
  private final String badge;
  private final Seller seller;
  private final ProductImages images;

  $$AutoValue_Product(
      int id,
      String title,
      Price price,
      @Nullable String badge,
      Seller seller,
      ProductImages images) {
    this.id = id;
    if (title == null) {
      throw new NullPointerException("Null title");
    }
    this.title = title;
    if (price == null) {
      throw new NullPointerException("Null price");
    }
    this.price = price;
    this.badge = badge;
    if (seller == null) {
      throw new NullPointerException("Null seller");
    }
    this.seller = seller;
    if (images == null) {
      throw new NullPointerException("Null images");
    }
    this.images = images;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public String title() {
    return title;
  }

  @Override
  public Price price() {
    return price;
  }

  @Nullable
  @Override
  public String badge() {
    return badge;
  }

  @Override
  public Seller seller() {
    return seller;
  }

  @SerializedName(value = "default_image")
  @Override
  public ProductImages images() {
    return images;
  }

  @Override
  public String toString() {
    return "Product{"
        + "id=" + id + ", "
        + "title=" + title + ", "
        + "price=" + price + ", "
        + "badge=" + badge + ", "
        + "seller=" + seller + ", "
        + "images=" + images
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Product) {
      Product that = (Product) o;
      return (this.id == that.id())
           && (this.title.equals(that.title()))
           && (this.price.equals(that.price()))
           && ((this.badge == null) ? (that.badge() == null) : this.badge.equals(that.badge()))
           && (this.seller.equals(that.seller()))
           && (this.images.equals(that.images()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.title.hashCode();
    h *= 1000003;
    h ^= this.price.hashCode();
    h *= 1000003;
    h ^= (badge == null) ? 0 : this.badge.hashCode();
    h *= 1000003;
    h ^= this.seller.hashCode();
    h *= 1000003;
    h ^= this.images.hashCode();
    return h;
  }

}
