package com.santander.marketprice;

import com.santander.marketprice.model.MarketPrice;

import java.math.BigDecimal;

public class TestStubs {

  public static MarketPrice getSampleMarketPrice() {
    return MarketPrice.builder()
        .instrument("GBP/USD")
        .bid(BigDecimal.valueOf(1.2500))
        .ask(BigDecimal.valueOf(1.2560))
        .build();
  }
}
