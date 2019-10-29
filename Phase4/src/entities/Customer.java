package entities;

public abstract class Customer {
 
	private String customerCode;
	private String customerType;
	private Person contact;	
	private String name;
	private Address address;
	
	public Customer(String customerCode, String customerType, Person contact, String name, Address address) {
		this.customerCode = customerCode;
		this.contact = contact;
		this.setCustomerType(customerType);
		this.name = name;
		this.address = address;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public Person getContact() {
		return contact;
	}
	public void setContact(Person contact) {
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	abstract void getTax();
	
	abstract void getDiscount();
}
