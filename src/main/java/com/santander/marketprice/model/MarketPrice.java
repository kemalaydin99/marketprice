package com.santander.marketprice.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@JsonPropertyOrder({"id", "instrument", "bid", "ask", "timestamp"})
public class MarketPrice {
  private Long id;
  private String instrument;
  private BigDecimal bid;
  private BigDecimal ask;
  private Date timestamp;
}
