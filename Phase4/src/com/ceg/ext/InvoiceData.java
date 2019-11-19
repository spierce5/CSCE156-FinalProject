package com.ceg.ext;

import java.sql.Connection;
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
	//removing every person record from the database 
	public static void removeAllPersons() {

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
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {}

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode, String name, String street, String city, String state, String zip, String country) {}

	/**
	 * 5. Removes all product records from the database
	 */
	public static void removeAllProducts() {}

	/**
	 * 6. Adds a SaleAgreement record to the database with the provided data.
	 */
	public static void addSaleAgreement(String productCode, String dateTime, String street, String city,String state, String zip, String country, double totalCost, double downPayment, double monthlyPayment,
			int payableMonths, double interestRate) {}

	/**
	 * 7. Adds a LeaseAgreement record to the database with the provided data.
	 */
	public static void addLeaseAgreement(String productCode, String name, String startDate, String endDate, String street, String city, String state, String zip, String country, double deposit, double monthlyCost) {}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {}

	/**
	 * 9. Adds an Amenity record to the database with the provided data.
	 */
	public static void addAmenity(String productCode, String name, double cost) {}

	/**
	 * 10. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String realtorCode, String invoiceDate) {}

	/**
	 * 12. Adds a particular SaleAgreement (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addSaleAgreementToInvoice(String invoiceCode, String productCode, int quantity) {}

	/**
	 * 13. Adds a particular LeaseAgreement (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addLeaseAgreementToInvoice(String invoiceCode, String productCode, int quantity) {}

     /**
     * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: agreementCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String agreementCode) {}

    /**
     * 15. Adds a particular amenity (corresponding to <code>productCode</code> to an
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     */
    public static void addAmenityToInvoice(String invoiceCode, String productCode, int quantity) {}

}
