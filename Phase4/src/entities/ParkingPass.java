package entities;

import java.time.LocalDate;

public class ParkingPass extends Product{
	
	private double parkingFee;
	private Product apartmentCode; 
	
	public ParkingPass(String productCode, String productType, double parkingFee) {
		super(productCode, productType);
		this.parkingFee = parkingFee;
	}

	public double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}

	public Product getApartmentCode() {
		return apartmentCode;
	}

	public void setApartmentCode(Product apartmentCode) {
		this.apartmentCode = apartmentCode;
	}
/*
 * Returns subtotal for parking fee multiplied by number of passes
 */
	public double calculateSubtotal(Customer customer, LocalDate date) {
		return (parkingFee * quantity) - calculateDiscount(customer,date);
	}
/*
 * Calculates tax on all parking passes
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
 * Returns total after tax
 */
	public double calculateTotalCost(Customer customer, LocalDate date) {
		return calculateSubtotal(customer, date) + calculateTax(customer, date);
	}
/*
* Returns discount based on association with lease/sale agreement
*/
	public double calculateDiscount(Customer customer, LocalDate date) { //change to calculate parking pass
		if(apartmentCode != null) {
			int units = 1;
			units = apartmentCode.quantity;
			if(units > quantity) {
				units = quantity;
			}
			return parkingFee * units;
		}
		else {
			return 0;
		}
	}
}