package com.santander.marketprice.service;

import com.santander.marketprice.exception.InstrumentNotFoundException;
import com.santander.marketprice.model.MarketPrice;
import com.santander.marketprice.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketPriceService {

  private final MarketRepository marketRepository;

  public Optional<MarketPrice> getMarketPrice(String instrumentName)
      throws InstrumentNotFoundException {

    return marketRepository.getMarketPrice(instrumentName);
  }

  public boolean publishMarketPrice(MarketPrice marketPrice) {
    return marketRepository.publishMarketPrice(marketPrice);
  }

  public List<MarketPrice> getAllPriceList() {
    return marketRepository.getAllPriceList();
  }
}
