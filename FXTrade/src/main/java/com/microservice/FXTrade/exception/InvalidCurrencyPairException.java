package com.microservice.FXTrade.exception;

public class InvalidCurrencyPairException extends RuntimeException{
	
	public InvalidCurrencyPairException(String message) {
		super(message);
	}

}