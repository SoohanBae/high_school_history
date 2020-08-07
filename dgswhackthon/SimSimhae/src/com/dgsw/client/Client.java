package com.dgsw.client;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.dgsw.ui.*;

public class Client implements Display{
	
	public static Statement stmt;
	public static Connection con;
	
	public static void main(String[] args) {
		
		//sql ¿¬°á
		try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.137.1/", "root", "root1234");
			stmt = con.createStatement();
			stmt.executeUpdate("use hack");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JDialog jf = new JDialog();

		jf.getContentPane().setLayout(null);
		jf.getContentPane().setBackground(Color.white);
		jf.setSize(new Dimension(width, height));
		jf.setUndecorated(false);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		UIManager.put("activeCaption", new javax.swing.plaf.ColorUIResource(
				Color.red));
				JDialog.setDefaultLookAndFeelDecorated(true);   
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		new Login();
//		Login.ShowLoginView(jf);
	}
}
