package driver;

import java.util.ArrayList;

import com.ceg.ext.InvoiceData;

import entities.*;
import reader.DatabaseReaderFile;
import reader.FlatFileReader;

public class InvoiceReport {

	public static void main(String[] args) {

		DatabaseReaderFile db = new DatabaseReaderFile();
		db.ConnectionToJdbc();
		
		//to print all methods 
		FlatFileReader fr = new FlatFileReader();
		ArrayList<Person> person = fr.readPerson();
		ArrayList<Customer> customers = fr.readCustomers();
		ArrayList<Product> products = fr.readProduct();
		InvoiceList list = db.readInvoices();
		
		//to get the summary and invoices
		list.getStart().getInvoice().PrintSummary(list);
		list.getStart().getInvoice().PrintInvoices(list);


		db.closeConnection();
	}
}