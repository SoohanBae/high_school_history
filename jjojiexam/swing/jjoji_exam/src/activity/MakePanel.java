package activity;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.ScrollPaneConstants;

public class MakePanel extends JPanel {
	JTextField examQNameText;
	JTextField examQImageText;
	JTextField examAText;
	ButtonGroup buttonGroup = new ButtonGroup();

	JPanel rowPanel;

	
	int examQNo;
	String examQName;
	String examQImage;
	String examARight;
	int examACount;
	
	Boolean showModeBool;
	
	ArrayList<JTextField> rowTextList = new ArrayList<JTextField>();
	ArrayList<JRadioButton> rowRadioList = new ArrayList<JRadioButton>();
	private JScrollPane examGae;
	private JPanel examJu;
	
	public MakePanel(int examQNo, String examQName, String examQImage,String examARight,int examACount) {
		this.examQNo = examQNo;
		this.examQName = examQName;
		this.examQImage = examQImage;
		this.examARight = examARight;
		this.examACount = examACount;
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new EmptyBorder(15, 10, 10, 10));
		add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_3.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_1.add(panel);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblNewLabel = new JLabel("\uBB38\uC81C \uC9C8\uBB38");
		lblNewLabel.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setPreferredSize(new Dimension(100, 35));
		panel.add(lblNewLabel);
		
		examQNameText = new JTextField();
		examQNameText.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 24));
		panel.add(examQNameText);
		examQNameText.setColumns(40);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uBBF8\uC9C0 \uACBD\uB85C");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setPreferredSize(new Dimension(100, 15));
		panel_2.add(lblNewLabel_1);
		
		examQImageText = new JTextField();
		examQImageText.setColumns(40);
		panel_2.add(examQImageText);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		examGae = new JScrollPane(panel_5);
		
		rowPanel = new JPanel();
		rowPanel.setBackground(Color.WHITE);
		panel_5.add(rowPanel);
		rowPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		examGae.setPreferredSize(new Dimension(400, 350));
		panel_4.add(examGae, BorderLayout.CENTER);
		examGae.setFocusable(false);
		examGae.setBorder(null);
		examGae.setBackground(Color.WHITE);
		
		examJu = new JPanel();
		examJu.setBackground(Color.WHITE);
		panel_4.add(examJu, BorderLayout.NORTH);
		examJu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		
		JLabel lblNewLabel_2 = new JLabel("\uB2E8\uB2F5\uC2DD \uB2F5");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setPreferredSize(new Dimension(100, 15));
		examJu.add(lblNewLabel_2);
		
		examAText = new JTextField();
		examJu.add(examAText);
		examAText.setColumns(10);
		
		examQNameText.setText(examQName);
		examQImageText.setText(examQImage);
		
		
		examAText.setText(examARight);
		baseData();
		rowPanelUpdate();
	
		
		if(examACount != 0) {
			showMode(true);
			
		}else {
			showMode(false);
		}
		
		
	}
	
	
	public void showMode(boolean gae){
		showModeBool = gae;
		examGae.setVisible(gae);
		examJu.setVisible(!gae);
	}
	
	public void baseData() {
		String q = "select * from examA where examQNo = ? order by examANumber;";

		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
		
			pstmt.setInt(1, examQNo);
			ResultSet rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()){
				JTextField examANoText = new JTextField();
				examANoText.setColumns(10);
				examANoText.setText(rs.getString("examAName"));
				
				rowTextList.add(examANoText);
				
				JRadioButton examANumber = new JRadioButton((i+1)+".");
				buttonGroup.add(examANumber);
				examANumber.setBackground(Color.WHITE);
				
				if(i == Integer.parseInt(examARight) - 1) {
					examANumber.setSelected(true);
				}
				rowRadioList.add(examANumber);
				
				i++;
			}
			
			if(rowTextList.size() == 0) {
				JTextField examANoText = new JTextField();
				examANoText.setColumns(10);
				examANoText.setText("´ä ÀÔ·Â");
				
				rowTextList.add(examANoText);
				
				JRadioButton examANumber = new JRadioButton("1.");
				buttonGroup.add(examANumber);
				examANumber.setBackground(Color.WHITE);
				
				examANumber.setSelected(true);

				rowRadioList.add(examANumber);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rowPanelUpdate(){
		
		rowPanel.removeAll();
		for(int i = 0; i < rowTextList.size(); i++) {
			JPanel examANoPanel = new JPanel();
			examANoPanel.setBackground(Color.WHITE);
			FlowLayout fl_examANoPanel = (FlowLayout) examANoPanel.getLayout();
			fl_examANoPanel.setAlignment(FlowLayout.LEFT);
			rowPanel.add(examANoPanel);
			
			examANoPanel.add(rowRadioList.get(i));
			examANoPanel.add(rowTextList.get(i));
			
			
		}
		
		

		

		
		
		
		
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		FlowLayout flowLayout_4 = (FlowLayout) panel_9.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		rowPanel.add(panel_9);
			
		JButton aAddBtn = new JButton("\uCD94\uAC00");
		
		aAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField examANoText = new JTextField();
				examANoText.setColumns(10);
				
				rowTextList.add(examANoText);
				
				JRadioButton examANumber = new JRadioButton((rowRadioList.size()+1)+".");
				buttonGroup.add(examANumber);
				examANumber.setBackground(Color.WHITE);
				

				rowRadioList.add(examANumber);
				
				rowPanelUpdate();
			}
		});
		aAddBtn.setForeground(Color.WHITE);
		aAddBtn.setBackground(new Color(0, 176, 240));
		panel_9.add(aAddBtn);
		
		JButton aDelBtn = new JButton("\uC0AD\uC81C");
		aDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rowTextList.remove(rowTextList.size()-1);

			
				rowRadioList.remove(rowRadioList.size()-1);
				
				rowPanelUpdate();
			}
		});
		aDelBtn.setForeground(Color.WHITE);
		aDelBtn.setBackground(new Color(0, 176, 240));
		panel_9.add(aDelBtn);
		
		revalidate();
		
	}
	

}
