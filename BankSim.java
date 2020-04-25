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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

class BankSim {
	
	static List<Object> mbrList = new ArrayList<Object>();
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int continueSearch = 1;
		int input = 1;
		String version = "0.1";
		String inputString = "";

		//Scanner scan = new Scanner(System.in); 
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		ToolKit tool = new ToolKit();
		
		loadMembers();
		// Test		
		for(int i = 0; i < mbrList.size(); ++i) {
			Member testMbr = new Member();
			testMbr = (Member)mbrList.get(i);
			System.out.println(testMbr.accessNbr + " " + testMbr.firstName + " " + testMbr.lastName);
		}
		// End Test
		System.out.println(mbrList);
		System.out.println("Welcome to BankSim version " + version + ".\n");
		while(continueSearch == 1) {
			System.out.println("Enter 1 to search for an existing member.");
			System.out.println("Enter 2 to create a new membership.");
			System.out.println("Enter 3 to quit.");
			inputString = scan.readLine();
			input = Integer.parseInt(inputString);
			if(input == 1) {
				System.out.println("Enter an access number to search:");
				inputString = scan.readLine();
				input = Integer.parseInt(inputString);
				for(int i = 0; i < mbrList.size(); ++i) {
					Member currentMbr = new Member();
					currentMbr = (Member)mbrList.get(i);
					if(input != currentMbr.accessNbr) {
						System.out.println("Member not found");
						continue;
					}
					else {
						currentMbr.mainRecord();
						break;
					}
				}
			}
			else if(input == 2) {
				System.out.println("Create");
				Member currentMbr = new Member();
				currentMbr.accessNbr = tool.generateAccessNbr();
				System.out.println("Enter the first name:");
				scan.readLine();
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
				currentMbr.mainRecord();
			}
			else if(input == 3) {
				continueSearch = 0;
			}
			else {
				System.out.println("Invalid");
			}
		}
		System.out.println(mbrList);
		saveMembers();
	}
	public static void loadMembers() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("members.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<Object> read = (List<Object>)ois.readObject();
		mbrList = read;
		ois.close();
	}
	public static void saveMembers() throws IOException {
		FileOutputStream fos = new FileOutputStream("members.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(mbrList); 
		oos.close();
	}
}
