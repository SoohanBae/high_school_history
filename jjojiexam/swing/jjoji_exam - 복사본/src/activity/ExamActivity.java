package activity;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExamActivity extends JPanel {

	/**
	 * Create the panel.
	 */
	JPanel examClassGrid;
	JButton addTeacher;
	
	ArrayList<ExamRow> examRowList = new ArrayList<ExamRow>();
	ArrayList<Integer> examNoList = new ArrayList<Integer>();
	String openClassCode = "";
	JButton addExam;
	
	public ExamActivity() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		

		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setFocusable(false);
		
	
		
		JScrollPane scrollPane = new JScrollPane(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		panel.setBackground(Color.WHITE);
		panel_3.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		examClassGrid = new JPanel();
		panel.add(examClassGrid);
		examClassGrid.setBackground(Color.WHITE);
		examClassGrid.setBorder(new EmptyBorder(10, 0, 0, 0));
		examClassGrid.setFocusable(false);
		examClassGrid.setLayout(new GridLayout(0, 1, 0, 20));
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("     \uC2DC\uD5D8 \uB9AC\uC2A4\uD2B8");
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setRequestFocusEnabled(true);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		addTeacher = new JButton("\uC120\uC0DD\uB2D8 \uCD94\uAC00");
		addTeacher.setForeground(Color.WHITE);
		panel_2.add(addTeacher);
		addTeacher.setBackground(new Color(0,176,240));
		
		addExam = new JButton("\uC2DC\uD5D8 \uCD94\uAC00");
		addExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addExam.setForeground(Color.WHITE);
		addExam.setBackground(new Color(0, 176, 240));
		panel_2.add(addExam);
		
		
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(null);
		scrollPane.setFocusable(false);
		scrollPane.setPreferredSize(new Dimension(868, 563));
		add(scrollPane);

	}

	public String getTimeFormat(String a) {
		String returnData = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			
			Date b = sdf.parse(a);
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM월 dd일 HH:mm");
			
			returnData = sdf2.format(b);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnData;
	}
	
	public void updateClass(String classCode) {
		//select u.classCode, c.className, (select GROUP_CONCAT(u1.userName SEPARATOR ', ') from user u1, classUser c1 where u1.userNo = c1.userNo and u1.userType = 2 and c1.classCode = c.classCode group by c1.classCode) as classTeacher from classUser u,class c where u.userNo = 10 and c.classCode = u.classCode;
		examClassGrid.removeAll();
		openClassCode = classCode;
		
		
		String q = "select e1.*, (select count(*) from examQ q where q.examNo = e1.examNo) examCount,\r\n" + 
				"(select GROUP_CONCAT(u1.userName SEPARATOR ', ') from user u1, classUser c1 where u1.userNo = c1.userNo and u1.userType = 2 and c1.classCode = e1.classCode group by c1.classCode) classTeacher\r\n" + 
				"from exam e1 where e1.classCode = ? order by examEndTime desc;";
		
		
		
		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
		
			pstmt.setString(1, classCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			examRowList = new ArrayList<ExamRow>();
			examNoList = new ArrayList<Integer>();
			
			while(rs.next()) {
				
				//"<html>4월 1일 11:30 ~ 4월 2일 12:20<br/>	김동균 / 25문항 / 10분</html>"
				

				String t2 = String.format("<html>%s ~ %s<br/> %s / %d문항 / %d분</html>", 
						getTimeFormat(rs.getString("examStartTime")),
						getTimeFormat(rs.getString("examEndTime")),
						rs.getString("classTeacher"),
						rs.getInt("examCount"),10);
				
				System.out.println(getTimeFormat(rs.getString("examStartTime")));
				
				
				
				
				
				
				
				
				ExamRow examRow = new ExamRow(rs.getString("examName"),t2);
				
				examRowList.add(examRow);
				examNoList.add(rs.getInt("examNo"));
				
				
				examClassGrid.add(examRow);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		revalidate();
		
	}
	
	
	
}
