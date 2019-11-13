package entities;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Period;

public class SaleAgreements extends Product {

	private LocalDateTime startDate;
	private Address address;
	private Double totalCost;
	private Double initialPayment;
	private Double monthlyPayment;
	private Double totalMonths;
	private Double interestRate;
	

	public SaleAgreements(String productCode, String productType, LocalDateTime dateTime, Address address,
			Double totalCost, Double initialPayment, Double monthlyPayment, Double totalMonths, Double interestRate) {
		super(productCode, productType);
		this.startDate = dateTime;
		this.address = address;
		this.totalCost = totalCost;
		this.initialPayment = initialPayment;
		this.monthlyPayment = monthlyPayment;
		this.totalMonths = totalMonths;
		this.interestRate = interestRate;
	}

	public LocalDateTime getDateTime() {
		return startDate;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.startDate = dateTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getInitialPayment() {
		return initialPayment;
	}

	public void setInitialPayment(double initialPayment) {
		this.initialPayment = initialPayment;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public double getTotalMonths() {
		return totalMonths;
	}

	public void setTotalMonths(double totalMonths) {
		this.totalMonths = totalMonths;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

/*
 * Calculates interest
 */
	public double calculateInterest(LocalDate date) {
		return (totalCost - initialPayment - amountPaid(date)) * (interestRate/100);
	}
/*
 * Returns sub total of all Sales
 */
	public double calculateSubtotal(Customer customer, LocalDate date) {
		return quantity * (monthlyPayment + (calculateInterest(date)));
	}
/*
 * Returns tax on all units
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
 * Returns total after tax
 */
	public double calculateTotalCost(Customer customer, LocalDate date) {
		return calculateSubtotal(customer, date) + calculateTax(customer, date);
	}
/*
 * Returns amount paid from start date to current date
 */
	public double amountPaid(LocalDate date){
		double amount = 0;
		LocalDate start = startDate.toLocalDate();
		Period timeBetween = Period.between(start, date);
		int monthsBetween = timeBetween.getMonths() + (timeBetween.getYears() * 12);
		amount = (monthsBetween) * monthlyPayment;
		return amount;
	}

}
