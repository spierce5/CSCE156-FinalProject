package entities;

import java.time.LocalDate;
import java.time.Period;

public class LeaseAgreements extends Product {

	private LocalDate startDate;
	private LocalDate endDate;
	private Address address;
	//private Customer customerName;
	private double pricePerApartment;
	private double deposit; 

	public LeaseAgreements(String productCode, String productType, LocalDate startDate, LocalDate endDate,
			Address address, double pricePerApartment, double deposit) {
		super(productCode, productType);
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.pricePerApartment = pricePerApartment;
		this.deposit = deposit;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getPricePerApartment() {
		return pricePerApartment;
	}

	public void setPricePerApartment(double pricePerApartment) {
		this.pricePerApartment = pricePerApartment;
	}

/*
 * calculates tax on all Lease Agreements
 */
	public double calculateTax(Customer customer, LocalDate date) {
		if(customer.isLowIncome()) {
			return 0;
		}
		else {
			return calculateSubtotal(customer, date) * 0.06;
		}
	}

/*
 * Returns subtotal dependent on full or partial month, quantity, and fees
 */	
	public double calculateSubtotal(Customer customer, LocalDate date) {
		int adjustedStart = startDate.getMonth().maxLength() - startDate.getDayOfMonth();
		int adjustedEnd = endDate.getDayOfMonth();
		
		double subtotal = 0;
		if(startDate.getYear() == (date.getYear()) && startDate.getMonth().equals(date.getMonth())) {
			subtotal =  (pricePerApartment + ((adjustedStart / startDate.getMonth().maxLength()) * pricePerApartment) + deposit) * quantity ;
		}//TO-DO:: fix algorithm
		else if(endDate.getYear() == date.getYear() && endDate.getMonth().equals(date.getMonth())) {
			subtotal = (pricePerApartment * (adjustedEnd / endDate.getMonth().maxLength()) * quantity);
		}
		else {
			subtotal = pricePerApartment;
		}
		return subtotal * quantity;
	}
	
/*
 * Returns total after taxes
 */
	public double calculateTotalCost(Customer customer, LocalDate date) {
		return calculateSubtotal(customer, date) + calculateTax(customer, date);
	}

	
	
	
	
	
}
