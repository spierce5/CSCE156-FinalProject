package entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {
	private String invoiceCode;
	private Customer customer;
	private Person landlord;
	private LocalDate date;
	private ArrayList<Product> products;
	private double fees;
	private boolean apartmentAssociation = false;
	private boolean leaseAssociation = false;
	private boolean amenityAssociation = false;
	

	
	public Invoice(String invoiceCode, Customer customer, Person landlord, LocalDate date, ArrayList<Product> products) {
		super();
		this.invoiceCode = invoiceCode;
		this.customer = customer;
		this.landlord = landlord;
		this.date = date;
		this.products = products;
		for(Product p: products) {
			if(p instanceof LeaseAgreements) {
				this.apartmentAssociation = true;
				this.leaseAssociation = true;
			}
			else if(p instanceof SaleAgreements) {
				this.apartmentAssociation = true;
			}
			else if(p instanceof Amenity) {
				this.amenityAssociation = true;
			}
		}
	}
/*
 * Getter and Setter Methods 
 */
	
	
	public String getInvoiceCode() {
		return invoiceCode;
	}
	
	public boolean isAmenityAssociation() {
		return amenityAssociation;
	}


	public void setAmenityAssociation(boolean amenityAssociation) {
		this.amenityAssociation = amenityAssociation;
	}


	public boolean isApartmentAssociation() {
		return apartmentAssociation;
	}

	public void setApartmentAssociation(boolean apartmentAssociation) {
		this.apartmentAssociation = apartmentAssociation;
	}

	public boolean isLeaseAssociation() {
		return leaseAssociation;
	}

	public void setLeaseAssociation(boolean leaseAssociation) {
		this.leaseAssociation = leaseAssociation;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Person getLandlord() {
		return landlord;
	}
	
	public void setLandlord(Person landlord) {
		this.landlord = landlord;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public double getFees() {
		return fees;
	}
	public double setFees(double fees) {	
		return fees;
	}
		
/*
 * Returns the sum of subtotals from all products on invoice
 */
	public double getSubtotal(ArrayList<Product> products) {
		double subtotal = 0;
		for(Product p: products) {
			subtotal += p.calculateSubtotal(customer, date);
		}
		return subtotal;
	}
	
	
/*
 * Calculates tax based on income level	
 */
	public double getTotalTax(ArrayList<Product> products) {
			double totalTax = 0;
			for(Product p: products) {
				totalTax += p.calculateTax(customer, date);
			}
			return totalTax;
	}
/*
* Calculates total after tax and before discount
*/
	public double totalAfterTax(ArrayList<Product> products, Customer customer) {
		double Total = 0;
		for(Product p: products) {
			if(p instanceof Amenity && leaseAssociation == true) {
				Total += (p.calculateTotalCost(customer, date) * 0.95);
			}
			else {
				Total += p.calculateTotalCost(customer, date);
			}
		}
		return Total;
	}
/*
 * Calculates total after taxes and discount
 */
	public double totalAfterDiscount(ArrayList<Product> products, Customer customer) {
		double Total = 0;
		Total = totalAfterTax(products, customer);
		Total += getDiscount(products, customer.isLowIncome(), apartmentAssociation);
		if(customer.isLowIncome()) {
			Total += 50.75;
		}
		return Total;
	}
/*
 * Calculates total discount
 */
	public double getDiscount(ArrayList<Product> products, boolean incomeDiscount, boolean housingDiscount) {
		double discount = 0;
		if(incomeDiscount) {
			discount = totalAfterTax(products, customer) * 0.1;
			if(housingDiscount) {
				discount += 1000;
			}
		}
		return discount * -1.0;
	}
	
	
/*
 * Takes an array of invoices and prints a summary 
 */
	public void PrintSummary(ArrayList<Invoice> list) {
		double subTotal1 = 0;
		double subTotal2 = 0;
		double subTotal3 = 0;
		double subTotal4 = 0;
		double subTotal5 = 0;
		
		System.out.println(String.format("%-10s %-30s %-20s %-16s %-12s %-12s %-12s %-12s", "Invoice",
				"Customer", "Realtor", "Subtotal", "Fees", "Tax", "Discount", "Total"));
		for(Invoice Inv: list) {
			if(Inv.getCustomer().isLowIncome()) 		{	//Printable version of customer type
				fees = 50.75;
				for(Product p: Inv.getProducts()) {
					if(p instanceof SaleAgreements || p instanceof LeaseAgreements) {
						//apartmentAssociation = true;
						Inv.leaseAssociation = true;
					}
				}
			}
			else {
				fees = 0.0;
			}
			
			String discount = String.format("%.2f", Inv.getDiscount(Inv.getProducts(), Inv.getCustomer().isLowIncome(), apartmentAssociation));
			System.out.println(String.format("%-10s %-30s %-20s $%-16.2f %-12s $%-12.2f %-12s $%-12.2f", Inv.getInvoiceCode(),
					Inv.getCustomer().getName(), Inv.getLandlord().getFullName(), Inv.getSubtotal(Inv.getProducts()),
					fees, Inv.getTotalTax(Inv.getProducts()), discount , Inv.totalAfterDiscount(Inv.getProducts(),Inv.getCustomer())));
			
			subTotal1 += Inv.getSubtotal(Inv.getProducts());
			subTotal2 += fees;
			subTotal3 += Inv.getTotalTax(Inv.getProducts());
			subTotal4 += Inv.getDiscount(Inv.getProducts(), Inv.getCustomer().isLowIncome(), apartmentAssociation);
			subTotal5 += Inv.totalAfterDiscount(Inv.getProducts(),Inv.getCustomer());
			
		}
		System.out.println("==============================================================================================================================");
		
		System.out.printf("Total %52s %.2f %8s %.2f %6s %.2f %5s %.2f %2s %.2f\n", "$", subTotal1, "$",subTotal2, "$",subTotal3, "$",subTotal4, "$",subTotal5);
	}
		
/*
 * Takes an array of invoices and prints out the individual invoices
 */
	public void PrintInvoices(ArrayList<Invoice> list) {
		System.out.println("Individual Invoice Details Report");
		System.out.println("==================================================");
		for(Invoice Inv: list) {
			String customerType = null;
			apartmentAssociation = false;
			if(Inv.getCustomer().isLowIncome()) {			//Printable version of customer type
				customerType = "[Low-Income]";
			}
			else {
				customerType = "[General]";
			}
			System.out.println("Invoice" + Inv.getInvoiceCode());
			System.out.println("========================");					//General Information
			System.out.println("Realtor: " + Inv.getLandlord().getFullName());
			System.out.println("Customer Info:\n " + Inv.getCustomer().getName() + "\n " +
			customerType + "\n " + Inv.getCustomer().getContact().getFullName() + "\n " + 
			Inv.getCustomer().getAddress().getStreet() + "\n " + Inv.getCustomer().getAddress().getCity() +
			", " + Inv.getCustomer().getAddress().getState() + ", " + Inv.getCustomer().getAddress().getZipCode() + 
			" " +  Inv.getCustomer().getAddress().getCountry());
			System.out.println("-------------------------------------------");		//Itemized Report
			System.out.println(String.format("%-12s %-60s %-15s %-15s %-15s", "Code", "Item", "Subtotal", "Tax", "Total"));
			for(Product p: Inv.getProducts()) {
				StringBuilder item = new StringBuilder();
				StringBuilder item2 = new StringBuilder();
				switch(p.getProductType()) {
				case "S":
					SaleAgreements sa = (SaleAgreements) p;
					if(Inv.getCustomer().isLowIncome()) {				//Applies housing credit due to SaleAgreement
						apartmentAssociation = true;
					}
					String interest = String.format("%.2f", sa.calculateInterest(Inv.getDate()));
					item.append("Sale Agreement @ " + sa.getAddress().getStreet());
					item2.append(sa.getQuantity() + " Units @ $" + sa.getMonthlyPayment() + " monthly, $" +  interest + " interest/unit");
					break;
				case "L":
					LeaseAgreements la = (LeaseAgreements) p;
					if(Inv.getCustomer().isLowIncome()) {				//Applies housing credit due to LeaseAgreement
						apartmentAssociation = true;
					}
					String price = String.format("%.2f", la.getPricePerApartment());
					item.append("Lease Agreement @ " + la.getAddress().getStreet());
					item2.append(la.getStartDate() + " (" + la.getQuantity() + " Units @ $" + price + "/Unit)");
					break;
				case "P":
					ParkingPass pk = (ParkingPass) p;
					String parking = String.format("%.2f", pk.getParkingFee());
					if(pk.getApartmentCode() == null){ 
						item.append("Parking Pass " + " (" + pk.getQuantity() + " Units @ $" + parking + ")");
					}
					else {
						item.append("Parking Pass " + pk.getApartmentCode().getProductCode() + " (" + pk.getQuantity() + " Units @ $"+ parking + " with " +
							(int) (pk.calculateDiscount(customer, date) / 55) + " free)");
					}
					break;
				case "A":
					Amenity am = (Amenity) p;
					String unitPrice = String.format("%.2f", am.getPrice());
					if(Inv.amenityAssociation && Inv.leaseAssociation) {
						item.append(am.getName() + " (" + am.getQuantity() + " Units @ $" + unitPrice + " /Unit with 5% off)");
					}
					else {
						item.append(am.getName() + " (" + am.getQuantity() + " Units @ $" + unitPrice + " /Unit)");
					}
					break;
				}
				double subTotal;
				double tax;
				double total;
				if(Inv.leaseAssociation && p.getProductType().equals("A")) {
					subTotal = (p.calculateSubtotal(Inv.getCustomer(), Inv.getDate()) * 0.95);
					if(Inv.getCustomer().isLowIncome()) {
						tax = 0;
					}
					else {
						tax = (subTotal * 0.04);
					}
				total = subTotal + tax;
				}
				else {
					subTotal = p.calculateSubtotal(Inv.getCustomer(), Inv.getDate());
					tax = p.calculateTax(Inv.getCustomer(), Inv.getDate());
					total = p.calculateTotalCost(Inv.getCustomer(), Inv.getDate());
				}
				System.out.println(String.format("%-12s %-60s %-15.2f %-15.2f %-15.2f", p.getProductCode(),
						item, subTotal, tax, total));
				if(item2 != null);{
					System.out.println(String.format("%-12s %-60s", " ", item2));
				}
			}
			System.out.println(String.format("%-72s %-45s", " ", "======================================"));
			System.out.println(String.format("%-72s %-15.2f %-15.2f %-15.2f", "SUBTOTALS", Inv.getSubtotal(Inv.getProducts()), Inv.getTotalTax(Inv.getProducts()), Inv.totalAfterTax(Inv.getProducts(), Inv.getCustomer())));
			if(apartmentAssociation) {
				System.out.println(String.format("%-105s %-15.2f", "DISCOUNT (10% LOW INCOME + $1000 HOUSING CREDIT)", Inv.getDiscount(Inv.getProducts(), Inv.getCustomer().isLowIncome(), apartmentAssociation)));
			}
			else if(Inv.getCustomer().isLowIncome()) {
				System.out.println(String.format("%-105s %-15.2f", "DISCOUNT (10% LOW INCOME)", Inv.getDiscount(Inv.getProducts(), true, apartmentAssociation)));
			}
			if(Inv.getCustomer().isLowIncome()) {
				System.out.println(String.format("%-105s %-15.2f", "Additional Fee (Low-Income)", 50.75));
			}
			System.out.println(String.format("%-105s %-15.2f", "Total", Inv.totalAfterDiscount(Inv.getProducts(), Inv.getCustomer())));
		}	
	}
}