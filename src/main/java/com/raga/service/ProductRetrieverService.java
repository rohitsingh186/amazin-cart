package com.raga.service;

import com.raga.vo.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductRetrieverService {

  private static final String PRODUCT_DETAILS_OUTPUT_FILE_NAME = "Step 1 - Product Details.json";

  private final RestTemplate restTemplate;
  private final String productDetailsApiUrl;
  private final ResultWriterService resultWriterService;

  @Autowired
  public ProductRetrieverService(RestTemplate restTemplate,
                                 @Value("${product.details.api.url}") String productDetailsApiUrl,
                                 ResultWriterService resultWriterService) {
    this.restTemplate = restTemplate;
    this.productDetailsApiUrl = productDetailsApiUrl;
    this.resultWriterService = resultWriterService;
  }

  public List<ProductDetails> retrieveProducts() throws IOException {
    ProductDetails[] productDetails = fetchProductDetails();

    writeResultIntoFile(productDetails);

    return Arrays.asList(productDetails);
  }

  private ProductDetails[] fetchProductDetails() {
    return this.restTemplate
      .getForEntity(this.productDetailsApiUrl, ProductDetails[].class)
      .getBody();
  }

  private void writeResultIntoFile(ProductDetails[] productDetails) throws IOException {
    this.resultWriterService.writeResultIntoFile(productDetails, PRODUCT_DETAILS_OUTPUT_FILE_NAME);
  }
}
