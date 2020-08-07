package DGSWproject.kr.hs.dgsw;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Ipinsert extends JFrame{

	Statement stmt = MainClass.stmt;
	String grade;
	String classes;
	String num;
	
	public Ipinsert(String ip) {

		JLabel gradelabel = new JLabel("학년");
		gradelabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		gradelabel.setBounds(12, 15, 43, 22);
		getContentPane().add(gradelabel);

		JComboBox gradeBox = new JComboBox();
		gradeBox.setToolTipText("1");
		gradeBox.addItem(1);
		gradeBox.setBounds(55, 17, 37, 21);
		getContentPane().add(gradeBox);

		JLabel classlabel = new JLabel("반");
		classlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		classlabel.setBounds(135, 15, 27, 22);
		getContentPane().add(classlabel);

		JComboBox classBox = new JComboBox();
		classBox.setToolTipText("1");
		for (int i = 1; i <= 3; i++)
			classBox.addItem(i);
		classBox.setBounds(160, 17, 37, 21);
		getContentPane().add(classBox);

		JLabel nolabel = new JLabel("번호");
		nolabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		nolabel.setBounds(230, 14, 43, 22);
		getContentPane().add(nolabel);

		JComboBox noBox = new JComboBox();
		noBox.setToolTipText("1");
		for (int i = 1; i <= 20; i++)
			noBox.addItem(i);
		noBox.setBounds(273, 16, 43, 21);
		getContentPane().add(noBox);

		JLabel label = new JLabel("자동로그인을 위해 내부 ip가 수집됩니다.");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label.setBounds(12, 50, 242, 22);
		getContentPane().add(label);

		JButton okbotton = new JButton("확인");
		okbotton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		okbotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				grade = gradeBox.getSelectedItem().toString();
				classes = classBox.getSelectedItem().toString();
				num = noBox.getSelectedItem().toString();

				int check = JOptionPane.showConfirmDialog(null, grade + "학년 " + classes + "반" + num + "번이 맞습니까?", "확인!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (check == JOptionPane.YES_OPTION) {

					String query = "update student set ip='" + ip + "' where grade=" + grade + " and class=" + classes
							+ " and num=" + num + ";";

					try {
						stmt.executeUpdate(query);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					System.out.println(query);
					dispose();
					new StudentClass(ip);
				}
			}
		});

		okbotton.setBounds(263, 50, 97, 23);
		
		getContentPane().add(okbotton);
		getContentPane().setLayout(null);
		setBounds(100, 100, 382, 120);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
	}
	
}
