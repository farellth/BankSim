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
	int viewMbr;
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
		viewMbr = 1;
		while(viewMbr == 1) {
			int prompt = 1;
			
			Account currentAcct = new Account();
			
			// Prints basic member info
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Member Record");
				System.out.println("Access Number:	" + accessNbr);
				System.out.println("Name:		" + firstName + " " + lastName);
				System.out.println("");
				System.out.println("Account List:");
				System.out.println("");
				
				// Prints member account list
				
				// Print checking account list, if any exist
				int chkExists = 0;
				for(int i = 0; i < acctList.size(); ++i) {
					currentAcct = (Account)acctList.get(i);
					if(currentAcct.rate == currentAcct.checkingRate) {
						if(chkExists == 0) {
							System.out.println("Checking");
							System.out.println("");
							chkExists = 1;
						}
						System.out.printf(currentAcct.acctNbr + "   $" + "%,.2f" + "\n", currentAcct.bal);
					}
				}
				
				// Print savings account list
				if(chkExists == 1) {
					System.out.println("");
				}
				System.out.println("Savings");
				System.out.println("");
				for(int i = 0; i < acctList.size(); ++i) {
					currentAcct = (Account)acctList.get(i);
					if(currentAcct.rate == currentAcct.savingsRate) {
						System.out.printf(currentAcct.acctNbr + "   $" + "%,.2f" + "\n", currentAcct.bal);
					}
				}
				
				// Member menu
				System.out.println("");
				System.out.println("Enter 1 to view member details.");
				System.out.println("Enter 2 to to access a specific account.");
				System.out.println("Enter 3 to perform a transaction.");
				System.out.println("Enter 4 to create a new account.");
				System.out.println("Enter 5 to return to member lookup.");
				inputString = scan.readLine();
				try {
					input = Integer.parseInt(inputString);
					prompt = 0;
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");					
				}
			}
			
			// Member details
			if(input == 1) {
				mbrDetails();
			}
			
			// Account details
			else if(input == 2) {
				prompt = 1;
				while(prompt == 1) {
					System.out.println("");
					System.out.println("Select an account to view its details:");
					System.out.println("");
					for(int i = 0; i < acctList.size(); ++i) {
						currentAcct = (Account)acctList.get(i);
						System.out.printf((i+1) + ". " + currentAcct.acctNbr + "   $" + "%,.2f" + "\n", currentAcct.bal);
					}
					inputString = scan.readLine();
					try {
						input = Integer.parseInt(inputString);
						currentAcct = (Account)acctList.get((input-1));
						System.out.println(currentAcct.bal);
						currentAcct.acctDetails();
						if(currentAcct.delAcct == 1) {
							acctList.remove(currentAcct);
							allAcctList.remove(currentAcct);
						}
						prompt = 0;
					} catch(NumberFormatException e) {
						System.out.println("Invalid input");
					}
				}
			}
			
			// Transactions
			else if(input == 3) {
				prompt = 1;
				while(prompt == 1) {
					System.out.println("");
					System.out.println("Select a type of transaction.");
					System.out.println("");
					System.out.println("Enter 1 to perform a deposit.");
					System.out.println("Enter 2 to perform a withdrawal.");
					System.out.println("Enter 3 to perform a transfer.");
					System.out.println("Enter 4 to cancel.");
					inputString = scan.readLine();
					try {
						input = Integer.parseInt(inputString);
						prompt = 0;
					} catch(NumberFormatException e) {
						System.out.println("Invalid input");
					}
				}
				
				// Deposit
				if(input == 1) {
					prompt = 1;
					while(prompt == 1) {
						System.out.println("");
						System.out.println("Select an account to add funds to:");
						System.out.println("");
						for(int i = 0; i < acctList.size(); ++i) {
							currentAcct = (Account)acctList.get(i);
							System.out.printf((i+1) + ". " + currentAcct.acctNbr + "   $" + "%,.2f" + "\n", currentAcct.bal);
						}
						inputString = scan.readLine();
						try {
							input = Integer.parseInt(inputString);
							currentAcct = (Account)acctList.get((input-1));
							prompt = 0;
						} catch(NumberFormatException e) {
							System.out.println("Invalid input");
						}
					}
					
					// Amount to deposit
					prompt = 1;
					while(prompt == 1) {
						System.out.println("");
						System.out.println("Enter an amount to add:");
						try {
							inputString = scan.readLine();
							amtInput = Float.parseFloat(inputString);
							currentAcct.deposit(amtInput);
							System.out.printf("The new balance of account " + currentAcct.acctNbr + " is $" + "%,.2f" + ".\n", currentAcct.bal);
							prompt = 0;
						} catch(NumberFormatException e) {
							System.out.println("Invalid input");
						}
					}
				}
				
				// Withdrawal
				else if(input == 2) {
					prompt = 1;
					while(prompt == 1) {
						System.out.println("");
						System.out.println("Select an account to remove funds from:");
						System.out.println("");
						for(int i = 0; i < acctList.size(); ++i) {
							currentAcct = (Account)acctList.get(i);
							System.out.printf((i+1) + ". " + currentAcct.acctNbr + "   $" + "%,.2f" + "\n", currentAcct.bal);
						}
						inputString = scan.readLine();
						try {
							input = Integer.parseInt(inputString);
							currentAcct = (Account)acctList.get((input-1));
							prompt = 0;
						} catch(NumberFormatException e) {
							System.out.println("Invalid input");
						}
					}
											
					// Amount to withdrawal
					prompt = 1;
					while(prompt == 1) {
						System.out.println("");
						System.out.println("Enter an amount to remove:");
						inputString = scan.readLine();
						try {
							amtInput = Float.parseFloat(inputString);
							currentAcct.withdrawal(amtInput);
							System.out.printf("The new balance of account " + currentAcct.acctNbr + " is $" + "%,.2f" + ".\n", currentAcct.bal);
							prompt = 0;
						} catch(NumberFormatException e) {
							System.out.println("Invalid input");
						}
						
					}
				}
				
				// Transfer
				else if(input == 3) {
					Account destAcct = new Account();
					
					// Originating account
					prompt = 1;
					while(prompt == 1) {
						System.out.println("");
						System.out.println("Select an account to remove funds from:");
						System.out.println("");
						for(int i = 0; i < acctList.size(); ++i) {
							currentAcct = (Account)acctList.get(i);
							System.out.printf((i+1) + ". " + currentAcct.acctNbr + "   $" + "%,.2f" + "\n", currentAcct.bal);
						}
						inputString = scan.readLine();
						try {
							input = Integer.parseInt(inputString);
							prompt = 0;
						} catch(NumberFormatException e) {
							System.out.println("Invalid input");
						}
					}
					
					// Destination account
					prompt = 1;
					while(prompt == 1) {
						currentAcct = (Account)acctList.get((input-1));
						System.out.println("");
						System.out.println("Select an account to add funds to:");
						System.out.println("");
						for(int i = 0; i < acctList.size(); ++i) {
							destAcct = (Account)acctList.get(i);
							System.out.printf((i+1) + ". " + destAcct.acctNbr + "   $" + "%,.2f" + "\n", destAcct.bal);
						}
						inputString = scan.readLine();
						try {
							input = Integer.parseInt(inputString);
							destAcct = (Account)acctList.get((input-1));
							prompt = 0;
						} catch(NumberFormatException e) {
							System.out.println("Invalid input");
						}
					}
					
					// Amount to transfer
					prompt = 1;
					while(prompt == 1) {
						System.out.println("");
						System.out.println("Enter an amount to transfer:");
						inputString = scan.readLine();
						try {
							amtInput = Float.parseFloat(inputString);
							currentAcct.withdrawal(amtInput);
							destAcct.deposit(amtInput);
							System.out.printf("The new balance of account " + currentAcct.acctNbr + " is $" + "%,.2f" + ".\n", currentAcct.bal);
							System.out.printf("The new balance of account " + destAcct.acctNbr + " is $" + "%,.2f" + ".\n", destAcct.bal);
							prompt = 0;
						} catch(NumberFormatException e) {
							System.out.println("Invalid input");
						}
					}
				}
			}
			
			// Create new account
			else if(input == 4) {
				prompt = 1;
				while(prompt == 1) {
					System.out.println("");
					System.out.println("Select an account type.");
					System.out.println("");
					System.out.println("Enter 1 for a savings account.");
					System.out.println("Enter 2 for a checking account.");
					inputString = scan.readLine();
					try {
						input = Integer.parseInt(inputString);
						System.out.println(input);
						createAcct(input);
						prompt = 0;
					} catch(NumberFormatException e) {
						System.out.println("Invalid input");
					}
				}
			}
			
			// Return to main menu
			else if(input == 5) {
				returnToMain();
			}
			
			// Invalid choice
			else {
				System.out.println("Invalid choice");
			}
		}
	}
	
	// Shows detailed member info
	public void mbrDetails() throws IOException {
		int viewMbrDetails = 1;
		int prompt = 1;
		
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		while(viewMbrDetails == 1) {
			while(prompt == 1) {
				System.out.println("");
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
				
				// Member details menu
				System.out.println("");
				System.out.println("Enter 1 to modify the member's information.");
				System.out.println("Enter 2 to delete the member record.");
				System.out.println("Enter 3 to return the main member record.");
				inputString = scan.readLine();
				try {
					input = Integer.parseInt(inputString);
					prompt = 0;
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			
			
			// Modify info
			if(input == 1) {
				int retry = 1;
				while(retry == 1) {
					retry = modifyInfo(retry);
				}
				prompt = 1;
			}
			
			// Delete member
			else if(input == 2) {
				deleteMbr();
				if(delMbr == 1) {
					viewMbrDetails = 0;
					returnToMain();
				}
				else {
					prompt = 1;
				}
				
			// Return to main record
			}
			else if(input == 3) {
				viewMbrDetails = 0;
			}
			
			// Invalid input
			else {
				System.out.println("Invalid choice");
			}
		}	
	}
	
	// Makes changes to member info
	public int modifyInfo(int retry) throws IOException {
		int prompt = 1;
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		while(prompt == 1) {
			System.out.println("");
			System.out.println("Select a field to modify:");
			System.out.println("1. Name");
			System.out.println("2. Date of Birth");
			System.out.println("3. Phone Number");
			System.out.println("4. Email Address");
			System.out.println("5. Social Security Number");
			System.out.println("6. Address");
			System.out.println("7. Cancel modify request");
			inputString = scan.readLine();
			try {
				input = Integer.parseInt(inputString);
				prompt = 0;
			} catch(NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		if(input == 1) {
			System.out.println("");
			System.out.println("Enter the first name:");
			firstName = scan.readLine();
			System.out.println("");
			System.out.println("Enter the last name:");
			lastName = scan.readLine();
			retry = 0;
		}
		else if(input == 2) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter the date of birth in DD/MM/YY format:");
				dateOfBirth = scan.readLine();
				if(dateOfBirth.matches("\\d{2}/\\d{2}/\\d{2}")) {
					prompt = 0;
				}
				else {
					System.out.println("That's not the proper format for a date of birth. Please try again.");
				}
			}
			retry = 0;
		}
		else if(input == 3) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter the phone number in ###-###-#### format:");
				phoneNbr = scan.readLine();
				if(phoneNbr.matches("\\d{3}-\\d{3}-\\d{4}")) {
					prompt = 0;
				}
				else {
					System.out.println("That's not the proper format for a phone number. Please try again.");
				}
			}
			retry = 0;
		}
		else if(input == 4) {
			System.out.println("");
			System.out.println("Enter the email address:");
			emailAddress = scan.readLine();
			retry = 0;
		}
		else if(input == 5) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter the social security number in ###-##-#### format:");
				socSecNbr = scan.readLine();
				if(socSecNbr.matches("\\d{3}-\\d{2}-\\d{4}")) {
					prompt = 0;
				}
				else {
					System.out.println("That's not the proper format for a social security number. Please try again.");
				}
			}
			retry = 0;
		}
		else if(input == 6) {
			System.out.println("");
			System.out.println("Enter the house number and street:");
			street = scan.readLine();
			System.out.println("");
			System.out.println("Enter the city:");
			city = scan.readLine();
			System.out.println("");
			System.out.println("Enter the state abbreviation:");
			state = scan.readLine();
			System.out.println("");
			System.out.println("Enter the zip code:");
			zipCode = scan.readLine();
			retry = 0;
		}
		else if(input == 7) {
			retry = 0;
		}
		else {
			System.out.println("");
			System.out.println("Invalid choice");
		}
		return retry;
	}
	
	// Delete the member
	public void deleteMbr() {
		int acctsAreEmpty = 1;
		Account currentAcct = new Account();
		
		// Checks all member accounts for a balance
		for(int i = 0; i < acctList.size(); ++i) {
			currentAcct = (Account)acctList.get(i);
			if(currentAcct.bal != 0) {
				acctsAreEmpty = 0;
			}
		}
		if(acctsAreEmpty == 0) {
			System.out.println("");
			System.out.println("At least one account has a balance. Clear the balance(s) and try again.");
		}
		else {
			System.out.println("");
			System.out.println("All accounts are empty, deleting member record...");
			delMbr = 1;
		}
	}
	
	// Creates a new account
	public void createAcct(int acctType) {
		ToolKit tool = new ToolKit();
		Account currentAcct = new Account();
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
		tool = null;
	}
	
	// Returns to the main menu
	public void returnToMain() throws IOException {
		viewMbr = 0;
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
	}
}