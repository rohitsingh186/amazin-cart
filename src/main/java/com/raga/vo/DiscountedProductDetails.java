package com.raga.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(
  fieldVisibility = Visibility.NONE,
  getterVisibility = Visibility.ANY
)
public class DiscountedProductDetails {

  private final ProductDetails productDetails;
  private final DiscountDetails discount;

  public DiscountedProductDetails(ProductDetails productDetails, DiscountDetails discountDetails) {
    this.productDetails = productDetails;
    this.discount = discountDetails;
  }

  public String getCategory() {
    return this.productDetails.getCategory();
  }

  public Integer getInventory() {
    return this.productDetails.getInventory();
  }

  public String getArrival() {
    return this.productDetails.getArrival();
  }

  public Double getRating() {
    return this.productDetails.getRating();
  }

  public String getCurrency() {
    return this.productDetails.getCurrency();
  }

  public Double getPrice() {
    return this.productDetails.getPrice();
  }

  public String getOrigin() {
    return this.productDetails.getOrigin();
  }

  public String getProduct() {
    return this.productDetails.getProduct();
  }

  public DiscountDetails getDiscount() {
    return discount;
  }
}
