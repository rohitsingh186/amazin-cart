package com.raga.service;

import com.raga.promotions.Promotion;
import com.raga.vo.DiscountedProductDetails;
import com.raga.vo.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountingService {

  private static final String DISCOUNTED_PRODUCT_DETAILS_OUTPUT_FILE_NAME =
    "Step 3 - Product Details with discounts.json";

  private final ResultWriterService resultWriterService;
  private final Promotion promotion;

  @Autowired
  public DiscountingService(ResultWriterService resultWriterService,
                            Promotion promotion) {
    this.resultWriterService = resultWriterService;
    this.promotion = promotion;
  }

  public void calculateDiscountForProducts(List<ProductDetails> productDetails)
    throws IOException {

    List<DiscountedProductDetails> productsWithDiscounts = calculateDiscounts(productDetails);

    writeResultIntoFile(productsWithDiscounts);
  }

  private List<DiscountedProductDetails> calculateDiscounts(List<ProductDetails> productDetails) {

    return productDetails.stream()
      .map(item -> new DiscountedProductDetails(item, promotion.calculateDiscount(item)))
      .collect(Collectors.toList());
  }

  private void writeResultIntoFile(List<DiscountedProductDetails> discountedProductDetailsList) throws IOException {

    this.resultWriterService.writeResultIntoFile(
      discountedProductDetailsList, DISCOUNTED_PRODUCT_DETAILS_OUTPUT_FILE_NAME);
  }
}
