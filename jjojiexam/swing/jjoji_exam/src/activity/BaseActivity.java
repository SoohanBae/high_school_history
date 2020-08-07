package activity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BaseActivity extends JFrame {
	public BaseActivity() {
	}

	protected JPanel contentPane;

	
	public void set(String t) {
		setTitle(t);
		setDefaultCloseOperation(2);
	}
	
	public void fshow() {
		pack();
		
		
	}
	

}
