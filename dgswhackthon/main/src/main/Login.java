package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


public class Login extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	Statement stmt = main.stmt;
	
	private JPanel contentPane;
	private JLabel lblClose;
	private JLabel lblMaximize;
	private JLabel lblMinimize;
	int tx=0,ty=0;
	private Boolean max;
	private JPanel ContaintPanel;
	private JLabel lblLogin;
	public static Object alldata[] = new Object[10];
	public static String Stations[] = {"없음","동대구역","대곡역","용산역"};
	private JLabel getLblClose() {
		if (lblClose == null) {
			lblClose = new JLabel("");
			lblClose.setHorizontalAlignment(SwingConstants.CENTER);
			lblClose.setBounds(12,6, 16, 16);
			max=false;
			lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Close.png")));
				
			lblClose.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_resources/CloseHover.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblClose.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Close.png")));
				}
			
				@Override
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
				
			});
		}
		return lblClose;
	}
	private JLabel getLblMaximize() {
		if (lblMaximize == null) {
			lblMaximize = new JLabel("");
			lblMaximize.setHorizontalAlignment(SwingConstants.CENTER);
			lblMaximize.setBounds(30,6, 16, 16);
			lblMaximize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/MaxMin.png")));
			
			/*lblMaximize.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if(!max) {
						lblMaximize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/MaximizeHover.png")));
					}
					else{
						lblMaximize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/ResizeHover.png")));
					}
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblMaximize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/MaxMin.png")));
				}
			
				@Override
				public void mouseClicked(MouseEvent e) {
				if(!max) {
					max=true;
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);	
				}
				else if(max) {
					max=false;
					frame.setExtendedState(JFrame.NORMAL);
					
				}
				
				}
				
			});*/
		}
		return lblMaximize;
	}
	private JLabel getLblMinimize() {
		if (lblMinimize == null) {
			lblMinimize = new JLabel("");
			lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Minimize.png")));
			lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
			lblMinimize.setBounds(48,6, 16, 16);
			
			
			
			lblMinimize.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/MinimizeHover.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Minimize.png")));
				}
			
				@Override
				public void mouseClicked(MouseEvent e) {
					lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Minimize.png")));
					setExtendedState(JFrame.ICONIFIED);
				}
				
			});
		}
		return lblMinimize;
	}
	private JPanel getContaintPanel() {
		if (ContaintPanel == null) {
			ContaintPanel = new JPanel();
			ContaintPanel.setBackground(new Color(255,255,255));
			ContaintPanel.setBounds(0, 28,1042, 582);
			ContaintPanel.setLayout(null);
		}
		return ContaintPanel;
	}
	
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon_resources/logo.png")));
		setTitle("DGSWus");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1042, 582);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				tx= e.getX();
				ty=e.getY();	
				
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen()-tx,e.getYOnScreen()-ty);
			
			}
		});
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0,0,0));
		contentPane.add(getLblClose());
		contentPane.add(getLblMaximize());
		contentPane.add(getLblMinimize());
		//contentPane.add(getLblLogin());
		
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//getContentPane().setLayout(new CardLayout(0, 0));
		//setSize(1042, 582);
		//setTitle("로그인");
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, "name_288399552896353");
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(661, 205, 301, 47);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setOpaque(false);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(661, 297, 301, 47);
		passwordField.setOpaque(false);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(passwordField);
		
		JButton button = new JButton(new ImageIcon("data\\image\\lbutton.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getlogin();
			}
		});
		button.setBorderPainted(false);
		button.setBounds(896, 382, 107, 42);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("data\\image\\bus.jpg"));
		lblNewLabel.setBounds(33, 33, 406, 413);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("data\\image\\login.png"));
		lblNewLabel_1.setBounds(461, 103, 535, 321);
		contentPane.add(lblNewLabel_1);
		contentPane.add(getContaintPanel());
		setUndecorated(true);
		setVisible(true);
	}
	
	
	public void getlogin() {
		if(textField.getText().equals("admin") && passwordField.getText().equals("1234")) {
			System.out.println("관리자");
			dispose();
			new Admin();
			return;
		}
		try {
			ResultSet rs = stmt.executeQuery("select * from user where id = '"+textField.getText()+"' and password = '"+passwordField.getText()+"'");
			if(rs.next()) {
				System.out.println("연결");
				alldata[0] = rs.getInt(1);
				alldata[1] = rs.getInt(2);
				alldata[2] = rs.getInt(3);
				alldata[3] = rs.getInt(4);
				alldata[4] = rs.getString(5);
				alldata[5] = rs.getString(6);
				alldata[6] = rs.getString(7);
				alldata[7] = rs.getString(8);
				alldata[8] = rs.getInt(9);
				alldata[9] = rs.getInt(10);

				dispose();
				new Home();
			}
			else {
				System.out.println("none");
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
