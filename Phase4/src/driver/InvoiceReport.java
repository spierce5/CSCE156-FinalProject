 package driver;


import java.util.ArrayList;


import entities.*;
import reader.FlatFileReader;


public class InvoiceReport{

	public static void main(String[] args){


		FlatFileReader fr = new FlatFileReader();
		ArrayList<Person> person = fr.readPerson();
		ArrayList<Customer> customers = fr.readCustomers();
		ArrayList<Product> products = fr.readProduct();
		ArrayList<Invoice> invoices = fr.readInvoice();
		
		invoices.get(0).PrintSummary(invoices);
		invoices.get(0).PrintInvoices(invoices); 
	
		
	/*	
		for(Invoice inV: invoices) {
			for(Product p: inV.getProducts()) {
				System.out.println(p.getProductCode() + " " +
			p.getProductType() + " " + p.getQuantity());
				switch(p.getProductType()) {
				case "P":
					ParkingPass parking = (ParkingPass) p;
					System.out.println(parking.getParkingFee());
					break;
				}
			}
		}
*/
	}
}
