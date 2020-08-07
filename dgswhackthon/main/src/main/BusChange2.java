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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class BusChange2 extends JFrame implements Frame{
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblClose;
	private JLabel lblMaximize;
	private JLabel lblMinimize;
	int tx=0,ty=0;
	private Boolean max;
	private JPanel ContaintPanel;
	private JLabel lblTitle;

	ImageIcon im[][] = new ImageIcon[4][3];
	int maxjaw[] = {0,80,40,40};
	int imin = 1;
	JButton btnNewButton_2;
	JButton button[] = new JButton[4];
	String yukyuk[] = {"없음","동대구역","대곡역","용산역"};
	JLabel lblNewLabel_1;
	JLabel label_1;
	int lot = 0;
	public BusChange2(String a, String b, String c, int iminim,int lo) {
		System.out.println("get"+lot);
		lot = lo;
		imin = iminim;
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
		contentPane.add(getLblTitle());
		
		JLabel label = new JLabel("\uBCC0\uACBD \uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?", SwingConstants.CENTER);
		label.setFont(new Font("나눔스퀘어", Font.PLAIN, 30));
		label.setBounds(256, 176, 578, 89);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel(c, SwingConstants.CENTER);
		label_2.setFont(new Font("나눔스퀘어", Font.PLAIN, 25));
		label_2.setBounds(347, 295, 397, 89);
		contentPane.add(label_2);
		contentPane.add(getContaintPanel());

		JLabel label_3 = new JLabel(a, SwingConstants.CENTER);
		label_3.setBounds(860, 10, 170, 74);
		ContaintPanel.add(label_3);
		label_3.setFont(new Font("나눔스퀘어", Font.BOLD, 30));
		
		
		
		
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

	
	
	private JPanel getContaintPanel() {
		if (ContaintPanel == null) {
			ContaintPanel = new JPanel();
			ContaintPanel.setBackground(new Color(255, 255, 255));
			ContaintPanel.setBounds(0, 28,1042, 582);
			ContaintPanel.setLayout(null);
			
			
			
			JButton btnNewButton_1 = new JButton(new ImageIcon("data\\image\\back.png"));
			btnNewButton_1.setBounds(11, 7, 60, 54);
			ContaintPanel.add(btnNewButton_1);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					
					dispose();
				}
			});
			btnNewButton_1.setBorderPainted(false);
			
			JButton btnNewButton = new JButton(new ImageIcon("data\\image\\submit.png"));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(lot <= 0) {
						System.out.println(lot);
						JOptionPane.showMessageDialog(null, "예매 안되요 ㅠㅠ","오류",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					try {
						System.out.println("update user set now = "+imin+" where id = '"+Login.alldata[0]+"';");
						main.stmt.executeUpdate("update user set now = "+imin+" where id = '"+Login.alldata[0]+"';");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "변경되었습니다.","성공",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					BusChange.changeImage();
				}
			});
			btnNewButton.setBorderPainted(false);
			btnNewButton.setBounds(464, 390, 164, 58);
			ContaintPanel.add(btnNewButton);
			
			
			
			
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
