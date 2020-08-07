package com.dgsw.ui;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import com.dgsw.client.*;

public class Login extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	Statement stmt = com.dgsw.client.Client.stmt;
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		setSize(980, 480);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "name_288399552896353");
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(44, 78, 13, 18);
		panel.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(109, 75, 116, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setBounds(44, 136, 25, 18);
		panel.add(lblPw);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 131, 113, 24);
		panel.add(passwordField);
		
		JButton button = new JButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getlogin();
			}
		});
		button.setBounds(292, 136, 105, 27);
		panel.add(button);
		setVisible(true);
	}
	public static void main(String[] args) {
		Login login = new Login();
	}
	
	public void getlogin() {
		try {
			ResultSet rs = stmt.executeQuery("select * from user where id = '"+textField+"' and password = '"+passwordField+"'");
			if(rs.next()) {
				System.out.println(rs.getString(5));
			}
			else {
				System.out.println("none");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
