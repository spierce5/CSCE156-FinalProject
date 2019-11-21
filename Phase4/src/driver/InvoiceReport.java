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
		// to print person method
		/*
		ArrayList<Person> person = db.readPersons();
		ArrayList<Customer> cust = db.readCustomers();
		ArrayList<Product> products = db.readProduct();
		InvoiceList list = db.readInvoices();

		InvoiceData invData = new InvoiceData();
		InvoiceData.addLeaseAgreement("wvg2", "asmi", "2017-01-13", "2018-01-12", "abcd Street", "Lincoln", "NE", "36398",
				"USA", 9087, 15890);
*/
		/*
		 * FlatFileReader fr = new FlatFileReader(); ArrayList<Person> person =
		 * fr.readPerson(); ArrayList<Customer> customers = fr.readCustomers();
		 * ArrayList<Product> products = fr.readProduct(); ArrayList<Invoice> invoices =
		 * fr.readInvoice();
		 * 
		 * InvoiceList list = new InvoiceList(); for(Invoice i: invoices) { list.add(i);
		 * }
		 * 
		 * System.out.println("DATA: "); for(Invoice g: invoices) {
		 * System.out.println(g.getInvoiceCode() + "----" + g.getInvoiceTotal()); }
		 * 
		 * System.out.println("Result"); for(Invoice i: list) {
		 * System.out.println(i.getInvoiceCode() + "Total: " +
		 * i.getSubtotal(i.getProducts())); }
		 */
		// invoices.get(0).PrintSummary(invoices);
		// invoices.get(0).PrintInvoices(invoices);
//		for(Invoice i: list) {
//			System.out.println(i.getInvoiceCode());
//			System.out.println(i.getInvoiceTotal());
//		}
		//list.getStart().getInvoice().PrintSummary(list);
		//list.getStart().getInvoice().PrintInvoices(list);
		
		InvoiceData.addLeaseAgreement("jsdf", "aflak", "2017-01-01", "2018-01-01",
				"Rover", "Gotham", "WV", "09999", "Canada", 515.0, 5.0);
		db.closeConnection();
	}
}