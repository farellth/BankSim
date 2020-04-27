//
// Account.java
//
// Simulates the accounts belonging to the members
// of the credit union
//
// Created in March 2020 by Robert Thompson
//

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
	int accessNbr, acctNbr;
	String acctType = "";
	BigDecimal transfer;
	
	public void acctDetails() {
		System.out.println("Account Details");
		System.out.println("Account Number:   " + acctNbr);
		System.out.println("Account Type:     " + acctType);
		System.out.println("Interest Rate:    " + rate + "%");
		System.out.println("Balance:          $" + bal);
	}
	public void deleteAcct(float amount) {
		System.out.println("Delete Account");
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