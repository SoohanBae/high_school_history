package activity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakeActivity extends JFrame {

	private JPanel contentPane;
	JPanel makeMainPanel;
	
	int selection = 0;
	


	/**
	 * Create the frame.
	 */
	
	ArrayList<MakePanel> makePanelList = new ArrayList<MakePanel>();
	CardLayout mainLayout;
	
	int examNo;
	private JLabel QCountLabel;
	private JComboBox comboBox;
	public JButton saveBtn;
	
	public MakeActivity(int examNo) {
		this.examNo = examNo;
		setDefaultCloseOperation(2);
		setBounds(0, 0, 1152, 648);
		setTitle("문제출제");
		
		fontSet();
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 15, 2, 15));
		panel.setBackground(new Color(204, 255, 255));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = comboBox.getSelectedIndex();
				setSelection(a);
			}
		});
		comboBox.setBackground(Color.WHITE);
		panel_1.add(comboBox);
		
		QCountLabel = new JLabel("QCountLabel");
		panel_1.add(QCountLabel);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setHgap(15);
		panel_2.setBackground(new Color(204, 255, 255));
		panel.add(panel_2);
		
		JButton prevBtn = new JButton("\uC774\uC804");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelection(selection - 1);
			}
		});
		prevBtn.setForeground(Color.WHITE);
		prevBtn.setBackground(new Color(0, 176, 240));
		panel_2.add(prevBtn);
		
		JButton nextBtn = new JButton("\uB2E4\uC74C");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelection(selection + 1);
			}
		});
		nextBtn.setForeground(Color.WHITE);
		nextBtn.setBackground(new Color(0, 176, 240));
		panel_2.add(nextBtn);
		
		JButton addBtn = new JButton("\uBB38\uC81C \uCD94\uAC00");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakePanel mp = new MakePanel(-1, "텍스트 입력", "", "1", 1);				
				
				makePanelList.add(mp);
				selection = makePanelList.size()-1;
				panelUpdate();
			}
			
		});
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(new Color(0, 176, 240));
		panel_2.add(addBtn);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(204, 255, 255));
		panel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
		
		JButton closeBtn = new JButton("\uCDE8\uC18C");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showOptionDialog(null, "저장하지 않고 종료하겠습니까?", "경고", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, null, null);
				if (a == JOptionPane.NO_OPTION) {
					return;
				}
				setVisible(false);
			}
		});
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setBackground(new Color(0, 176, 240));
		panel_3.add(closeBtn);
		
		saveBtn = new JButton("\uC800\uC7A5 \uBC0F \uC885\uB8CC");
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setBackground(new Color(0, 176, 240));
		panel_3.add(saveBtn);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 0, 5, 10));
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5, BorderLayout.SOUTH);
		
		JButton delButton = new JButton("\uC0AD\uC81C");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(makePanelList.size() <= 1) {
					JOptionPane.showMessageDialog(null, "최소 1개의 질문이 있어야 합니다", "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				makePanelList.remove(selection);
				int a = JOptionPane.showOptionDialog(null, "정말 삭제하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, null, null);
				if (a == JOptionPane.NO_OPTION) {
					return;
				}
				panelUpdate();
			}
		});
		delButton.setForeground(Color.WHITE);
		delButton.setBackground(new Color(0, 176, 240));
		panel_5.add(delButton);
		
		JButton juBtn = new JButton("\uB2E8\uB2F5\uD615");
		juBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makePanelList.get(selection).showMode(false);
			}
		});
		juBtn.setForeground(Color.WHITE);
		juBtn.setBackground(new Color(0, 176, 240));
		panel_5.add(juBtn);
		
		JButton gaeBtn = new JButton("\uC120\uB2E4\uD615");
		gaeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				makePanelList.get(selection).showMode(true);
			}
		});
		gaeBtn.setForeground(Color.WHITE);
		gaeBtn.setBackground(new Color(0, 176, 240));
		panel_5.add(gaeBtn);
		
		makeMainPanel = new JPanel();
		panel_4.add(makeMainPanel, BorderLayout.CENTER);
		
		
		
		

		
		dataUpdate();
		
		setLocationRelativeTo(null);
	}
	
	
	public void setSelection(int i){
		i = Math.max(0, i);
		i = Math.min(makePanelList.size()-1, i);
		
		mainLayout.show(makeMainPanel, "mp_"+i);
		
		Integer cm[] = new Integer[makePanelList.size()];
		for (int j = 0; j < cm.length; j++) {
			cm[j] = j+1;
		}
		comboBox.setModel(new DefaultComboBoxModel<Integer>(cm));
		comboBox.setSelectedIndex(i);
		
		QCountLabel.setText("/"+(makePanelList.size()));
		
		
		selection = i;
	}
	
	public void panelUpdate() {
		makeMainPanel.removeAll();
		makeMainPanel.setLayout(mainLayout = new CardLayout(0, 0));
		
		//for
		//makeMainPanel.add(mp, "mp_"+i);
		
		for (int i = 0; i < makePanelList.size(); i++) {
			makeMainPanel.add(makePanelList.get(i),"mp_"+i);
		}
		
		
		setSelection(selection);
		revalidate();
	}
	
	public void dataUpdate() {
		
		try {
		
			String q = "select *,(select count(*) from examA a where q.examQNo = a.examQNo) examACount from examQ q where q.examNo = ? order by q.examQNumber";
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
		
			pstmt.setInt(1,examNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				MakePanel mp = new MakePanel(rs.getInt("examQNo"), rs.getString("examQName"), rs.getString("examQImage"), rs.getString("examARight"), rs.getInt("examACount"));
				
				
				makePanelList.add(mp);
				
				
				i++;
			}
		
			if(i == 0) {
				MakePanel mp = new MakePanel(-1, "텍스트 입력", "", "1", 1);				
				
				makePanelList.add(mp);
			}
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelUpdate();
		
	}
	
	
	public void insertExamA() {
		String q = "select * from examQ q where q.examNo = ? order by q.examQNumber";
		
		String ea = "insert into examA values";
		
		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
		
			pstmt.setInt(1, rest.Rest.examNo);
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				if(makePanelList.get(i).showModeBool == false) {
					continue;
				}
				for (int j = 0; j < makePanelList.get(i).rowTextList.size(); j++) {
					String d = makePanelList.get(i).rowTextList.get(j).getText();
					
					ea += String.format("('0',%d,%d,'%s'),", rs.getInt("examQNo"),j+1,d);
					
				}

				i++;
				
				
			}
			
			if(ea.equals("insert into examA values")) {
				return;
			}
			Statement stmt = rest.Rest.con.createStatement();
			System.out.println(ea);
			stmt.executeUpdate(ea.substring(0,ea.length()-1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insertExamQ() {
		String q = "insert into examQ values";
		
		
		for (int i = 0; i < makePanelList.size(); i++) {
			int examQNumber = i+1;
			String examQName = makePanelList.get(i).examQNameText.getText();
			String examQImage = makePanelList.get(i).examQImageText.getText();
			if(examQImage.equals("")) {
				examQImage = null;
			}else {
				examQImage = "'"+examQImage+"'";
			}
			
			String examARight = "";
			
			if(makePanelList.get(i).showModeBool == true) {
				//객관식
				for (int j = 0; j < makePanelList.get(i).rowRadioList.size(); j++) {
					if( makePanelList.get(i).rowRadioList.get(j).isSelected()) {
						examARight = String.valueOf(j+1);
					}
				}
			}else {
				examARight = makePanelList.get(i).examAText.getText();
			}
			
			
			q += String.format("('0',%d,'%s',%s,'%s',%d),",examQNumber,examQName,examQImage,examARight,rest.Rest.examNo);
			
		}
		
		
		try {
			Statement stmt = rest.Rest.con.createStatement();
			stmt.executeUpdate(q.substring(0, q.length()-1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteData() {
		String q = "delete from examQ where examNo = ?";
		
		
		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
			pstmt.setInt(1, rest.Rest.examNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void fontSet() {
		Font deFont = new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 16);
	
	UIManager.put("Button.font",deFont);
	UIManager.put("ToggleButton.font",deFont);
	UIManager.put("RadioButton.font",deFont);
	UIManager.put("CheckBox.font",deFont);
	UIManager.put("ColorChooser.font",deFont);
	UIManager.put("ComboBox.font",deFont);
	UIManager.put("Label.font",deFont);
	UIManager.put("List.font",deFont);
	UIManager.put("MenuBar.font",deFont);
	UIManager.put("MenuItem.font",deFont);
	UIManager.put("RadioButtonMenuItem.font",deFont);
	UIManager.put("CheckBoxMenuItem.font",deFont);
	UIManager.put("Menu.font",deFont);
	UIManager.put("PopupMenu.font",deFont);
	UIManager.put("OptionPane.font",deFont);
	UIManager.put("Panel.font",deFont);
	UIManager.put("ProgressBar.font",deFont);
	UIManager.put("ScrollPane.font",deFont);
	UIManager.put("Viewport.font",deFont);
	UIManager.put("TabbedPane.font",deFont);
	UIManager.put("Table.font",deFont);
	UIManager.put("TableHeader.font",deFont);
	UIManager.put("TextField.font",deFont);
	UIManager.put("PasswordField.font",deFont);
	UIManager.put("TextArea.font",deFont);
	UIManager.put("TextPane.font",deFont);
	UIManager.put("EditorPane.font",deFont);
	UIManager.put("TitledBorder.font",deFont);
	UIManager.put("ToolBar.font",deFont);
	UIManager.put("ToolTip.font",deFont);
	UIManager.put("Tree.font",deFont);
}

}
