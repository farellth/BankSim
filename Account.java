//
// Account.java
//
// Simulates the accounts belonging to the members
// of the credit union
//
// Created in March 2020 by Robert Thompson
//

public class Account {
	float rate;
	float bal = 0;
	int accessNbr, acctNbr;
	String acctType = "";
	
	public void mainRecord() {
		System.out.println("Account Record");
	}
	public void acctDetails() {
		System.out.println("Account Details");
	}
	public void deleteAcct(float amount) {
		System.out.println("Delete Account");
	}
	public void deposit(float amount) {
		System.out.println("Deposit");
		bal += amount;
	}
	public void withdrawal(float amount) {
		System.out.println("Withdrawal");
		bal -= amount;
	}
}