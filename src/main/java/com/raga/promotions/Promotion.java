package com.raga.promotions;

import com.raga.vo.DiscountDetails;
import com.raga.vo.ProductDetails;

public interface Promotion {

  DiscountDetails calculateDiscount(ProductDetails productDetails);
}
