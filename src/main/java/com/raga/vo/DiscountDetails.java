package com.raga.vo;

public class DiscountDetails implements Comparable {

  private final Double discountAmount;
  private final String discountTag;

  public DiscountDetails(Double discountAmount, String discountTag) {
    this.discountAmount = discountAmount;
    this.discountTag = discountTag;
  }

  public Double getDiscountAmount() {
    return discountAmount;
  }

  public String getDiscountTag() {
    return discountTag;
  }

  @Override
  public int compareTo(Object o) {
    return ((DiscountDetails) o).getDiscountAmount().compareTo(this.getDiscountAmount());
  }
}
