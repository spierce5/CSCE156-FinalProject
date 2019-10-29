package reader;

import java.io.File;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import entities.*;
import java.util.LinkedHashMap;



public class FlatFileReader {
	LinkedHashMap<String,Person> personMap = new LinkedHashMap<String,Person>();
	LinkedHashMap<String,Customer> customerMap = new LinkedHashMap<String,Customer>();
	LinkedHashMap<String,Product> productMap = new LinkedHashMap<String,Product>();

/*
 *  Returns an array of customers created by reading a .dat file and populates a LinkedHashMap
 */
	public ArrayList<Customer> readCustomers(){
		String fileName = "data/Customers.dat";
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<Customer> customers = new ArrayList<Customer>();

		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				data.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		data.remove(0);								//Removes first line of file
		for (String item: data) {
			String token[] = item.split(";");
			String address = token[4];
			Address add = new Address(address);
			
			switch(token[1]) {
			case "G":
				StandardCustomer strdCustomer = new StandardCustomer(token[0], token[1], personMap.get(token[2]), token[3], add);
				customerMap.put(token[0], strdCustomer);									//Map: Name-->Customer
				customers.add(strdCustomer);
				break;
			case "L":
				LowIncome lowIncCustomer = new LowIncome(token[0], token[1], personMap.get(token[2]), token[3], add);
				customerMap.put(token[0], lowIncCustomer);									//Map: Name-->Customer
				customers.add(lowIncCustomer);
				break;
			}
		}

		sc.close();
		return customers;
	}

/*
 *  Returns an array of persons created by reading a .dat file and populates a LinkedHashMap
 */
	public ArrayList<Person> readPerson(){
		String fileName = "data/Persons.dat";
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<Person> personList = new ArrayList<Person>();


		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				data.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		data.remove(0);								//Removes first line of file
		for(String item: data){
			String token[] = item.split(";");
			String name = token[1];
			String tokenName[] = name.split(",");		//Separate name
			Address address = new Address(token[2]);		//Creates Address Object for Person Parameter
			Person person = new Person(token[0], tokenName[0], tokenName[1], address);
			personList.add(person);
			personMap.put(token[0], person);						//Fills personMap: p-code-->Person

			if(token.length == 4){						//Checks for email
				String email = token[3];
				String tokenEmail[] = email.split(",");
				ArrayList<String> emailList = new ArrayList<String>();
				for(int i=0; i < tokenEmail.length; i++){		//Adds all listed emails to array
					emailList.add(tokenEmail[i]);
				}
				person.setEmail(emailList);
			}
			else {
				ArrayList<String> emailList = new ArrayList<String>();
				emailList.add("None");
				person.setEmail(emailList);
			}
		}

		sc.close();
		return personList;
	}

/*
 *  Returns an array of Products created by reading a .dat file. Products are sorted by type.
 */
	public ArrayList<Product> readProduct(){
		String fileName = "data/Products.dat";
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<Product> products = new ArrayList<Product>();

		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				data.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		data.remove(0);	
		for(String item: data) {
			String token[] = item.split(";");
			String productType = token[1];

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			switch(productType){
			case "L":					//Product Type: Lease-Agreement
				LocalDate leaseEndDate = LocalDate.parse(token[3], dateFormatter);
				LocalDate leaseStartDate = LocalDate.parse(token[2], dateFormatter);

				String la = token[4];
				Address leaseAddress = new Address(la);
				double d = Double.parseDouble(token[6]);
				LeaseAgreements a = new LeaseAgreements(token[0], token[1], leaseStartDate, leaseEndDate, leaseAddress, customerMap.get(token[5]), d);
				productMap.put(token[0], a);
				products.add(a);
				break;
			case "A":					//Product Type: Amenity
				Amenity am = new Amenity(token[0], token[1], token[2], Double.parseDouble(token[3]));
				productMap.put(token[0], am);
				products.add(am);									//May need more code to check if renter
				break;
			case "S":					//Product Type: Sale-Agreement

				LocalDateTime moveInDate = LocalDateTime.parse(token[2], dateTimeFormatter);
				Address saleAddress = new Address(token[3]);
				SaleAgreements s = new SaleAgreements(token[0], token[1], moveInDate, saleAddress, Double.parseDouble(token[4]), Double.parseDouble(token[5]), Double.parseDouble(token[6]), Double.parseDouble(token[7]), Double.parseDouble(token[8]));
				productMap.put(token[0], s);
				products.add(s);
				break;
			case "P":					//ProductType: Parking Pass
				ParkingPass ps = new ParkingPass(token[0], token[1], Double.parseDouble(token[2]));
				products.add(ps);								
				productMap.put(token[0], ps);
				break;
			default:
				System.err.println("Product Type Unknown");
			}




		}
		return products;
	}
	
/*
 *  Reads an invoice and stores data
 */
	public ArrayList<Invoice> readInvoice(){
		String fileName = "data/Invoices.dat";
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				data.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data.remove(0);	
		for(String item: data) {
			String token[] = item.split(";");
			LocalDate date = LocalDate.parse(token[3], dTF);
			
			ArrayList<Product> products = new ArrayList<Product>();		//populate product list
			String productToken[] = token[4].split(",");
			for(String pT: productToken) {
				String divpT[] = pT.split(":");
				productMap.get(divpT[0]).setQuantity(Integer.parseInt(divpT[1]));
				if(divpT.length == 3) {
					ParkingPass parking = (ParkingPass) productMap.get(divpT[0]);
					parking.setApartmentCode(productMap.get(divpT[2]));
					products.add(parking);
				}
				else {
					products.add(productMap.get(divpT[0]));
				}
			}
			
			Invoice inV = new Invoice(token[0], customerMap.get(token[1]), personMap.get(token[2]), date, products);
			invoices.add(inV);
		}
		return invoices;
		
	
	}	
}
