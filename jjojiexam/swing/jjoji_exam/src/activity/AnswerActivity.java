package activity;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;

public class AnswerActivity extends JPanel {
	JTable table;
	JTextField answerTitle;
	JTextField answerStartDate;
	JTextField answerEndDate;
	JTextField answerUseTime;
	JTextField answerUseTimeS;

	/**
	 * Create the panel.
	 */
	JLabel answerExamCount;
	
	JComboBox answerEndHour,answerEndMinute;
	JComboBox answerStartHour,answerStartMinute;
	JButton answerSaveBtn;
	JButton answerDeleteBtn;
	JButton answerBackBtn;
	
	DefaultTableModel model;
	
	public AnswerActivity() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(869, 225));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(20, 10, 10, 10));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new EmptyBorder(10, 100, 0, 110));
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 3, 30, 0));
		
		answerSaveBtn = new JButton("\uC800\uC7A5");
		
		answerSaveBtn.setForeground(Color.WHITE);
		answerSaveBtn.setBackground(new Color(51, 204, 255));
		panel_3.add(answerSaveBtn);
		
		answerDeleteBtn = new JButton("\uC0AD\uC81C");
		answerDeleteBtn.setForeground(Color.WHITE);
		answerDeleteBtn.setBackground(new Color(51, 204, 255));
		panel_3.add(answerDeleteBtn);
		
		answerBackBtn = new JButton("\uCDE8\uC18C");
		answerBackBtn.setForeground(Color.WHITE);
		answerBackBtn.setBackground(new Color(51, 204, 255));
		panel_3.add(answerBackBtn);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new EmptyBorder(0, 0, 0, 10));
		panel_1.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("               \uC81C\uBAA9");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblNewLabel);
		
		JLabel label = new JLabel("\uC2DC\uC791\uC77C");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(label);
		
		JLabel label_1 = new JLabel("\uC885\uB8CC\uC77C");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(label_1);
		
		JLabel label_2 = new JLabel("\uC2DC\uAC04");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(label_2);
		
		JLabel label_3 = new JLabel("\uBB38\uD56D\uC218");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(label_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_1.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panel_9.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_9);
		
		answerTitle = new JTextField();
		panel_9.add(answerTitle);
		answerTitle.setColumns(30);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_6);
		
		answerStartDate = new JTextField();
		answerStartDate.setEditable(false);
		answerStartDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new CalCal().form(answerStartDate);
			}
		});
		panel_6.add(answerStartDate);
		answerStartDate.setColumns(7);
		
		answerStartHour = new JComboBox();
		answerStartHour.setBackground(Color.WHITE);
		answerStartHour.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		panel_6.add(answerStartHour);
		
		JLabel lblNewLabel_1 = new JLabel(":");
		panel_6.add(lblNewLabel_1);
		
		answerStartMinute = new JComboBox();
		answerStartMinute.setBackground(Color.WHITE);
		answerStartMinute.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		panel_6.add(answerStartMinute);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_7);
		
		answerEndDate = new JTextField();
		answerEndDate.setEditable(false);
		answerEndDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new CalCal().form(answerEndDate);
			}
		});
		answerEndDate.setColumns(7);
		panel_7.add(answerEndDate);
		
		answerEndHour = new JComboBox();
		answerEndHour.setBackground(Color.WHITE);
		answerEndHour.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		panel_7.add(answerEndHour);
		
		JLabel label_4 = new JLabel(":");
		panel_7.add(label_4);
		
		answerEndMinute = new JComboBox();
		answerEndMinute.setBackground(Color.WHITE);
		answerEndMinute.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		panel_7.add(answerEndMinute);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_8);
		
		answerUseTime = new JTextField();
		panel_8.add(answerUseTime);
		answerUseTime.setColumns(3);
		
		JLabel lblNewLabel_2 = new JLabel("\uBD84");
		panel_8.add(lblNewLabel_2);
		
		answerUseTimeS = new JTextField();
		answerUseTimeS.setColumns(3);
		panel_8.add(answerUseTimeS);
		
		JLabel label_5 = new JLabel("\uCD08");
		panel_8.add(label_5);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		FlowLayout flowLayout_4 = (FlowLayout) panel_10.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_10);
		
		answerExamCount = new JLabel("25\uBB38\uD56D");
		answerExamCount.setHorizontalTextPosition(SwingConstants.CENTER);
		answerExamCount.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(answerExamCount);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new EmptyBorder(20, 10, 10, 20));
		panel.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(5, 1, 10, 10));
		
		JButton btnNewButton = new JButton("\uBB38\uC81C \uC218\uC815");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeActivity ma = new MakeActivity(rest.Rest.examNo);
				ma.saveBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ma.deleteData();
						ma.insertExamQ();
						ma.insertExamA();
						
						JOptionPane.showMessageDialog(null, "저장되었습니다", "아 자고싶어", JOptionPane.INFORMATION_MESSAGE);
						dataUpdate();
						ma.setVisible(false);
						
					}

					
				});
				ma.setVisible(true);
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(51, 204, 255));
		panel_2.add(btnNewButton);
		
		JButton button_1 = new JButton("\uBB38\uC81C \uD30C\uC77C \uB0B4\uBCF4\uB0B4\uAE30");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				 FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                        "jjoji 파일", // 파일 이름에 창에 출력될 문자열
	                        "jjoji"); // 파일 필터로 사용되는 확장자. *.jpg. *.gif만 나열됨
	            chooser.setFileFilter(filter); // 파일 다이얼로그에 파일 필터 설정
	            
	            // 파일 다이얼로그 출력
	            int ret = chooser.showSaveDialog(null);
	            if(ret != JFileChooser.APPROVE_OPTION) { // 사용자가  창을 강제로 닫았거나 취소 버튼을 누른 경우
	                return;
	            }
	            
	            
	            String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명을 알아온다.
	            System.out.println(filePath);
				
	            
	            BufferedOutputStream bs = null;
	        	try {
	        		bs = new BufferedOutputStream(new FileOutputStream(filePath+".jjoji"));
	        		String str = String.valueOf(rest.Rest.examNo);
	        		bs.write(str.getBytes()); //Byte형으로만 넣을 수 있음

	        	} catch (Exception e1) {
	                        e1.getStackTrace();
	        		// TODO: handle exception
	        	}finally {
	        		try {
						bs.close();

						JOptionPane.showMessageDialog(null, "파일이 출력되었습니다", "확인", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} //반드시 닫는다.
	        	} 
	            
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(51, 204, 255));
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("\uBB38\uC81C \uD30C\uC77C \uBD88\uB7EC\uC624\uAE30");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 JFileChooser chooser = new JFileChooser();
				 FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                        "jjoji 파일", // 파일 이름에 창에 출력될 문자열
	                        "jjoji"); // 파일 필터로 사용되는 확장자. *.jpg. *.gif만 나열됨
	            chooser.setFileFilter(filter); // 파일 다이얼로그에 파일 필터 설정
	            
	            // 파일 다이얼로그 출력
	            int ret = chooser.showOpenDialog(null);
	            if(ret != JFileChooser.APPROVE_OPTION) { // 사용자가  창을 강제로 닫았거나 취소 버튼을 누른 경우
	                return;
	            }
	            
	            // 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
	            String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명을 알아온다.
	            System.out.println(filePath);
		    
			
	            int examNo = 0;
	            
	            try {

	     	        FileInputStream fileStream = null; // 파일 스트림
	     	        
	     	        fileStream = new FileInputStream( filePath);// 파일 스트림 생성
	     	        //버퍼 선언
	     	        byte[ ] readBuffer = new byte[fileStream.available()];
	     	        while (fileStream.read( readBuffer ) != -1){}
	     	        
	     	        String s = new String(readBuffer);
	     	        System.out.println(s); //출력

	     	        examNo = Integer.parseInt(s);
	     	        
	     	        fileStream.close(); //스트림 닫기
	     	    } catch (Exception e1) {
	     		e1.getStackTrace();
	     	    }
			
	            MakeActivity ma = new MakeActivity(examNo);
				ma.saveBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ma.deleteData();
						ma.insertExamQ();
						ma.insertExamA();
						
						JOptionPane.showMessageDialog(null, "저장되었습니다", "아 자고싶어", JOptionPane.INFORMATION_MESSAGE);
						dataUpdate();
						ma.setVisible(false);
						
					}

					
				});
				ma.setVisible(true);
			}
				
			
		});
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(51, 204, 255));
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("\uD55C\uAE00\uD30C\uC77C\uB85C \uCD9C\uB825");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
 
	            // 파일 다이얼로그 출력
	            int ret = chooser.showSaveDialog(null);
	            if(ret != JFileChooser.APPROVE_OPTION) { // 사용자가  창을 강제로 닫았거나 취소 버튼을 누른 경우
	                return;
	            }
	            
	            
	            
	            // 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
	            String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명을 알아온다.
	            System.out.println(filePath);
	            
	            
	            
	            try {
	            	FileOutputStream output = new FileOutputStream(filePath+".txt");
	            
	            	String text = "";
		            
	            	String q1 = "select * from examQ where examNo = ?";
					PreparedStatement pstmt1 = rest.Rest.con.prepareStatement(q1);
				
					pstmt1.setInt(1, rest.Rest.examNo);
					
					ResultSet rs1 = pstmt1.executeQuery();
	            
					
					while(rs1.next()) {

						String data = rs1.getString("examQNumber")+". "+rs1.getString("examQName"); 
						text+= data+"                    ";
						output.write((data+"\n\n").getBytes());
						
						String q2 = "select * from examA where examQNo = ?";
						PreparedStatement pstmt2 = rest.Rest.con.prepareStatement(q2);
					
						pstmt2.setInt(1, rs1.getInt("examQNo"));
						
						ResultSet rs2 = pstmt2.executeQuery();
		            
						
						while(rs2.next()) {
							
							
							
							data = rs1.getString("examQNumber")+"-"+rs2.getString("examANumber")+". "+rs2.getString("examAName");
							text+= data + "         ";
							output.write((data+"\n").getBytes());
						}
						
						output.write("\n\n\n".getBytes());
						text+="                                                ";
						
						
					}

					HWPFile hwpFile = HWPReader.fromFile("source/test.hwp");
				
					Section s = hwpFile.getBodyText().getSectionList().get(0);
					
					Paragraph p = s.getParagraph(0);

					p.getText().addString(text);
					HWPWriter.toFile(hwpFile, filePath+".hwp");
					
					
					
					
					
					

			        output.close();
					
					
					JOptionPane.showMessageDialog(null, "파일이 출력되었습니다", "확인", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
	            
	            
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(new Color(51, 204, 255));
		panel_2.add(button_3);
		
		JButton button = new JButton("\uC815\uC624\uD45C \uC778\uC1C4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 204, 255));
		panel_2.add(button);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		add(table, BorderLayout.CENTER);
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"userNo", "userName", "score", "className"
				}
			);
		table.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		
		
		
	}
	

	
	
	public void dataUpdate() {
		String q = "select *,(select count(*) from examQ q where q.examNo = e1.examNo) examCount from exam e1 where e1.examNo = ?";
		
		
		
		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
		
			pstmt.setInt(1, rest.Rest.examNo);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				
				
				answerTitle.setText(rs.getString("examName"));
				
				String examUseTimeText = rs.getString("examUseTime");
				String[] examUseTimeSplit = examUseTimeText.split(":");
				int examH = Integer.parseInt(examUseTimeSplit[0]);
				int examM = Integer.parseInt(examUseTimeSplit[1]);
				int examS = Integer.parseInt(examUseTimeSplit[2]);
				answerUseTime.setText(String.valueOf(examH*60+examM));
				answerUseTimeS.setText(String.valueOf(examS));
				answerExamCount.setText(rs.getString("examCount")+" 문항");
				
				String examStartTime = rs.getString("examStartTime");
				
				answerStartDate.setText(examStartTime.substring(0, 10));
				System.out.println(examStartTime);
				answerStartHour.setSelectedIndex(Integer.parseInt(examStartTime.substring(11,13))-1);
				answerStartMinute.setSelectedIndex(Integer.parseInt(examStartTime.substring(14,16)));
				
				
				String examEndTime = rs.getString("examEndTime");
				
				answerEndDate.setText(examEndTime.substring(0, 10));
				answerEndHour.setSelectedIndex(Integer.parseInt(examEndTime.substring(11,13))-1);
				answerEndMinute.setSelectedIndex(Integer.parseInt(examEndTime.substring(14,16)));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setNumRows(0);
		
		q = "select distinct u1.userNo,u1.userName, " + 
				"(select count(*) from exam e, examQ q, result r, resultA a where e.examNo = r.examNo and r.resultNo = a.resultNo and q.examNo = e.examNo and q.examQNumber = a.resultANumber and q.examARight = a.examARight and r.userNo = r1.userNo and r.resultNo = r1.resultNo) " + 
				"/(select count(*) from examQ q where q.examNo = e1.examNo)*100 score, " + 
				"c.className " + 
				"from exam e1, result r1, class c, user u1 " + 
				"where e1.examNo = r1.examNo and c.classCode = e1.classCode and r1.userNo = u1.userNo and e1.examNo = ?;";
		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
		
			pstmt.setInt(1, rest.Rest.examNo);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				
				
				Object a[] = new Object[4];
				
				a[0] = rs.getString("userNo");
				a[1] = rs.getString("userName");
				a[2] = rs.getString("score");
				a[3] = rs.getString("className");
				System.out.println(a[0]);
				model.addRow(a);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
