package com.dgswus.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.AbstractListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Registration_Optimal_Path extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblClose;
	private JLabel lblMaximize;
	private JLabel lblMinimize;
	int tx = 0, ty = 0;
	private Boolean max;
	private JPanel ContaintPanel;
	private JLabel lblTitle;
	private JComboBox local;
	private int isSelect;
	private int isProvin;
	private JComboBox provincial;
	String str[] = { "시 선택", "창원시", "김해시", "진주시", "거제시", "통영시", "사천시", "밀양시", "부산광역시", "양산시", "울산광역시 ", "고성군", "거창군", "남해군"
			, "산청군", "의령군", "창녕군", "하동군", "함안군", "함양군", "합천군"};
	String str2[] = { "시 선택", "포항시", "경주시", "대구광역시", "울진군", "영덕군", "울릉군", "구미시", "경산시", "김천시", "영천시", "칠곡군", "성주군", "고령군"
			, "청도군", "군위군", "안동시", "영주시", "의성군", "예천군", "봉화군", "청송군", "영양군", "상주시", "문경시"
	};
	private JLabel lblBack;
	private JLabel lblGuide;
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// frame = new Theme();
	// //Remove the top bar
	// frame.setUndecorated(true);
	//
	// frame.setResizable(true);
	// frame.setBackground(new Color(0,0,0,65));
	// frame.setVisible(true);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public Registration_Optimal_Path() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/icon_resources/logo.png")));
		setTitle("DGSWus");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(960, 720);
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
			ContaintPanel.setBounds(0, 28, 960, 698);
			ContaintPanel.setLayout(null);
			ContaintPanel.add(getLocal());
			ContaintPanel.add(getProvincial());
			ContaintPanel.add(getLblBack());
			ContaintPanel.add(getLblGuide());
		}
		return ContaintPanel;
	}

	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Registration Optimal Path");
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTitle.setForeground(new Color(0x861cfe));
			lblTitle.setBounds(79, 4, 189, 18);
		}
		return lblTitle;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	private JComboBox getLocal() {
		if (local == null) {
			local = new JComboBox();
			local.setModel(new DefaultComboBoxModel(new String[] {"\uC9C0\uC5ED \uC120\uD0DD", "\uACBD\uAE30\uB3C4", "\uAC15\uC6D0\uB3C4", "\uCDA9\uCCAD\uBD81\uB3C4", "\uCDA9\uCCAD\uB0A8\uB3C4", "\uC804\uB77C\uBD81\uB3C4", "\uC804\uB77C\uB0A8\uB3C4", "\uACBD\uC0C1\uBD81\uB3C4", "\uACBD\uC0C1\uB0A8\uB3C4"}));
			local.setBounds(46, 234, 90, 24);
			local.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if("경상남도".equals(e.getItem())){
						isSelect = 0;
						provincial.setVisible(true);
						provincial.setModel(new DefaultComboBoxModel(str));
					}else if("경상북도".equals(e.getItem())){
						isSelect = 1;
						provincial.setVisible(true);
						provincial.setModel(new DefaultComboBoxModel(str2));
					}
				}
			});
		}
		return local;
	}
	private JComboBox getProvincial() {
		if (provincial == null) {
			provincial = new JComboBox();
			provincial.setBounds(170, 234, 97, 24);
			provincial.setVisible(false);
			provincial.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if("진주시".equals(e.getItem())){
						isProvin = 0;
						lblGuide.setIcon(new ImageIcon(Registration_Optimal_Path.class.getResource("/icon_resources/jinjoo.jpg")));
						lblGuide.setVisible(true);
					}else if("김해시".equals(e.getItem())){
						isProvin = 1;
						lblGuide.setIcon(new ImageIcon(Registration_Optimal_Path.class.getResource("/icon_resources/kimhae.jpg")));
						lblGuide.setVisible(true);
					}else if("경주시".equals(e.getItem())) {
						isProvin = 2;
						lblGuide.setIcon(new ImageIcon(Registration_Optimal_Path.class.getResource("/icon_resources/goengjoo.jpg")));
						lblGuide.setVisible(true);
					}
				}
			});
		}
		return provincial;
	}
	private JLabel getLblBack() {
		if (lblBack == null) {
			lblBack = new JLabel("");
			lblBack.setIcon(new ImageIcon(Registration_Optimal_Path.class.getResource("/icon_resources/back.jpg")));
			lblBack.setBounds(28, 12, 69, 73);
			lblBack.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					frame = new Home();
					frame.setUndecorated(true);
					((Home) frame).setFrame(frame);
					frame.setResizable(true);
					frame.setBackground(new Color(0,0,0));
			
					frame.setVisible(true);
					setExtendedState(JFrame.ICONIFIED);
				}
			});
		}
		return lblBack;
	}
	private JLabel getLblGuide() {
		if (lblGuide == null) {
			lblGuide = new JLabel("");
			lblGuide.setBounds(303, 98, 617, 506);
			setVisible(false);
			
		}
		return lblGuide;
	}
}
