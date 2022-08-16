package com.santander.marketprice.repository;

import com.santander.marketprice.TestStubs;
import com.santander.marketprice.exception.InstrumentNotFoundException;
import com.santander.marketprice.model.MarketPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MarketRepositoryTest {

  @InjectMocks private MarketRepository marketRepository;

  @Test
  void getMarketPriceShouldThrowException() throws InstrumentNotFoundException {
    MarketPrice expected = TestStubs.getSampleMarketPrice();
    InstrumentNotFoundException thrown =
        Assertions.assertThrows(
            InstrumentNotFoundException.class,
            () -> marketRepository.getMarketPrice("DUMMY/VALUE"),
            "Expected getMarketPrice() to throw, but it didn't");

    Assertions.assertTrue(thrown.getMessage().contains("DUMMY/VALUE"));
  }

  @Test
  void publishMarketPrice() throws InstrumentNotFoundException {
    MarketPrice expected = TestStubs.getSampleMarketPrice();
    expected.setAsk(BigDecimal.valueOf(2.0));
    expected.setTimestamp(new Date());
    Assertions.assertTrue(marketRepository.publishMarketPrice(expected));
    Optional<MarketPrice> actual = marketRepository.getMarketPrice("GBP/USD");
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(BigDecimal.valueOf(2.0), actual.get().getAsk());
  }
}
