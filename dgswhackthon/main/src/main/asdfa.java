package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class asdfa extends JFrame {

	private JTable table;
	DefaultTableModel model;

	String yuk[] = { "없음", "동대구역", "대곡역", "용산역" };

	public asdfa() throws ClassNotFoundException {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1042, 582);
		setTitle("관리자");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(58, 61, 374, 388);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\uB300\uACE1\uC5ED");
		label.setBounds(166, 10, 36, 15);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 64, 283, 264);
		panel.add(scrollPane);

		String col[] = { "학번", "이름", "기본역" };
		String o[][] = { { "2114", "임준혁", "동대구" }, { "0000", "0000", "0000" }, { "1111", "1111", "1111" } };
		model = new DefaultTableModel(o, col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}

		};

		int gae1 = 0;

		table = new JTable(model);
		scrollPane.setViewportView(table);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(0);

		for (int i = 0; i < col.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}

		table.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		table.setRowHeight(35);
		Color back = new Color(0, 0, 0, 0);
		table.getTableHeader().setBackground(new Color(110, 145, 255));
		table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 18));
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setBackground(Color.WHITE);
		table.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		System.out.println();

		setVisible(true);
	}

	public static void main(String[] args) throws ClassNotFoundException {
		asdfa admin = new asdfa();
	}


	public void getlogin() {

	}
}
