
package net.albertogarrido.dawandalite.model;

import com.google.gson.annotations.SerializedName;

 abstract class $AutoValue_Category extends Category {

  private final int id;
  private final String name;
  private final String imgUrl;

  $AutoValue_Category(
      int id,
      String name,
      String imgUrl) {
    this.id = id;
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    if (imgUrl == null) {
      throw new NullPointerException("Null imgUrl");
    }
    this.imgUrl = imgUrl;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public String name() {
    return name;
  }

  @SerializedName(value = "image_url")
  @Override
  public String imgUrl() {
    return imgUrl;
  }

  @Override
  public String toString() {
    return "Category{"
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "imgUrl=" + imgUrl
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Category) {
      Category that = (Category) o;
      return (this.id == that.id())
           && (this.name.equals(that.name()))
           && (this.imgUrl.equals(that.imgUrl()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.id;
    h *= 1000003;
    h ^= this.name.hashCode();
    h *= 1000003;
    h ^= this.imgUrl.hashCode();
    return h;
  }

}
