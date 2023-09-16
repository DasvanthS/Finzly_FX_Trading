package com.microservice.FXTrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.FXTrade.model.CurrencyPairRate;
import com.microservice.FXTrade.service.CurrencyPairRateService;

@RestController
@RequestMapping("api/currencyPairRates")
public class CurrencyPairRateController {
	
	@Autowired
	private CurrencyPairRateService currencyPairRateService;

    @PostMapping("AddCurrencyPairRate") //Add new CurrencyPair
    public ResponseEntity<String> saveCurrencyPairRate(@RequestBody CurrencyPairRate currencyPairRate) {
        if(currencyPairRateService.saveCurrencyPairRate(currencyPairRate)) {
	        return ResponseEntity.status(HttpStatus.CREATED).body("Currency pair rate saved successfully.");

        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Currency pair already found.");
    }

    @PutMapping("updateCurrencyPair/{currencyPair}") //Update existing CurrencyPair
    public ResponseEntity<String> updateCurrencyPairRateRate(@PathVariable String currencyPair, @RequestParam Double rate) {
        if(currencyPairRateService.updateCurrencyPairRateRate(currencyPair, rate)) {
	        return ResponseEntity.status(HttpStatus.OK).body("Currency pair rate updated successfully.");

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currency pair not found.");
    }

    @DeleteMapping("deleteCurrencyPair/{currencyPair}") //Delete existing CurrencyPair
    public ResponseEntity<String> deleteCurrencyPairRate(@PathVariable String currencyPair) {
        if(currencyPairRateService.deleteCurrencyPairRate(currencyPair)) {
	        return ResponseEntity.status(HttpStatus.OK).body("Currency pair rate deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currency pair not found.");
    }
    

    @GetMapping("getAllCurrencyPair") //List all CurrencyPair
    public ResponseEntity<List<CurrencyPairRate>> getAllCurrencyPairRates() {
        List<CurrencyPairRate> currencyPairRates = currencyPairRateService.getAllCurrencyPairRates();
        return ResponseEntity.ok(currencyPairRates);
    }

}
