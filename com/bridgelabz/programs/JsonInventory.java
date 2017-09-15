package com.bridgelabz.programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.utility.Utility;



public class JsonInventory {
	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException{
		Scanner scanner=new Scanner(System.in);
		JSONArray inventory=new JSONArray();
		JSONObject inventoryObj=new JSONObject();
		
		System.out.println("What do you want to do \n1.Write Inventory\n2.Read from file");
		int choice=scanner.nextInt();
		if(choice==1){
		inventory=Utility.getInventory();
		try{
			FileWriter writer = new FileWriter("/home/bridgeit/Documents/JsonDoc.json", false);
			PrintWriter out = new PrintWriter(writer);
			out.write(inventory.toJSONString());
			System.out.println(inventory.toJSONString());
			out.close();
			writer.close();
		}catch(Exception e){
			System.out.println(e);
		}
		}else{
			JSONParser jsonParser=new JSONParser();
			FileReader reader=new FileReader("/home/bridgeit/Documents/JsonDoc.json");
			 inventory=(JSONArray)jsonParser.parse(reader);
			Utility.calculateValue(inventory);
		}
	}
}
