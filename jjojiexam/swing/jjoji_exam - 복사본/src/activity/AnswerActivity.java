package activity;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	
	public AnswerActivity() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		scrollPane.add(table);
		
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
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(51, 204, 255));
		panel_2.add(btnNewButton);
		
		JButton button_1 = new JButton("\uBB38\uC81C \uD30C\uC77C \uB0B4\uBCF4\uB0B4\uAE30");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(51, 204, 255));
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("\uBB38\uC81C \uD30C\uC77C \uBD88\uB7EC\uC624\uAE30");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(51, 204, 255));
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("\uD55C\uAE00\uD30C\uC77C\uB85C \uC778\uC1C4");
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(new Color(51, 204, 255));
		panel_2.add(button_3);
		
		JButton button = new JButton("\uC815\uC624\uD45C \uC778\uC1C4");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 204, 255));
		panel_2.add(button);

		
		
		
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
				answerExamCount.setText(rs.getString("examCount")+" ¹®Ç×");
				
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
		
	}

}
