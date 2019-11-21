package com.ceg.ext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class connectionFactory {

	// connecting eclipse and database
	static Connection conn = null;
	Statement stat = null;
	PreparedStatement ps = null;

	static final String driverJDBC = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://cse.unl.edu/spierce"; // jdbc:mysql://cse.unl.edu/ajayswal
	static final String username = "spierce"; // ajayswal
	static final String password = "pa4hUY"; // Shivbaba98853#

	// method to connect to JDBC and checking for errors
	public static Connection getConnection() {
		try {
			if (conn != null && !conn.isClosed())
				return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = _getConnection();
		return conn;
	}

	private static Connection _getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connectionFactory.url, connectionFactory.username,
					connectionFactory.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return conn;
	}

	public static void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
