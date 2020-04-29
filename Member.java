//
// Member.java
//
// Simulates the members of the credit union
//
// Created in 2020 by Robert Thompson
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float amtInput;
	int delMbr = 0;
	int continueSearch;
	int input;
	int accessNbr;
	String inputString = "";
	String dateOfBirth = "";
	String socSecNbr = "";
	String firstName = "";
	String lastName = "";
	String phoneNbr = "";
	String street = "";
	String city = "";
	String state = "";
	String emailAddress = "";
	String zipCode = "";

	static List<Object> allAcctList = new ArrayList<Object>();
	static List<Object> acctList = new ArrayList<Object>();
	
	public void mainRecord() throws IOException, ClassNotFoundException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		loadAccounts();
		loadMemberAccounts();
		if (acctList.size() == 0) {
			createAcct(1);
		}
		System.out.println(allAcctList);
		System.out.println(acctList);
		continueSearch = 1;
		while(continueSearch == 1) {
			System.out.println(BankSim.mbrList);
			
			// Prints basic member info
			System.out.println("Member Record");
			System.out.println("Access Number: " + accessNbr);
			System.out.println("Name: " + firstName + " " + lastName);
			System.out.println("");
			System.out.println("Account List:");
			System.out.println("");
			
			// Prints member account list
			Account currentAcct = new Account();
			for(int i = 0; i < acctList.size(); ++i) {
				currentAcct = (Account)acctList.get(i);
				//if(currentAcct.rate == currentAcct.checkingRate) {
					System.out.println(currentAcct.acctNbr + "   $" + currentAcct.bal + " " + currentAcct.rate);
				//}
			}
			
			// Member menu
			System.out.println("");
			System.out.println("Enter 1 to view member details.");
			System.out.println("Enter 2 to to access a specific account.");
			System.out.println("Enter 3 to perform a transaction.");
			System.out.println("Enter 4 to create a new account.");
			System.out.println("Enter 5 to return to member lookup.");
			inputString = scan.readLine();
			input = Integer.parseInt(inputString);
			
			// Member details
			if(input == 1) {
				mbrDetails();
			}
			
			// Account details
			else if(input == 2) {
				System.out.println("Select an account to view its details:");
				for(int i = 0; i < acctList.size(); ++i) {
					currentAcct = (Account)acctList.get(i);
					System.out.println((i+1) + " " + currentAcct.acctNbr + "   $" + currentAcct.bal);
				}
				inputString = scan.readLine();
				input = Integer.parseInt(inputString);
				currentAcct = (Account)acctList.get((input-1));
				currentAcct.acctDetails();
			}
			
			// Transactions
			else if(input == 3) {
				System.out.println("Enter 1 to perform a deposit.");
				System.out.println("Enter 2 to perform a withdrawal.");
				System.out.println("Enter 3 to perform a transfer.");
				System.out.println("Enter 4 to cancel.");
				inputString = scan.readLine();
				input = Integer.parseInt(inputString);
				
				// Deposit
				if(input == 1) {
					System.out.println("Select an account to add funds to:");
					for(int i = 0; i < acctList.size(); ++i) {
						currentAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + currentAcct.acctNbr + "   $" + currentAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					currentAcct = (Account)acctList.get((input-1));
					
					// Amount to deposit
					System.out.println("Enter an amount to add:");
					inputString = scan.readLine();
					amtInput = Float.parseFloat(inputString);
					currentAcct.deposit(amtInput);
					System.out.println("The new balance of account " + currentAcct.acctNbr + " is $" + currentAcct.bal + ".");
				}
				
				// Withdrawal
				else if(input == 2) {
					System.out.println("Select an account to remove funds from:");
					for(int i = 0; i < acctList.size(); ++i) {
						currentAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + currentAcct.acctNbr + "   $" + currentAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					currentAcct = (Account)acctList.get((input-1));
					
					// Amount to withdrawal
					System.out.println("Enter an amount to remove:");
					inputString = scan.readLine();
					amtInput = Float.parseFloat(inputString);
					currentAcct.withdrawal(amtInput);
					System.out.println("The new balance of account " + currentAcct.acctNbr + " is $" + currentAcct.bal + ".");
				}
				
				// Transfer
				else if(input == 3) {
					Account destAcct = new Account();
					
					// Originating account
					System.out.println("Select an account to remove funds from:");
					for(int i = 0; i < acctList.size(); ++i) {
						currentAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + currentAcct.acctNbr + "   $" + currentAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					
					// Destination account
					currentAcct = (Account)acctList.get((input-1));
					System.out.println("Select an account to add funds to:");
					for(int i = 0; i < acctList.size(); ++i) {
						destAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + destAcct.acctNbr + "   $" + destAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					destAcct = (Account)acctList.get((input-1));
					
					// Amount to transfer
					System.out.println("Enter an amount to transfer:");
					inputString = scan.readLine();
					amtInput = Float.parseFloat(inputString);
					currentAcct.withdrawal(amtInput);
					destAcct.deposit(amtInput);
					System.out.println("The new balance of account " + currentAcct.acctNbr + " is $" + currentAcct.bal + ".");
					System.out.println("The new balance of account " + destAcct.acctNbr + " is $" + destAcct.bal + ".");
				}
			}
			
			// Create new account
			else if(input == 4) {
				System.out.println("Create account");
				System.out.println("Enter 1 for a savings account.");
				System.out.println("Enter 2 for a checking account.");
				inputString = scan.readLine();
				input = Integer.parseInt(inputString);
				System.out.println(input);
				createAcct(input);
			}
			
			// Return to main menu
			else if(input == 5) {
				returnToMain();				
			}
			
			// Invalid choice
			else {
				System.out.println("Invalid");
			}
		}
	}
	
	// Shows detailed member info
	public void mbrDetails() throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Member Details");
		System.out.println("Access Number:			" + accessNbr);
		System.out.println("Name:          			" + firstName + " " + lastName);
		System.out.println("Date of Birth: 			" + dateOfBirth);
		System.out.println("Phone Number:  			" + phoneNbr);
		System.out.println("Email Address:			" + emailAddress);
		System.out.println("Social Security Number:		" + socSecNbr);
		System.out.println("Address:			" + street);
		System.out.println("				" + city + "," + state);
		System.out.println("				" + zipCode);
		System.out.println("");
		System.out.println("Enter 1 to modify the member's information.");
		System.out.println("Enter 2 to delete the member record.");
		System.out.println("Enter 3 to return the main member record.");
		
		// Member details prompt
		inputString = scan.readLine();
		input = Integer.parseInt(inputString);
		
		// Modify info
		//while(continueSearch == 1) {
			if(input == 1) {
				int retry = 1;
				while(retry == 1) {
					retry = modifyInfo(retry);
				}
			}
			
			// Delete member
			else if(input == 2) {
				deleteMbr();
				returnToMain();
				
			// Return to main record
			}
			else if(input == 3) {
				
			}
			
			// Invalid input
			else {
				System.out.println("Invalid");
			}
		//}
		//continueSearch = 1;
	}
	
	// Makes changes to member info
	public int modifyInfo(int retry) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Select a field to modify:");
		System.out.println("1. Name");
		System.out.println("2. Date of Birth");
		System.out.println("3. Phone Number");
		System.out.println("4. Email Address");
		System.out.println("5. Social Security Number");
		System.out.println("6. Address");
		System.out.println("7. Cancel modify request");
		inputString = scan.readLine();
		input = Integer.parseInt(inputString);
		if(input == 1) {
			System.out.println("Enter the first name:");
			firstName = scan.readLine();
			System.out.println("Enter the last name:");
			lastName = scan.readLine();
			retry = 0;
		}
		else if(input == 2) {
			System.out.println("Enter the date of birth in DD/MM/YY format:");
			dateOfBirth = scan.readLine();
			retry = 0;
		}
		else if(input == 3) {
			System.out.println("Enter the phone number in ###-###-#### format:");
			phoneNbr = scan.readLine();
			retry = 0;
		}
		else if(input == 4) {
			System.out.println("Enter the email address:");
			emailAddress = scan.readLine();
			retry = 0;
		}
		else if(input == 5) {
			System.out.println("Enter the social security number in ###-##-#### format:");
			socSecNbr = scan.readLine();
			retry = 0;
		}
		else if(input == 6) {
			System.out.println("Enter the house number and street:");
			street = scan.readLine();
			System.out.println("Enter the city:");
			city = scan.readLine();
			System.out.println("Enter the state abbreviation:");
			state = scan.readLine();
			System.out.println("Enter the zip code:");
			zipCode = scan.readLine();
			retry = 0;
		}
		else if(input == 7) {
			retry = 0;
		}
		else {
			System.out.println("Invalid");
		}
		return retry;
	}
	
	// Delete the member
	public void deleteMbr() {
		int acctsAreEmpty = 1;
		Account currentAcct = new Account();
		System.out.println("Delete member record");
		// Checks all member accounts for a balance
		for(int i = 0; i < acctList.size(); ++i) {
			currentAcct = (Account)acctList.get(i);
			if(currentAcct.bal != 0) {
				acctsAreEmpty = 0;
			}
		}
		if(acctsAreEmpty == 0) {
			System.out.println("At least one account has a balance. Clear the balance(s) and try again.");
		}
		else {
			System.out.println("All accounts are empty, deleting member record...");
			delMbr = 1;
		}
	}
	
	// Creates a new account
	public void createAcct(int acctType) {
		ToolKit tool = new ToolKit();
		Account currentAcct = new Account();
		System.out.println("Debug");
		currentAcct.acctNbr = tool.generateAcctNbr();
		currentAcct.accessNbr = accessNbr;
		if(acctType == 1) {
			currentAcct.acctType = "Savings";
			currentAcct.rate = currentAcct.savingsRate;
		}
		else if(acctType == 2) {
			currentAcct.acctType = "Checking";
			currentAcct.rate = currentAcct.checkingRate;
		}
		acctList.add(currentAcct);
		allAcctList.add(currentAcct);
		System.out.println(currentAcct.acctNbr + "   " + currentAcct.bal);
		tool = null;
	}
	
	// Returns to the main menu
	public void returnToMain() throws IOException {
		continueSearch = 0;
		saveAccounts();
		acctList.clear();
	}
	
	// Loads list of accounts for all members from file
	public static void loadAccounts() throws IOException, ClassNotFoundException {
		File f = new File("accounts.txt");
		if(f.isFile()) {
			FileInputStream fis = new FileInputStream("accounts.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<Object> read = (List<Object>)ois.readObject();
			allAcctList = read;
			ois.close();
		}
	}
	
	// Creates a list of only the accounts belonging to the current member
	public void loadMemberAccounts() {
		for(int i = 0; i < allAcctList.size(); ++i) {
			Account copyAccount = new Account();
			copyAccount = (Account)allAcctList.get(i);
			if(accessNbr == copyAccount.accessNbr) {
				acctList.add(copyAccount);
			}
		}
	}
	
	// Saves list of accounts for all members to file
	public static void saveAccounts() throws IOException {
		FileOutputStream fos = new FileOutputStream("accounts.txt", false);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(allAcctList);
		oos.close();
		for(int i = 0; i < allAcctList.size(); ++i) {
			Account currentAcct = new Account();
			currentAcct = (Account)allAcctList.get(i);
			System.out.println(currentAcct.acctNbr + "   $" + currentAcct.bal + " for access # " + currentAcct.accessNbr);
		}
	}
}