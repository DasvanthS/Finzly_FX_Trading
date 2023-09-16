package com.microservice.FXTrade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tradeNo;
	private String customerName;
	private String currencyPair;
	private Double rate;
	private Double amount;
	private Double convertedAmount;
	private boolean tradeConfirmed = false;
	
	public Trade() {
		
	}
	
	public void setTradeNo(Long tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getTradeNo() {
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

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public double getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(Double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	public boolean isTradeConfirmed() {
		return tradeConfirmed;
	}

	public void setTradeConfirmed(boolean tradeConfirmed) {
		this.tradeConfirmed = tradeConfirmed;
	}
	
}
