package com.santander.marketprice.listener;

import com.santander.marketprice.model.MarketPrice;

import java.util.List;

public interface MarketPriceParser {

  List<MarketPrice> parseMarketPrices(String message);
}
