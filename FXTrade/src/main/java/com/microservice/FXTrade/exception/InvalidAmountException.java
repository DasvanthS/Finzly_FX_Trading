package com.microservice.FXTrade.exception;

public class InvalidAmountException extends RuntimeException{
	
	public InvalidAmountException(String message) {
		super(message);
	}

}
