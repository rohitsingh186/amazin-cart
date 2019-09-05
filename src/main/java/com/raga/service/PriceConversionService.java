package com.raga.service;

import com.raga.vo.PriceConversionRates;
import com.raga.vo.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceConversionService {

  private static final String INR = "INR";
  private static final String PRODUCT_DETAILS_WITH_PRICE_IN_INR_OUTPUT_FILE_NAME =
    "Step 2 - Product Details With INR.json";

  private final RestTemplate restTemplate;
  private final String conversionRatesApiUrl;
  private final ResultWriterService resultWriterService;

  @Autowired
  public PriceConversionService(RestTemplate restTemplate,
                                @Value("${conversion.rates.api.url}") String conversionRatesApiUrl,
                                ResultWriterService resultWriterService) {
    this.restTemplate = restTemplate;
    this.conversionRatesApiUrl = conversionRatesApiUrl;
    this.resultWriterService = resultWriterService;
  }

  public List<ProductDetails> convertPriceToIndianRupee(List<ProductDetails> productDetails) throws IOException {
    PriceConversionRates priceConversionRates = fetchPriceConversionRates();

    List<ProductDetails> productDetailsWithPriceInInr =
      convertProductPricesToInr(productDetails, priceConversionRates);

    writeResultIntoFile(productDetailsWithPriceInInr);

    return productDetailsWithPriceInInr;
  }

  private PriceConversionRates fetchPriceConversionRates() {
    return this.restTemplate
      .getForEntity(this.conversionRatesApiUrl, PriceConversionRates.class)
      .getBody();
  }

  private List<ProductDetails> convertProductPricesToInr(List<ProductDetails> productDetails,
                                                         PriceConversionRates priceConversionRates) {
    return productDetails
      .stream()
      .map(item -> {
        if (INR.equals(item.getCurrency())) {
          return item;
        } else {
          Double priceInInr = convertPriceToInr(item, priceConversionRates);
          return new ProductDetails(item, priceInInr);
        }
      })
      .collect(Collectors.toList());
  }

  private Double convertPriceToInr(ProductDetails item, PriceConversionRates priceConversionRates) {
    String itemCurrency = item.getCurrency();
    Double itemCurrencyConversionRateFromBase = priceConversionRates.getRates().get(itemCurrency);
    Double inrConversionRateFromBase = priceConversionRates.getRates().get(INR);
    Double priceInOtherCurrency = item.getPrice();

    return priceInOtherCurrency * (inrConversionRateFromBase / itemCurrencyConversionRateFromBase);
  }

  private void writeResultIntoFile(List<ProductDetails> productDetailsWithConvertedPrice) throws IOException {
    this.resultWriterService.writeResultIntoFile(
      productDetailsWithConvertedPrice, PRODUCT_DETAILS_WITH_PRICE_IN_INR_OUTPUT_FILE_NAME);
  }
}
