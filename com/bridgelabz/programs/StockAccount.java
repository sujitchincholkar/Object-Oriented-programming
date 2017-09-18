package com.bridgelabz.programs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.bridgelabz.utility.Utility;

public class StockAccount {
	Scanner scanner =new Scanner(System.in);
	LinkedList<CompanyShares> companylist =new LinkedList<>();
	Stack<String> transactionStack=new Stack<>();
	Queue<String> dateTimeQueue=new Queue<>();
	public StockAccount(String file){
		String data[]=Utility.readFile(file);
		for(int i=0;i<data.length;i++){
			String symbol=data[i++];
			int noOfshares=Integer.parseInt(data[i++]);
			double  sharePrice=Double.parseDouble(data[i++]);
			String dateTime=data[i];
			CompanyShares shares=new CompanyShares(symbol,noOfshares,sharePrice,dateTime);
			companylist.add(shares);
		}
	}
	public double valueOf(){
		double valueOfAccount=0.0;
		for(int i=0;i<companylist.size();i++){
			valueOfAccount=valueOfAccount+companylist.get(i).getValue();
		}
		return valueOfAccount;
	}
	public void buy(int amount,String symbol){
		
		System.out.println("Enter price per share for "+symbol);
		double price=scanner.nextDouble();
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy-hh:mm");
		String dateTime=format.format(date);
		CompanyShares share=new CompanyShares(symbol,amount,price,dateTime);
		transactionStack.push("purchased");
		dateTimeQueue.enqueue(dateTime);
		companylist.add(share);
	}
	public void sell(int amount,String symbol){
		int index=0;
		for(index=0;index<companylist.size();index++){
			if(companylist.get(index).getSymbol().equals(symbol)){
				break;
			}
		}
		int numberOfShares=companylist.get(index).getNumberOfShares()-amount;
		if(numberOfShares>=0){
			companylist.get(index).setNumberOfShares(numberOfShares);
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy-hh:mm");
			String dateTime=format.format(date);
			companylist.get(index).setDateTime(dateTime);
			dateTimeQueue.enqueue(dateTime);
			transactionStack.push("Sold");
		}else{
			System.out.println("number of shares to sell is greater than actual shares");
		}
	}
	public void save(String file){
		ArrayList<String> list=new ArrayList<>();
		for(int i=0;i<companylist.size();i++){
			list.add(companylist.get(i).getSymbol());
			list.add(String.valueOf(companylist.get(i).getNumberOfShares()));
			list.add(String.valueOf(companylist.get(i).getPrice()));
			list.add(companylist.get(i).getDateTime());
		}
		String data[]= list.toArray(new String[list.size()]);
		Utility.writeFile(data, file);
	}
	public void printReport(){
		for(int index=0;index<companylist.size();index++){
			System.out.println("Stock symbol ="+companylist.get(index).getSymbol());
			System.out.println("Number of shares ="+companylist.get(index).getNumberOfShares());
			System.out.println("Last transaction date and time="+companylist.get(index).getDateTime());
			System.out.println("Share price ="+companylist.get(index).getPrice());
			System.out.println("Total value ="+companylist.get(index).getValue());
			System.out.println("========================");
		}
	}
	public static void main(String args[]){
		String file="/home/bridgeit/Documents/account";
		
		char ans='y';
		while(ans=='y'||ans=='Y'){
			StockAccount account=new StockAccount(file);
		System.out.println("Enter your operatons \n1.Buy \n2.Sell \n3.Print report ");
		int choice=account.scanner.nextInt();
		switch(choice){
			case 1:
				System.out.println("Enter stock symbol and number of stock");
				String symbol=account.scanner.next();
				int numberOfStock=account.scanner.nextInt();
				account.buy(numberOfStock, symbol);
				account.save(file);
				System.out.println("After buying");
				account.printReport();
			break;
			case 2:
				System.out.println("Enter stock symbol and number of shares to sell");
				String symbol1=account.scanner.next();
				int amount=account.scanner.nextInt();
				account.sell(amount, symbol1);
				account.save(file);
				System.out.println("After selling");
				account.printReport();
			break;
			case 3:
				account.printReport();
			break;
			 default :
				 System.out.println("Invalid choice");
		}
		System.out.println("Do you want to continue [y/n]");
		ans=account.scanner.next().charAt(0);
		}
	}
}
