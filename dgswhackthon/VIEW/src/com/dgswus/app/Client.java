 package com.dgswus.app;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Client {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = null;
					frame = new Login();
					//Remove the top bar
					frame.setUndecorated(true);
					
					frame.setResizable(true);
					frame.setBackground(new Color(0,0,0));
					((Login) frame).setFrame(frame);
					frame.setVisible(true);
					
//					frame.removeAll();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
