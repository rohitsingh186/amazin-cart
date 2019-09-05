package com.raga.vo;

import java.util.Date;
import java.util.Map;

public class PriceConversionRates {

  private final Map<String, Double> rates;
  private final String base;
  private final Date date;

  @SuppressWarnings("unused")
  private PriceConversionRates() {
    this(null, null, null);
  }

  public PriceConversionRates(Map<String, Double> rates, String base, Date date) {
    this.rates = rates;
    this.base = base;
    this.date = date;
  }

  public Map<String, Double> getRates() {
    return rates;
  }

  public String getBase() {
    return base;
  }

  public Date getDate() {
    return date;
  }
}
