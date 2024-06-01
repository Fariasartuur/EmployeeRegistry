package main;

import java.util.Random;
import java.util.Scanner;

public class Registration {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String department;
	private String shortDepartment;

	Scanner sc = new Scanner(System.in);

	public Registration() {
		System.out.println("Enter the first name: ");
		this.firstName = sc.nextLine().toLowerCase();
		System.out.println("Enter the last name: ");
		this.lastName = sc.nextLine().toLowerCase();

		System.out.println("Choose the department\n1 - Sales\n2 - Human Resources\n3 - Software Development\n4 - Marketing\n5 - None");

		int choice = sc.nextInt();
		this.shortDepartment = getShortDepartment(choice);

		this.email = generateEmail();
		this.password = generatePassword();

	}

	public String getShortDepartment(int num) {

		switch (num) {
		case 1:
			this.department = "Sales";
			return "sal.";
		case 2:
			this.department = "Human Resources";
			return "hr.";
		case 3:
			this.department = "Software Development";
			return "dev.";
		case 4:
			this.department = "Marketing";
			return "mkt.";
		case 5:
			this.department = "None";
			return "";
		default:
			System.out.println("Invalid Department");
			return null;
		}
	}

	public String generateEmail() {
		
		String[] firstNameParts = this.firstName.split(" ");
		String firstPart = firstNameParts[0];

		String[] lastNameParts = this.lastName.split(" ");
		String lastPart = lastNameParts[0];

		return firstPart + "." + lastPart + "@" + this.shortDepartment + "gmail.com";
	}

	public String generatePassword() {

		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*";

		Random random = new Random();
		int length = 8;
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			password[i] = characters.charAt(random.nextInt(characters.length()));
		}
		return new String(password);
	}

	public void showInformation() {

		String firstNameCapitalized = this.firstName.substring(0, 1).toUpperCase() + this.firstName.substring(1);
		String lastNameCapitalized = this.lastName.substring(0, 1).toUpperCase() + this.lastName.substring(1);

		System.out.println("First Name: " + firstNameCapitalized);
		System.out.println("Last Name: " + lastNameCapitalized);
		System.out.println("Email: " + this.email);
		System.out.println("Password: " + this.password);
		if (this.department.equals("sal.")) {
			System.out.println("Department: Sales");
		} else if (this.department.equals("hr.")) {
			System.out.println("Department: Human Resources");
		} else if (this.department.equals("dev.")) {
			System.out.println("Department: Software Development");
		} else if (this.department.equals("mkt.")) {
			System.out.println("Department: Marketing");
		}

	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
