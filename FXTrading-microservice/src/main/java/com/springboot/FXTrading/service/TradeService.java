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

	public String bookTrade(Trade trade) {
		if((!isValidString(trade.getCustomerName())) || !trade.getCurrencyPair().equalsIgnoreCase("USDINR") || trade.getAmount() < 1) {
			return "Enter valid inputs";
		}
		else {
			trade.setTradeNo(tradeCount++);
			trade.setRate(CURRENCY_RATE.get("USDINR"));
			trade.setAmount(trade.getAmount() * trade.getRate());
			trade.setTradeConfirmed(true);
			trades.add(trade);
			return "Trade for USDINR has been booked with rate " + trade.getRate() +
                    ", The amount of Rs " + indianFormat.format(trade.getAmount()) + " will be transferred in 2 working days to " +
                    trade.getCustomerName() + ".";
		}
			
	}
		
	public List<Trade> getAllTrade(){
		return new ArrayList<>(trades);
	}
	
	public boolean isValidString(String input) {
		return input!=null && !input.trim().isEmpty() && input.matches("^[a-zA-Z]+[a-zA-Z ]+[.]*$");
	}

	
}
