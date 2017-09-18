package com.bridgelabz.programs;

import java.util.Scanner;

public class StockReport{
	String name;
	int  noOfShares;
	int sharePrice;
	public StockReport(String name,int noOfShares,int sharePrice){
		this.name=name;
		this.noOfShares=noOfShares;
		this.sharePrice=sharePrice;
	}
	public static void main(String args[]){
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter number of stocks");
		int noOfStocks=scanner.nextInt();
		StockReport stocks[]=new StockReport[noOfStocks];
		for(int i=0;i<noOfStocks;i++){
			System.out.println("Enter Name of Stock,number of shares and price of share");
				String name=scanner.next();
				int noOfShares=scanner.nextInt();
				int sharePrice=scanner.nextInt();
				stocks[i]=new StockReport(name,noOfShares,sharePrice);
		}
		 displayReport(stocks);
	}
	public static void displayReport(StockReport stock[]){
		for(int i=0;i<stock.length;i++){
			System.out.println("Stock name ="+stock[i].name);
			System.out.println("Number of stock ="+stock[i].noOfShares);
			System.out.println("Share price ="+stock[i].sharePrice);
			System.out.println("Total price ="+(stock[i].noOfShares*stock[i].sharePrice));
			System.out.println("=======================");
		}
	}
}
