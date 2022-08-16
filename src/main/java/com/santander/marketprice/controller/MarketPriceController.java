package com.santander.marketprice.controller;

import com.santander.marketprice.exception.InstrumentNotFoundException;
import com.santander.marketprice.listener.MarketPriceListener;
import com.santander.marketprice.model.MarketPrice;
import com.santander.marketprice.service.MarketPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/marketprice/")
public class MarketPriceController {

  private final MarketPriceService marketPriceService;
  private final MarketPriceListener messageListener;

  @Operation(summary = "Get Instrument latest price by Instrument Id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Instrument found",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = MarketPrice.class))
            }),
        @ApiResponse(responseCode = "404", description = "Instrument not found", content = @Content)
      })
  @GetMapping("instruments/{instrumentName}")
  public MarketPrice getMarketPrice(@PathVariable String instrumentName)
      throws InstrumentNotFoundException {

    return marketPriceService
        .getMarketPrice(instrumentName)
        .orElseThrow(() -> new InstrumentNotFoundException(instrumentName));
  }

  @Operation(summary = "Process Message and update latest Instrument prices")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Market Prices Processed ",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = MarketPrice.class))
            }),
        @ApiResponse(responseCode = "404", description = "Empty Message", content = @Content)
      })
  @PostMapping("/message/{message}")
  public List<MarketPrice> processMarketPriceMessage(@PathVariable String message) {
    messageListener.onMessage(message);
    return marketPriceService.getAllPriceList();
  }
}
