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
	
	//transient Scanner scan = new Scanner(System.in);
	//BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	
	public void mainRecord() throws IOException, ClassNotFoundException {
		//Scanner scan = new Scanner(System.in);
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
			System.out.println("Member Record");
			System.out.println("Access Number: " + accessNbr);
			System.out.println("Name: " + firstName + " " + lastName);
			System.out.println("");
			System.out.println("Account List:");
			System.out.println("");
			Account currentAcct = new Account();
			for(int i = 0; i < acctList.size(); ++i) {
				currentAcct = (Account)acctList.get(i);
				System.out.println(currentAcct.acctNbr + "   $" + currentAcct.bal);
			}
			System.out.println("");
			System.out.println("Enter 1 to view member details.");
			System.out.println("Enter 2 to to access a specific account.");
			System.out.println("Enter 3 to perform a transaction.");
			System.out.println("Enter 4 to create a new account.");
			System.out.println("Enter 5 to return to member lookup.");
			
			inputString = scan.readLine();
			input = Integer.parseInt(inputString);
			if(input == 1) {
				mbrDetails();
			}
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
			else if(input == 3) {
				System.out.println("Enter 1 to perform a deposit.");
				System.out.println("Enter 2 to perform a withdrawal.");
				System.out.println("Enter 3 to perform a transfer.");
				System.out.println("Enter 4 to cancel.");
				inputString = scan.readLine();
				input = Integer.parseInt(inputString);
				if(input == 1) {
					System.out.println("Select an account to add funds to:");
					for(int i = 0; i < acctList.size(); ++i) {
						currentAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + currentAcct.acctNbr + "   $" + currentAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					currentAcct = (Account)acctList.get((input-1));
					System.out.println("Enter an amount to add:");
					inputString = scan.readLine();
					amtInput = Float.parseFloat(inputString);
					currentAcct.deposit(amtInput);
					System.out.println("The new balance of account " + currentAcct.acctNbr + " is $" + currentAcct.bal + ".");
				}
				else if(input == 2) {
					System.out.println("Select an account to remove funds from:");
					for(int i = 0; i < acctList.size(); ++i) {
						currentAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + currentAcct.acctNbr + "   $" + currentAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					currentAcct = (Account)acctList.get((input-1));
					System.out.println("Enter an amount to remove:");
					inputString = scan.readLine();
					amtInput = Float.parseFloat(inputString);
					currentAcct.withdrawal(amtInput);
					System.out.println("The new balance of account " + currentAcct.acctNbr + " is $" + currentAcct.bal + ".");
				}
				else if(input == 3) {
					Account recAcct = new Account();
					
					System.out.println("Select an account to remove funds from:");
					for(int i = 0; i < acctList.size(); ++i) {
						currentAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + currentAcct.acctNbr + "   $" + currentAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					currentAcct = (Account)acctList.get((input-1));
					System.out.println("Select an account to add funds to:");
					for(int i = 0; i < acctList.size(); ++i) {
						recAcct = (Account)acctList.get(i);
						System.out.println((i+1) + " " + recAcct.acctNbr + "   $" + recAcct.bal);
					}
					inputString = scan.readLine();
					input = Integer.parseInt(inputString);
					recAcct = (Account)acctList.get((input-1));
					System.out.println("Enter an amount to transfer:");
					inputString = scan.readLine();
					amtInput = Float.parseFloat(inputString);
					currentAcct.withdrawal(amtInput);
					recAcct.deposit(amtInput);
					System.out.println("The new balance of account " + currentAcct.acctNbr + " is $" + currentAcct.bal + ".");
					System.out.println("The new balance of account " + recAcct.acctNbr + " is $" + recAcct.bal + ".");
				}
			}
			else if(input == 4) {
				System.out.println("Create account");
				System.out.println("Enter 1 for a savings account.");
				System.out.println("Enter 2 for a checking account.");
				inputString = scan.readLine();
				input = Integer.parseInt(inputString);
				System.out.println(input);
				createAcct(input);
			}
			else if(input == 5) {
				continueSearch = 0;
				saveAccounts();
				acctList.clear();
			}
			else {
				System.out.println("Invalid");
			}
		}
	}
	public void mbrDetails() {
		System.out.println("Member Details");
		System.out.println("Access Number:");
		System.out.println("Enter 1 to modify the member's information.");
		System.out.println("Enter 2 to delete the member record.");
		System.out.println("Enter 3 to return the main member record/");
	}
	public void modifyInfo() {
		System.out.println("Modify Info");
		System.out.println("");
	}
	public void deleteMbr() {
		System.out.println("Delete member record");
	}
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
	public void loadMemberAccounts() {
		for(int i = 0; i < allAcctList.size(); ++i) {
			Account copyAccount = new Account();
			copyAccount = (Account)allAcctList.get(i);
			if(accessNbr == copyAccount.accessNbr) {
				acctList.add(copyAccount);
			}
		}
	}
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