package com.raga.vo;

public class ProductDetails {

  private static final String INR = "INR";

  private final String category;
  private final Integer inventory;
  private final String arrival;
  private final Double rating;
  private final String currency;
  private final Double price;
  private final String origin;
  private final String product;

  @SuppressWarnings("unused")
  private ProductDetails() {
    this(null, null, null, null, null, null, null, null);
  }

  public ProductDetails(String category, Integer inventory, String arrival, Double rating,
                        String currency, Double price, String origin, String product) {
    this.category = category;
    this.inventory = inventory;
    this.arrival = arrival;
    this.rating = rating;
    this.currency = currency;
    this.price = price;
    this.origin = origin;
    this.product = product;
  }

  public ProductDetails(ProductDetails productDetails, Double priceInInr) {
    this.category = productDetails.getCategory();
    this.inventory = productDetails.getInventory();
    this.arrival = productDetails.getArrival();
    this.rating = productDetails.getRating();
    this.currency = INR;
    this.price = priceInInr;
    this.origin = productDetails.getOrigin();
    this.product = productDetails.getProduct();
  }

  public String getCategory() {
    return category;
  }

  public Integer getInventory() {
    return inventory;
  }

  public String getArrival() {
    return arrival;
  }

  public Double getRating() {
    return rating;
  }

  public String getCurrency() {
    return currency;
  }

  public Double getPrice() {
    return price;
  }

  public String getOrigin() {
    return origin;
  }

  public String getProduct() {
    return product;
  }
}
