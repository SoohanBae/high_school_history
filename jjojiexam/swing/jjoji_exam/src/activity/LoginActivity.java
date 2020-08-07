package activity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class LoginActivity extends JPanel {
	private JTextField loginId;
	private JPasswordField loginPw;
	
	public JButton loginLoginBtn;

	public LoginActivity() {
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("source/main2.jpg").getImage().getScaledInstance(960, 620, Image.SCALE_SMOOTH)));
		add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uCABD\uC9C0\uC2A4\uCFE8");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 22));
		panel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new EmptyBorder(20, 0, 0, 0));
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_3.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(4, 1, 0, 5));
		
		JLabel lblNewLabel_2 = new JLabel("\uB85C\uADF8\uC778");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_1.add(lblNewLabel_2);
		
		
		
		loginId = new JTextField(30);
		panel_1.add(loginId);
		loginId.setColumns(10);
		
		loginPw = new JPasswordField();
		loginPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		loginPw.setEchoChar('*');
		panel_1.add(loginPw);
		loginPw.setColumns(10);
		loginPw.setPreferredSize(new Dimension(80, 30));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(2, 1, 5, 5));
		
		loginLoginBtn = new JButton("로그인");
	
		loginLoginBtn.setForeground(Color.WHITE);
		loginLoginBtn.setBackground(new Color(0, 176, 240));
		panel_2.add(loginLoginBtn);
		
		JButton loginSignUp = new JButton("회원가입");
		loginSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		loginSignUp.setForeground(Color.WHITE);
		loginSignUp.setBackground(new Color(0, 176, 240));
		loginSignUp.setPreferredSize(new Dimension(80, 40));
		panel_2.add(loginSignUp);

	}
	
	public boolean loginFun() {
		String q = "select * from user where userEmail = ? and userPw = ?";
		
		try {
			PreparedStatement pstmt = rest.Rest.con.prepareStatement(q);
		
			pstmt.setString(1, loginId.getText());
			pstmt.setString(2, getPassword(loginPw.getText(), "asdf"));
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rest.Rest.userNo = rs.getInt("userNo");
				System.out.println("로그인");
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다", "로그인 실패", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getPassword(String passwordToHash, String salt){
		String generatedPassword = null;
		    try {
		         MessageDigest md = MessageDigest.getInstance("SHA-512");
		         //md.update(salt.getBytes(StandardCharsets.UTF_8));
		         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
//		         StringBuilder sb = new StringBuilder();
//		         for(int i=0; i< bytes.length ;i++){
//		            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//		         }
//		         generatedPassword = sb.toString();
//		         
		         
		         return Base64.getEncoder().encodeToString(bytes);
		        } 
		       catch (NoSuchAlgorithmException e){
		        e.printStackTrace();
		       }
		    return generatedPassword;
		}
}
