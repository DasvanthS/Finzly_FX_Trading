package com.fxtrade.dasvanth;

public class Trade {
	
	private static int tradeCount = 0;
	private int tradeID;
	private String customerName;
	private String currencyPair;
	private double rate;
	private double amount;
	private boolean tradeConfirmed = false;
	
	public Trade(String customerName, String currencyPair, double rate, double amount) {
		tradeCount++;
		this.tradeID = tradeCount;
		this.customerName = customerName;
		this.currencyPair = currencyPair;
		this.rate = rate;
		this.amount = amount;
		this.tradeConfirmed = true;
	}
	
	public boolean getTradeConfirmed(){
		return this.tradeConfirmed;
	}
	
	public String getAmount() {
		return FXTrade.indianFormatMethod(this.amount);
	}
	
	public double getRate() {
		return this.rate;
	}
	
	public String getCustomerName(){
		return this.customerName;
	}

	@Override
	public String toString() {
        return String.format("%-8d %-12s %-12s INR %-20s %-10.2f", this.tradeID, this.currencyPair, this.customerName, getAmount(), this.rate);

	}
	
	
	
	

}
