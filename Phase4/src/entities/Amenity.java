package entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Amenity extends Product{

	private double price;
	private String name;
	private boolean discount = false;
	
	public Amenity(String productCode, String productType, String name, double cost) {
		super(productCode, productType);
		this.price = cost;
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setCost(double cost) {
		this.price = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*
 * Calculates sub total for items plus a service fee to check if customer is low income
 */
	public double calculateSubtotal(Customer customer, LocalDate date) {
		return price * quantity;
	}
/*
 * returns tax based on income
 */
	public double calculateTax(Customer customer, LocalDate date) {
		if(customer instanceof LowIncome) {
			return 0;
		}
		else {
			return calculateSubtotal(customer, date) * 0.04;
		}
	}

/*
 * Returns total cost
 */
	public double calculateTotalCost(Customer customer, LocalDate date) {
		return calculateSubtotal(customer, date) + calculateTax(customer, date);
	}
}
