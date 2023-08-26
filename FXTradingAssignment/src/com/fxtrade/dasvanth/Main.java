package com.fxtrade.dasvanth;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		FXTrade fxTrade = new FXTrade();

		Scanner scan = new Scanner(System.in);

		boolean notQuit = true;
		while(notQuit) {
			System.out.println();
			System.out.println("Select one of the following options");
			System.out.println("Book Trade - 1");
			System.out.println("Print Trade - 2");
			System.out.println("Exit - 3");
			
			if (scan.hasNextInt()) {
				int inputChoice = scan.nextInt();
				scan.nextLine();
				

				switch(inputChoice) {
					case 1 : 
						fxTrade.bookTrade(scan);
						break;
					case 2 : 
						fxTrade.printTrades();
						break;
					case 3 :
						if(fxTrade.exit(scan)) {
							notQuit = false;
						}
						break;
					default :
	                    System.out.println("Invalid input.");

				}
			}
			else {
				System.out.println("Invalid Input");
				scan.nextLine();
			}
			
		}
		
	}

}
