package com.raga.promotions;

import com.raga.vo.DiscountDetails;
import com.raga.vo.ProductDetails;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Component
@Profile(value = "promotionSetA")
public class PromotionSetA implements Promotion {

  private static final double PERCENTAGE_TO_FRACTION_MULTIPLIER = 0.01;
  private static final int FIRST_ELEMENT = 0;

  @Override
  public DiscountDetails calculateDiscount(ProductDetails productDetails) {
    List<DiscountDetails> discountsAvailable = new ArrayList<>();

    // TODO: Need to refactor this class to make it cleaner, readable

    if ("Africa".equals(productDetails.getOrigin())) {
      Double discountAmount = 0.07 * productDetails.getPrice();
      String discountTag = "get 7% off";
      DiscountDetails discountDetails = buildDiscountDetails(discountAmount, discountTag);
      discountsAvailable.add(discountDetails);
    }

    if (productDetails.getRating() <= 2) {
      int discountPercentage = (productDetails.getRating() == 2) ? 4 : 8;
      Double discountAmount = PERCENTAGE_TO_FRACTION_MULTIPLIER * discountPercentage * productDetails.getPrice();
      String discountTag = "get " + discountPercentage + "% off";
      DiscountDetails discountDetails = buildDiscountDetails(discountAmount, discountTag);
      discountsAvailable.add(discountDetails);
    }

    if (newArrayList("electronics", "electronics").contains(productDetails.getCategory())
      && (productDetails.getPrice() >= 500)) {

      Double discountAmount = 100.0;
      String discountTag = "get RS 100 off";
      DiscountDetails discountDetails = buildDiscountDetails(discountAmount, discountTag);
      discountsAvailable.add(discountDetails);
    }

    if (discountsAvailable.isEmpty() && (productDetails.getPrice() > 1000)) {
      Double discountAmount = 0.02 * productDetails.getPrice();
      String discountTag = "get 2% off";
      DiscountDetails discountDetails = buildDiscountDetails(discountAmount, discountTag);
      discountsAvailable.add(discountDetails);
    }

    if (discountsAvailable.isEmpty()) {
      return buildDiscountDetails(0.0, "No discount available");
    } else {
      Collections.sort(discountsAvailable);
      return discountsAvailable.get(FIRST_ELEMENT);
    }
  }

  private DiscountDetails buildDiscountDetails(Double discountAmount, String discountTag) {
    return new DiscountDetails(discountAmount, discountTag);
  }
}
