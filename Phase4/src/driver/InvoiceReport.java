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
		
		
		ArrayList<Person> person = db.readPersons();
		ArrayList<Customer> cust = db.readCustomers();
		ArrayList<Product> products = db.readProduct();
		InvoiceList list = db.readInvoices();

		list.getStart().getInvoice().PrintSummary(list);
		list.getStart().getInvoice().PrintInvoices(list);
		
	
		db.closeConnection();
	}
}