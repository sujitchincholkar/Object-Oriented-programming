package com.bridgelabz.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utility {
	static Scanner scanner=new Scanner(System.in);
	public static JSONObject getInventory(String items[]){
		
		JSONObject inventoryObj=new JSONObject();
		
		for(int i=0;i<items.length;i++){
			JSONArray inventory=new JSONArray();
			System.out.println("How many types of "+items[i]+" you want to add in inventory");
			int types=scanner.nextInt();
			while(types>0){
			JSONObject item=new JSONObject();
			System.out.println("Enter data for "+items[i]+" .....");
			System.out.println("Name ,weight and price per KG");
			String name=scanner.next();
			double weight=scanner.nextDouble();
			double price=scanner.nextDouble();
			item.put("Name",name);
			item.put("weight",weight);
			item.put("price",price);
			inventory.add(item);
			types--;
			}
			
			inventoryObj.put(items[i],inventory);
			
		}
		return inventoryObj;
	}
	public static void calculateValue(JSONObject inventoryObj,String items[]) {
		for(int j=0;j<items.length;j++){
			JSONArray inventory=(JSONArray) inventoryObj.get(items[j]);
			for(int i=0;i<inventory.size();i++){
				JSONObject item=(JSONObject) inventory.get(i);
				Double totalValue=(Double)item.get("weight")*(Double)item.get("price");
				System.out.println("Total value for "+items[i]+" of type "+item.get("Name")+
					" is "+totalValue);
			}
		}
	}
	public static JSONObject readJSONFile(String file){
		JSONParser jsonParser=new JSONParser();
		FileReader reader;
		JSONObject jsonObject=new JSONObject();
		try {
			reader = new FileReader(file);
			jsonObject=(JSONObject)jsonParser.parse(reader);
			reader.close();
		} catch ( IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
		
	}
	public static JSONArray readJSONArray(String file){
		JSONParser jsonParser=new JSONParser();
		FileReader reader;
		JSONArray jsonObject=new JSONArray();
		try {
			reader = new FileReader(file);
			jsonObject=(JSONArray)jsonParser.parse(reader);
			reader.close();
		} catch ( IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
		
	}
	public static void writeJSONFile(String  file,JSONObject jsonObject){
		FileWriter writer;
		try {
			writer = new FileWriter(file, false);
			PrintWriter out = new PrintWriter(writer);
			out.write(jsonObject.toJSONString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static void writeJSONFile(String  file,JSONArray jsonObject){
		FileWriter writer;
		try {
			writer = new FileWriter(file, false);
			PrintWriter out = new PrintWriter(writer);
			out.write(jsonObject.toJSONString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**This method read file into string array
	 * @param filePath
	 * @return -Returns string array of words from file
	 */ 
	public static String[] readFile(String filePath) {
		String words[] = {};
		ArrayList<String> lines = new ArrayList<String>();
		String line, temp[] = {};
		BufferedReader bufferedReader;
		FileReader file;
		int index = 0;
		try {
			file = new FileReader(filePath);
			bufferedReader = new BufferedReader(file);
			while ((line = bufferedReader.readLine()) != null) {
				temp = line.split(",|\\s");
				for (int i = 0; i < temp.length; i++) {
					lines.add(temp[i]);

				}
			}
			words = lines.toArray(new String[lines.size()]);
			bufferedReader.close();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return words;
	}
	
	/**This method write given string on given filePath
	 * @param word     -Array of String to write
	 * @param filePath -File path with file name
	 */
	public static void writeFile(String word[], String filePath) {

		try {
			FileWriter writer = new FileWriter(filePath, false);
			PrintWriter out = new PrintWriter(writer);
			for (int i = 0; i < word.length; i++) {
				out.write(word[i] + " ");
			}
			out.close();
			writer.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
