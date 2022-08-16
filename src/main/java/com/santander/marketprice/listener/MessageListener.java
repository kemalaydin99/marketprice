package com.santander.marketprice.listener;

import org.springframework.stereotype.Component;

@Component
public interface MessageListener {

  void onMessage(final String message);
}
