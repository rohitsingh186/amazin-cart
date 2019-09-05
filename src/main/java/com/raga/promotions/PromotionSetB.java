package com.raga.promotions;

import com.raga.vo.DiscountDetails;
import com.raga.vo.ProductDetails;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Profile(value = "promotionSetB")
public class PromotionSetB implements Promotion {

  private static final double PERCENTAGE_TO_FRACTION_MULTIPLIER = 0.01;
  private static final int FIRST_ELEMENT = 0;

  @Override
  public DiscountDetails calculateDiscount(ProductDetails productDetails) {
    List<DiscountDetails> discountsAvailable = new ArrayList<>();

    // TODO: Need to refactor this class to make it cleaner, readable

    if (productDetails.getInventory() > 20) {
      Double discountAmount = 0.12 * productDetails.getPrice();
      String discountTag = "get 12% off";
      DiscountDetails discountDetails = buildDiscountDetails(discountAmount, discountTag);
      discountsAvailable.add(discountDetails);
    }

    if ("NEW".equals(productDetails.getArrival())) {
      Double discountAmount = 0.07 * productDetails.getPrice();
      String discountTag = "get 7% off";
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
