package entities;

import java.time.LocalDate;
import java.time.Period;

public class LeaseAgreements extends Product {

	private LocalDate startDate;
	private LocalDate endDate;
	private Address address;
	private Customer customerName;
	private double pricePerApartment;

	public LeaseAgreements(String productCode, String productType, LocalDate startDate, LocalDate endDate,
			Address address, Customer customerName, double pricePerApartment) {
		super(productCode, productType);
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.customerName = customerName;
		this.pricePerApartment = pricePerApartment;
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

	public Customer getCustomerName() {
		return customerName;
	}

	public void setCustomerName(Customer customerName) {
		this.customerName = customerName;
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
		if(customer instanceof LowIncome) {
			return 0;
		}
		else {
			return calculateSubtotal(customer) * 0.06;
		}
	}
/*
 * This method has been overloaded
 */
	public double calculateSubtotal(Customer customer) {
		return 0;
	}
/*
 * Returns subtotal dependent on full or partial month, quantity, and fees
 */	
	public double calculateSubtotal(Customer customer, LocalDate date) {
		Period timeBetweenStart = Period.between(startDate, date);
		Period timeBetweenEnd = Period.between(endDate, date);
		double subtotal = 0;
		if(timeBetweenStart.getMonths() < 1) {
			subtotal =  pricePerApartment - (timeBetweenStart.getDays() * (pricePerApartment / date.getMonth().maxLength()));
		}
		else if(timeBetweenEnd.getMonths() < 1) {
			subtotal = pricePerApartment - (timeBetweenEnd.getDays() * (pricePerApartment / date.getMonth().maxLength()));
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