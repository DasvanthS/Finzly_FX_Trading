package com.microservice.FXTrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.FXTrade.model.Trade;
import com.microservice.FXTrade.service.TradeService;

@RestController
@RequestMapping("api/trade")
public class TradeController {
	
	@Autowired
	private TradeService tradeService;
	
	@PostMapping("bookTrade") // Book a trade
	public String bookTrade(@RequestBody Trade trade) {
		return tradeService.bookTrade(trade);
	}
	
	@GetMapping("listAllTrades") // List all trades
	public ResponseEntity<Object> getAllTrades(){
		List<Trade> trades = tradeService.getAllTrades();
		if(trades.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No trades found");
		}else {
			return ResponseEntity.status(HttpStatus.FOUND).body(trades);
		}		
	}

}
