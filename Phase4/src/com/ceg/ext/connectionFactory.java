package com.ceg.ext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class connectionFactory {

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
	
}
