//
// ToolKit.java
//
// Contains various utility methods to supplement
// main program
//
// Created in 2020 by Robert Thompson
//

import java.util.Random;

public class ToolKit {
	public int generateAccessNbr() {
		int accessNbr;
		int accessNbrLength = 7;
		int accessDigit;
		String strAccessNbr = "";
		
		Random r = new Random();
		
		for(int i = 0; i < accessNbrLength; ++i) {
			accessDigit = r.nextInt(10);
			strAccessNbr = strAccessNbr + String.valueOf(accessDigit);			
		}
		accessNbr = Integer.parseInt(strAccessNbr);
		System.out.println(accessNbr);
		return accessNbr;
	}
	public int generateAcctNbr() {
		int acctNbr;
		int acctNbrLength = 8;
		int acctDigit;
		String strAcctNbr = "";
		
		Random r = new Random();
		
		for(int i = 0; i < acctNbrLength; ++i) {
			acctDigit = r.nextInt(10);
			strAcctNbr = strAcctNbr + String.valueOf(acctDigit);
		}
		acctNbr = Integer.parseInt(strAcctNbr);
		System.out.println(acctNbr);
		return acctNbr;
	}
}