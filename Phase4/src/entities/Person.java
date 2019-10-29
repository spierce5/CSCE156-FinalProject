package entities;
import java.util.ArrayList;

public class Person {
	
	private String personCode;
	private String lastName;
	private String firstName;
	private Address address;		
	private ArrayList<String> email;
	
	
	public Person(String personCode, String lastName, String firstName, Address address) {
		super();
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.setAddress(address);
	}
	
	public String getPersonCode() {
		return personCode;
	}
	
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder(getLastName());
		sb.append(",");
		sb.append(getFirstName());
		return sb.toString();
	}
	
}
