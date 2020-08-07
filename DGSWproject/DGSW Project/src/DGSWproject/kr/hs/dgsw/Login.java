package DGSWproject.kr.hs.dgsw;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.sql.ResultSet;
import java.sql.Statement;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame{
	private JTextField idField;
	private JPasswordField passwordField;

	Statement stmt = MainClass.stmt;
	
	String id;
	String password;
	
	public Login(String table, String name){
		getContentPane().setLayout(null);
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		idLabel.setBounds(33, 65, 97, 32);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(idLabel);
		
		JLabel label = new JLabel(name + "로그인");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("배달의민족 도현", Font.PLAIN, 20));
		label.setBounds(3, 10, 476, 36);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("비밀번호");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		label_1.setBounds(33, 108, 97, 32);
		getContentPane().add(label_1);
		
		idField = new JTextField();
		if(table == "admin")
			idField.setText("DGSWuser");
		else if(table == "teacher")
			idField.setText("teacher12");
		else if(table == "wakeup")
			idField.setText("wakeup");
		idField.setBounds(130, 62, 186, 36);
		getContentPane().add(idField);
		idField.setColumns(10);
	
		
		passwordField = new JPasswordField();
		if(table == "admin")
			passwordField.setText("dgsw102");
		else if(table == "teacher")
			passwordField.setText("tea12");
		else if(table == "wakeup")
			passwordField.setText("wakeup102");
		passwordField.setBounds(130, 108, 186, 36);
		getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("로그인");
		loginButton.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cnt = 0;
				id = idField.getText();
				password = passwordField.getText();
				
				
				String query = "select id from "+ table +" where id='"+id + "'";
				System.out.println(query);
				
				try {
					ResultSet result = stmt.executeQuery(query);
					
					while (result.next()) {
						cnt++;
					}
				} catch (Exception e1) {
					System.out.println("error");
				}
				
				if(cnt == 1){
					query = "select * from "+ table +" where id='" + id + "'and password='" + password + "'";
					System.out.println(query);
					
					try {
						ResultSet result = stmt.executeQuery(query);

						while (result.next()) {
							cnt++;
						}
					} catch (Exception e2) {
						System.out.println("error");
					}
					
					if(cnt == 2){
						if(table.equals("teacher")){
							new TeacherClass(idField.getText());
							dispose();
						}
						else if(table.equals("wakeup")){
							new WakeupClass();
							dispose();
						}
						else if(table.equals("admin")){
							new DBadminClass();
							dispose();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "해당하는 아이디가 없습니다!");
				}
			}
		});
		loginButton.setBounds(338, 60, 99, 88);
		getContentPane().add(loginButton);
		
		JLabel label_2 = new JLabel("\uD14C\uC2A4\uD2B8\uB97C \uC704\uD574 \uAC12\uC774 \uB4E4\uC5B4\uAC00 \uC788\uC2B5\uB2C8\uB2E4.");
		label_2.setBounds(112, 153, 249, 15);
		getContentPane().add(label_2);
		
		setResizable(false);
		setBounds(100, 100, 510, 217);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
