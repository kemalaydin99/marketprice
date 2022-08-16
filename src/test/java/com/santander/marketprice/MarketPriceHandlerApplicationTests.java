package com.santander.marketprice;

import com.santander.marketprice.controller.MarketPriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MarketPriceHandlerApplicationTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private MarketPriceController marketPriceController;

  @Test
  void contextLoads() {}

  @Test
  public void testControllerGetMarketPrice_GBP_USD() throws Exception {
    mockMvc
        .perform(get("/marketprice/instruments/GBP%2FUSD").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
