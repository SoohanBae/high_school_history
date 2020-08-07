package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Home extends JFrame implements Frame{
	private JPanel contentPane;
	private JLabel lblClose;
	private JLabel lblMaximize;
	private JLabel lblMinimize;
	int tx=0,ty=0;
	private Boolean max;
	private JPanel ContaintPanel;
	private JLabel lblTitle;
	private JLabel lblBC;
	private JLabel lblROP;
	private JLabel lblText;
	private JLabel lblCS;
	private JFrame frame;
	private JLabel lblBus1;
	private JLabel lblBus2;
	private JLabel lblBus3;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new Theme();
//					//Remove the top bar
//					frame.setUndecorated(true);
//					
//					frame.setResizable(true);
//					frame.setBackground(new Color(0,0,0,65));
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/icon_resources/logo.png")));
		setTitle("DGSWus");

		
		try {
			ResultSet rs = main.stmt.executeQuery("select * from user where no = "+Login.alldata[0]);
			if(rs.next()) {
				System.out.println("ø¨∞·");
				Login.alldata[0] = rs.getInt(1);
				Login.alldata[1] = rs.getInt(2);
				Login.alldata[2] = rs.getInt(3);
				Login.alldata[3] = rs.getInt(4);
				Login.alldata[4] = rs.getString(5);
				Login.alldata[5] = rs.getString(6);
				Login.alldata[6] = rs.getString(7);
				Login.alldata[7] = rs.getString(8);
				Login.alldata[8] = rs.getInt(9);
				Login.alldata[9] = rs.getInt(10);

			}
			else {
				System.out.println("none");
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0,0,0));
		contentPane.add(getLblClose());
		contentPane.add(getLblMaximize());
		contentPane.add(getLblMinimize());
		contentPane.add(getContaintPanel());
		contentPane.add(getLblTitle());
		
		
		
		
		setUndecorated(true);
		setVisible(true);
	}
	
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
			ContaintPanel.setBounds(0, 28,1042,554);
			ContaintPanel.setLayout(null);
			ContaintPanel.add(getLblBC());
			ContaintPanel.add(getLblROP());
			ContaintPanel.add(getLblText());
			ContaintPanel.add(getLblCS());
			ContaintPanel.add(getLblBus1());
			ContaintPanel.add(getLblBus2());
			ContaintPanel.add(getLblBus3());
			
			JLabel lblText1 = new JLabel("\uB3D9\uB300\uAD6C",0);
			lblText1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
			lblText1.setBounds(490, 171, 100, 27);
			ContaintPanel.add(lblText1);
			
			
			JLabel lblText2 = new JLabel("\uB300\uACE1",0);
			lblText2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
			lblText2.setBounds(669, 169, 90, 27);
			ContaintPanel.add(lblText2);
			
			JLabel lblText3 = new JLabel("\uC6A9\uC0B0",0);
			lblText3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
			lblText3.setBounds(828, 171, 100, 27);
			ContaintPanel.add(lblText3);
			
			JLabel lblSeta1 = new JLabel("18\uBA85",0);
			lblSeta1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
			lblSeta1.setBounds(490, 208, 100, 27);
			ContaintPanel.add(lblSeta1);
			
			try {
				ResultSet rs = main.stmt.executeQuery("select count(now) from user where now = 1;");
				if(rs.next()) {
					lblSeta1.setText(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			JLabel label = new JLabel("29\uBA85",0);
			label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
			label.setBounds(659, 208, 100, 27);
			ContaintPanel.add(label);
			
			try {
				ResultSet rs = main.stmt.executeQuery("select count(now) from user where now = 2;");
				if(rs.next()) {
					label.setText(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JLabel label_1 = new JLabel("9\uBA85",0);
			label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
			label_1.setBounds(828, 208, 100, 27);
			ContaintPanel.add(label_1);
			
			try {
				ResultSet rs = main.stmt.executeQuery("select count(now) from user where now = 3;");
				if(rs.next()) {
					label_1.setText(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return ContaintPanel;
	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Home");
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTitle.setForeground(new Color(0x861cfe));
			lblTitle.setBounds(79, 4, 46, 18);
		}
		return lblTitle;
	}
	private JLabel getLblBC() {
		if (lblBC == null) {
			lblBC = new JLabel("");
			lblBC.setIcon(new ImageIcon(Home.class.getResource("/icon_resources/BusChange.png")));
			lblBC.setBounds(724, 270, 200, 201);
			lblBC.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					new BusChange();
				}

			});
		}
		return lblBC;
	}
	private JLabel getLblROP() {
		if (lblROP == null) {
			lblROP = new JLabel("");
			lblROP.setIcon(new ImageIcon(Home.class.getResource("/icon_resources/Registration Optimal Path.png")));
			lblROP.setBounds(500, 270, 200, 201);
			lblROP.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					new Registration_Optimal_Path();

				}

			});
			
		}
		return lblROP;
	}
	private JLabel getLblText() {
		if (lblText == null) {
			lblText = new JLabel(Login.alldata[4].toString()+"¥‘ π›∞©Ω¿¥œ¥Ÿ.");
			lblText.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 40));
			lblText.setBounds(40, 172, 448, 113);
		}
		return lblText;
	}
	private JLabel getLblCS() {
		if (lblCS == null) {
			lblCS = new JLabel(Login.alldata[4].toString()+"¥‘¿Ã º±≈√«—ø™¿∫ "+ Login.Stations[(int) Login.alldata[9]]+" ¿‘¥œ¥Ÿ.");
			lblCS.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
			lblCS.setBounds(62, 277, 374, 27);
		}
		return lblCS;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	private JLabel getLblBus1() {
		if (lblBus1 == null) {
			lblBus1 = new JLabel("");
			lblBus1.setIcon(new ImageIcon(Home.class.getResource("/icon_resources/bus.jpg")));
			lblBus1.setBounds(490, 59, 100, 100);
		}
		return lblBus1;
	}
	private JLabel getLblBus2() {
		if (lblBus2 == null) {
			lblBus2 = new JLabel("");
			lblBus2.setIcon(new ImageIcon(Home.class.getResource("/icon_resources/bus.jpg")));
			lblBus2.setBounds(659, 59, 100, 100);
		}
		return lblBus2;
	}
	private JLabel getLblBus3() {
		if (lblBus3 == null) {
			lblBus3 = new JLabel("");
			lblBus3.setIcon(new ImageIcon(Home.class.getResource("/icon_resources/bus.jpg")));
			lblBus3.setBounds(828, 59, 100, 100);
		}
		return lblBus3;
	}
}
