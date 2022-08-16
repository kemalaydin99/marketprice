package com.santander.marketprice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Instrument not found.") // 404
public class InstrumentNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public InstrumentNotFoundException(String instrumentName) {
    super("No instrument found with name: " + instrumentName);
  }
}
