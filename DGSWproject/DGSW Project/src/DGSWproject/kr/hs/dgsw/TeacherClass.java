package DGSWproject.kr.hs.dgsw;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.synth.SynthSplitPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.management.modelmbean.ModelMBean;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.SelectableChannel;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Iterator;
import java.awt.event.ActionEvent;



public class TeacherClass extends JFrame implements Runnable {
	boolean first = true;
	int combocnt=3;
	boolean lr = true; //"왼쪽이 투르"
	DefaultTableCellRenderer dtcr;
	Thread th = new Thread(this);
	String sql,sqll;
	Statement stmt = MainClass.stmt;
	Connection conn = MainClass.conn;
	private JTable table;
	private JTable table_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textField;
	int mon;
	int day;
	String tabled;
	String no;
	int classname;
	int gradename;
	DefaultTableModel model;
	DefaultTableModel model_1;
	Calendar cal = Calendar.getInstance();
	JComboBox numbox;
	String[][] moa = {
		{"교실","1-1"},
		{"교실","1-2"},
		{"교실","1-3"},
		{"프실","1"},
		{"프실","2"},
		{"렙실","1"},
		{"렙실","2"},
		{"렙실","3"},
		{"렙실","4"},
		{"렙실","5"},
		{"렙실","6"},
		{"렙실","7"},
		{"렙실","8"},
		{"렙실","9"},
		{"렙실","10"},
		{"렙실","11"},
		{"렙실","12"},
		{"렙실","13"},
		{"렙실","14"},
		{"체육관",""},
		{"외출",""},
		{"기타","오케스트라실"},
		{"기타","로보틱스기능실"},
		{"기타","과학실"},
		{"기타","음악실"},
		{"기타","웹프로그래밍실"},
		{"기타","책마루도서실"}
	};
	JLabel timelabel;
	private int hour;
	private int min;
	private int daw;
	String tables;
	JFileChooser fileDlg = new JFileChooser();

    	

	public TeacherClass(String id){
		
		classname=Integer.parseInt(id.substring(8,9));
		gradename=Integer.parseInt(id.substring(7,8));
		System.out.println(classname);
		
		
		
		
		
		timelabel = new JLabel("11\uC6D4 28\uC77C 8\uC2DC 40\uBD84");
		timelabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		timelabel.setBounds(30, 6, 227, 31);
		getContentPane().add(timelabel);
		
		getday();
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 66, 265, 288);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		
		//
		
		dtcr = new DefaultTableCellRenderer(); // 테이블셀 렌더러 객체를 생성.
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);              // 생성한 렌더러의 가로정렬을 CENTER로
		
		model = new DefaultTableModel(){
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};
		
		table = new JTable(model);
		panel.add(table, BorderLayout.CENTER);
		panel.add(new JScrollPane(table));
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(362, 66, 265, 288);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		model_1 = new DefaultTableModel(){
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};
		
		table_1 = new JTable(model_1);
		panel_1.add(table_1, BorderLayout.CENTER);
		panel_1.add(new JScrollPane(table_1));
		
		
		
		
		

		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tables);
				if(combocnt>=3 || (first && combocnt>=2)){
				if(lr)
					moabogi(comboBox_2.getSelectedItem().toString(),tables.substring(0, tables.indexOf("table")));
				else
					studentsbogi(comboBox_2.getSelectedItem().toString(),tables.substring(0, tables.indexOf("table")));
				}
			}
		});
		comboBox_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboBox_2.setBounds(505, 12, 104, 21);
		getContentPane().add(comboBox_2);
		
		JComboBox comboBox_1 = new JComboBox();
		String item[] = {"심야자습","체육관","정기외출","버스탑승"};
		for(int i = 0 ;i<4;i++){
			comboBox_1.addItem(item[i]);
		}
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combocnt=0;
				int select = comboBox_1.getSelectedIndex();
				
				if(select == 0)
					tables="nighttable";
				else if(select == 1)
					tables="gymtable";
				else if(select == 2)
					tables="outingtable";
				else if(select == 3)
					tables="bustable";
				combocnt++;
				comboBox_2.removeAllItems();
				sql = "select date from "+tables+" order by date";
				try {	
					ResultSet rs = stmt.executeQuery(sql);
				
					int j=0;
					combocnt++;
					while(rs.next()){
						comboBox_2.addItem(rs.getString("date"));
						j++;
					}
					combocnt++;
					if(j!=0)
					comboBox_2.setSelectedIndex(j-1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*if(lr)
					moabogi(comboBox_2.getSelectedItem().toString(),tables.substring(0, tables.indexOf("table")));
				else
					studentsbogi(comboBox_2.getSelectedItem().toString(),tables.substring(0, tables.indexOf("table")));*/
				
				first=false;
			}
		});
		comboBox_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		comboBox_1.setBounds(375, 12, 104, 21);
		comboBox_1.setSelectedIndex(0);
		getContentPane().add(comboBox_1);
		
		
		
		
		
		
		
		JRadioButton eventmo = new JRadioButton("\uBAA8\uC544\uBCF4\uAE30");
		eventmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lr=true;
				moabogi(comboBox_2.getSelectedItem().toString(),tables.substring(0, tables.indexOf("table")));
			}
		});
		buttonGroup_1.add(eventmo);
		eventmo.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		eventmo.setBounds(362, 37, 104, 23);
		eventmo.setSelected(true);
		getContentPane().add(eventmo);
		
		
		
		JRadioButton eventstudent = new JRadioButton("\uD559\uC0DD\uBCC4");
		eventstudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			lr=false;
			studentsbogi(comboBox_2.getSelectedItem().toString(),tables.substring(0, tables.indexOf("table")));
			}
		});
		buttonGroup_1.add(eventstudent);
		eventstudent.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		eventstudent.setBounds(467, 37, 97, 23);
		getContentPane().add(eventstudent);
		
		
		
		model.setNumRows(0);
		String cn[]={"위치","세부위치위치","학생수"};
		model.setColumnIdentifiers(cn);
		
		try {
			int cnt=0;
			for(int j=0;j<moa.length;j++){
				sql="select * from "+tabled+" ";
				
				if(!moa[j][1].equals("")){
					sql = sql+"where location='"+moa[j][0]+"' and num='"+moa[j][1]+"';";
				}else{
					sql=sql+"where location='"+moa[j][0]+"';";
				}
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					cnt++;
				}
				String arr[] = new String[3];
				arr[0] = moa[j][0];
				arr[1] = moa[j][1];
				arr[2] = String.valueOf(cnt);
				model.addRow(arr);
				cnt=0;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("이벤트");
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);

		
		
		
		JRadioButton locationmo = new JRadioButton("\uBAA8\uC544\uBCF4\uAE30");
		locationmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				model.setNumRows(0);
				String cn[]={"위치","세부위치위치","학생수"};
				model.setColumnIdentifiers(cn);
				
				try {
					int cnt=0;
					for(int j=0;j<moa.length;j++){
						sql="select * from "+tabled+" ";
						
						if(!moa[j][1].equals("")){
							sql = sql+"where location='"+moa[j][0]+"' and num='"+moa[j][1]+"';";
							System.out.println("조건문");
						}else{
							sql=sql+"where location='"+moa[j][0]+"';";
						}
						System.out.println(sql);
						ResultSet rs = stmt.executeQuery(sql);
						
						while(rs.next()){
							cnt++;
						}
						String arr[] = new String[3];
						arr[0] = moa[j][0];
						arr[1] = moa[j][1];
						arr[2] = String.valueOf(cnt);
						model.addRow(arr);
						cnt=0;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("이벤트");
				table.setModel(model);
				
				table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(0).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setPreferredWidth(500);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);

			}
		});
		buttonGroup.add(locationmo);
		locationmo.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		locationmo.setBounds(30, 37, 104, 23);
		locationmo.setSelected(true);
		getContentPane().add(locationmo);
		
		JRadioButton locationpl = new JRadioButton("\uD3BC\uCCD0\uBCF4\uAE30");
		locationpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				String cn[]={"위치","세부위치","학년","반","번호","이름"};
				model.setColumnIdentifiers(cn);
				
				try {
			
					for(int j=0;j<moa.length;j++){
						sql="select no from "+tabled+" ";
						
						if(!moa[j][1].equals("")){
							sql = sql+"where location='"+moa[j][0]+"' and num='"+moa[j][1]+"'";
							System.out.println("조건문");
						}else{
							sql=sql+"where location='"+moa[j][0]+"'";
						}
						sql="select * from student where no in("+sql+");";
						System.out.println(sql);
						ResultSet rs = stmt.executeQuery(sql);
						
						while(rs.next()){
							String arr[] = new String[6];
							arr[0] = moa[j][0];
							arr[1] = moa[j][1];
							arr[2] = rs.getString("grade");
							arr[3] = rs.getString("class");
							arr[4] = rs.getString("num");
							arr[5] = rs.getString("name");
							model.addRow(arr);
						}
					
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("이벤트");
				table.setModel(model);
				
				table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(3).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(4).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(5).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(2).setPreferredWidth(60);
				table.getColumnModel().getColumn(3).setPreferredWidth(30);
				table.getColumnModel().getColumn(4).setPreferredWidth(60);
				table.getColumnModel().getColumn(5).setPreferredWidth(100);
				table.getColumnModel().getColumn(0).setPreferredWidth(100);
				table.getColumnModel().getColumn(1).setPreferredWidth(270);

				
			}
		});
		buttonGroup.add(locationpl);
		locationpl.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		locationpl.setBounds(136, 37, 97, 23);
		getContentPane().add(locationpl);
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Excel \uB2E4\uC6B4\uB85C\uB4DC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					fileDlg.setFileFilter(new FileNameExtensionFilter("엑셀 파일","xlsx"));
					int result = fileDlg.showSaveDialog(null);
		            if(result == JFileChooser.APPROVE_OPTION) //파일을 선택하고 열었을때 이벤트
		            {	
		            	
		            	writeToExcell(table, Paths.get(fileDlg.getSelectedFile().toString() + ".xlsx"));


		            }
					
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "오류가 났습니다. 프로그램을 관리자 권한으로 실행시켜 주세요!");
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnNewButton.setBounds(30, 360, 263, 31);
		getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Excel \uB2E4\uC6B4\uB85C\uB4DC");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					fileDlg.setFileFilter(new FileNameExtensionFilter("엑셀 파일","xlsx"));
					int result = fileDlg.showSaveDialog(null);
		            if(result == JFileChooser.APPROVE_OPTION) //파일을 선택하고 열었을때 이벤트
		            {	
		            	
		            	writeToExcell(table_1, Paths.get(fileDlg.getSelectedFile().toString() + ".xlsx"));


		            }
					
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "오류가 났습니다. 프로그램을 관리자 권한으로 실행시켜 주세요!");
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		button.setBounds(362, 360, 265, 31);
		getContentPane().add(button);
		
		TitledBorder name = new TitledBorder("학생외출등록");
		name.setTitleFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(name);
		panel_2.setBounds(29, 415, 598, 105);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("1-2");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(17, 29, 49, 25);
		panel_2.add(lblNewLabel_1);
		
		numbox = new JComboBox();
		numbox.setBounds(56, 32, 41, 21);
		for(int i=1;i<=20;i++){
			numbox.addItem(i);
		}
		panel_2.add(numbox);
		
		JLabel label = new JLabel("\uBC88");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		label.setBounds(103, 30, 49, 25);
		panel_2.add(label);
		
		textField = new JTextField();
		textField.setBounds(94, 61, 140, 29);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\uC678\uCD9C \uC0AC\uC720");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		label_1.setBounds(13, 63, 95, 25);
		panel_2.add(label_1);
		
		
		
		
		JComboBox monthBox1 = new JComboBox();
		for (int i = 1; i <= 12; i++)
			monthBox1.addItem(i);
		monthBox1.setSelectedIndex(cal.get(Calendar.MONTH));
		monthBox1.setBounds(321, 65, 41, 21);
		panel_2.add(monthBox1);
		
		JLabel label_3 = new JLabel("\uC6D4");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_3.setBounds(369, 67, 21, 21);
		panel_2.add(label_3);
		
		JComboBox dayBox1 = new JComboBox();
		for (int i = 1; i <= 31; i++) {
			String m = monthBox1.getSelectedItem().toString();

			if (m.equals("2") && i == 29)
				break;

			if ((m.equals("4") || m.equals("6") || m.equals("9") || m.equals("11")) && i == 31)
				break;

			dayBox1.addItem(i);
		}
		dayBox1.setSelectedIndex(cal.get(Calendar.DATE)-1);
		dayBox1.setBounds(391, 65, 41, 21);
		panel_2.add(dayBox1);
		
		monthBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String d = dayBox1.getSelectedItem().toString();
				dayBox1.removeAllItems();
				String m = monthBox1.getSelectedItem().toString();

				System.out.println(d);
				System.out.println(m);

				for (int i = 1; i <= 31; i++) {

					if (m.equals("2") && i == 29) {
						if (Integer.parseInt(d) > 28)
							d = "28";
						break;
					}

					if ((m.equals("4") || m.equals("6") || m.equals("9") || m.equals("11")) && i == 31) {
						if (Integer.parseInt(d) > 30)
							d = "30";

						break;
					}
					dayBox1.addItem(i);
				}

				dayBox1.setSelectedIndex(Integer.parseInt(d) - 1);
			}
		});
		
		JLabel label_4 = new JLabel("\uC77C");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_4.setBounds(439, 67, 21, 21);
		panel_2.add(label_4);
		
		JComboBox hourBox1 = new JComboBox();
		for (int i = 0; i <= 24; i++)
			hourBox1.addItem(i);
		hourBox1.setSelectedIndex(cal.get(Calendar.HOUR_OF_DAY));
		hourBox1.setBounds(461, 65, 41, 21);
		panel_2.add(hourBox1);
		
		JLabel label_5 = new JLabel("\uC2DC");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_5.setBounds(509, 67, 21, 21);
		panel_2.add(label_5);
		
		JComboBox miniteBox1 = new JComboBox();
		for (int i = 0; i <= 60; i++)
			miniteBox1.addItem(i);
		miniteBox1.setSelectedIndex(cal.get(Calendar.MINUTE));
		miniteBox1.setBounds(528, 65, 41, 21);
		panel_2.add(miniteBox1);
		
		JLabel label_6 = new JLabel("\uBD84");
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_6.setBounds(576, 67, 21, 21);
		panel_2.add(label_6);
		
		JLabel label_2 = new JLabel("\uC2DC\uC791\uC77C : ");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_2.setBounds(249, 29, 65, 21);
		panel_2.add(label_2);
		
		
		JComboBox monthBox2 = new JComboBox();
		for (int i = 1; i <= 12; i++)
			monthBox2.addItem(i);
		monthBox2.setSelectedIndex(cal.get(Calendar.MONTH));
		monthBox2.setBounds(321, 26, 41, 21);
		panel_2.add(monthBox2);
		
		JLabel label_7 = new JLabel("\uC6D4");
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_7.setBounds(369, 28, 21, 21);
		panel_2.add(label_7);
		
		JComboBox dayBox2 = new JComboBox();
		for (int i = 1; i <= 31; i++) {
			String m = monthBox2.getSelectedItem().toString();

			if (m.equals("2") && i == 29)
				break;

			if ((m.equals("4") || m.equals("6") || m.equals("9") || m.equals("11")) && i == 31)
				break;

			dayBox2.addItem(i);
		}
		dayBox2.setSelectedIndex(cal.get(Calendar.DATE)-1);
		dayBox2.setBounds(391, 26, 41, 21);
		panel_2.add(dayBox2);
		
		monthBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String d = dayBox2.getSelectedItem().toString();
				dayBox2.removeAllItems();
				String m = monthBox2.getSelectedItem().toString();

				System.out.println(d);
				System.out.println(m);

				for (int i = 1; i <= 31; i++) {

					if (m.equals("2") && i == 29) {
						if (Integer.parseInt(d) > 28)
							d = "28";
						break;
					}

					if ((m.equals("4") || m.equals("6") || m.equals("9") || m.equals("11")) && i == 31) {
						if (Integer.parseInt(d) > 30)
							d = "30";

						break;
					}
					dayBox2.addItem(i);
				}

				dayBox2.setSelectedIndex(Integer.parseInt(d) - 1);
			}
		});
		
		JLabel label_8 = new JLabel("\uC77C");
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_8.setBounds(439, 28, 21, 21);
		panel_2.add(label_8);
		
		JComboBox hourBox3 = new JComboBox();
		for (int i = 0; i <= 24; i++)
			hourBox3.addItem(i);
		hourBox3.setSelectedIndex(cal.get(Calendar.HOUR_OF_DAY));
		hourBox3.setBounds(461, 26, 41, 21);
		panel_2.add(hourBox3);
		
		JLabel label_9 = new JLabel("\uC2DC");
		label_9.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_9.setBounds(509, 28, 21, 21);
		panel_2.add(label_9);
		
		JComboBox miniteBox2 = new JComboBox();
		for (int i = 0; i <= 60; i++)
			miniteBox2.addItem(i);
		miniteBox2.setSelectedIndex(cal.get(Calendar.MINUTE));
		miniteBox2.setBounds(528, 26, 41, 21);
		panel_2.add(miniteBox2);
		
		JLabel label_10 = new JLabel("\uBD84");
		label_10.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_10.setBounds(576, 28, 21, 21);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("귀가일 : ");
		label_11.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_11.setBounds(248, 64, 65, 21);
		panel_2.add(label_11);
		
		
		JButton btnNewButton_1 = new JButton("\uB4F1\uB85D");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sql = "INSERT INTO `dgsw`.`outing` (`no`, `stmon`, `stday`, `sthour`, `stmin`, `endmon`, `endday`, `endhour`, `endmin`, `reason`) VALUES "
						+ "('"+getno()+"', '"+monthBox2.getSelectedItem().toString()+"', '"+dayBox2.getSelectedItem().toString()+"', '"+hourBox3.getSelectedItem().toString()+"', '"+miniteBox2.getSelectedItem().toString()+"', '"+
						monthBox1.getSelectedItem().toString()+"', '"+dayBox1.getSelectedItem().toString()+"', '"+hourBox1.getSelectedItem().toString()+"', '"+miniteBox1.getSelectedItem().toString()+"', '"+textField.getText()+"');";
				System.out.println(sql);
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(129, 25, 108, 31);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeacherClass(id);
				th.interrupt(); dispose();
			}
		});
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnNewButton_2.setBounds(207, 11, 109, 23);
		getContentPane().add(btnNewButton_2);
		
		setResizable(false);
		setBounds(100, 100, 667, 559);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JRadioButton locationstudent = new JRadioButton("\uD559\uC0DD\uBCC4");
		locationstudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				
				String cn[]={"학년","반","번호","이름","위치","세부위치"};
				model.setColumnIdentifiers(cn);
				
				try {
					sql="select * from "+tabled+";";
					ResultSet rs = stmt.executeQuery(sql);
					sql="select * from student";
					Statement stmtt = conn.createStatement();
					ResultSet rs2 = stmtt.executeQuery(sql);
					
					while(rs.next()){
						rs2.next();
						String arr[] = new String[6];
						arr[0] = rs2.getString("grade");
						arr[1] = rs2.getString("class");
						arr[2] = rs2.getString("num");
						arr[3] = rs2.getString("name");
						arr[4] = rs.getString("location");
						arr[5] = rs.getString("num");
						model.addRow(arr);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("이벤트");
				table.setModel(model);
				
				table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(3).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(4).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(5).setCellRenderer(dtcr);
				table.getColumnModel().getColumn(0).setPreferredWidth(60);
				table.getColumnModel().getColumn(1).setPreferredWidth(30);
				table.getColumnModel().getColumn(2).setPreferredWidth(60);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(100);
				table.getColumnModel().getColumn(5).setPreferredWidth(300);
				
				
			}
		});
		buttonGroup.add(locationstudent);
		locationstudent.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		locationstudent.setBounds(237, 37, 97, 23);
		getContentPane().add(locationstudent);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(30, 10, 57, 15);
		getContentPane().add(lblNewLabel);
		
		
		
		setVisible(true);
		th.start();
	}
	protected String getno() {
		
		sqll="select no from student where grade="+gradename+" and class="+classname+" and num="+numbox.getSelectedItem();
		System.out.println(sqll);
		Statement stmtt;
		try {
			stmtt = conn.createStatement();
		
			ResultSet rs = stmtt.executeQuery(sqll);
			while(rs.next()){
				no=rs.getString("no");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return no;
	}
	public void getday(){//날짜
		sql="select * from date";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				mon=rs.getInt("month");
				day=rs.getInt("day");
				hour=rs.getInt("hour");
				min=rs.getInt("min");
				daw=rs.getInt("daw");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabled=String.valueOf(mon)+"_"+String.valueOf(day);
		timelabel.setText(mon+"월 "+day+"일 "+hour+"시 "+min+"분");
	}
	@Override
	public void run() {
		Calendar cal = Calendar.getInstance();
		
		try{
			while(true){
				cal = Calendar.getInstance();
				
				Thread.sleep(60000);
				if(cal.get(Calendar.SECOND)<4){
					Thread.sleep(5000);
				
					
				}
				System.out.println("다시불러옴");
					
					
				dispose();
				new TeacherClass("teacher"+gradename+classname); 
				break;	
					
				
			
			}
		} catch (InterruptedException e) {

			System.out.println("쓰레드 중지 예외");
		} finally {


		System.out.println("Thread is dead…");

		}
		
	}
	
	public void writeToExcell(JTable table, Path path) throws FileNotFoundException, IOException {
	    new WorkbookFactory();
	    Workbook wb = new XSSFWorkbook(); //Excell workbook
	    Sheet sheet = wb.createSheet(); //WorkSheet
	    Row row = sheet.createRow(2); //Row created at line 3
	    TableModel model = table.getModel(); //Table model


	    Row headerRow = sheet.createRow(0); //Create row at line 0
	    for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
	        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
	    }

	    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
	        for(int cols = 0; cols < table.getColumnCount(); cols++){ //For each table column
	            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
	        }

	        //Set the row to the next one in the sequence 
	        row = sheet.createRow((rows + 3)); 
	    }
	    wb.write(new FileOutputStream(path.toString()));//Save the file     
	    wb.close();
	    JOptionPane.showMessageDialog(null,"다운성공!!!!");
	}
	
	public void moabogi(String tables, String co){
		if(co.equals("bus")){
			gymmo(tables);
			return;
		}
		model_1.setNumRows(0);
		String cn[]={"반","학생수"};
		model_1.setColumnIdentifiers(cn);
		
		try{
			int cnt=0;
			int cntt=0;
			String arr[] = new String[2];
			sql="select "+co+" from "+tables+" where no<=20 and "+co+"=1";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[0]="1-1";
			arr[1]=String.valueOf(cnt);
			model_1.addRow(arr);
			cntt+=cnt;
			cnt=0;
			
			sql="select "+co+" from "+tables+" where no>20 and no<=39 and "+co+"=1";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[0]="1-2";
			arr[1]=String.valueOf(cnt);
			model_1.addRow(arr);
			cntt+=cnt;
			cnt=0;
			
			sql="select "+co+" from "+tables+" where no>=40 and "+co+"=1";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[0]="1-3";
			arr[1]=String.valueOf(cnt);
			model_1.addRow(arr);
			cntt+=cnt;
			cnt=0;
			
			arr[0]="합계";
			arr[1]=String.valueOf(cntt);
			model_1.addRow(arr);
		}catch(SQLException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		table_1.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(1).setCellRenderer(dtcr);
	}
	
	private void gymmo(String tables) {
		String co="bus";
		model_1.setNumRows(0);
		String cn[]={"반","대곡역","동대구역"};
		model_1.setColumnIdentifiers(cn);
		
		try{
			int cnt=0;
			int cntt=0;
			int cnttt=0;
			String arr[] = new String[3];
			sql="select "+co+" from "+tables+" where no<=20 and "+co+"=1";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[0]="1-1";
			arr[1]=String.valueOf(cnt);
			cntt+=cnt;
			cnt=0;
			sql="select "+co+" from "+tables+" where no<=20 and "+co+"=2";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[2]=String.valueOf(cnt);
			model_1.addRow(arr);
			cnttt+=cnt;
			cnt=0;
			
			
			sql="select "+co+" from "+tables+" where no>=20 and no<=39 and "+co+"=1";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[0]="1-2";
			arr[1]=String.valueOf(cnt);
			cntt+=cnt;
			cnt=0;
			sql="select "+co+" from "+tables+" where no>=20 and no<=39 and "+co+"=2";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[2]=String.valueOf(cnt);
			model_1.addRow(arr);
			cnttt+=cnt;
			cnt=0;
			sql="select "+co+" from "+tables+" where no>=40 and "+co+"=1";
				rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[0]="1-3";
			arr[1]=String.valueOf(cnt);
			cntt+=cnt;
			cnt=0;
			sql="select "+co+" from "+tables+" where no>=40 and "+co+"=2";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				cnt++;
			}
			arr[2]=String.valueOf(cnt);
			model_1.addRow(arr);
			cnttt+=cnt;
			cnt=0;
			
			arr[0]="합계";
			arr[1]=String.valueOf(cntt);
			arr[2]=String.valueOf(cnttt);
			model_1.addRow(arr);
			
		}catch(SQLException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		table_1.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		
	}
	public void studentsbogi(String tables, String co){
		if(co.equals("bus")){
			gymstu(tables);
			return;
		}
		model_1.setNumRows(0);
		
		String cn[]={"학년","반","번호","이름"};
		model_1.setColumnIdentifiers(cn);
		
		try {
			sql="select no from "+tables+" where "+co+"=1";
			sql="select * from student where no in("+sql+");";
			Statement stmtt = conn.createStatement();
			ResultSet rs2 = stmtt.executeQuery(sql);
			
			while(rs2.next()){
				String arr[] = new String[6];
				arr[0] = rs2.getString("grade");
				arr[1] = rs2.getString("class");
				arr[2] = rs2.getString("num");
				arr[3] = rs2.getString("name");
				model_1.addRow(arr);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("이벤트");
		table_1.setModel(model_1);
		
		table_1.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(30);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
		
	}
	private void gymstu(String tables) {
		String co="bus";
		model_1.setNumRows(0);
		
		String cn[]={"학년","반","번호","이름","도착지"};
		model_1.setColumnIdentifiers(cn);
		
		try {
			sql="select no from "+tables+" where "+co+"=1";
			sql="select * from student where no in("+sql+");";
			Statement stmtt = conn.createStatement();
			ResultSet rs2 = stmtt.executeQuery(sql);
			
			while(rs2.next()){
				String arr[] = new String[6];
				arr[0] = rs2.getString("grade");
				arr[1] = rs2.getString("class");
				arr[2] = rs2.getString("num");
				arr[3] = rs2.getString("name");
				arr[4] = "대곡역";
				model_1.addRow(arr);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sql="select no from "+tables+" where "+co+"=2";
			sql="select * from student where no in("+sql+");";
			Statement stmtt = conn.createStatement();
			ResultSet rs2 = stmtt.executeQuery(sql);
			
			while(rs2.next()){
				String arr[] = new String[6];
				arr[0] = rs2.getString("grade");
				arr[1] = rs2.getString("class");
				arr[2] = rs2.getString("num");
				arr[3] = rs2.getString("name");
				arr[4] = "동대구역";
				model_1.addRow(arr);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("이벤트");
		table_1.setModel(model_1);
		
		table_1.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(30);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
		
	}
}
