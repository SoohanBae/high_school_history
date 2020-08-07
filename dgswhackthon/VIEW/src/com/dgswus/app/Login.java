package com.dgswus.app;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JButton;

public class Login extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblClose;
	private JLabel lblMaximize;
	private JLabel lblMinimize;
	int tx = 0, ty = 0;
	private Boolean max;
	private JPanel ContaintPanel;
	private JLabel lblTitle;
	private JLabel lblLogin;

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
	public Login() {
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
		contentPane.add(getContaintPanel());
		ContaintPanel.add(getLblLogin());
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
			ContaintPanel.add(getLblLogin());
		}
		return ContaintPanel;
	}

	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("LogIn");
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTitle.setForeground(new Color(0x861cfe));
			lblTitle.setBounds(79, 4, 46, 18);
		}
		return lblTitle;
	}

	private JLabel getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new JLabel("");
			lblLogin.setIcon(new ImageIcon(Login.class.getResource("/icon_resources/btn_login.png")));
			lblLogin.setBounds(698, 328, 113, 42);
			
			lblLogin.addMouseListener(new MouseAdapter() {

				@Override
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
		return lblLogin;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
