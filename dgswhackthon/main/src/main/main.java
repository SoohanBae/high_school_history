package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
	public static Statement stmt;
	public static Connection con;

	public static void main(String[] args) { 
		// sql ¿¬°á
		try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.113/", "root", "root1234");
			stmt = con.createStatement();
			stmt.executeUpdate("use hack");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		new Login();
	}
}
