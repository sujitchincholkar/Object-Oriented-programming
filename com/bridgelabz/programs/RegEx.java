/***********************************************
 * Purpose :​
 *           
 * @author  Sujit Chincholkar
 * @version 1.0
 * @since   24/08/2017          
 ***********************************************/
package com.bridgelabz.programs;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		String date2=format.format(date);
		System.out.println("Enter your name ");
		String name=sc.nextLine();
		System.out.println("Enter your full name ");
		String fullName=sc.nextLine();
		System.out.println("Enter your Contact number");
		String contact=sc.nextLine();
		reg.replaceString("<<name>>", name);
		reg.replaceString("<<full​ ​ name>>​", fullName);
		reg.replaceString("xxxxxxxxxx",contact);
		reg.replaceString("01/01/2016",date2);
		System.out.println(message);
		sc.close();
	}
	public void replaceString(String toReplace,String replaceWith){
		Pattern pattern=Pattern.compile(toReplace);
		Matcher matcher=pattern.matcher(message);
		message=matcher.replaceAll(replaceWith);
	}
}
