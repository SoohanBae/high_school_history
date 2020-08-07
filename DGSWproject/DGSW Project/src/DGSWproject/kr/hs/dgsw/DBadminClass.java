package DGSWproject.kr.hs.dgsw;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class DBadminClass extends JFrame implements Runnable {

	public static boolean pause = false;
	public static boolean stopped = false;
	
	Statement stmt = MainClass.stmt;
	Connection conn = MainClass.conn;
	
	List<OutingData> otdata = new ArrayList();
	OutingData element = new OutingData();
	int otindex = 0;
	
	String num;
	
	public String sql;

	Calendar cal = Calendar.getInstance();
	int mon = 0;
	int day = 0;
	int hour = 0;
	int min = 0;
	int daw = 0;
	Calendar call = Calendar.getInstance();
	public DBadminClass() {
		
		JOptionPane.showMessageDialog(null, "�ð������� �����մϴ�.");
		getContentPane().setLayout(null);
		ButtonGroup bg = new ButtonGroup();

		JRadioButton auto = new JRadioButton("\uD604\uC7AC\uC2DC\uAC04 \uC0AC\uC6A9");
		auto.setFont(new Font("���� ���", Font.PLAIN, 16));
		auto.setBounds(37, 17, 156, 31);
		getContentPane().add(auto);
		auto.setSelected(true);
		bg.add(auto);
		auto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("������̺�Ʈ");
				run2();
				JOptionPane.showMessageDialog(null, "�ð����� �Ϸ�!");
				pause = false;

			}
		});

		JRadioButton notauto = new JRadioButton(
				"\uC784\uC758\uB85C \uC2DC\uAC04 \uBCC0\uACBD(\uD14C\uC2A4\uD2B8 \uC6A9\uB3C4)");
		notauto.setFont(new Font("���� ���", Font.PLAIN, 16));
		notauto.setBounds(37, 63, 281, 31);
		getContentPane().add(notauto);
		bg.add(notauto);
		notauto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�����̺�Ʈ");
				pause = true;

			}
		});

		JComboBox monthBox = new JComboBox();
		monthBox.setBounds(45, 109, 41, 21);
		for (int i = 1; i <= 12; i++)
			monthBox.addItem(i);
		monthBox.setSelectedIndex(cal.get(Calendar.MONTH));
		getContentPane().add(monthBox);

		JLabel lblNewLabel = new JLabel("\uC6D4");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
		lblNewLabel.setBounds(93, 111, 21, 21);
		getContentPane().add(lblNewLabel);

		JComboBox dayBox = new JComboBox();
		dayBox.setBounds(140, 109, 41, 21);
		for (int i = 1; i <= 31; i++) {
			String m = monthBox.getSelectedItem().toString();

			if (m.equals("2") && i == 29)
				break;

			if ((m.equals("4") || m.equals("6") || m.equals("9") || m.equals("11")) && i == 31)
				break;

			dayBox.addItem(i);
		}
		dayBox.setSelectedIndex(cal.get(Calendar.DATE)-1);
		getContentPane().add(dayBox);

		monthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String d = dayBox.getSelectedItem().toString();
				dayBox.removeAllItems();
				String m = monthBox.getSelectedItem().toString();

				System.out.println(d);
				System.out.println(m);

				for (int i = 1; i <= 31; i++) {

					if (m.equals("2") && i == 29) {
						if (Integer.parseInt(d) > 28)
							d = "28";
						break;
					}

					if ((m.equals("4") || m.equals("6") || m.equals("9") || m.equals("11")) && i == 31) {
						if (Integer.parseInt(d) > 30)
							d = "30";

						break;
					}
					dayBox.addItem(i);
				}

				dayBox.setSelectedIndex(Integer.parseInt(d) - 1);
			}
		});

		JLabel label = new JLabel("\uC77C");
		label.setFont(new Font("���� ���", Font.PLAIN, 14));
		label.setBounds(188, 111, 21, 21);
		getContentPane().add(label);

		JComboBox hourBox = new JComboBox();
		hourBox.setBounds(249, 109, 41, 21);
		for (int i = 0; i <= 24; i++)
			hourBox.addItem(i);
		hourBox.setSelectedIndex(cal.get(Calendar.HOUR_OF_DAY));
		getContentPane().add(hourBox);

		JLabel label_1 = new JLabel("\uC2DC");
		label_1.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_1.setBounds(297, 111, 21, 21);

		getContentPane().add(label_1);

		JComboBox miniteBox = new JComboBox();
		miniteBox.setBounds(355, 109, 41, 21);
		for (int i = 0; i <= 60; i++)
			miniteBox.addItem(i);
		miniteBox.setSelectedIndex(cal.get(Calendar.MINUTE));
		getContentPane().add(miniteBox);

		JLabel label_2 = new JLabel("\uBD84");
		label_2.setFont(new Font("���� ���", Font.PLAIN, 14));
		label_2.setBounds(403, 111, 21, 21);
		getContentPane().add(label_2);

		JButton button = new JButton("\uD655\uC778");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(notauto.isSelected()){
					mon = Integer.parseInt(monthBox.getSelectedItem().toString());
					day = Integer.parseInt(dayBox.getSelectedItem().toString());
					hour = Integer.parseInt(hourBox.getSelectedItem().toString());
					min = Integer.parseInt(miniteBox.getSelectedItem().toString());
					
					
					call.set(2017,mon-1,day,0,0,0);
					daw=call.get(Calendar.DAY_OF_WEEK);
					System.out.println(mon+"_"+day);
					System.out.println(daw);
					run2();
					JOptionPane.showMessageDialog(null, "�ð����� �Ϸ�!");
				}
			}
		});
		button.setFont(new Font("���� ���", Font.PLAIN, 14));
		button.setBounds(327, 69, 97, 23);
		getContentPane().add(button);
		
		JLabel lblNewLabel_1 = new JLabel("\uD604\uC7AC\uC2DC\uAC04 \uC0AC\uC6A9\uC744 \uB204\uB974\uBA74 \uC2DC\uAC04\uC774 \uC790\uB3D9\uC73C\uB85C \uD758\uB7EC\uAC11\uB2C8\uB2E4.");
		lblNewLabel_1.setBounds(37, 145, 411, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel label_3 = new JLabel("\uC784\uC758\uB85C \uC2DC\uAC04 \uBCC0\uACBD\uC2DC \uC2DC\uAC04\uC774 \uD758\uB7EC\uAC00\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
		label_3.setBounds(37, 163, 305, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\uC2DC\uAC04\uC744 \uBCC0\uACBD\uD558\uACE0 \uD655\uC778\uBC84\uD2BC\uC744 \uB20C\uB7EC \uC8FC\uC138\uC694");
		label_4.setBounds(36, 182, 233, 15);
		getContentPane().add(label_4);
		
		
		
		setBounds(100, 100, 510, 242);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
		
			
		
		cal = Calendar.getInstance();
		mon = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		min = cal.get(Calendar.MINUTE);
		daw = cal.get(Calendar.DAY_OF_WEEK); // 1 = �Ͽ���, 2= ������, 3 = ȭ����
		
		run2();
		JOptionPane.showMessageDialog(null, "�ð����� �Ϸ�!");
		
		Thread main = new Thread(this);
		main.start();
	}

	public void run2(){
		
		int hourI = hour;
		int minI = min;
		
		min = 0;
		hour = 0;
		
		try {
			sql = "select * from outing"; 
			ResultSet rs = stmt.executeQuery(sql); 
			otdata.clear();
			otindex = 0;
			
			while(rs.next()){
				element.set(rs.getInt("num"), rs.getInt("no"), rs.getInt("stmon"), rs.getInt("stday"), rs.getInt("sthour"), rs.getInt("stmin"), rs.getInt("endmon"), rs.getInt("endday"), rs.getInt("endhour"), rs.getInt("endmin"), rs.getString("reason"));
				otdata.add(otindex++, element); 
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("��" + otdata.get(0).num);
		daw=call.get(Calendar.DAY_OF_WEEK);
		
		while(!(hourI == hour && minI == min)){
			
			ChangeDB();
			
			min++;
			if(min == 60){
				min = 0;
				hour++;
			}
			
		}
		sql = "update date set month = " + mon + ", day = " + day + ", hour = " + hourI + ", min = " + minI +", daw = " + daw + ";";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {

		
		
		try {
			int cnt =0;
			
			sql = "show tables like '"+mon + "_" + day +"';";
			Statement stmtt = conn.createStatement();
			ResultSet result = stmtt.executeQuery(sql);
			System.out.println(sql);
			while (result.next()) {
				cnt++;
			}
			
			if(cnt == 0){
				newday();
			}
			
			while (!stopped) {

				if (pause) {
					System.out.println("������ ������");

					Thread.sleep(2000);

					continue;
				}

				System.out.println("Threa is alive��");
				cal = Calendar.getInstance();
				mon = cal.get(Calendar.MONTH) + 1;
				day = cal.get(Calendar.DAY_OF_MONTH);
				hour = cal.get(Calendar.HOUR_OF_DAY);
				min = cal.get(Calendar.MINUTE);
				daw = cal.get(Calendar.DAY_OF_WEEK); // 1 = �Ͽ���, 2= ������, 3 = ȭ����
				
				System.out.println(mon + "�� " + day + "�� " + hour + "�� " + min + "�� " + daw + "����");
				
				sql = "select * from outing"; 
				ResultSet rs = stmt.executeQuery(sql); 
				otdata.clear();
				otindex = 0;
				
				while(rs.next()){
					element.set(rs.getInt("num"), rs.getInt("no"), rs.getInt("stmon"), rs.getInt("stday"), rs.getInt("sthour"), rs.getInt("stmin"), rs.getInt("endmon"), rs.getInt("endday"), rs.getInt("endhour"), rs.getInt("endmin"), rs.getString("reason"));
					otdata.add(otindex++, element); 
				}
				System.out.println(otdata.get(0).num);
				
				sql = "update date set month = " + mon + ", day = " + day + ", hour = " + hour + ", min = " + min +", daw = " + daw + ";";
				System.out.println(sql);
				stmt.executeUpdate(sql);
				
				ChangeDB();
				
				if(cal.get(Calendar.SECOND) <= 2)
					Thread.sleep(60000);
				else{
					while(!(cal.get(Calendar.SECOND) <= 2))
						cal = Calendar.getInstance();
						Thread.sleep(950);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println("�ý��� ����");
	}

	public void ChangeDB(){
		if (hour == 0 && min == 0) {
			newday();
		}
		switch (daw) {
		case 2:
			monday();
			break;
		case 3:
			tuesday();
			break;
		case 4:
			wensday();
			break;
		case 5:
			thursday();
			break;
		case 6:
			friday();
		default:
			break;
		}
		
		try{
			for(int i = 0; i < otdata.size(); i++){
				if(mon == otdata.get(i).stmon && day == otdata.get(i).stday && hour == otdata.get(i).sthour && min == otdata.get(i).stmin){
					sql = "update " + mon + "_" + day + " set " + "location = '����',  num='"+ otdata.get(i).reason +"' where no="+ otdata.get(i).no+";";
					System.out.println(sql);
					stmt.executeUpdate(sql);
				}
				else if(mon == otdata.get(i).endmon && day == otdata.get(i).endday && hour == otdata.get(i).endhour && min == otdata.get(i).endmin){
					
					
					sql = "select class from student where no="+otdata.get(i).no;
					ResultSet rs = stmt.executeQuery(sql); 
					
					while(rs.next()){
						num = rs.getString(1).toString();
					}
					sql = "update " + mon + "_" + day + " set " + "location = '����', num='1-" + num + "' where no=" + otdata.get(i).no+";";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					otdata.remove(i);
				}
			}
		} catch(SQLException e){ 
			e.printStackTrace();
		}
		
		
	}
	
	public void newday() {
		try {
			sql = "update date set month = " + mon + ", day = " + day + ", hour = " + hour + ", min = " + min +", daw = " + daw + ";";
			stmt.executeUpdate(sql);
			
			int cnt = 0;
			
			sql = "show tables like '"+mon + "_" + day +"';";
			Statement stmtt = conn.createStatement();
			ResultSet result = stmtt.executeQuery(sql);
			System.out.println(sql);
			while (result.next()) {
				cnt++;
			}
			System.out.println(cnt);
			if(cnt == 1){
				sql = "update "+mon+"_"+day+" set location ='����', num='1-1' where no<=20";
				stmt.executeUpdate(sql);
				sql = "update "+mon+"_"+day+" set location ='����', num='1-2' where no>=21 and no<=39";
				stmt.executeUpdate(sql);
				sql = "update "+mon+"_"+day+" set location ='����', num='1-2' where no>=40";
			}
			else{		
				sql = "Create table " + mon + "_" + day
						+ "( no int not null auto_increment, location VarChar(20) not null, num VarChar(20) not null, ";
				System.out.println(daw);
				if(daw>=2 && 6>=daw){
					
					sql=sql+"gym boolean DEFAULT false, night boolean DEFAULT false, ";
					String sqll = "insert into gymtable (date) values ('"+mon+"_"+day+"')";
					System.out.println(sqll);
					stmt.executeUpdate(sqll);
					sqll = "insert into nighttable (date) values ('"+mon+"_"+day+"')";
					System.out.println(sqll);
					stmt.executeUpdate(sqll);
				}
				
				if (daw == 3) {
					sql = sql + "outing boolean DEFAULT false,";
					String sqll = "insert into outingtable (date) values ('"+mon+"_"+day+"')";
					System.out.println(sqll);
					stmt.executeUpdate(sqll);
				} else if (daw == 6) {
					sql = sql + "bus int DEFAULT 0,";
					String sqll = "insert into bustable (date) values ('"+mon+"_"+day+"')";
					System.out.println(sqll);
					stmt.executeUpdate(sqll);
				}
	
				sql = sql + "primary key(no));";
				
				System.out.println(sql);
				stmt.executeUpdate(sql);
				
				
				sql = "insert into " + mon + "_" + day + " (location, num) values"; 
				for (int i = 1; i <= 59; i++) {
					if (i <= 20) {
						sql = sql + "(" + "'����'" + ",'1-1'";
						sql = sql + "),";
					} else if (i <= 39) {
						sql = sql + "(" + "'����'" + ",'1-2'";
						sql = sql + "),";
						
					} else {
						sql = sql + "(" + "'����'" + ",'1-3'";
						if(i!=59)
							sql = sql + "),";	
					}
				}
				sql = sql + ");";
				System.out.println(sql);
				stmt.executeUpdate(sql);
				
				for(int i = 0; i < otdata.size(); i++){
					if(mon >= otdata.get(i).stmon && day >= otdata.get(i).stday && mon <= otdata.get(i).endmon && day <= otdata.get(i).endday){
						sql = "update " + mon + "_" + day + " set " + "location = '����',  num='"+ otdata.get(i).reason +"' where no="+ otdata.get(i).no+";";
						System.out.println(sql);
						stmt.executeUpdate(sql);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void skill(){
		try {
			
			sql = "update "+ mon + "_" + day +" set location = '����', num = '4' where no in(select no from infoskill) and location = '����';";
			stmt.executeUpdate(sql);
			
			sql = "update " + mon + "_" + day + " set location = '����', num = '2' where no in(select no from pageskill) and location = '����';";
			stmt.executeUpdate(sql);
			
			sql = "update "+ mon + "_" + day +" set location = '��Ÿ', num = '�κ�ƽ����ɽ�' where no in(select no from mrskill) and location = '����';";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void endskill(){
		
		Goclassrom("infoskill", "����", "4");
		Goclassrom("pageskill", "����", "2");
		Goclassrom("mrskill", "��Ÿ", "�κ�ƽ����ɽ�");
			
	}
	
	public void monday(){
		try{
			if(hour == 15 && min == 30){  //3�� 30�� ��Ÿ�����
				sql = "update "+ mon + "_" + day +" set location = '��Ÿ', num = '���ǽ�' where no in(select no from monguitar) and location = '����';";
				stmt.executeUpdate(sql);
			}
			else if(hour == 16	&& min == 35){   //4�� 35�� ����� ����, â���� �����ذ�ɷ� , ��ɹ� 
				sql = "update "+ mon + "_" + day +" set location = '����', num = '2' where no in(select no from monraspberry) and location = '����';";
				stmt.executeUpdate(sql);
				
				sql = "update "+ mon + "_" + day +" set location = '����', num = '14' where no in(select no from moncreative) and location = '����';";
				stmt.executeUpdate(sql);
				
				skill();
				
			}
			else if(hour == 17 && min == 25){   //5�� 20�� ����� ����, �������� �����ذ�ɷ�, ��Ÿ����� ����
				Goclassrom("monguitar", "��Ÿ", "���ǽ�");
 				Goclassrom("monraspberry", "����", "2");
 				Goclassrom("moncreative","����", "14");
			}

			else if(hour == 18 && min == 25){    //6�� 25�� , ��ɹ� ����
				endskill();
			}
			else if(hour == 19 && min == 5){
				sql = "update "+ mon + "_" + day +" set location = '��Ÿ', num = '�ްԽ�' where no in(select no from moncoffee) and location = '����';";
				stmt.executeUpdate(sql);
				gogym();
			}
			else if(hour == 20 && min ==5){
				backgym();
			}
			else if(hour == 21 && min == 5){
				Goclassrom("moncoffee", "��Ÿ", "�ްԽ�");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void tuesday(){
		try{
			
			if(hour == 16 && min == 35){   //4�� 35�� ����Ƽ ,���������, ����, ��ɹ� 
				sql = "update "+ mon + "_" + day +" set location = '����', num = '1' where no in(select no from tueunity) and location = '����';";
				stmt.executeUpdate(sql);
				sql = "update "+ mon + "_" + day +" set location = '����', num = '2' where no in(select no from tueraspberry) and location = '����';";
				stmt.executeUpdate(sql);
				sql = "update "+ mon + "_" + day +" set location = '����', num = '14' where no in(select no from tuesequence) and location = '����';";
				stmt.executeUpdate(sql);
				
				skill();
			}
			else if(hour == 17 && min == 25){   //5�� 20�� ����� ����, ���� ����
 				Goclassrom("tueraspberry", "����", "2");
 				Goclassrom("tuesequence", "����", "14");
			}
			else if(hour == 18 && min == 25){    //6�� 25�� ����Ƽ ����, ��ɹ� ����
				Goclassrom("tueunity", "����","1");
				endskill();
			}
			else if(hour == 19 && min ==5){
				gogym();
			}
			else if(hour == 20 && min ==5){
				backgym();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void wensday(){
		try{
			
			if(hour == 16 && min == 35){   //4�� 35�� ���̽�, ����Ƽ, ������� ,���ʿ���, ��ɹ� 
				sql = "update "+ mon + "_" + day +" set location = '����', num = '1' where no in(select no from wedpython) and location = '����';";
				stmt.executeUpdate(sql);
				sql = "update "+ mon + "_" + day +" set location = '����', num = '2' where no in(select no from wedunity) and location = '����';";
				stmt.executeUpdate(sql);
				sql = "update "+ mon + "_" + day +" set location = '����', num = '13' where no in(select no from wedkorean) and location = '����';";
				stmt.executeUpdate(sql);
				sql = "update "+ mon + "_" + day +" set location = '����', num = '14' where no in(select no from wedenglish) and location = '����';";
				stmt.executeUpdate(sql);
				skill();
			}
			else if(hour == 17 && min == 25){   //5�� 20��  ���̽�, ����Ƽ, �������, ���ʿ���, ����
 				Goclassrom("wedpython", "����", "1");
 				Goclassrom("wedunity", "����", "2");
 				Goclassrom("wedkorean", "����", "13");
 				Goclassrom("wedenglish", "����", "14");
			}
			else if(hour == 17 && min == 35){   //5�� 35�� ������ ��ŷ
				sql = "update "+ mon + "_" + day +" set location = '����', num = '14' where no in(select no from wedthinking) and location = '����';";
				stmt.executeUpdate(sql);
			}
			else if(hour == 18 && min == 25){   //6�� 25�� ��ɹ� ����
				endskill();
			}
			else if(hour == 19 && min == 5){
				sql = "update "+ mon + "_" + day +" set location = '��Ÿ', num = '�ްԽ�' where no in(select no from wedbake) and location = '����';";
				stmt.executeUpdate(sql);
				gogym();
			}
			else if(hour == 20 && min ==5){
				backgym();
			}
			else if(hour == 21 && min == 5){   //9�� 5�� ������ ��ŷ ����
				Goclassrom("wedthinking", "����", "14");
				Goclassrom("wedbake", "��Ÿ", "�ްԽ�");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void thursday(){
		try{
			
			if(hour == 16 && min == 35){   //4�� 35�� ���̽�, ����Ƽ3D, â���� �����ذ�ɷ�, ��ɹ� 
				sql = "update "+ mon + "_" + day +" set location = '����', num = '1' where no in(select no from thupython) and location = '����';";
				stmt.executeUpdate(sql);
				sql = "update "+ mon + "_" + day +" set location = '����', num = '2' where no in(select no from thuunity) and location = '����';";
				stmt.executeUpdate(sql);
				sql = "update "+ mon + "_" + day +" set location = '����', num = '14' where no in(select no from thucreative) and location = '����';";
				stmt.executeUpdate(sql);
				
				skill();
			}
			else if(hour == 17 && min == 25){   //5�� 20�� ���̽�, â���� �����ذ�ɷ�����
 				Goclassrom("thupython", "����", "1");
 				Goclassrom("thucreative", "����", "14");
			}
			else if(hour == 18 && min == 25){    //6�� 25�� ����Ƽ 3D ����, ��ɹ� ����
				Goclassrom("thuunity","����", "2");
				endskill();
			}
			else if(hour == 19 && min == 5){   //7�� 5�� ����ó����ɻ�
				sql = "update "+ mon + "_" + day +" set location = '����', num = '1' where no in(select no from thulicense) and location = '����';";
				stmt.executeUpdate(sql);
				gogym();
			}
			else if(hour == 20 && min ==5){
				backgym();
			}
			else if(hour == 21 && min == 5){   //9�� 5�� ����ó����ɻ�  ����
				Goclassrom("thulicense", "����", "1");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void friday(){
		if(hour == 19 && min ==5){
			gogym();
		}
		else if(hour == 20 && min ==5){
			backgym();
		}
	}
	
	public void Goclassrom(String table, String location, String num){
		try{
			sql = "select no from "+ mon + "_" + day + " where no in(select no from " + table +") and location = '"+location + "' and num='"+num +"';";

			
			Statement stmtt = conn.createStatement();
			ResultSet result = stmtt.executeQuery(sql);
			
			String no;
			
			while (result.next()) {
				
				no = result.getString(1).toString();
				
				if(Integer.parseInt(no) <= 20)
					sql = "update "+ mon + "_" + day + " set location = '����', num = '1-1' where no ='"+no+"';";
				else if(Integer.parseInt(no) <= 39)
					sql = "update "+ mon + "_" + day + " set location = '����', num = '1-2' where no ='"+no+"';";
				else 
					sql = "update "+ mon + "_" + day + " set location = '����', num = '1-3' where no ='"+no+"';";
				

				stmt.executeUpdate(sql); //�������� ��ġ
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void gogym(){
		try{
			sql = "update "+ mon + "_" + day + " set location = 'ü����', num = '1' where gym = 1";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void backgym(){
	try {
		sql = "update "+ mon + "_" + day + " set location = '����', num = '1-1' where location = 'ü����' and no<=20;";
		
			stmt.executeUpdate(sql);
		
		sql = "update "+ mon + "_" + day + " set location = '����', num = '1-2' where location = 'ü����' and no<=39;";
		stmt.executeUpdate(sql);
		sql = "update "+ mon + "_" + day + " set location = '����', num = '1-3' where location = 'ü����'";		
		stmt.executeUpdate(sql); //�������� ��ġ
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
