package com.santander.marketprice.service;

import com.santander.marketprice.TestStubs;
import com.santander.marketprice.exception.InstrumentNotFoundException;
import com.santander.marketprice.model.MarketPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketPriceServiceTest {

  @Mock MarketPriceService marketPriceService;

  @BeforeAll
  static void setUp() {}

  @Test
  void getMarketPriceTest() throws InstrumentNotFoundException {
    MarketPrice expected = TestStubs.getSampleMarketPrice();
    when(marketPriceService.getMarketPrice(anyString())).thenReturn(Optional.of(expected));
    Optional<MarketPrice> actual = marketPriceService.getMarketPrice("GBP/USD");
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(expected, actual.get());
  }

  @Test
  void publishMarketPriceTest() throws InstrumentNotFoundException {
    MarketPrice expected = TestStubs.getSampleMarketPrice();
    when(marketPriceService.publishMarketPrice(any(MarketPrice.class))).thenReturn(true);
    Assertions.assertTrue(marketPriceService.publishMarketPrice(expected));

    when(marketPriceService.getMarketPrice(anyString())).thenReturn(Optional.of(expected));
    Optional<MarketPrice> actual = marketPriceService.getMarketPrice("GBP/USD");
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(expected, actual.get());
  }

  @Test
  void getLatestMarketPriceTest() throws InstrumentNotFoundException {
    MarketPrice expected = TestStubs.getSampleMarketPrice();
    expected.setAsk(BigDecimal.valueOf(2.0));
    expected.setTimestamp(new Date());
    when(marketPriceService.publishMarketPrice(any(MarketPrice.class))).thenReturn(true);
    Assertions.assertTrue(marketPriceService.publishMarketPrice(expected));

    when(marketPriceService.getMarketPrice(anyString())).thenReturn(Optional.of(expected));
    Optional<MarketPrice> actual = marketPriceService.getMarketPrice("GBP/USD");
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(BigDecimal.valueOf(2.0), actual.get().getAsk());
  }
}
