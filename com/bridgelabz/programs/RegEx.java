package com.bridgelabz.programs;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
	static String message="Hello ​ <<name>> , ​ We​ ​ have​ ​ your​ ​ fullname​ ​ as​ ​ <<full​ ​ name>>​ ​ in​ ​ our​"
			+ " ​ system.​ ​ your​ ​ contact​ ​ number​ ​ is​ ​ 91-xxxxxxxxxx. Please,let​ ​ us​ ​ know​ "
			+ "​ in​ ​ case​ ​ of​ ​ any​ ​ clarification​ ​ Thank​ ​ you​ ​ BridgeLabz​ ​ 01/01/2016.";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		RegEx reg=new RegEx();
		
		System.out.println("Enter your name ");
		String name=sc.nextLine().trim();
		System.out.println("Enter your full name ");
		String fullName=sc.nextLine();
		System.out.println("Enter your Contact number");
		String contact=sc.nextLine();
		System.out.println("Enter todays date");
		String date=sc.next();
		reg.replaceString("<<name>>", name);
		reg.replaceString("<<full​ ​ name>>​", fullName);
		reg.replaceString("xxxxxxxxxx",contact);
		reg.replaceString("01/01/2016", date);
		System.out.println(message);
		
	}
	public void replaceString(String toReplace,String replaceWith){
		Pattern pattern=Pattern.compile(toReplace);
		Matcher matcher=pattern.matcher(message);
		message=matcher.replaceAll(replaceWith);
	}
}
