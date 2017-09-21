package com.bridgelabz.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

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
	public static void displayJson(JSONObject object){
		Set keyList =  object.keySet();
		String[] keys = (String[]) keyList.toArray(new String[keyList.size()]);
		for(int i=0;i<keys.length;i++){
			System.out.println(keys[i]+"="+object.get(keys[i]));
		}
		System.out.println("================");
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
	/**This method writes Json object in specified file
	 * @param file       -file to write json object
	 * @param jsonObject -Json object to write in file	
	 */
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
	/**This method writes Json object in specified file
	 * @param file       -file to write json object
	 * @param jsonArray -Json object to write in file	
	 */
	public static void writeJSONFile(String  file,JSONArray jsonArray){
		FileWriter writer;
		try {
			writer = new FileWriter(file, false);
			PrintWriter out = new PrintWriter(writer);
			out.write(jsonArray.toJSONString());
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
	/**This method reads specified file to check json object
	 * @param doctorFile -
	 */
	public static void searchDoctor(String doctorFile) {
		String searchBy[]={"Name","Id","Specilization","Availability"};
		System.out.println("Search doctor by:\n1.Name\n2.Id\n3.Specilization\n4.Availability");
		int choice =scanner.nextInt();
		System.out.println("Enter "+searchBy[choice-1]+" you want");
		String valueToSearch=scanner.next();
		JSONArray doctorList=readJSONArray(doctorFile);
		int presentAtIndex=isPresent(doctorList,searchBy[choice-1],valueToSearch);
		if((presentAtIndex)>=0){
			JSONObject doctor=(JSONObject) doctorList.get(presentAtIndex);
			displayJson(doctor);
		}else{
			System.out.println("Doctor with "+searchBy[choice-1]+"="+valueToSearch+
					" is not present");
		}
		
		
	}
	public static int isPresent(JSONArray doctorList, String key,
			String valueToSearch) {
		int presentAt=-1;
		for(int index=0;index<doctorList.size();index++){
			JSONObject doctor=(JSONObject) doctorList.get(index);
			if(doctor.get(key).equals(valueToSearch)){
				presentAt=index;
				break;
			}
		}
		return presentAt;
	}
	public static void searchPatient(String patientFile) {
		String searchBy[]={"Name","Id","Specilization","Availability"};
		System.out.println("Search doctor by:\n1.Name\n2.Id\n3.Specilization\n4.Availability");
		int choice =scanner.nextInt();
		System.out.println("Enter "+searchBy[choice-1]+" you want");
		String valueToSearch=scanner.next();
		JSONArray patientList=readJSONArray(patientFile);
		int presentAtIndex=isPresent(patientList,searchBy[choice-1],valueToSearch);
		if((presentAtIndex)>=0){
			JSONObject doctor=(JSONObject) patientList.get(presentAtIndex);
			displayJson(doctor);
		}else{
			System.out.println("Patient with "+searchBy[choice-1]+"="+valueToSearch+
					" is not found");
		}
		
	}
	public static void appoint(String doctorFile, String patientFile) {
		System.out.println("With whom you want to take appointment");
		String name=scanner.next();
		JSONArray doctorList=readJSONArray(doctorFile);
		JSONObject doctor;
		int index=isPresent(doctorList,"Name",name);
		if(index>=0){
			doctor=(JSONObject) doctorList.get(index);
			if(doctor.get("Name").equals(name)){
				if(Integer.parseInt(doctor.get("Appointments")+"")<5){
					JSONArray patientList=(JSONArray) doctor.get("Patients");
					System.out.println(doctor.toJSONString());
					doctor.remove("Patients");
					getPatientDetials(patientList);
					doctor.put("Patients",patientList);
					doctor.put("Appointments",Integer.parseInt((doctor.get("Appointments"))+"")+1);
					writeJSONFile(doctorFile,doctorList);
					writeJSONFile(patientFile,patientList);
				}else {
					System.out.println("Sorry Dr."+name+" is not available");
				}
			}
		}else{
			System.out.println("You might have enter wrong name try again ");
		}
		
	}
	private static JSONArray getPatientDetials(JSONArray patientList) {
		JSONObject patient=new JSONObject();
		String keys[]={"Name","Id","Mobile_No","Age"};
		for(int index=0;index<keys.length;index++){
			System.out.println("Enter "+keys[index]);
			String value=scanner.next();
			patient.put(keys[index], value);
		}
		patientList.add(patient);
		return patientList;
	}
	public static void enterDoctor(String doctorFile) {
		JSONObject doctor=new JSONObject();
		String keys[]={"Name","Id","Specilization","Availability"};
		for(int index=0;index<keys.length;index++){
			System.out.println("Enter "+keys[index]);
			String value=scanner.next();
			doctor.put(keys[index], value);
		}
		doctor.put("Appointments",0 );
		
		JSONArray patientList=new JSONArray();
		doctor.put("Patients", patientList);
		JSONArray doctorsList=readJSONArray(doctorFile);
		doctorsList.add(doctor);
		writeJSONFile(doctorFile,doctorsList);
	}
	
	
}
