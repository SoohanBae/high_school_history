package rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Rest {

	public static Connection con;
	public static Statement stmt;
	
	public static int userNo;
	public static int examNo;
	
	public static void dbs() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/","root","1234");
			stmt = con.createStatement();
			stmt.executeUpdate("use jjoji");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
