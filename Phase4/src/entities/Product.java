package entities;

import java.time.LocalDate;

public abstract class Product {

	private String productCode;
	private String productType;
	protected int quantity = 1;

	public Product(String productCode, String productType) {
		this.productCode = productCode;
		this.productType = productType;
	}
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public abstract double calculateSubtotal(Customer customer, LocalDate date);
	public abstract double calculateTax(Customer customer, LocalDate date);
	public abstract double calculateTotalCost(Customer customer, LocalDate date);
	
}

