package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Admin extends JFrame{
	
	Statement stmt = main.stmt;
	private JTable table[] = new JTable[3];
	DefaultTableModel model[] = new DefaultTableModel[3];
	
	
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblClose;
	private JLabel lblMaximize;
	private JLabel lblMinimize;
	int tx=0,ty=0;
	private Boolean max;
	private JPanel ContaintPanel;
	private JLabel lblTitle;
	
	JFileChooser fileDlg = new JFileChooser();
	
	String yuk[] = {"¾øÀ½","µ¿´ë±¸¿ª","´ë°î¿ª","¿ë»ê¿ª"};
	public Admin() {

		setTitle("°ü¸®ÀÚ");
		getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(58, 61, 374, 388);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/icon_resources/logo.png")));
		setTitle("DGSWus");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1042, 582);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				tx = e.getX();
				ty = e.getY();

			}
			
			
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - tx, e.getYOnScreen() - ty);

			}
		});
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.add(getLblClose());
		contentPane.add(getLblMaximize());
		contentPane.add(getLblMinimize());
		contentPane.add(getLblTitle());
		
		int gae[] = new int[3];
		
		
		String col[] = {"ÇÐ¹ø","ÀÌ¸§","±âº»¿ª"};
		model[0] = new DefaultTableModel(null, col) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 1)
					return Integer.class;
				else
					return String.class;
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		
		
		
		
		gae[0]=0;
		
		try {
			ResultSet rs = stmt.executeQuery("select * from user where now = 1");
			while(rs.next()) {
				String s = rs.getString(2);
				s+=rs.getString(3);
				s+=String.format("%02d", rs.getInt(4));
				Object o[] = {Integer.parseInt(s),rs.getString(5),yuk[rs.getInt(9)]};
				
				model[0].addRow(o);
				System.out.println(o);
				gae[0]++;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		table[0] = new JTable(model[0]);
		
		
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(0);
		
		for (int i = 0; i < col.length; i++) {
			table[0].getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		table[0].setFont(new Font("³ª´®½ºÄù¾î", Font.BOLD, 20));
		table[0].setRowHeight(30);
		Color back = new Color(0, 0, 0, 0);
		table[0].getTableHeader().setBackground(new Color(110, 145, 255));
		table[0].getTableHeader().setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		table[0].getTableHeader().setForeground(Color.BLACK);
		table[0].getTableHeader().setReorderingAllowed(false);
		table[0].getTableHeader().setResizingAllowed(false);
		table[0].setGridColor(Color.LIGHT_GRAY);
		table[0].setBackground(Color.WHITE);
		table[0].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(getContaintPanel());
		
		JLabel lblNewLabel = new JLabel("ÃÑ " + gae[0] +"/80¸í");
		lblNewLabel.setBounds(236, 380, 108, 35);
		ContaintPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("³ª´®½ºÄù¾î", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 80, 280, 290);
		ContaintPanel.add(scrollPane);
		scrollPane.setViewportView(table[0]);
		
		
		
		JLabel label = new JLabel("\uB3D9\uB300\uAD6C\uC5ED",0);
		label.setFont(new Font("³ª´®½ºÄù¾î", Font.PLAIN, 33));
		label.setBounds(64, 28, 283, 44);
		ContaintPanel.add(label);
		
		
		
		
		
		
		
		
		model[1] = new DefaultTableModel(null, col) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 1)
					return Integer.class;
				else
					return String.class;
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		
		
		
		
		gae[1]=0;
		
		try {
			ResultSet rs = stmt.executeQuery("select * from user where now = 2");
			while(rs.next()) {
				String s = rs.getString(2);
				s+=rs.getString(3);
				s+=String.format("%02d", rs.getInt(4));
				Object o[] = {Integer.parseInt(s),rs.getString(5),yuk[rs.getInt(9)]};
				
				model[1].addRow(o);
				System.out.println(o);
				gae[1]++;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		table[1] = new JTable(model[1]);
		
		
		
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(0);
		
		for (int i = 0; i < col.length; i++) {
			table[1].getColumnModel().getColumn(i).setCellRenderer(dtcr2);
		}
		table[1].setFont(new Font("³ª´®½ºÄù¾î", Font.BOLD, 20));
		table[1].setRowHeight(30);
		table[1].getTableHeader().setBackground(new Color(110, 145, 255));
		table[1].getTableHeader().setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		table[1].getTableHeader().setForeground(Color.BLACK);
		table[1].getTableHeader().setReorderingAllowed(false);
		table[1].getTableHeader().setResizingAllowed(false);
		table[1].setGridColor(Color.LIGHT_GRAY);
		table[1].setBackground(Color.WHITE);
		table[1].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
				
				
		JLabel lblNewLabel2 = new JLabel("ÃÑ " + gae[1] +"/40¸í");
		lblNewLabel2.setBounds(563, 386, 108, 35);
		ContaintPanel.add(lblNewLabel2);
		lblNewLabel2.setFont(new Font("³ª´®½ºÄù¾î", Font.BOLD, 15));
		
		
		
		
		
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(391, 80, 280, 290);
		ContaintPanel.add(scrollPane2);
		scrollPane2.setViewportView(table[1]);
		
		
		
		JLabel label2 = new JLabel("\uB300\uACE1\uC5ED",0);
		label2.setFont(new Font("³ª´®½ºÄù¾î", Font.PLAIN, 33));
		label2.setBounds(395, 28, 283, 44);
		ContaintPanel.add(label2);
		
		
		
		
		
		

		model[2] = new DefaultTableModel(null, col) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 1)
					return Integer.class;
				else
					return String.class;
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		
		
		
		
		gae[2]=0;
		
		try {
			ResultSet rs = stmt.executeQuery("select * from user where now = 3");
			while(rs.next()) {
				String s = rs.getString(2);
				s+=rs.getString(3);
				s+=String.format("%02d", rs.getInt(4));
				Object o[] = {Integer.parseInt(s),rs.getString(5),yuk[rs.getInt(9)]};
				
				model[2].addRow(o);
				System.out.println(o);
				gae[2]++;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		table[2] = new JTable(model[2]);
		
		
		
		DefaultTableCellRenderer dtcr3 = new DefaultTableCellRenderer();
		dtcr3.setHorizontalAlignment(0);
		
		for (int i = 0; i < col.length; i++) {
			table[2].getColumnModel().getColumn(i).setCellRenderer(dtcr3);
		}
		table[2].setFont(new Font("³ª´®½ºÄù¾î", Font.BOLD, 20));
		table[2].setRowHeight(30);
		table[2].getTableHeader().setBackground(new Color(110, 145, 255));
		table[2].getTableHeader().setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		table[2].getTableHeader().setForeground(Color.BLACK);
		table[2].getTableHeader().setReorderingAllowed(false);
		table[2].getTableHeader().setResizingAllowed(false);
		table[2].setGridColor(Color.LIGHT_GRAY);
		table[2].setBackground(Color.WHITE);
		table[2].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(723, 80, 280, 290);
		ContaintPanel.add(scrollPane3);
		scrollPane3.setViewportView(table[2]);
		
		
		JLabel lblNewLabel3 = new JLabel("ÃÑ " + gae[2] +"/40¸í");
		lblNewLabel3.setBounds(896, 386, 108, 35);
		ContaintPanel.add(lblNewLabel3);
		lblNewLabel3.setFont(new Font("³ª´®½ºÄù¾î", Font.BOLD, 15));

		JLabel label3 = new JLabel("\uC6A9\uC0B0\uC5ED",0);
		label3.setFont(new Font("³ª´®½ºÄù¾î", Font.PLAIN, 33));
		label3.setBounds(720, 28, 283, 44);
		ContaintPanel.add(label3);
		
		
		
		setUndecorated(true);
		setVisible(true);
	}
	private JLabel getLblClose() {
		if (lblClose == null) {
			lblClose = new JLabel("");
			lblClose.setHorizontalAlignment(SwingConstants.CENTER);
			lblClose.setBounds(12, 6, 16, 16);
			max = false;
			lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Close.png")));

			lblClose.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_resources/CloseHover.png")));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Close.png")));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}

			});
		}
		return lblClose;
	}

	private JLabel getLblMaximize() {
		if (lblMaximize == null) {
			lblMaximize = new JLabel("");
			lblMaximize.setHorizontalAlignment(SwingConstants.CENTER);
			lblMaximize.setBounds(30, 6, 16, 16);
			lblMaximize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/MaxMin.png")));

			/*
			 * lblMaximize.addMouseListener(new MouseAdapter() {
			 * 
			 * @Override public void mouseEntered(MouseEvent e) { if(!max) {
			 * lblMaximize.setIcon(new
			 * ImageIcon(getClass().getResource("/icon_resources/MaximizeHover.png"))); }
			 * else{ lblMaximize.setIcon(new
			 * ImageIcon(getClass().getResource("/icon_resources/ResizeHover.png"))); }
			 * 
			 * }
			 * 
			 * @Override public void mouseExited(MouseEvent e) { lblMaximize.setIcon(new
			 * ImageIcon(getClass().getResource("/icon_resources/MaxMin.png"))); }
			 * 
			 * @Override public void mouseClicked(MouseEvent e) { if(!max) { max=true;
			 * frame.setExtendedState(JFrame.MAXIMIZED_BOTH); } else if(max) { max=false;
			 * frame.setExtendedState(JFrame.NORMAL);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * });
			 */
		}
		return lblMaximize;
	}

	private JLabel getLblMinimize() {
		if (lblMinimize == null) {
			lblMinimize = new JLabel("");
			lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Minimize.png")));
			lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
			lblMinimize.setBounds(48, 6, 16, 16);

			lblMinimize.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/MinimizeHover.png")));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Minimize.png")));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Minimize.png")));
					setExtendedState(JFrame.ICONIFIED);
				}

			});
		}
		return lblMinimize;
	}

	private JPanel getContaintPanel() {
		if (ContaintPanel == null) {
			ContaintPanel = new JPanel();
			ContaintPanel.setBackground(new Color(255, 255, 255));
			ContaintPanel.setBounds(0, 28, 1042, 582);
			ContaintPanel.setLayout(null);
			
			JButton button = new JButton("\uC5D1\uC140 \uCD9C\uB825\uD558\uAE30");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						
						fileDlg.setFileFilter(new FileNameExtensionFilter("¿¢¼¿ ÆÄÀÏ","xlsx"));
						int result = fileDlg.showSaveDialog(null);
			            if(result == JFileChooser.APPROVE_OPTION) //ÆÄÀÏÀ» ¼±ÅÃÇÏ°í ¿­¾úÀ»¶§ ÀÌº¥Æ®
			            {	
			            	
			            	writeToExcell(table, Paths.get(fileDlg.getSelectedFile().toString() + ".xlsx"));


			            }
						
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "¿À·ù°¡ ³µ½À´Ï´Ù. ÇÁ·Î±×·¥À» °ü¸®ÀÚ ±ÇÇÑÀ¸·Î ½ÇÇà½ÃÄÑ ÁÖ¼¼¿ä!");
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			});
			button.setFont(new Font("³ª´®½ºÄù¾î", Font.PLAIN, 20));
			button.setBackground(new Color(0, 255, 102));
			button.setBounds(470, 475, 169, 44);
			ContaintPanel.add(button);
			
			
			
			

		}
		return ContaintPanel;
	}

	public void writeToExcell(JTable table[], java.nio.file.Path path) throws FileNotFoundException, IOException {
	    new WorkbookFactory();
	    Workbook wb = new XSSFWorkbook(); //Excell workbook
	    Sheet sheet[] = new Sheet[3];
	    for (int i = 0; i < table.length; i++) {
			
		
		    sheet[i] = wb.createSheet(Login.Stations[i+1]); //WorkSheet
		    Row row = sheet[i].createRow(2); //Row created at line 3
		    TableModel model = table[i].getModel(); //Table model
	
	
		    Row headerRow = sheet[i].createRow(0); //Create row at line 0
		    for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
		        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
		    }
	
		    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
		        for(int cols = 0; cols < table[i].getColumnCount(); cols++){ //For each table column
		            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
		        }
	
		        //Set the row to the next one in the sequence 
		        row = sheet[i].createRow((rows + 3)); 
		    }
	    }
	    wb.write(new FileOutputStream(path.toString()));//Save the file     
	    wb.close();
	    JOptionPane.showMessageDialog(null,"´Ù¿î¼º°ø!!!!");
	}
	
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Bus Change");
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTitle.setForeground(new Color(0x861cfe));
			lblTitle.setBounds(79, 4, 100, 18);
		}
		return lblTitle;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;

	}
}
