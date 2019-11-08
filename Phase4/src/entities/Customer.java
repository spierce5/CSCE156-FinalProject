package entities;

public class Customer {
 
	private String customerCode;
	private String customerType;
	private Person contact;	
	private String name;
	private Address address;
	private boolean lowIncome = false;
	

	public Customer(String customerCode, String customerType, Person contact, String name, Address address) {
		super();
		this.customerCode = customerCode;
		this.customerType = customerType;
		this.contact = contact;
		this.name = name;
		this.address = address;
		if(customerType.equals("L")) {
			this.lowIncome = true;
		}
		else if(customerType.equals("G")) {
			this.lowIncome = false;
		}
	
	}
	
	public boolean isLowIncome() {
		return lowIncome;
	}
	
	public void setLowIncome(boolean lowIncome) {
		this.lowIncome = lowIncome;
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
	

}
