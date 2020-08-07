package activity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalCal extends AFrame{
	JLabel lday[] = new JLabel[42];
	JLabel ldate, yearB, yearN, monthB, monthN;
	Calendar calendar = Calendar.getInstance();
	int year, month, day;
	
	JTextField jt;
	

	
	public void form(JTextField jt2) {
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseExited(MouseEvent e) {
//				setVisible(false);
//			}
//		});
		jt = jt2;
		formset("");
		setUndecorated(true);
		n.add(yearB = new JLabel("<<"));
		n.add(monthB = new JLabel("<"));
		n.add(ldate = new JLabel(new SimpleDateFormat("yyyy?MM").format(new Date()),0));
		ldate.setPreferredSize(new Dimension(170, 25));
		n.add(monthN = new JLabel(">"));
		n.add(yearN = new JLabel(">>"));
		yearB.addMouseListener(this);
		yearN.addMouseListener(this);
		monthB.addMouseListener(this);
		monthN.addMouseListener(this);
		
		c.setLayout(new GridLayout(8, 7));
		String ln[] = {"일","월","화","수","목","금","토"};
		for (int i = 0; i < ln.length; i++) {
			c.add(jl = new JLabel(ln[i],0));
			jl.setPreferredSize(new Dimension(40,40));
			if(i==0) {
				jl.setForeground(Color.red);
			}else if(i==6) {
				jl.setForeground(Color.BLUE);
			}
		}
		
		for (int i = 0; i < lday.length; i++) {
			c.add(lday[i] = new JLabel("",0));
			lday[i].setPreferredSize(new Dimension(40, 40));
			lday[i].addMouseListener(this);
		}
		
		year = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
		month = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
		update();
		
		getRootPane().setBorder(BorderFactory.createLineBorder(SystemColor.textHighlight));
		Show();
	}

	public void update() {
		ldate.setText(year+"/"+new DecimalFormat("00").format(month));
		for (int i = 0; i < lday.length; i++) {
			lday[i].setText("");
			lday[i].setVisible(false);
		}
		calendar.set(year, month-1, 1);
		int Fday=calendar.get(Calendar.DAY_OF_WEEK) - 1;
		calendar.set(year, month,0);
		int Lday=calendar.get(Calendar.DATE);
		for (int i = Fday; i < Lday+Fday; i++) {
			day = i-Fday+1;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(new SimpleDateFormat("yyyy-MM-dd").format(ldate.getText()+day));
			} catch (Exception e) {
			}
			lday[i].setText(day+"");
			lday[i].setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource().equals(yearB)) {
			year--;
			update();
			for (int i = 0; i < lday.length; i++) {
				lday[i].setBorder(null);
			}
		}
		if(e.getSource().equals(yearN)) {
			year++;
			update();
			for (int i = 0; i < lday.length; i++) {
				lday[i].setBorder(null);
			}
		}
		if(e.getSource().equals(monthB)) {
			if(month == 1) {
				month = 12;
				year--;
			}else {
				month--;
			}
			
			update();
			for (int i = 0; i < lday.length; i++) {
				lday[i].setBorder(null);
			}
		}
		if(e.getSource().equals(monthN)) {
			if(month == 12) {
				month = 1;
				year++;
			}else {
				month++;
			}
			
			update();
			for (int i = 0; i < lday.length; i++) {
				lday[i].setBorder(null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < lday.length; i++) {
			if(e.getSource().equals(lday[i])) {
				jt.setText(year+"-"+new DecimalFormat("00").format(month)+"-"+new DecimalFormat("00").format(Integer.valueOf(lday[i].getText())));
				System.out.println(year+"-"+new DecimalFormat("00").format(month)+"-"+new DecimalFormat("00").format(Integer.valueOf(lday[i].getText())));
				setVisible(false);
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		CalCal c = new CalCal();
		c.form(new JTextField());

	}
	
	
}

