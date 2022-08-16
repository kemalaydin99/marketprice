package com.santander.marketprice.listener;

import com.santander.marketprice.model.MarketPrice;
import com.santander.marketprice.service.MarketPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MarketPriceListener implements MessageListener {

  private static final BigDecimal COMISSION_RATE = BigDecimal.valueOf(0.01);
  private final MarketPriceParser marketPriceParser;
  private final MarketPriceService marketPriceService;

  @Override
  public void onMessage(String message) {
    List<MarketPrice> priceList = marketPriceParser.parseMarketPrices(message);
    for (MarketPrice marketPrice : priceList) {
      applyMarketPriceComission(marketPrice);
      marketPriceService.publishMarketPrice(marketPrice);
    }
  }

  private void applyMarketPriceComission(MarketPrice marketPrice) {

    marketPrice.setAsk(marketPrice.getAsk().subtract(marketPrice.getAsk().multiply(COMISSION_RATE)));
    marketPrice.setBid(marketPrice.getBid().add(marketPrice.getBid().multiply(COMISSION_RATE)));
  }
}
