
package net.albertogarrido.dawandalite.model;

 abstract class $$AutoValue_Price extends Price {

  private final String currency;
  private final long cents;

  $$AutoValue_Price(
      String currency,
      long cents) {
    if (currency == null) {
      throw new NullPointerException("Null currency");
    }
    this.currency = currency;
    this.cents = cents;
  }

  @Override
  public String currency() {
    return currency;
  }

  @Override
  public long cents() {
    return cents;
  }

  @Override
  public String toString() {
    return "Price{"
        + "currency=" + currency + ", "
        + "cents=" + cents
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Price) {
      Price that = (Price) o;
      return (this.currency.equals(that.currency()))
           && (this.cents == that.cents());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.currency.hashCode();
    h *= 1000003;
    h ^= (this.cents >>> 32) ^ this.cents;
    return h;
  }

}
