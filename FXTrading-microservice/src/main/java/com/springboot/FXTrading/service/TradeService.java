package com.springboot.FXTrading.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.FXTrading.model.Trade;

@Service
public class TradeService {
	
	
	private static int tradeCount = 1;
	public static final DecimalFormat indianFormat = new DecimalFormat("#,###,###.00");
	private static List<Trade> trades = new ArrayList<>();
	private static Map<String, Double> CURRENCY_RATE = new HashMap<>(); 
	static {
		CURRENCY_RATE.put("USDINR", 66.00);
    }

	public boolean bookTrade(Trade trade) {
		if((!isValidString(trade.getCustomerName())) || !trade.getCurrencyPair().equalsIgnoreCase("USDINR") || trade.getAmount()<1) {
			return false;
		}
		else {
			trade.setTradeNo(tradeCount);
			trade.setRate(CURRENCY_RATE.get("USDINR"));
			trade.setAmount(trade.getAmount()*trade.getRate());
			trade.setTradeConfirmed(true);
			trades.add(trade);
			tradeCount++; 
			return true;
		}
			
	}
		
	public List<Trade> getAllTrade(){
		return trades;
	}
	
	public boolean isValidString(String input) {
		if(input.equals("") || input.equals(null) || input.equals(".")) {
			return false;
		}
	    String regex = "^[a-zA-Z]+[a-zA-Z ]+[.]*$";
        return input.matches(regex);
	}

	
}
