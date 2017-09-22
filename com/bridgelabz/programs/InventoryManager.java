/***********************************************
 * Purpose :This program Create​ ​ InventoryManager​ ​ to​ ​ manage​ ​ the
 * 			Inventory.​
 *           
 * @author  Sujit Chincholkar
 * @version 1.0
 * @since   24/08/2017          
 ***********************************************/
package com.bridgelabz.programs;

import java.util.Scanner;

import org.json.simple.JSONObject;

import com.bridgelabz.utility.Utility;

public class InventoryManager {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		InventoryFactory factory=new InventoryFactory();
		JSONObject inventory=new JSONObject(); 
		String file="/home/bridgeit/Documents/inventory";
		String items[];
		String properties[]={"name","weight","price"};
		System.out.println("Enter task you want to perform\n1.Create Inventory "
				+ "\n2.Read Inventory and calculate cost");
		int choice=scanner.nextInt();
		if(choice==1){
			System.out.println("How many items are there to add in Inventory?");
			int noOfItems=scanner.nextInt();
			items=new String[noOfItems];
			System.out.println("Enter item names");
			for(int i=0;i<noOfItems;i++){
				items[i]=scanner.next();
			}
			inventory=factory.createInventory(items, properties);
			Utility.writeJSONFile(file, inventory);
		}else{
			
			factory.calculateInventory(file);
		}
	
	}

}
