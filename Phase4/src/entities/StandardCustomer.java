package entities;

public class StandardCustomer extends Customer {

	public StandardCustomer(String customerCode, String customerType, Person contact, String name, Address address) {
		super(customerCode, customerType, contact, name, address);
		
	}

	@Override
	void getTax() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void getDiscount() {
		// TODO Auto-generated method stub
		
	}

}
