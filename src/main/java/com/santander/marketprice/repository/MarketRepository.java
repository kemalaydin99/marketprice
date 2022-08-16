package com.santander.marketprice.repository;

import com.santander.marketprice.exception.InstrumentNotFoundException;
import com.santander.marketprice.model.MarketPrice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MarketRepository {
  private static final Map<String, MarketPrice> priceMap = new ConcurrentHashMap<>();

  public Optional<MarketPrice> getMarketPrice(String instrument)
      throws InstrumentNotFoundException {
    if (isNotBlank(instrument)) {
      MarketPrice price = priceMap.get(instrument);
      if (price != null) return Optional.ofNullable(price);
    }

    throw new InstrumentNotFoundException(instrument);
  }

  public boolean publishMarketPrice(MarketPrice marketPrice) {
    if (marketPrice != null && isNotBlank(marketPrice.getInstrument())) {
      MarketPrice oldPrice = priceMap.get(marketPrice.getInstrument());
      if (oldPrice == null || oldPrice.getTimestamp().before(marketPrice.getTimestamp())) {
        priceMap.put(marketPrice.getInstrument(), marketPrice);
        return true;
      }
    }

    return false;
  }

  private boolean isNotBlank(String text) {
    return text != null && !text.isBlank();
  }

  public List<MarketPrice> getAllPriceList() {
    return priceMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
  }
}
