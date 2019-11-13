//Helped by Jaelle Kondohoma

package reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.PreparedStatement;

import entities.*;

public class DatabaseReaderFile {
	
	//connecting eclipse and database
	static Connection connect = null;
	Statement stat = null;
	PreparedStatement ps = null;

	static final String driverJDBC = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://cse.unl.edu/ajayswal";
	static final String username = "ajayswal";
	static final String password = "Shivbaba98853#";

	// method  to connect to JDBC and checking for errors
	public void ConnectionToJdbc() {
		try {
			Class.forName(driverJDBC);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
			System.exit(-1);
		}

		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Queries the database for all elements in the Person table, parses the information, and returns an array of Person objects
	 */
	public ArrayList<Person> readPersons() {
		ArrayList<Person> people = new ArrayList<Person>();
		String query1;
		query1 = "SELECT * from Person";

		//
		try {
			stat = connect.createStatement();
			ResultSet rs = stat.executeQuery(query1);

			while (rs.next()) {
				int personID = rs.getInt("PersonId");
				String personCode = rs.getString("PersonCode");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				int addressID = rs.getInt("AddressId");

				Person person = new Person(personCode, lastName, firstName, getAddress(addressID));
				person.setEmail(getEmails(personID));

				people.add(person);
			}
			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return people;
	}

	/*
	 * Queries the database for all email addresses associated with a specific PersonId
	 */
	public ArrayList<String> getEmails(int PersonId) {
		ArrayList<String> emailList = new ArrayList<String>();

		try {
			ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Email WHERE PersonId = ?");
			ps.setInt(1, PersonId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String str = rs.getString("Email");
				emailList.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emailList;
	}
/*
 * Queries the database for an address with a specific AddressId
 */
	public Address getAddress(int AddressId) {

		Address address = null;

		try {

			ps = (PreparedStatement) connect.prepareStatement("SELECT * from Address where AddressId = ?");
			ps.setInt(1, AddressId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String country = rs.getString("Country");
				String zipCode = rs.getString("Zip");

				address = new Address(street, city, state, country, zipCode);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return address;
	}
/*
 * Queries the database for all elements in the Address table, parses the information, and returns an array of Addresses
 */
	public ArrayList<Customer> readCustomers() {

		ArrayList<Customer> customers = new ArrayList<Customer>();

		String query1;
		query1 = "SELECT * from Customer";

		try {

			stat = connect.createStatement();
			ResultSet rs = stat.executeQuery(query1);

			while (rs.next()) {
				int customerID = rs.getInt("CustomerId");			//is this used somewhere?
				String customerCode = rs.getString("CustomerCode");
				String customerType = rs.getString("CustomerType");
				String clientName = rs.getString("ClientName");
				int personID = rs.getInt("personId");
				int addressID = rs.getInt("AddressId");

					Customer newCustomer = new Customer(customerCode, customerType, person(personID), clientName,
							getAddress(addressID));
					customers.add(newCustomer);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return customers;
	}
/*
 * Queries the database for a Person associated with specific PersonId
 */
	public Person person(int PersonId) {

		PreparedStatement stat = null;
		Person person1 = null;

		try {

			stat = (PreparedStatement) connect.prepareStatement("SELECT * from Person where PersonId = ?");
			stat.setInt(1, PersonId);

			ResultSet rs = stat.executeQuery();
			while (rs.next()) {

				int personID = rs.getInt("PersonId");
				String personCode = rs.getString("PersonCode");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				int addressID = rs.getInt("AddressId");

				person1 = new Person(personCode, lastName, firstName, getAddress(addressID));
				person1.setEmail(getEmails(personID));

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return person1;
	}
	
/*
 * Queries the database for all elements in the Product table, parses the data, and returns an array of products
 */
	public ArrayList<Product> readProduct(){
		
		ArrayList<Product> products = new ArrayList<Product>();

		String query1;
		query1 = "SELECT * from Product";
		
		try {

			Statement stat = connect.createStatement();
			ResultSet rs = stat.executeQuery(query1);

			while (rs.next()) {
				
				
				
				}
			}  catch (Exception e) {

				e.printStackTrace();

			}
		
		return products;
	}
/*
 * Queries the database for an element from either the SaleAgreement, LeaseAgreement, ParkingPass, or Amenity table given a ProductType and ProductId
 */
	public Product product(int productId, String productCode, String productType) {
		Product product = null;
		try {
		//	ps = (PreparedStatement) connect.prepareStatement("SELECT * from Product where ProductId = ? AND ProductCode = ? AND ProductType = ?");
		//	ps.setInt(1, ProductId);
		//	ps.setString(3, ProductType);
			ResultSet rs;
			int quantity;
			
			switch(productType) {
				case "A":
					ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Amenity WHERE ProductId = ?");
					ps.setInt(1, productId);
					rs = ps.executeQuery();
					double price = rs.getDouble("Price");
					String description = rs.getString("Description");
					quantity = rs.getInt("Quantity");
					Amenity amenity = new Amenity(productCode, productType, description, price);
					amenity.setQuantity(quantity);
					product = amenity;
					break;
				case "P":
					ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM ParkingPass WHERE ProductId = ?");
					ps.setInt(1, productId);
					rs = ps.executeQuery();
					double parkingFee = rs.getDouble("ParkingFee"); //Updated missing column in ParkingPass
					String apartmentCode = rs.getString("LeaseCode");//Needs to be updated to Product type
					
					break;
				case "L":
					//do stuff
					break;
				case "S": 
					//do stuff
					break;
				default:
					System.err.println("Product Type Not Found");
					break;					
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return product;
	}

}
