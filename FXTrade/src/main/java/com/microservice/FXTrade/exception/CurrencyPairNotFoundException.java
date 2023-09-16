package com.microservice.FXTrade.exception;

public class CurrencyPairNotFoundException extends RuntimeException{
	
	public CurrencyPairNotFoundException(String message) {
		super(message);
	}

}
