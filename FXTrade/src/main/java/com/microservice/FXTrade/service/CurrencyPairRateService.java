package com.microservice.FXTrade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.FXTrade.dao.CurrencyPairRateDAO;
import com.microservice.FXTrade.exception.CurrencyPairAlreadyFoundException;
import com.microservice.FXTrade.exception.CurrencyPairNotFoundException;
import com.microservice.FXTrade.model.CurrencyPairRate;

@Service
public class CurrencyPairRateService {
	
    @Autowired
	private CurrencyPairRateDAO currencyPairRateDAO;

    public boolean saveCurrencyPairRate(CurrencyPairRate currencyPairRate) {	
		Optional<CurrencyPairRate> existingCurrencyPairRate = currencyPairRateDAO.findByCurrencyPair(currencyPairRate.getCurrencyPair());
		if(existingCurrencyPairRate.isEmpty()) {
	        currencyPairRateDAO.save(currencyPairRate);
	        return true;
		}
		throw new CurrencyPairAlreadyFoundException("Currency Pair is already found!");
    }

    public boolean updateCurrencyPairRateRate(String currencyPair, Double rate) {
    	Optional<CurrencyPairRate> existingCurrencyPairRate = currencyPairRateDAO.findByCurrencyPair(currencyPair);
    	if(existingCurrencyPairRate.isPresent()) {
            currencyPairRateDAO.updateRate(currencyPair, rate);
            return true;
    	}    
		throw new CurrencyPairNotFoundException("Currency Pair is not found!");

    }

    public boolean deleteCurrencyPairRate(String currencyPair) {
    	Optional<CurrencyPairRate> existingCurrencyPairRate = currencyPairRateDAO.findByCurrencyPair(currencyPair);
    	if(existingCurrencyPairRate.isPresent()) {
    		CurrencyPairRate currencyPairRate = existingCurrencyPairRate.get();
            currencyPairRateDAO.deleteCurrencyPair(currencyPairRate);
            return true;
    	}
		throw new CurrencyPairNotFoundException("Currency Pair is not found!");
    }

    public List<CurrencyPairRate> getAllCurrencyPairRates() {
        return currencyPairRateDAO.getAll();
    }
	
    public Optional<CurrencyPairRate> findByCurrencyPair(String currencyPair) {
        return currencyPairRateDAO.findByCurrencyPair(currencyPair);
    } 

}
