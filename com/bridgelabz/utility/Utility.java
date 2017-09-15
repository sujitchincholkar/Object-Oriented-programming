package com.bridgelabz.utility;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Utility {
	static Scanner scanner=new Scanner(System.in);
	public static JSONArray getInventory(){
		String items[]={"Rice","Pulses","Wheat"};
		JSONArray inventory=new JSONArray();
		for(int i=0;i<3;i++){
			JSONObject item=new JSONObject();
			System.out.println("Enter data for "+items[i]);
			System.out.println("Name ,weight and price per KG");
			String name=scanner.next();
			double weight=scanner.nextDouble();
			double price=scanner.nextDouble();
			item.put("Name",name);
			item.put("weight",weight);
			item.put("price",price);
			inventory.add(item);
		}
		return inventory;
	}
	public static void calculateValue(JSONArray inventory) {
		String items[]={"Rice","Pulses","Wheat"};
		for(int i=0;i<inventory.size();i++){
			JSONObject item=(JSONObject) inventory.get(i);
			Double totalValue=(Double)item.get("weight")*(Double)item.get("price");
			System.out.println("Total value for "+items[i]+" of name "+item.get("Name")+
					" is "+totalValue);
		}
	}
}
