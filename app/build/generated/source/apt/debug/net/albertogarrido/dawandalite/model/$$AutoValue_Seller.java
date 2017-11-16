
package net.albertogarrido.dawandalite.model;

 abstract class $$AutoValue_Seller extends Seller {

  private final long id;
  private final String username;
  private final String country;
  private final int rating;

  $$AutoValue_Seller(
      long id,
      String username,
      String country,
      int rating) {
    this.id = id;
    if (username == null) {
      throw new NullPointerException("Null username");
    }
    this.username = username;
    if (country == null) {
      throw new NullPointerException("Null country");
    }
    this.country = country;
    this.rating = rating;
  }

  @Override
  public long id() {
    return id;
  }

  @Override
  public String username() {
    return username;
  }

  @Override
  public String country() {
    return country;
  }

  @Override
  public int rating() {
    return rating;
  }

  @Override
  public String toString() {
    return "Seller{"
        + "id=" + id + ", "
        + "username=" + username + ", "
        + "country=" + country + ", "
        + "rating=" + rating
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Seller) {
      Seller that = (Seller) o;
      return (this.id == that.id())
           && (this.username.equals(that.username()))
           && (this.country.equals(that.country()))
           && (this.rating == that.rating());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= (this.id >>> 32) ^ this.id;
    h *= 1000003;
    h ^= this.username.hashCode();
    h *= 1000003;
    h ^= this.country.hashCode();
    h *= 1000003;
    h ^= this.rating;
    return h;
  }

}
