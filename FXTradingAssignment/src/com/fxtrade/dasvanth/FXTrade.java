package com.fxtrade.dasvanth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FXTrade{
	
	private static Map<String, Double> CURRENCY_RATE = new HashMap<>(); 
	static {
		CURRENCY_RATE.put("USDINR", 66.00);
    	}
	public static double RATE = CURRENCY_RATE.get("USDINR");
	private static String CURRENCY = "USDINR";
	private static List<Trade> TRADES = new ArrayList<>();
	
	public void printTrades() {
		System.out.printf("%-8s %-12s %-12s %-23s %-10s%n", "TradeNO", "CurrencyPair", "CustomerName", "Amount", "Rate");
		Iterator<Trade> itr = TRADES.listIterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println();
		
	}
	
	public boolean exit(Scanner scan) {
		System.out.println("Do you really want to exit? (y/n)");
        char exitInput = scan.next().charAt(0);
        if(exitInput == 'y' || exitInput == 'Y'){
            System.out.println("Bye! Have a good day.");
            return true;
        }
        else if(exitInput == 'n' || exitInput == 'N') {
        	System.out.println();
            return false;
        }
        System.out.println("Invalid Input");
        System.out.println();
        return false;
	}
	
	public static String indianFormatMethod(double amount) {
		DecimalFormat indianFormat = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("en", "IN"));
        indianFormat.applyPattern("#,##,##,##0.00");
        String formattedAmount = indianFormat.format(amount);
        return formattedAmount;
	}
	
	public boolean isValidString(String input) {
		if(input.equals("") || input.equals(null) || input.equals(".")) {
			return false;
		}
	    String regex = "^[a-zA-Z]+[a-zA-Z ]+[.]*$";
        return input.matches(regex);
	}
	
	public void bookTrade(Scanner scan) {
	    String customerName;
	    String currencyPair;
	    double amount;
	    String getRateInput;
	    String bookCancelInput;

	    // Customer Name
	    do {
	        System.out.println("Enter Customer Name");
	        customerName = scan.next();
	        scan.nextLine();

	        if (!isValidString(customerName)) {
	            System.out.println("Invalid Input. Name must be a String.");
	        }
	    } while (!isValidString(customerName));

	    // Currency Pair
	    do {
	        System.out.println("Enter Currency Pair");
	        currencyPair = scan.nextLine().toUpperCase();

	        if (!currencyPair.equals(CURRENCY)) {
	            System.out.println("Invalid Currency Pair. Only USDINR will be accepted");
	        }
	    } while (!currencyPair.equals(CURRENCY));

	    // Amount
	    amount = getValidAmount(scan);

	    // Rate 
	    do {
	        System.out.print("Do you want to get Rate? Yes/No ");
	        getRateInput = scan.nextLine();

	        if (!getRateInput.equalsIgnoreCase("yes") && !getRateInput.equalsIgnoreCase("no")) {
	            System.out.println("Invalid Input");
	        }
	    } while (!getRateInput.equalsIgnoreCase("yes") && !getRateInput.equalsIgnoreCase("no"));

	    if (getRateInput.equalsIgnoreCase("yes")) {
	        System.out.println("You are transferring INR " + indianFormatMethod(amount * RATE) +
	                " to " + customerName + " (The rate was " + RATE + ")");
	    }

	    // Book/Cancel 
	    do {
	        System.out.println("Book/Cancel this trade? (Book/Cancel)");
	        bookCancelInput = scan.nextLine();

	        if (bookCancelInput.equalsIgnoreCase("book")) {
	            Trade trade = new Trade(customerName, currencyPair, RATE, amount * RATE);
	            TRADES.add(trade);
	            if (trade.getTradeConfirmed()) {
	                System.out.println("Trade for USDINR has been booked with rate " + trade.getRate() +
	                        ", The amount of Rs " + trade.getAmount() + " will be transferred in 2 working days to " +
	                        trade.getCustomerName() + ".");
	                System.out.println();
	            }
	        } else if (bookCancelInput.equalsIgnoreCase("cancel")) {
	            System.out.println("Trade is Cancelled");
	            System.out.println();
	        } else {
	            System.out.println("Invalid Input");
	        }
	    } while (!bookCancelInput.equalsIgnoreCase("book") && !bookCancelInput.equalsIgnoreCase("cancel"));
	}

	public double getValidAmount(Scanner scan) {
	    double amount = 0;

	    do {
	        System.out.print("Enter amount to transfer: ");
	        if (!scan.hasNextDouble()) {
	            System.out.println("Invalid Input. Enter Amount in double.");
	            scan.nextLine(); 
	            continue;
	        }
	        amount = scan.nextDouble();
	        scan.nextLine();

	        if (amount < 1) {
	            System.out.println("Minimum amount should be 1 USD");
	        }
	    } while (amount < 1);

	    return amount;
	}
	
	public static void updateCurrencyRate(String currencyCode, double newRate) {
		if(CURRENCY_RATE.containsKey(currencyCode)) {
	        CURRENCY_RATE.put(currencyCode, newRate);
	        RATE = newRate;
		}
    }
	
	public static void addCurrencyRate(String currencyCode, double newRate) {
		if(!CURRENCY_RATE.containsKey(currencyCode)) {
	        CURRENCY_RATE.put(currencyCode, newRate);
		}
    }
	
	public static void deleteCurrencyRate(String currencyCode) {
		CURRENCY_RATE.remove(currencyCode);
    }
	
	public static void updateCurrency(String currency) {
		CURRENCY = currency;
	}

	public static double getRate() {
		return RATE;
	}
	
	public static String getCurrency() {
		return CURRENCY;
	}
	
	public static void printCurrencyRates() {
        for (Map.Entry<String, Double> entry : CURRENCY_RATE.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
