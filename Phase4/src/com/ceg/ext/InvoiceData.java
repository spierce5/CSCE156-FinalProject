package com.ceg.ext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 *
 */

// Odds: Asmita, Evens: Sam
public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	// removing every person record from the database
	public static void removeAllPersons() {

		Connection connect = connectionFactory.getConnection();
		PreparedStatement ps = null;

		try {

			String query = "DELETE  from InvoiceProduct";
			ps = (PreparedStatement) connect.prepareStatement(query);
			ps.executeUpdate();

			String query1 = "DELETE from Product";
			ps = (PreparedStatement) connect.prepareStatement(query1);
			ps.executeUpdate();

			String query2 = "DELETE from Email";
			ps = (PreparedStatement) connect.prepareStatement(query2);
			ps.executeUpdate();

			String query3 = "DELETE from Customer";
			ps = (PreparedStatement) connect.prepareStatement(query3);
			ps.executeUpdate();

			String query4 = "DELETE from Invoice";
			ps = (PreparedStatement) connect.prepareStatement(query4);
			ps.executeUpdate();
			
			String query5 = "DELETE from Person";
			ps = (PreparedStatement) connect.prepareStatement(query5);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			connectionFactory.closeConnection();
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 *
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) {
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email 
	 */
	public static void addEmail(String personCode, String email) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement ps = null;

		try {

			
			String query = "SELECT * from Email e join person p where p.PersonId = e. PersonId";
			ps = (PreparedStatement) connect.prepareStatement(query);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			connectionFactory.closeConnection();

		}

	}
	
	//personId using personCode
	public static int getPersonId(String personCode) {
		
		String query = "Select PersonId from Person where personCode = ?";
		Connection connect = connectionFactory.getConnection();
		PreparedStatement ps = null;
		Statement stat = null;
		
		try {
			
			ps = (PreparedStatement) connect.prepareStatement(query);
			ps.setString(1, personCode);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				//fix me: get personId usong person code
				
				int personID = rs.getInt("PersonId");
			}

			
		}catch (Exception e) {
			e.printStackTrace();
			connectionFactory.closeConnection();

		}

		
		return null;
	}
	

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement ps = null;

		try {

			String query = "DELETE  from Invoice";
			ps = (PreparedStatement) connect.prepareStatement(query);
			ps.executeUpdate();

			String query1 = "DELETE from InvoiceProduct";
			ps = (PreparedStatement) connect.prepareStatement(query1);
			ps.executeUpdate();

			String query2 = "DELETE from Customer";
			ps = (PreparedStatement) connect.prepareStatement(query2);
			ps.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
			connectionFactory.closeConnection();
		}

	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,
			String name, String street, String city, String state, String zip, String country) {
		
		
	}

	/**
	 * 5. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		
		Connection connect = connectionFactory.getConnection();
		PreparedStatement ps = null;

		try {

			String query = "DELETE  from InvoiceProduct";
			ps = (PreparedStatement) connect.prepareStatement(query);
			ps.executeUpdate();

			String query1 = "DELETE from LeaseAgreement";
			ps = (PreparedStatement) connect.prepareStatement(query1);
			ps.executeUpdate();

			String query2 = "DELETE from SaleAgreement";
			ps = (PreparedStatement) connect.prepareStatement(query2);
			ps.executeUpdate();

			String query3 = "DELETE from Amenity";
			ps = (PreparedStatement) connect.prepareStatement(query3);
			ps.executeUpdate();

			String query4 = "DELETE from ParkingPass";
			ps = (PreparedStatement) connect.prepareStatement(query4);
			ps.executeUpdate();
			
			String query5 = "DELETE from Product";
			ps = (PreparedStatement) connect.prepareStatement(query5);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			connectionFactory.closeConnection();
		}
	}

	/**
	 * 6. Adds a SaleAgreement record to the database with the provided data.
	 */
	public static void addSaleAgreement(String productCode, String dateTime, String street, String city, String state,
			String zip, String country, double totalCost, double downPayment, double monthlyPayment, int payableMonths,
			double interestRate) {
	}

	/**
	 * 7. Adds a LeaseAgreement record to the database with the provided data.
	 */
	public static void addLeaseAgreement(String productCode, String name, String startDate, String endDate,
			String street, String city, String state, String zip, String country, double deposit, double monthlyCost) {
	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
	}

	/**
	 * 9. Adds an Amenity record to the database with the provided data.
	 */
	public static void addAmenity(String productCode, String name, double cost) {
	}

	/**
	 * 10. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
	}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String realtorCode, String invoiceDate) {
	}

	/**
	 * 12. Adds a particular SaleAgreement (corresponding to
	 * <code>productCode</code> to an invoice corresponding to the provided
	 * <code>invoiceCode</code> with the given number of units
	 */

	public static void addSaleAgreementToInvoice(String invoiceCode, String productCode, int quantity) {
	}

	/**
	 * 13. Adds a particular LeaseAgreement (corresponding to
	 * <code>productCode</code> to an invoice corresponding to the provided
	 * <code>invoiceCode</code> with the given begin/end dates
	 */
	public static void addLeaseAgreementToInvoice(String invoiceCode, String productCode, int quantity) {
	}

	/**
	 * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of quantity. NOTE: agreementCode may be null
	 */
	public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity,
			String agreementCode) {
	}

	/**
	 * 15. Adds a particular amenity (corresponding to <code>productCode</code> to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of quantity.
	 */
	public static void addAmenityToInvoice(String invoiceCode, String productCode, int quantity) {
	}

}
