package com.microservice.FXTrade.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.FXTrade.dao.CurrencyPairRateDAO;
import com.microservice.FXTrade.dao.TradeDAO;
import com.microservice.FXTrade.exception.InvalidAmountException;
import com.microservice.FXTrade.exception.InvalidCurrencyPairException;
import com.microservice.FXTrade.exception.InvalidNameException;
import com.microservice.FXTrade.model.CurrencyPairRate;
import com.microservice.FXTrade.model.Trade;

@Service
public class TradeService {

	private TradeDAO tradeDAO;
	
    private CurrencyPairRateDAO currencyPairRateDAO;
    
    private static final Logger logger = LoggerFactory.getLogger(TradeService.class);
    
    @Autowired
    public TradeService(TradeDAO tradeDAO, CurrencyPairRateDAO currencyPairRateDAO) {
    	this.tradeDAO = tradeDAO;
    	this.currencyPairRateDAO = currencyPairRateDAO;
    }
	    
    public String bookTrade(Trade trade) {	
    		
		if((!isValidString(trade.getCustomerName().trim()))){
			throw new InvalidNameException("Enter a valid name.");
		}
		
		if(trade.getAmount() < 1) {
	        throw new InvalidAmountException("Enter a valid amount. Amount cannot be zero or negative.");
		}
		
		if(currencyPairRateDAO.findByCurrencyPair(trade.getCurrencyPair().toUpperCase()).isEmpty()) {
	    	throw new InvalidCurrencyPairException("Enter a valid currency pair. The entered CurrencyPair is not listed");
		}
		
		CurrencyPairRate currency = currencyPairRateDAO.findByCurrencyPair(trade.getCurrencyPair()).get();
		
		trade.setCustomerName(trade.getCustomerName().trim());
		trade.setCurrencyPair(currency.getCurrencyPair());
		trade.setRate(currency.getRate());
		trade.setConvertedAmount(trade.getAmount() * currency.getRate());
		trade.setTradeConfirmed(true);
		tradeDAO.saveTrade(trade);
		
		logger.info("Trade booked");
		
		return "Trade for " + trade.getCurrencyPair() + " has been booked with rate " + trade.getRate() +
                ", The amount of " + trade.getConvertedAmount() + " will be transferred in 2 working days to " +
                trade.getCustomerName() + ".";
			
	}
    
    public List<Trade> getAllTrades() {
        return tradeDAO.getAll();
    }
    
    public boolean isValidString(String input) {
		return input!=null && !input.trim().isEmpty() && input.matches("^[a-zA-Z]+[a-zA-Z ]+[.]*$");
	}
}
