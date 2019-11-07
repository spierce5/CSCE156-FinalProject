 package driver;


import java.util.ArrayList;


import entities.*;
import reader.DatabaseReaderFile;
import reader.FlatFileReader;


public class InvoiceReport{

	public static void main(String[] args){

		DatabaseReaderFile db = new DatabaseReaderFile();
		db.ConnectionToJdbc();
		//to print person method
		ArrayList<Person> person = db.readPersons();
		System.out.println(person);
		ArrayList<Customer> cust = db.readCustomers();
		System.out.println(cust);

		

//		FlatFileReader fr = new FlatFileReader();
//		`ArrayList<Person> person = fr.readPerson();
//		ArrayList<Customer> customers = fr.readCustomers();
//		ArrayList<Product> products = fr.readProduct();
//		ArrayList<Invoice> invoices = fr.readInvoice();
//		
//		invoices.get(0).PrintSummary(invoices);
//		invoices.get(0).PrintInvoices(invoices); 
		
		
		db.closeConnection();
	}
}