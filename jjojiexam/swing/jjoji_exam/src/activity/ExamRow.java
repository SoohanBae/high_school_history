package activity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExamRow extends JPanel {

	/**
	 * Create the panel.
	 */
	JButton examChange, examDelete;
	
	public ExamRow(String t1, String t2) {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(840, 120));
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 0, 10, 10));
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(2, 1, 0, 10));
		
		examChange = new JButton("");
		examChange.setFocusable(false);
		examChange.setIcon(new ImageIcon(new ImageIcon("source/su_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH)));
		
		examChange.setRolloverIcon(new ImageIcon(new ImageIcon("source/su_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH)));
		examChange.setPressedIcon(new ImageIcon(new ImageIcon("source/su_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH)));
		examChange.setRequestFocusEnabled(false);
		examChange.setContentAreaFilled(false);
		examChange.setBorderPainted(false);
		examChange.setPreferredSize(new Dimension(36, 36));
		panel_2.add(examChange);
		
		examDelete = new JButton("");
		examDelete.setFocusable(false);
		examDelete.setIcon(new ImageIcon(new ImageIcon("source/close_1.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH)));
		
		examDelete.setRolloverIcon(new ImageIcon(new ImageIcon("source/close_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH)));
		examDelete.setPressedIcon(new ImageIcon(new ImageIcon("source/close_2.png").getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH)));
		examDelete.setRequestFocusEnabled(false);
		examDelete.setContentAreaFilled(false);
		examDelete.setBorderPainted(false);
		examDelete.setPreferredSize(new Dimension(36, 36));
		panel_2.add(examDelete);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(15, 15, 10, 1));
		panel_4.setBackground(Color.WHITE);
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel(t1);
		lblNewLabel_2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 24));
		panel_4.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JLabel label = new JLabel(t2);
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		panel_4.add(label, BorderLayout.SOUTH);
		

	}
	


}
