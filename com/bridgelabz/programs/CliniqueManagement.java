package com.bridgelabz.programs;

import java.util.Scanner;

import com.bridgelabz.utility.Utility;

public class CliniqueManagement {
	static Scanner scanner = new Scanner(System.in);
	private static String doctorFile = "/home/bridgeit/Documents/Doctor";
	private static String patientFile = "/home/bridgeit/Documents/Patient";

	public static void main(String args[]) {

		String wantToContinue = "yes";
		while (wantToContinue.equals("yes")) {
			System.out
					.println("What do you want to do \n1.Search doctor\n2.Search Patient"
							+ "\n3.Take Appointment \n4.Enter new Doctor");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				Utility.searchDoctor(doctorFile);
				break;
			case 2:
				Utility.searchPatient(patientFile);
				break;
			case 3:
				Utility.appoint(doctorFile, patientFile);
				break;
			case 4:
				Utility.enterDoctor(doctorFile);
				break;
			default:
				System.out.println("Invalid Choice");

			}
			System.out.println("Do you want to continue[yes/no]");
			wantToContinue = scanner.next();
		}
	}

}
