package com.springboot.FXTrading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.FXTrading.model.Trade;
import com.springboot.FXTrading.service.TradeService;

@RestController
@RequestMapping("trade")
public class TradeController {
	
	@Autowired
	TradeService service;
		
	@PostMapping("book")
	public String bookTrade(@RequestBody Trade trade) {
		
		boolean isAdded = service.bookTrade(trade);
		if(isAdded) {
			return "Trade for USDINR has been booked with rate " + trade.getRate() +
                    ", The amount of Rs " + TradeService.indianFormat.format(trade.getAmount()) + " will be transferred in 2 working days to " +
                    trade.getCustomerName() + ".";
		}else {
			return "Enter valid inputs";
		}
	}
	

	@GetMapping("list")
	public ResponseEntity<Object> getAllTrades(){
		
		List<Trade> trades = service.getAllTrade();
		if(trades.isEmpty()) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Object>(trades, HttpStatus.FOUND);
		}		
	}
	
	@GetMapping("exit")
	public void exit(){
		System.exit(0);
	}

}
