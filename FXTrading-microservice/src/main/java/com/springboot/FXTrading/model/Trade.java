package com.springboot.FXTrading.model;

public class Trade {
	
	private int tradeNo;
	private String customerName;
	private String currencyPair;
	private double rate;
	private double amount;
	private boolean tradeConfirmed = false;
	
	public Trade() {
		
	}
	
	public Trade(String customerName, String currencyPair, double amount) {
		this.customerName = customerName;
		this.currencyPair = currencyPair;
		this.amount = amount;
		this.tradeConfirmed = true;
	}
	
	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
	}

	public int getTradeNo() {
		return tradeNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isTradeConfirmed() {
		return tradeConfirmed;
	}

	public void setTradeConfirmed(boolean tradeConfirmed) {
		this.tradeConfirmed = tradeConfirmed;
	}
	
	

}
