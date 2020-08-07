package activity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AFrame extends JFrame implements ActionListener, MouseListener{

	JPanel c,s,n,e,w,mp,p,pp,ppp;
	JLabel jl;
	
	public void formset(String tt) {
		setTitle(tt);
		setDefaultCloseOperation(2);
		add(c = new JPanel(new BorderLayout()),BorderLayout.CENTER);
		add(s = new JPanel(new FlowLayout()),BorderLayout.SOUTH);
		add(n = new JPanel(new FlowLayout()), BorderLayout.NORTH);
		add(e = new JPanel(new BorderLayout()),BorderLayout.EAST);
		add(s = new JPanel(new BorderLayout()),BorderLayout.WEST);
	}
	
	public void Show() {
		pack();
		
		
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = Math.max(0,p.x - 30);
		int y = Math.max(0,p.y-30);
		setLocation(new Point(x, y));
		setVisible(true);
	}
	
	public void Cbtn(JPanel jp, JLabel jl, int w, int h) {
		jp.add(jl);
		jl.setIcon(new ImageIcon(new ImageIcon("지급파일/Up.png").getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		jl.addMouseListener(this);
		jl.setVerticalAlignment(0);
		jl.setHorizontalAlignment(0);
	}
	
	public void Popip(JPanel jp, JTextField jt, JButton jbt) {
		jp.add(jt);
		jt.setFocusable(false);
		jp.add(jbt);
		jbt.addActionListener(this);
		jbt.setMargin(getInsets());
		jbt.setPreferredSize(new Dimension(25, 25));
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
