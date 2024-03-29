package com.raga;

import com.raga.service.DiscountingService;
import com.raga.service.PriceConversionService;
import com.raga.service.ProductRetrieverService;
import com.raga.vo.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final ProductRetrieverService productRetrieverService;
  private final PriceConversionService priceConversionService;
  private final DiscountingService discountingService;

  @Autowired
  public Application(ProductRetrieverService productRetrieverService,
                     PriceConversionService priceConversionService,
                     DiscountingService discountingService) {

    this.productRetrieverService = productRetrieverService;
    this.priceConversionService = priceConversionService;
    this.discountingService = discountingService;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("************** Hey ! See I started running ! **************");

    List<ProductDetails> productDetails = this.productRetrieverService
      .retrieveProducts();

    List<ProductDetails> productDetailsWithPriceInInr = this.priceConversionService
      .convertPriceToIndianRupee(productDetails);

    this.discountingService
      .calculateDiscountForProducts(productDetailsWithPriceInInr);

    System.out.println("************** Hey ! See I finished ! Yeay ! **************");
  }
}
