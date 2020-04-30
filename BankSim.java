//
// BankSim.java
//
// Main class for a banking simulation that purports
// to be a system used by employees to manage
// accounts based on the requests of the members
// of a credit union
//
// Created in 2020 by Robert Thompson
//

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class BankSim {
	
	static List<Object> mbrList = new ArrayList<Object>();
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int keepMainMenu = 1;
		int input = 1;
		String inputString = "";

		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		ToolKit tool = new ToolKit();
		
		loadMembers();
				
		while(keepMainMenu == 1) {
			int prompt = 1;
						
			// Main menu
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter 1 to search for an existing member.");
				System.out.println("Enter 2 to create a new membership.");
				System.out.println("Enter 3 to quit.");
				inputString = scan.readLine();
				try {
					input = Integer.parseInt(inputString);
					prompt = 0;
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			// Member search
			if(input == 1) {
				mbrSearch();
			}
			
			// Create new Member
			else if(input == 2) {
				System.out.println("");
				System.out.println("Membership Application");
				System.out.println("");
				Member currentMbr = new Member();
				currentMbr.accessNbr = tool.generateAccessNbr();
				System.out.println("Enter the first name:");
				currentMbr.firstName = scan.readLine();
				System.out.println("Enter the last name:");
				currentMbr.lastName = scan.readLine();
				System.out.println("Enter the date of birth in DD/MM/YY format:");
				currentMbr.dateOfBirth = scan.readLine();
				System.out.println("Enter the social security number in ###-##-#### format:");
				currentMbr.socSecNbr = scan.readLine();
				System.out.println("Enter the phone number in ###-###-#### format:");
				currentMbr.phoneNbr = scan.readLine();
				System.out.println("Enter the email address:");
				currentMbr.emailAddress = scan.readLine();
				System.out.println("Enter the house number and street:");
				currentMbr.street = scan.readLine();
				System.out.println("Enter the city:");
				currentMbr.city = scan.readLine();
				System.out.println("Enter the state abbreviation:");
				currentMbr.state = scan.readLine();
				System.out.println("Enter the zip code:");
				currentMbr.zipCode = scan.readLine();
				mbrList.add(currentMbr);
				currentMbr.mainRecord();
				
				//Check if member should be deleted
				if(currentMbr.delMbr == 1) {
					mbrList.remove(currentMbr);
				}
			}
			
			// Quit
			else if(input == 3) {
				keepMainMenu = 0;				
			}
			
			// Invalid input
			else {
				System.out.println("Invalid choice");
			}
		}
		saveMembers();
		cleanAcctList();
	}
	
	// Member search
	public static void mbrSearch() throws IOException, ClassNotFoundException {
		int prompt = 1;
		int mbrSearchInput = 0;
		String mbrSearchInputString = "";
		
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		while(prompt == 1) {
			System.out.println("");
			System.out.println("Select a search method:");
			System.out.println("1. Access Number");
			System.out.println("2. Name");
			System.out.println("3. Date of Birth");
			System.out.println("4. Phone Number");
			System.out.println("5. Email Address");
			System.out.println("6. Social Security Number");
			System.out.println("7. Cancel search");
			mbrSearchInputString = scan.readLine();
			try {
				mbrSearchInput = Integer.parseInt(mbrSearchInputString);
				prompt = 0;
			} catch(NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		if(mbrSearchInput == 1) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter an access number to search.");
				mbrSearchInputString = scan.readLine();
				try {
					mbrSearchInput = Integer.parseInt(mbrSearchInputString);
					for(int i = 0; i < mbrList.size(); ++i) {
						Member currentMbr = new Member();
						currentMbr = (Member)mbrList.get(i);
						if(mbrSearchInput == currentMbr.accessNbr) {
							currentMbr.mainRecord();
							//Check if member should be deleted
							if(currentMbr.delMbr == 1) {
								mbrList.remove(i);
							}
							break;
						}
						else if(i == mbrList.size() - 1) {
							System.out.println("Member not found");
						}
					}
					prompt = 0;
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			
		}
		else if(mbrSearchInput == 2) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter a first and last name to search.");
				mbrSearchInputString = scan.readLine();
				for(int i = 0; i < mbrList.size(); ++i) {
					Member currentMbr = new Member();
					currentMbr = (Member)mbrList.get(i);
					if(mbrSearchInputString.equals(currentMbr.firstName + " " + currentMbr.lastName)) {
						currentMbr.mainRecord();
						//Check if member should be deleted
						if(currentMbr.delMbr == 1) {
							mbrList.remove(i);
						}
						break;
					}
					else if(i == mbrList.size() - 1) {
						System.out.println("Member not found");
					}
				}
				prompt = 0;
			}
		}
		else if(mbrSearchInput == 3) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter a date of birth to search.");
				mbrSearchInputString = scan.readLine();
				for(int i = 0; i < mbrList.size(); ++i) {
					Member currentMbr = new Member();
					currentMbr = (Member)mbrList.get(i);
					if(mbrSearchInputString.equals(currentMbr.dateOfBirth)) {
						currentMbr.mainRecord();
						//Check if member should be deleted
						if(currentMbr.delMbr == 1) {
							mbrList.remove(i);
						}
						break;
					}
					else if(i == mbrList.size() - 1) {
						System.out.println("Member not found");
					}
				}
				prompt = 0;
			}
		}
		else if(mbrSearchInput == 4) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter a phone number to search.");
				mbrSearchInputString = scan.readLine();
				for(int i = 0; i < mbrList.size(); ++i) {
					Member currentMbr = new Member();
					currentMbr = (Member)mbrList.get(i);
					if(mbrSearchInputString.equals(currentMbr.phoneNbr)) {
						currentMbr.mainRecord();
						//Check if member should be deleted
						if(currentMbr.delMbr == 1) {
							mbrList.remove(i);
						}
						break;
					}
					else if(i == mbrList.size() - 1) {
						System.out.println("Member not found");
					}
				}
				prompt = 0;
			}
		}
		else if(mbrSearchInput == 5) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter an email address to search.");
				mbrSearchInputString = scan.readLine();
				for(int i = 0; i < mbrList.size(); ++i) {
					Member currentMbr = new Member();
					currentMbr = (Member)mbrList.get(i);
					if(mbrSearchInputString.equals(currentMbr.emailAddress)) {
						currentMbr.mainRecord();
						//Check if member should be deleted
						if(currentMbr.delMbr == 1) {
							mbrList.remove(i);
						}
						break;
					}
					else if(i == mbrList.size() - 1) {
						System.out.println("Member not found");
					}
				}
				prompt = 0;
			}
		}
		else if(mbrSearchInput == 6) {
			prompt = 1;
			while(prompt == 1) {
				System.out.println("");
				System.out.println("Enter a social security number to search.");
				mbrSearchInputString = scan.readLine();
				for(int i = 0; i < mbrList.size(); ++i) {
					Member currentMbr = new Member();
					currentMbr = (Member)mbrList.get(i);
					if(mbrSearchInputString.equals(currentMbr.socSecNbr)) {
						currentMbr.mainRecord();
						//Check if member should be deleted
						if(currentMbr.delMbr == 1) {
							mbrList.remove(i);
						}
						break;
					}
					else if(i == mbrList.size() - 1) {
						System.out.println("Member not found");
					}
				}
				prompt = 0;
			}
		}
		else if(mbrSearchInput == 7) {
			prompt = 0;
		}
		else {
			System.out.println("Invalid choice");
		}
	}
	
	// Load member list from file
	public static void loadMembers() throws IOException, ClassNotFoundException {
		File f = new File("members.txt");
		
		// Checks if file exists
		if(f.isFile()) {
			FileInputStream fis = new FileInputStream("members.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<Object> read = (List<Object>)ois.readObject();
			mbrList = read;
			ois.close();
		}
	}
	
	// Save member list to file
	public static void saveMembers() throws IOException {
		FileOutputStream fos = new FileOutputStream("members.txt", false);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(mbrList); 
		oos.close();
	}
	public static void cleanAcctList() throws IOException, ClassNotFoundException {
				
		List<Object> allAcctList = new ArrayList<Object>();
		Member cleanMbr = new Member();
		Account cleanAcct = new Account();
		
		// Loads account list to check through
		File f = new File("accounts.txt");
		if(f.isFile()) {
			FileInputStream fis = new FileInputStream("accounts.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<Object> read = (List<Object>)ois.readObject();
			allAcctList = read;
			ois.close();
		}
		for(int i = 0; i < allAcctList.size(); ++i) {
			int doesAccessMatch = 0;
			cleanAcct = (Account)allAcctList.get(i);
			for(int x = 0; x < mbrList.size(); ++x) {
				doesAccessMatch = 0;
				cleanMbr = (Member)mbrList.get(x);
				if(cleanAcct.accessNbr == cleanMbr.accessNbr) {
					doesAccessMatch = 1;
					break;
				}
			}
			if(doesAccessMatch == 0) {
				allAcctList.remove(i);
			}
		}
		
		// Save account list to file
		FileOutputStream fos = new FileOutputStream("accounts.txt", false);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(allAcctList);
		oos.close();
	}
}
