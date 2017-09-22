/***********************************************
 * Purpose :This program crrates new Inventory Object.​
 *           
 * @author  Sujit Chincholkar
 * @version 1.0
 * @since   24/08/2017          
 ***********************************************/
package com.bridgelabz.programs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.utility.Utility;

public class InventoryFactory {
	/**This method accecpt the inventory for specified items and for given properties
	 * @param items
	 * @param property
	 * @return
	 */
	public static JSONObject createInventory(String items[],String property[]){
		JSONObject inventoryObj=new JSONObject();
		
		for(int i=0;i<items.length;i++){
			JSONArray inventory=new JSONArray();
			System.out.println("How many types of "+items[i]+" you want to add in inventory");
			Scanner scanner=new Scanner(System.in);
			int types=scanner.nextInt();
			while(types>0){
			JSONObject item=new JSONObject();
			System.out.println("Enter data for "+items[i]+" .....");
		
			for(int j=0;j<property.length;j++){
				System.out.println("Enter "+property[j]);
				String data =scanner.next();
				item.put(property[j],data);
			}
			inventory.add(item);
			types--;
			}
			
			inventoryObj.put(items[i],inventory);
			
		}
		return inventoryObj;
	}
	/**This method reads json from file calculate total value of items.
	 * @param file -File name with absolute path.
	 */
	public void calculateInventory(String file){
		JSONObject inventory=Utility.readJSONFile(file);
		Set keyList =  inventory.keySet();
		String[] kesyArray = (String[]) keyList.toArray(new String[keyList.size()]);
		for(int i=0;i<kesyArray.length;i++){
			JSONArray array=(JSONArray) inventory.get(kesyArray[i]);
			for(int j=0;j<array.size();j++){
				JSONObject object=(JSONObject) array.get(j);
				Double total=Double.parseDouble(String.valueOf((object.get("weight"))))*
						Double.parseDouble(String.valueOf((object.get("price"))));
				System.out.println("Total value of "+kesyArray[i]+" of type "+object.get("name")
						+" "+total);
			}
		}
		
	}
	
}
