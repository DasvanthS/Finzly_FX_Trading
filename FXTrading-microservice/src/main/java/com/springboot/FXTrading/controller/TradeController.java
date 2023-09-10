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
@RequestMapping("api/trade")
public class TradeController {
	
	@Autowired
	TradeService service;
		
	@PostMapping("bookTrade")
	public String bookTrade(@RequestBody Trade trade) {
		return service.bookTrade(trade);
	}

	@GetMapping("listAllTrades")
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
