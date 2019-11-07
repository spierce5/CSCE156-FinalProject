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
	static Connection connect = null;
	Statement stat = null;

	static final String driverJDBC = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://cse.unl.edu/ajayswal";
	static final String username = "ajayswal";
	static final String password = "Shivbaba98853#";

	// method
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

	public ArrayList<Person> readPersons() {
		ArrayList<Person> people = new ArrayList<Person>();
		String query1;
		query1 = "SELECT * from Person";
		Statement stat1;

		try {
			stat1 = connect.createStatement();
			ResultSet rs = stat1.executeQuery(query1);

			while (rs.next()) {
				int personID = rs.getInt("PersonId");
				String personCode = rs.getString("PersonCode");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				int addressID = rs.getInt("AddressId");

				Person person = new Person(personCode, lastName, firstName, address(addressID));
				person.setEmail(email(personID));

				people.add(person);
			}
			stat1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return people;
	}

	// email
	public ArrayList<String> email(int PersonId) {
		ArrayList<String> string = new ArrayList<String>();

		PreparedStatement stat = null;

		try {

			stat = (PreparedStatement) connect.prepareStatement("SELECT * from Email where PersonId = ?");
			stat.setInt(1, PersonId);

			ResultSet rs = stat.executeQuery();
			while (rs.next()) {

				String str = rs.getString("Email");
				string.add(str);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return string;

	}

	public Address address(int AddressId) {

		PreparedStatement stat = null;
		Address address = null;

		try {

			stat = (PreparedStatement) connect.prepareStatement("SELECT * from Address where AddressId = ?");
			stat.setInt(1, AddressId);

			ResultSet rs = stat.executeQuery();
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

	public ArrayList<Customer> readCustomers() {

		ArrayList<Customer> customers = new ArrayList<Customer>();

		String query1;
		query1 = "SELECT * from Customer";

		try {

			Statement stat = connect.createStatement();
			ResultSet rs = stat.executeQuery(query1);

			while (rs.next()) {
				int customerID = rs.getInt("CustomerId");
				String customerCode = rs.getString("CustomerCode");
				String customerType = rs.getString("CustomerType");
				String clientName = rs.getString("ClientName");
				int personID = rs.getInt("personId");
				int addressID = rs.getInt("AddressId");

				// for lowerIncome customers
				if (customerType.equals("L")) {
					LowIncome lowIncCustomer = new LowIncome(customerCode, customerType, person(personID), clientName,
							address(addressID));

					customers.add(lowIncCustomer);
				} else {
					StandardCustomer StandCustomer = new StandardCustomer(customerCode, customerType, person(personID), clientName,
							address(addressID));

					customers.add(StandCustomer);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return customers;
	}

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

				person1 = new Person(personCode, lastName, firstName, address(addressID));
				person1.setEmail(email(personID));

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return person1;
	}

}
