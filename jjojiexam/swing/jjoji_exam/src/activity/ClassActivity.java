package activity;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassActivity extends JPanel {

	/**
	 * Create the panel.
	 */

	int selectionClass = -2;
	JPanel classPanel;
	ArrayList<String> classDataCode = new ArrayList<String>();
	ArrayList<JPanel> classDataPanel = new ArrayList<JPanel>();
	JPanel avgPanel, calPanel;
	CardLayout ca;
	JPanel examMainPanel;
	String nowShowData = "";

	ExamActivity examActivity;
	AnswerActivity answerActivity;

	public ClassActivity() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel.setBackground(new Color(0, 176, 240));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel_1.setBackground(new Color(0, 176, 240));
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(
				new ImageIcon("source/pencil.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		lblNewLabel.setPreferredSize(new Dimension(26, 26));
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("  \uCABD\uC9C0\uC2A4\uCFE8");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 176, 240));
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton lblNewLabel_1 = new JButton("");
		lblNewLabel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!nowShowData.equals("answerActivity")) {
					String name = JOptionPane.showInputDialog(null, "수업 이름을 입력해 주세요.");

					if (name == null) {
						return;
					}

					String code = getCode();

					String q = "insert into class values(?,?);";

					try {
						PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);

						pstmt.setString(1, code);
						pstmt.setString(2, name);

						pstmt.executeUpdate();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					q = "insert into classUser values('0',?,?) ";

					try {
						PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);

						pstmt.setString(1, code);
						pstmt.setInt(2, rest.Rest.userNo);

						pstmt.executeUpdate();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					updateClass();
				}
			}
		});
		lblNewLabel_1.setFocusable(false);
		lblNewLabel_1.setRolloverIcon(new ImageIcon(
				new ImageIcon("source/plus_2.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		lblNewLabel_1.setPressedIcon(new ImageIcon(
				new ImageIcon("source/plus_2.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		lblNewLabel_1.setRequestFocusEnabled(false);
		lblNewLabel_1.setContentAreaFilled(false);
		lblNewLabel_1.setBorderPainted(false);
		lblNewLabel_1.setPreferredSize(new Dimension(36, 36));
		lblNewLabel_1.setBackground(new Color(0, 176, 240));
		panel_2.add(lblNewLabel_1);

		lblNewLabel_1.setIcon(new ImageIcon(
				new ImageIcon("source/plus.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));

		JButton lblNewLabel_2 = new JButton("");
		lblNewLabel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!nowShowData.equals("answerActivity")) {

					updateClass();

				}

			}
		});

		lblNewLabel_2.setFocusable(false);
		lblNewLabel_2.setRolloverIcon(new ImageIcon(
				new ImageIcon("source/refresh_2.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		lblNewLabel_2.setPressedIcon(new ImageIcon(
				new ImageIcon("source/refresh_2.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		lblNewLabel_2.setRequestFocusEnabled(false);
		lblNewLabel_2.setContentAreaFilled(false);
		lblNewLabel_2.setBorderPainted(false);
		lblNewLabel_2.setPreferredSize(new Dimension(36, 36));
		lblNewLabel_2.setBackground(new Color(0, 176, 240));
		panel_2.add(lblNewLabel_2);

		lblNewLabel_2.setIcon(new ImageIcon(
				new ImageIcon("source/refresh.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));

		JButton lblNewLabel_3 = new JButton("");
		lblNewLabel_3.setFocusable(false);
		lblNewLabel_3.setContentAreaFilled(false);
		lblNewLabel_3.setBorderPainted(false);
		lblNewLabel_3.setRequestFocusEnabled(false);
		lblNewLabel_3.setRolloverIcon(new ImageIcon(
				new ImageIcon("source/setting_2.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		lblNewLabel_3.setPressedIcon(new ImageIcon(
				new ImageIcon("source/setting_2.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		lblNewLabel_3.setPreferredSize(new Dimension(36, 36));
		lblNewLabel_3.setBackground(new Color(0, 176, 240));
		panel_2.add(lblNewLabel_3);

		lblNewLabel_3.setIcon(new ImageIcon(
				new ImageIcon("source/setting.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(8);
		flowLayout.setHgap(0);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 176, 240)));
		add(panel_3, BorderLayout.WEST);

		
		
		classPanel = new JPanel();
		classPanel.setBackground(Color.WHITE);
		panel_3.add(classPanel);
		classPanel.setLayout(new GridLayout(0, 1, 0, 5));

		examMainPanel = new JPanel();
		examMainPanel.setBackground(Color.WHITE);
		add(examMainPanel, BorderLayout.CENTER);

		ca = new CardLayout(0, 0);
		examMainPanel.setLayout(ca);

		
		JLabel homeActivity = new JLabel("");
		homeActivity.setIcon(new ImageIcon(new ImageIcon("source/homeActivity.png").getImage().getScaledInstance(870, 565, Image.SCALE_SMOOTH)));
		examMainPanel.add(homeActivity,"homeActivity");
		
		JLabel creditActivity = new JLabel("");
		creditActivity.setIcon(new ImageIcon(new ImageIcon("source/creditActivity.png").getImage().getScaledInstance(870, 565, Image.SCALE_SMOOTH)));
		examMainPanel.add(creditActivity,"creditActivity");
		
		examActivity = new ExamActivity();

		examActivity.addTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				while (true) {

					String userEmail = JOptionPane.showInputDialog(null, "선생님 이메일을 입력해 주세요.");

					if (userEmail == null) {
						return;
					}

					String q = "select * from user where userEmail = ? and userType = 2";

					try {
						PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);

						pstmt.setString(1, userEmail);

						ResultSet rs = pstmt.executeQuery();

						if (rs.next()) {

							String q2 = "insert into classuser values('0',?,?);";

							PreparedStatement pstmt2 = rest.Rest.con.prepareStatement(q2);

							pstmt2.setString(1, examActivity.openClassCode);
							pstmt2.setInt(2, rs.getInt("userNo"));

							pstmt2.executeUpdate();

							updateClass();
							break;

						} else {
							JOptionPane.showMessageDialog(null, "유저를 찾을 수 없습니다!", "확인",
									JOptionPane.INFORMATION_MESSAGE);
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		examActivity.addExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				try {
					
					// TODO Auto-generated catch block
					
					String q = "insert into exam values('0',?,?,?,?,?,0,0);";
					
					PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
					
					
					pstmt.setString(1, "시험 제목");
					
					SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					pstmt.setString(2, da.format(new Date()));
					pstmt.setString(3, da.format(addHoursToJavaUtilDate(new Date(), 1)));
					
					pstmt.setString(4, "00:10:00");
					pstmt.setString(5, examActivity.openClassCode);
					
					
					pstmt.executeUpdate();
					updateClass();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}

				
				try {
					
					// TODO Auto-generated catch block
					
					String q = "select examNo from exam order by examNo desc limit 1";
					
					Statement stmt = rest.Rest.con.createStatement();
					
					
					
					ResultSet rs = stmt.executeQuery(q);
					if(rs.next()) {
						rest.Rest.examNo = rs.getInt("examNo");
						answerActivity.dataUpdate();
						selectActivity("answerActivity");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});

		examMainPanel.add(examActivity, "examActivity");

		answerActivity = new AnswerActivity();

		answerActivity.answerSaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String q = "update exam set examName = ?, examStartTime = ?, examEndTime = ?, examUseTime = ? where examNo = ?";

				try {
					PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);

					pstmt.setString(1, answerActivity.answerTitle.getText());
					String examStartTime = String.format("%s %s:%s:00", answerActivity.answerStartDate.getText(),
							answerActivity.answerStartHour.getSelectedItem().toString(),
							answerActivity.answerStartMinute.getSelectedItem().toString());
					pstmt.setString(2, examStartTime);
					String examEndTime = String.format("%s %s:%s:00", answerActivity.answerEndDate.getText(),
							answerActivity.answerEndHour.getSelectedItem().toString(),
							answerActivity.answerEndMinute.getSelectedItem().toString());
					pstmt.setString(3, examEndTime);
					pstmt.setString(4, String.format("00:%s:%s", answerActivity.answerUseTime.getText(),
							answerActivity.answerUseTimeS.getText()));
					pstmt.setInt(5, rest.Rest.examNo);

					pstmt.executeUpdate();

					selectActivity("homeActivity");
					updateClass();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		answerActivity.answerDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				deleteExam();

			}
		});
		answerActivity.answerBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectActivity("examActivity");
			}
		});

		examMainPanel.add(answerActivity, "answerActivity");

		examMainPanel.add(examActivity, "examActivity");

		updateClass();
	}

	
	public Date addHoursToJavaUtilDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}  
	
	public void selectActivity(String name) {
		ca.show(examMainPanel, name);
		nowShowData = name;
	}

	public void deleteExam() {
		int a = JOptionPane.showOptionDialog(null, "정말 삭제하겠습니까?", "삭제", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, null, null);
		if (a == JOptionPane.NO_OPTION) {
			return;
		}

		String q = "delete from exam where examNo = ?";

		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);

			pstmt.setInt(1, rest.Rest.examNo);

			pstmt.executeUpdate();
			selectActivity("examActivity");
			updateClass();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCode() {

		String text = "";
		while (true) {

			text = UUID.randomUUID().toString().substring(0, 6);

			String q = "select * from class where classCode = ?";

			try {
				PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);

				pstmt.setString(1, text);

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {

				} else {
					break;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return text;

	}

	public void checkOutClass(int nextSelection) {
		selectActivity("examActivity");
		if (selectionClass == -2) {
			avgPanel.setBackground(Color.WHITE);
		} else if (selectionClass == -1) {
			System.out.println("컬러종료");
			calPanel.setBackground(Color.WHITE);
		} else {
			classDataPanel.get(selectionClass).setBackground(Color.WHITE);
		}
		selectionClass = nextSelection;

		if (nextSelection == -2) {
			avgPanel.setBackground(SystemColor.menu);
			selectActivity("homeActivity");
		} else if (nextSelection == -1) {
			calPanel.setBackground(SystemColor.menu);
			selectActivity("creditActivity");
		} else {
			classDataPanel.get(nextSelection).setBackground(SystemColor.menu);
			examActivity.updateClass(classDataCode.get(nextSelection));

			for (int i = 0; i < examActivity.examRowList.size(); i++) {
				examActivity.examRowList.get(i).examChange.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						System.out.println("마우스 클릭");

						for (int j = 0; j < examActivity.examRowList.size(); j++) {
							if (e.getSource().equals(examActivity.examRowList.get(j).examChange)) {
								rest.Rest.examNo = examActivity.examNoList.get(j);
								answerActivity.dataUpdate();
								selectActivity("answerActivity");
								break;
							}
						}

					}
				});

				examActivity.examRowList.get(i).examDelete.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						System.out.println("마우스 클릭");

						for (int j = 0; j < examActivity.examRowList.size(); j++) {
							if (e.getSource().equals(examActivity.examRowList.get(j).examDelete)) {
								rest.Rest.examNo = examActivity.examNoList.get(j);
								deleteExam();
								break;
							}
						}

					}
				});

			}

		}

	}

	// mouse 이벤트 셀렉션 처리

	public void updateClass() {
		// select u.classCode, c.className, (select GROUP_CONCAT(u1.userName SEPARATOR
		// ', ') from user u1, classUser c1 where u1.userNo = c1.userNo and u1.userType
		// = 2 and c1.classCode = c.classCode group by c1.classCode) as classTeacher
		// from classUser u,class c where u.userNo = 10 and c.classCode = u.classCode;
		classPanel.removeAll();
		classDataCode = new ArrayList<String>();
		classDataPanel = new ArrayList<JPanel>();

		updateClassPanel();

		String q = "select u.classCode, c.className, (select GROUP_CONCAT(u1.userName SEPARATOR ', ') from "
				+ "user u1, classUser c1 "
				+ "where u1.userNo = c1.userNo and u1.userType = 2 and c1.classCode = c.classCode group by c1.classCode) as classTeacher "
				+ "from classUser u,class c where u.userNo = ? and c.classCode = u.classCode;";

		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);

			pstmt.setInt(1, rest.Rest.userNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				JPanel classDataPanelDefault = new JPanel();
				classDataPanelDefault.setBorder(new EmptyBorder(0, 15, 0, 0));
				classDataPanelDefault.setBackground(Color.WHITE);
				classPanel.add(classDataPanelDefault);
				classDataPanelDefault.setLayout(new BorderLayout(0, 0));

				classDataPanelDefault.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						for (JPanel i : classDataPanel) {
							if (e.getSource().equals(i)) {
								i.setBackground(SystemColor.menu);
								break;
							}
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						for (int i = 0; i < classDataPanel.size(); i++) {
							if (e.getSource().equals(classDataPanel.get(i))) {
								if (i != selectionClass) {
									classDataPanel.get(i).setBackground(Color.WHITE);
								}
								break;
							}
						}
					}

					@Override
					public void mouseClicked(MouseEvent e) {

						for (int i = 0; i < classDataPanel.size(); i++) {
							if (e.getSource().equals(classDataPanel.get(i))) {
								checkOutClass(i);
								break;
							}
						}

					}
				});

				// rest.Rest.examNo = 1;
				// selectActivity("answerActivity");
				//
				// answerActivity.dataUpdate();

				JLabel classDataImage = new JLabel("");
				classDataImage.setIcon(new ImageIcon(
						new ImageIcon("source/exam.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
				classDataPanelDefault.add(classDataImage, BorderLayout.WEST);
				classDataImage.setPreferredSize(new Dimension(42, 42));

				JPanel classDataPanelDefault2 = new JPanel();
				// classDataPanelDefault2.setOpaque(true);
				classDataPanelDefault2.setBackground(new Color(0, 0, 0, 0));
				classDataPanelDefault.add(classDataPanelDefault2, BorderLayout.CENTER);
				classDataPanelDefault2.setLayout(new BorderLayout(0, 0));
				classDataPanelDefault2.setBorder(new EmptyBorder(5, 0, 5, 0));

				JLabel classDataNameDefault = new JLabel(rs.getString("classname"));
				classDataNameDefault.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 15));
				classDataPanelDefault2.add(classDataNameDefault, BorderLayout.CENTER);

				JLabel classDataUserDefault = new JLabel(rs.getString("classTeacher"));
				classDataUserDefault.setForeground(Color.GRAY);
				classDataUserDefault.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 12));
				classDataUserDefault.setVerticalAlignment(SwingConstants.TOP);
				classDataPanelDefault2.add(classDataUserDefault, BorderLayout.SOUTH);

				classDataCode.add(rs.getString("classCode"));
				classDataPanel.add(classDataPanelDefault);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int tmp = selectionClass;
		selectionClass = -2;
		checkOutClass(tmp);
		revalidate();

	}

	public void updateClassPanel() {
		classPanel.removeAll();
		avgPanel = new JPanel();
		avgPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				avgPanel.setBackground(SystemColor.menu);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (selectionClass != -2)
					avgPanel.setBackground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkOutClass(-2);
			}
		});
		classPanel.add(avgPanel);
		avgPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
		avgPanel.setBackground(SystemColor.menu);
		avgPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setPreferredSize(new Dimension(42, 42));
		lblNewLabel_5.setIcon(new ImageIcon(
				new ImageIcon("source/home.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		avgPanel.add(lblNewLabel_5, BorderLayout.WEST);

		JLabel lblNewLabel_6 = new JLabel("\uD1B5\uACC4                                              ");
		lblNewLabel_6.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 14));
		avgPanel.add(lblNewLabel_6, BorderLayout.CENTER);

		calPanel = new JPanel();
		calPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				calPanel.setBackground(SystemColor.menu);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (selectionClass != -1)
					calPanel.setBackground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				checkOutClass(-1);
			}
		});
		classPanel.add(calPanel);
		calPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
		calPanel.setBackground(Color.WHITE);
		calPanel.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("");
		label.setPreferredSize(new Dimension(42, 42));
		calPanel.add(label, BorderLayout.WEST);
		label.setIcon(new ImageIcon(
				new ImageIcon("source/cal.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));

		JLabel label_1 = new JLabel("\uD06C\uB808\uB527");
		label_1.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 14));
		calPanel.add(label_1, BorderLayout.CENTER);

		JLabel label_2 = new JLabel("    \uACFC\uBAA9\uBCC4 \uBCF4\uAE30");
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 12));
		label_2.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(240, 240, 240)));
		label_2.setBackground(Color.WHITE);
		classPanel.add(label_2);
	}

}
