package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class BusChange extends JFrame implements Frame{
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblClose;
	private JLabel lblMaximize;
	private JLabel lblMinimize;
	int tx=0,ty=0;
	private Boolean max;
	private JPanel ContaintPanel;
	private JLabel lblTitle;

	static ImageIcon im[][] = new ImageIcon[4][3];
	static int maxjaw[] = {0,80,40,40};
	static int imin = 1;
	static JButton btnNewButton_2;
	static JLabel button[] = new JLabel[4];
	static String yukyuk[] = {"없음","동대구역","대곡역","용산역"};
	static JLabel lblNewLabel_1;
	static JLabel label_1;
	static int lot = 0;
	public BusChange() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/icon_resources/logo.png")));
		setTitle("DGSWus");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1042, 582);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				tx = e.getX();
				ty = e.getY();

			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - tx, e.getYOnScreen() - ty);

			}
		});
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.add(getLblClose());
		contentPane.add(getLblMaximize());
		contentPane.add(getLblMinimize());
		contentPane.add(getLblTitle());
		
		
		
		
		
		for (int i = 1; i < im.length; i++) {
			im[i][0] = new ImageIcon("data\\image\\y_"+i+".png");
			im[i][1] = new ImageIcon("data\\image\\n_"+i+".png");
			im[i][2] = new ImageIcon("data\\image\\c"+i+".jpg");
		}
		
		JButton btnNewButton = new JButton(new ImageIcon("data\\image\\right.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imin = Math.min(3, imin+1);
				changeImage();
			}
		});
		btnNewButton.setBounds(320, 400, 32, 32);
		getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		
		JButton buttons = new JButton(new ImageIcon("data\\image\\left.png"));
		buttons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imin = Math.max(1, imin-1);
				changeImage();
			}
		});
		buttons.setBounds(80, 400, 32, 32);
		getContentPane().add(buttons);
		buttons.setBorderPainted(false);
		
		button[1] = new JLabel("New button");
		button[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				imin=1;
				changeImage();
			}
		});
//		button[1].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				imin=1;
//				changeImage();
//			}
//		});
		button[1].setBounds(140, 400, 32, 32);
		getContentPane().add(button[1]);
//		button[1].setBorderPainted(false);
		
		button[2] = new JLabel("New button");
		button[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				imin=2;
				changeImage();
			}
		});
//		button[2].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				imin=2;
//				changeImage();
//			}
//		});
		button[2].setBounds(200, 400, 32, 32);
		getContentPane().add(button[2]);
//		button[2].setBorderPainted(false);
		
		button[3] = new JLabel("New button");
		button[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				imin=3;
				changeImage();
			}
		});
//		button[3].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				imin=3;
//				changeImage();
//			}
//		});
		button[3].setBounds(260, 400, 32, 32);
		getContentPane().add(button[3]);
//		button[3].setBorderPainted(false);
		
		lblNewLabel_1 = new JLabel("현 동대구역의 탑승 인원은 18명 입니다.",0);
		
		lblNewLabel_1.setFont(new Font("나눔스퀘어", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(410, 152, 578, 89);
		getContentPane().add(lblNewLabel_1);
		
		label_1 = new JLabel("남은 좌석 : 22석",0);
		label_1.setFont(new Font("나눔스퀘어", Font.PLAIN, 25));
		label_1.setBounds(504, 271, 397, 89);
		getContentPane().add(label_1);
	
		
		btnNewButton_2 = new JButton(new ImageIcon("data\\image\\c1.jpg"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BusChange2(Login.Stations[imin], lblNewLabel_1.getText(), label_1.getText(), imin, lot);
				changeImage();
			}
		});
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(144, 159, 156, 156);
		getContentPane().add(btnNewButton_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("버스 안탐");
		chckbxNewCheckBox.setFont(new Font("나눔스퀘어", Font.PLAIN, 25));
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(867, 491, 137, 32);
		getContentPane().add(chckbxNewCheckBox);
		contentPane.add(getContaintPanel());
		changeImage();
		
		
		
		
		
		setUndecorated(true);
		setVisible(true);
	}

	private JLabel getLblClose() {
		if (lblClose == null) {
			lblClose = new JLabel("");
			lblClose.setHorizontalAlignment(SwingConstants.CENTER);
			lblClose.setBounds(12, 6, 16, 16);
			max = false;
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
			lblMaximize.setBounds(30, 6, 16, 16);
			lblMaximize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/MaxMin.png")));

			/*
			 * lblMaximize.addMouseListener(new MouseAdapter() {
			 * 
			 * @Override public void mouseEntered(MouseEvent e) { if(!max) {
			 * lblMaximize.setIcon(new
			 * ImageIcon(getClass().getResource("/icon_resources/MaximizeHover.png"))); }
			 * else{ lblMaximize.setIcon(new
			 * ImageIcon(getClass().getResource("/icon_resources/ResizeHover.png"))); }
			 * 
			 * }
			 * 
			 * @Override public void mouseExited(MouseEvent e) { lblMaximize.setIcon(new
			 * ImageIcon(getClass().getResource("/icon_resources/MaxMin.png"))); }
			 * 
			 * @Override public void mouseClicked(MouseEvent e) { if(!max) { max=true;
			 * frame.setExtendedState(JFrame.MAXIMIZED_BOTH); } else if(max) { max=false;
			 * frame.setExtendedState(JFrame.NORMAL);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * });
			 */
		}
		return lblMaximize;
	}

	private JLabel getLblMinimize() {
		if (lblMinimize == null) {
			lblMinimize = new JLabel("");
			lblMinimize.setIcon(new ImageIcon(getClass().getResource("/icon_resources/Minimize.png")));
			lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
			lblMinimize.setBounds(48, 6, 16, 16);

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

	
	public static void changeImage() {
		for (int j = 1; j < im.length; j++) {
			if(j == imin) {
				button[j].setIcon(im[j][0]);
				btnNewButton_2.setIcon(im[j][2]);
				
				try {
					ResultSet rs = main.stmt.executeQuery("select count(now) from user where now = "+j+";");
					if(rs.next()) {
						lblNewLabel_1.setText("현 "+yukyuk[j]+"의 탑승 인원은 "+rs.getString(1)+"명 입니다.");
						label_1.setText("현재 남은 좌석의 수는 "+(maxjaw[j]-rs.getInt(1))+"개 입니다.");
						lot = maxjaw[j]-rs.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				button[j].setIcon(im[j][1]);
		
		}
	}
	
	private JPanel getContaintPanel() {
		if (ContaintPanel == null) {
			ContaintPanel = new JPanel();
			ContaintPanel.setBackground(new Color(255, 255, 255));
			ContaintPanel.setBounds(0, 28,1042, 582);
			ContaintPanel.setLayout(null);
			
			JButton btnNewButton_1 = new JButton(new ImageIcon("data\\image\\back.png"));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					new Home();
				}
			});
			btnNewButton_1.setBounds(12, 10, 60, 54);
			ContaintPanel.add(btnNewButton_1);
			btnNewButton_1.setBorderPainted(false);

		}
		return ContaintPanel;
	}

	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Bus Change");
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTitle.setForeground(new Color(0x861cfe));
			lblTitle.setBounds(79, 4, 100, 18);
		}
		return lblTitle;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;

	}
	
}
