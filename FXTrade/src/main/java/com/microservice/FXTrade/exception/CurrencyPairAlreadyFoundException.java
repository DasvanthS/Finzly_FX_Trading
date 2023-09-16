package com.microservice.FXTrade.exception;

public class CurrencyPairAlreadyFoundException extends RuntimeException {

	public CurrencyPairAlreadyFoundException(String message) {
		super(message);
	}
}
