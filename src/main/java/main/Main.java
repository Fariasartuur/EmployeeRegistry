package main;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Registration> registrations = new ArrayList<Registration>();

		System.out.println("How many registrations do you want to make?");
		int quantity = sc.nextInt();

		for (int i = 0; i < quantity; i++) {
			registrations.add(new Registration());
		}

		for (Registration registration : registrations) {
			registration.showInformation();
			System.out.println("");
		}
		
		ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.writeToExcel(registrations, "registrations.xlsx");
        
		sc.close();
	}

}