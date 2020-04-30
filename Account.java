//
// Account.java
//
// Simulates the accounts belonging to the members
// of the credit union
//
// Created in March 2020 by Robert Thompson
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float savingsRate = (float)0.25;
	float checkingRate = (float)0.05;
	float rate;
	float bal = 0;
	int accessNbr, acctNbr, input;
	int delAcct = 0;
	String acctType = "";
	String inputString = "";
	BigDecimal transfer;
	
	public void acctDetails() throws IOException {
		int viewAcct = 1;
		int prompt = 1;
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		while(viewAcct == 1) {
			while(prompt == 1) {
				System.out.println("Account Details");
				System.out.println("Account Number:   " + acctNbr);
				System.out.println("Account Type:     " + acctType);
				System.out.println("Interest Rate:    " + rate + "%");
				System.out.println("Balance:          $" + bal);
				System.out.println("");
				
				// Account details menu
				System.out.println("Enter 1 to delete this account.");
				System.out.println("Enter 2 to return to the main member record.");
				inputString = scan.readLine();
				try {
					input = Integer.parseInt(inputString);
					prompt = 0;
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			if(input == 1) {
				deleteAcct();
				viewAcct = 0;
			}
			else if(input == 2) {
				viewAcct = 0;
			}
			else {
				System.out.println("Invalid choice");
			}
		}
	}
	public void deleteAcct() {
		if(bal != 0) {
			System.out.println("Account has a balance. Clear the balance and try again.");
		}
		else {
			System.out.println("Account is empty, deleting account...");
			delAcct = 1;
		}
	}
	public void deposit(float amount) {
		System.out.println("Deposit");
		bal += amount;
		transfer = round(bal,2);
		bal = transfer.floatValue();
	}
	public void withdrawal(float amount) {
		System.out.println("Withdrawal");
		bal -= amount;
		transfer = round(bal,2);
		bal = transfer.floatValue();
	}
	public BigDecimal round(float toRound, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(toRound));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
}