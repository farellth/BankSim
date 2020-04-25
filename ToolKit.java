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
	public void createMbr(int accessNbr) {

	}
	public void createAcct(int acctType, int accessNbr) {
		
	}
	public int generateAccessNbr() {
		int accessNbr;
		Random r = new Random();
		
		accessNbr = r.nextInt((9999999 - 1000000) + 1000000) + 1000000;
		
		return accessNbr;
	}
	public int generateAcctNbr() {
		int acctNbr;
		Random r = new Random();
		
		acctNbr = r.nextInt((99999999 - 10000000) + 10000000) + 10000000;

		return acctNbr;
	}
}