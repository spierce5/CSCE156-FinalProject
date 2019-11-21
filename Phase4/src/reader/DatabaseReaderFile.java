//Helped by Jaelle Kondohoma

package reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.jdbc.PreparedStatement;

import entities.*;

public class DatabaseReaderFile {

	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	//connecting eclipse and database
	static Connection connect = null;
	Statement stat = null;
	PreparedStatement ps = null;

	static final String driverJDBC = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://cse.unl.edu/spierce"; //jdbc:mysql://cse.unl.edu/ajayswal
	static final String username = "spierce";					//ajayswal
	static final String password = "pa4hUY";			//Shivbaba98853#

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
 * Queries the database for a Person associated with specific PersonId
 */
	public Person getPerson(int PersonId) {
		Person person1 = null;
		try {
			ps = (PreparedStatement) connect.prepareStatement("SELECT * from Person where PersonId = ?");
			ps.setInt(1, PersonId);
			ResultSet rs = ps.executeQuery();
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
 * Queries the database for a Person associated with specific PersonId
 */
	public Customer getCustomer(int CustomerId) {
		Customer customer = null;

		try {
			ps = (PreparedStatement) connect.prepareStatement("SELECT * from Customer where PersonId = ?");
			ps.setInt(1, CustomerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String customerCode = rs.getString("CustomerCode");
				String customerType = rs.getString("CustomerType");
				String clientName = rs.getString("ClientName");
				int personID = rs.getInt("personId");
				int addressID = rs.getInt("AddressId");

				customer = new Customer(customerCode, customerType, getPerson(personID), clientName, getAddress(addressID));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
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
				Customer customer = getCustomer(rs.getInt("CustomerId"));
				customers.add(customer);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return customers;
	}

/*
 * Queries the database for all elements in the Product table, parses the data, and returns an array of products
 */
	public ArrayList<Product> readProduct(){

		ArrayList<Product> products = new ArrayList<Product>();

		try {

			ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Product");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product product = getProduct(rs.getInt("ProductId"), rs.getString("ProductCode"), rs.getString("ProductType"));
				products.add(product);
				}
			}  catch (Exception e) {

				e.printStackTrace();

			}

		return products;
	}
/*
 * Queries the database for an element from either the SaleAgreement, LeaseAgreement, ParkingPass, or Amenity table given a ProductType and ProductId
 */
	public Product getProduct(int productId, String productCode, String productType) {
		Product product = null;
		try {
			ResultSet rs;
			int quantity = 1;
			ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Product WHERE ProductId = ?");
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if(rs.next()) {
				quantity = rs.getInt("Quantity");
			}
			
			switch(productType) {
				case "A":
					ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Amenity WHERE ProductId = ?");
					ps.setInt(1, productId);
					rs = ps.executeQuery();
					while (rs.next()) {
						double price = rs.getDouble("Price");
						String description = rs.getString("Description");
						Amenity amenity = new Amenity(productCode, productType, description, price);
						amenity.setQuantity(quantity);
						product = amenity;
					}
					ps.close();
					rs.close();
					break;
				case "P":
					ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM ParkingPass WHERE ProductId = ?");
					ps.setInt(1, productId);
					rs = ps.executeQuery();
					while (rs.next()) {
						double parkingFee = rs.getDouble("ParkingFee"); 
						ParkingPass parking = new ParkingPass(productCode, productType, parkingFee);
						parking.setQuantity(quantity);
						if(rs.getString("LeaseCode") != null) {
							String apartmentCode = rs.getString("LeaseCode");
							//Recursively obtains Lease or Sale Agreement associated with the ParkingPass
							ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Product WHERE ProductCode = ?");
							ps.setString(1, apartmentCode);
							ResultSet rs2 = ps.executeQuery();
							while (rs2.next()) {
								Product apartment = getProduct(rs2.getInt("ProductId"), rs2.getString("ProductCode"),
										rs2.getString("ProductType"));
								parking.setApartmentCode(apartment);
							}
						}
						product = parking;
					}
					ps.close();
					rs.close();
					break;
				case "L":
					ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM LeaseAgreement WHERE ProductId = ?");
					ps.setInt(1, productId);
					rs = ps.executeQuery();
					while (rs.next()) {
						LocalDate startDate = LocalDate.parse(rs.getString("StartDate"), dateFormatter);
						LocalDate endDate = LocalDate.parse(rs.getString("EndDate"), dateFormatter);
						Address address = getAddress(rs.getInt("AddressId"));
						double pricePerApartment = rs.getDouble("Price");
						double deposit = rs.getDouble("Deposit");
						
						//Obtains customer via InvoiceProduct
						ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM InvoiceProduct WHERE ProductId = ?");
						ps.setInt(1, productId);
						ResultSet rs2 = ps.executeQuery();
						while (rs2.next()) {
							Customer customer = getCustomer(rs2.getInt("CustomerId"));
							LeaseAgreements lease = new LeaseAgreements(productCode, productType, startDate, endDate,
									address, customer, pricePerApartment, deposit);
							lease.setQuantity(quantity);
							product = lease;
						}
					}
					ps.close();
					rs.close();	
					break;
				case "S":
					ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM SaleAgreement WHERE ProductId = ?");
					ps.setInt(1, productId);
					rs = ps.executeQuery();
					while (rs.next()) {
						LocalDateTime date = LocalDateTime.parse(rs.getString("SaleDate"), dateTimeFormatter);
						Address address1 = getAddress(rs.getInt("AddressId"));
						double totalCost = rs.getDouble("TotalCost");
						double initialPayment = rs.getDouble("InitialPayment");
						double monthlyPayment = rs.getDouble("MonthlyPayment");
						double totalMonths = rs.getDouble("TotalMonths");
						double interestRate = rs.getDouble("InterestRate");
						SaleAgreements sale = new SaleAgreements(productCode, productType, date, address1, totalCost,
								initialPayment, monthlyPayment, totalMonths, interestRate);
						sale.setQuantity(quantity);
						product = sale;
					}
					ps.close();
					rs.close();
					break;
				default:
					System.err.println("Product Type Not Found");
					ps.close();
					rs.close();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return product;
	}
/*
 * Returns an Invoice created from data acquired from database queries
 */
	public Invoice getInvoice(int invoiceId) {
		Invoice newInvoice = null;
		try {
			ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Invoice WHERE InvoiceId = ?");
			ps.setInt(1, invoiceId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				//data that can be gathered from Invoice table
				String invoiceCode = rs.getString("InvoiceCode");
				LocalDate date = LocalDate.parse(rs.getString("InvoiceDate"), dateFormatter);
				Customer customer = getCustomer(rs.getInt("CustomerId"));
				Person landlord = getPerson(rs.getInt("Realtor"));
				
				//data that requires other queries
				ArrayList<Product> products = new ArrayList<Product>();
				ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Product p JOIN InvoiceProduct i "
						+ "ON i.ProductId = p.ProductId WHERE InvoiceId = ?");
				ps.setInt(1, invoiceId);
				ResultSet rs2 = ps.executeQuery();
				Product product = null;
				while (rs2.next()) {
					product = getProduct(rs2.getInt("ProductId"), rs2.getString("ProductCode"),
							rs2.getString("ProductType"));
					products.add(product);
				}
				newInvoice = new Invoice(invoiceCode, customer, landlord, date, products);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return newInvoice;
	}
/*
 * Returns an InvoiceList of Invoices created using the getInvoice method
 */
	public InvoiceList readInvoices() {
		InvoiceList invoices = new InvoiceList();

		try {
			ps = (PreparedStatement) connect.prepareStatement("SELECT * FROM Invoice");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Invoice invoice = getInvoice(rs.getInt("InvoiceId"));
				invoices.add(invoice);
				}
		}  
		catch (Exception e) {
			e.printStackTrace();
		}
		return invoices;
	}

}
