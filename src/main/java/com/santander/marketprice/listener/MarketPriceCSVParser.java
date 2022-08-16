package com.santander.marketprice.listener;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.santander.marketprice.model.MarketPrice;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MarketPriceCSVParser implements MarketPriceParser {

  @Override
  public List<MarketPrice> parseMarketPrices(String message) {

    CsvMapper csvMapper = new CsvMapper();

    CsvSchema csvSchema =
        csvMapper.typedSchemaFor(MarketPrice.class).withColumnSeparator(',').withComments();
    /*
          CsvSchema xschema = CsvSchema.builder()
                  .addColumn("id")
                  .addColumn("instrument")
                  .addColumn("instrument")
                  .addColumn("bid")
                  .addColumn("ask")
                  .addColumn("timestamp")
                  .build();
    */
    List<MarketPrice> priceList = null;
    try {
      MappingIterator<MarketPrice> it =
          csvMapper.readerFor(MarketPrice.class).with(csvSchema).readValues(message);
      priceList = it.readAll();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return priceList;
  }
}
