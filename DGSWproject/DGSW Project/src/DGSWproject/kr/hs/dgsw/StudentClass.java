package DGSWproject.kr.hs.dgsw;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import java.sql.*;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Buffer;

import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.interfaces.RSAKey;


public class StudentClass extends JFrame implements Runnable{
	Thread th = new Thread(this);
	int likearr[] = new int[100];
	String listenarr[] = new String[100];
	JButton likeButton[] = new JButton[100];
	JButton listenButton[] = new JButton[100];
	int kk=0;
	int no = 0;
	String sql;
	String ip;
	Statement stmt = MainClass.stmt;
	Connection conn = MainClass.conn;
	private JTextField guitarField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField musictitle;
	private JTextField singer;
	private JTextField url;
	private JTable table;
	JComboBox<String> classromcomboBox;
	JComboBox<String> labcomboBox;
	JComboBox<String> programcomboBox;
	JComboBox<String> specialcomboBox;
	private JRadioButton classroomButton;
	private JRadioButton programButton;
	private JRadioButton labButton;
	private JRadioButton specialButton;
	private JRadioButton guitarButton;	
	private JRadioButton gymButton;	
	int buttonnum=1; //1-±³½Ç 2-·¾½Ç 3-ÇÁ½Ç 4-Ã¼À°°ü 5-Æ¯¼ö½Ç 6-±âÅ¸
	String location;
	String tabled;
	String num;
	int day;
	int mon;
	int hour;
	int min;
	int daw;
	int firstbuffer=0;
	JLabel start;
	JLabel end;
	JRadioButton outingButton;
	JLabel timelabel;
	JLabel namelabel;
	private JRadioButton nighttruebutton;
	private JRadioButton nightfalsebutton;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel label;
	private JRadioButton gymtruebutton;
	private JRadioButton gymfalsebutton;
	private JLabel label_1;
	private JRadioButton outingtruebutton;
	private JRadioButton outingfalsebutton;
	private JLabel label_3;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private JLabel label_4;
	private JRadioButton busdaegukbottun;
	private JRadioButton busdongbutton;
	private JRadioButton busnobutton;
	private final ButtonGroup buttonGroup_5 = new ButtonGroup();
	DefaultTableModel model;
	public StudentClass(String ip){
		this.ip = ip;
		
		
		setBounds(100, 100, 953, 560);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		TitledBorder name = new TitledBorder("ÇÐ»ýÀÌµ¿»óÈ²");
		name.setTitleFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		
		JPanel panel = new JPanel();
		panel.setBorder(name);
		panel.setBounds(12, 436, 866, 85);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		classroomButton = new JRadioButton("\uAD50\uC2E4");
		classroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updatemylocation("±³½Ç",classromcomboBox);
			}
		});
		classroomButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		buttonGroup.add(classroomButton);
		panel.add(classroomButton);
		
		classromcomboBox = new JComboBox<String>();
		classromcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(firstbuffer<4){
					firstbuffer++;
					return;
				}
				classroomButton.setSelected(true);
				updatemynum("±³½Ç", classromcomboBox.getSelectedItem().toString());
			}
		});
		classromcomboBox.addItem("1-1");
		classromcomboBox.addItem("1-2");
		classromcomboBox.addItem("1-3");
		classromcomboBox.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		panel.add(classromcomboBox);
		
		labButton = new JRadioButton("\uB819\uC2E4");
		labButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatemylocation("·¾½Ç", labcomboBox);
			}
		});
		labButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		buttonGroup.add(labButton);
		panel.add(labButton);
		
		labcomboBox = new JComboBox<String>();
		labcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(firstbuffer<4){
					firstbuffer++;
					return;
				}
				labButton.setSelected(true);
				updatemynum("·¾½Ç", labcomboBox.getSelectedItem().toString());
			}
		});
		for(int j=1;j<=14;j++)
			labcomboBox.addItem(String.valueOf(j));
		labcomboBox.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		panel.add(labcomboBox);
		
		programButton = new JRadioButton("\uD504\uC2E4");
		programButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updatemylocation("ÇÁ½Ç", programcomboBox);
			}
		});
		programButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		buttonGroup.add(programButton);
		panel.add(programButton);
		
		programcomboBox = new JComboBox<String>();
		programcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				programButton.setSelected(true);
				if(firstbuffer<4){
					firstbuffer++;
					return;
				}
				updatemynum("ÇÁ½Ç", programcomboBox.getSelectedItem().toString());
			}
		});
		for(int j=1;j<=2;j++)
			programcomboBox.addItem(String.valueOf(j));
		programcomboBox.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		panel.add(programcomboBox);
		
		gymButton = new JRadioButton("\uCCB4\uC721\uAD00");
		gymButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql="update "+tabled+" set location='Ã¼À°°ü', num='0' where no="+no;
				System.out.println(sql);
				try {
					stmt.execute(sql);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		gymButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		buttonGroup.add(gymButton);
		panel.add(gymButton);
		
		specialButton = new JRadioButton("\uD2B9\uC218\uC2E4");
		specialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatemylocation("Æ¯¼ö½Ç", specialcomboBox);
			}
		});
		specialButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		buttonGroup.add(specialButton);
		panel.add(specialButton);
		
		specialcomboBox = new JComboBox<String>();
		specialcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(firstbuffer<4){
					firstbuffer++;
					return;
				}
				specialButton.setSelected(true);
				updatemynum("Æ¯¼ö½Ç", programcomboBox.getSelectedItem().toString());
			}
		});
		String specialtmp[] = {"¿ÀÄÉ½ºÆ®¶ó½Ç","·Îº¸Æ½½º±â´É½Ç","°úÇÐ½Ç","À½¾Ç½Ç","À¥ÇÁ·Î±×·¡¹Ö½Ç","Ã¥¸¶·çµµ¼­½Ç"};
		for(int j=0;j<specialtmp.length;j++)
			specialcomboBox.addItem(specialtmp[j]);
		specialcomboBox.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		panel.add(specialcomboBox);
		
		guitarButton = new JRadioButton("\uAE30\uD0C0");
		guitarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql="update "+tabled+" set location='±âÅ¸', num='"+guitarField.getText()+"' where no="+no;
				System.out.println(sql);
				try {
					stmt.execute(sql);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		guitarButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		buttonGroup.add(guitarButton);
		panel.add(guitarButton);
		
		guitarField = new JTextField();
		guitarField.addKeyListener(new KeyAdapter() {
		
			@Override
			public void keyReleased(KeyEvent e) {
				guitarButton.setSelected(true);
				updatemynum("±âÅ¸", guitarField.getText());
			}
		});
		
		guitarField.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		panel.add(guitarField);
		guitarField.setColumns(10);
		
		outingButton = new JRadioButton("\uC678\uCD9C\uC911");
		buttonGroup.add(outingButton);
		outingButton.setEnabled(false);
		outingButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		panel.add(outingButton);
		
		
		TitledBorder name2 = new TitledBorder("¿ÜÃâ¿¹Á¤");
		name2.setTitleFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(name2);
		panel_1.setBounds(12, 248, 401, 100);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		start = new JLabel("\uC2DC\uC791\uC2DC\uAC04 :  11\uC6D4 18\uC77C 6\uC2DC 40\uBD84");
		panel_1.add(start);
		start.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		
		end = new JLabel("\uADC0\uAC00\uC2DC\uAC04 :  11\uC6D4 18\uC77C 6\uC2DC 40\uBD84");
		end.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		panel_1.add(end);
		
		timelabel = new JLabel("11\uC6D4 28\uC77C \uD654\uC694\uC77C 8\uC2DC 40\uBD84");
		timelabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		timelabel.setHorizontalAlignment(start.CENTER);
		timelabel.setBounds(38, 10, 348, 31);
		getContentPane().add(timelabel);
		
		namelabel = new JLabel("1\uD559\uB144 2\uBC18 5\uBC88 \uBC30\uC218\uD55C");
		namelabel.setHorizontalAlignment(SwingConstants.CENTER);
		namelabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		namelabel.setBounds(38, 41, 348, 31);
		getContentPane().add(namelabel);
		
		TitledBorder name3 = new TitledBorder("È°µ¿Ã¼Å©");
		name3.setTitleFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 74, 401, 164);
		panel_2.setBorder(name3);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(68, 21, 257, 117);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		getday();
		System.out.println("daw="+daw);
		if(daw>=2 && daw<=6){
			JLabel lblNewLabel_1 = new JLabel("½É¾ßÀÚ½À");
			lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			panel_7.add(lblNewLabel_1);
			
			nighttruebutton = new JRadioButton("\uC608");
			nighttruebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set night = true where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			nighttruebutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			buttonGroup_1.add(nighttruebutton);
			panel_7.add(nighttruebutton);
			
			nightfalsebutton = new JRadioButton("\uC544\uB2C8\uC694");
			nightfalsebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sql="update "+mon+"_"+day+" set night = false where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			nightfalsebutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			buttonGroup_1.add(nightfalsebutton);
			panel_7.add(nightfalsebutton);
			
			
			label = new JLabel("¾ßÀÚÃ¼À°°ü");
			label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			panel_7.add(label);
			
			gymtruebutton = new JRadioButton("\uC608");
			gymtruebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set gym = true where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup_4.add(gymtruebutton);
			gymtruebutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			panel_7.add(gymtruebutton);
			
			gymfalsebutton = new JRadioButton("\uC544\uB2C8\uC694");
			gymfalsebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set gym = false where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup_4.add(gymfalsebutton);
			gymfalsebutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
				panel_7.add(gymfalsebutton);
		}
		
		if(daw==3){
			label_1 = new JLabel("Á¤±â¿ÜÃâ");
			label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			panel_7.add(label_1);
			
			outingtruebutton = new JRadioButton("\uC608");
			outingtruebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set outing = true where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup_3.add(outingtruebutton);
			outingtruebutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			panel_7.add(outingtruebutton);
			
			outingfalsebutton = new JRadioButton("\uC544\uB2C8\uC694");
			outingfalsebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set outing = false where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup_3.add(outingfalsebutton);
			outingfalsebutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			panel_7.add(outingfalsebutton);
		}
		
		label_3 = new JLabel((String) null);
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		panel_7.add(label_3);
		
		if(daw==6){
			label_4 = new JLabel("\uD558\uAD50\uBC84\uC2A4");
			label_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			label_4.setBounds(41, 105, 72, 25);
			panel_2.add(label_4);
			
			busdaegukbottun = new JRadioButton("\uB300\uACE1\uC5ED");
			busdaegukbottun.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set bus = 1 where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup_5.add(busdaegukbottun);
			busdaegukbottun.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			busdaegukbottun.setBounds(118, 101, 79, 33);
			panel_2.add(busdaegukbottun);
			
			busdongbutton = new JRadioButton("\uB3D9\uB300\uAD6C\uC5ED");
			busdongbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set bus = 2 where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup_5.add(busdongbutton);
			busdongbutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			busdongbutton.setBounds(203, 101, 97, 33);
			panel_2.add(busdongbutton);
			
			busnobutton = new JRadioButton("\uC548\uD0D0");
			busnobutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sql="update "+mon+"_"+day+" set bus =  where no="+no;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup_5.add(busnobutton);
			busnobutton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
			busnobutton.setBounds(304, 101, 86, 33);
			panel_2.add(busnobutton);
		}
		panel_2.add(panel_7);
		
		TitledBorder name4 = new TitledBorder("³ë·¡µî·Ï");
		name4.setTitleFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(name4);
		panel_3.setBounds(440, 313, 442, 124);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_5 = new JLabel("\uB178\uB798\uC81C\uBAA9 : ");
		label_5.setBounds(13, 47, 98, 25);
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		panel_3.add(label_5);
		
		musictitle = new JTextField();
		musictitle.setBounds(100, 50, 143, 25);
		panel_3.add(musictitle);
		musictitle.setColumns(10);
		
		singer = new JTextField();
		singer.setColumns(10);
		singer.setBounds(310, 50, 120, 25);
		panel_3.add(singer);
		
		JLabel label_6 = new JLabel("\uAC00\uC218 : ");
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		label_6.setBounds(259, 51, 53, 25);
		panel_3.add(label_6);
		
		JLabel lblUrl = new JLabel("\uC720\uD29C\uBE0C url : ");
		lblUrl.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		lblUrl.setBounds(11, 84, 98, 25);
		panel_3.add(lblUrl);
		
		url = new JTextField();
		url.setBounds(108, 87, 321, 25);
		panel_3.add(url);
		url.setColumns(10);
		
		JButton button = new JButton("\uB4F1\uB85D");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(musictitle.getText().toString().equals("")){
					JOptionPane.showMessageDialog(null, "³ë·¡Á¦¸ñÀÌ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù!");
					return;
				}
				if(singer.getText().toString().equals("")){
					JOptionPane.showMessageDialog(null, "°¡¼ö¸íÀÌ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù!");
					return;
				}
				if(!url.getText().contains("www.youtube.com/") ){
					JOptionPane.showMessageDialog(null, "¿ÏÀüÇÑ À¯Æ©ºê ÁÖ¼Ò¸¦ Àû¾îÁÖ¼¼¿ä!");
					return;
				}
				
				//INSERT INTO `dgsw`.`music` (`no`, `name`, `singer`, `likenum`, `checkplay`, `url`) VALUES ('25', 'Èò¼ö¿°°í·¡', 'À±µµÇö', '12', '0', 'https://www.youtube.com/watch?v=v-pjWpCSAVI');
				try {
					int num = 0;
					sql="insert into music (`no`, `name`, `singer`, `likenum`, `checkplay`, `url`) "
							+ "VALUES ('"+no+"', '"+musictitle.getText()+"', '"+singer.getText()+"', '0', '0', '"+url.getText()+"');";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					
					sql="select * from music";
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()){
						num=rs.getInt("num");
					}
					
					sql="create table music_"+num+"(no int not null,primary key(no))";
					stmt.executeUpdate(sql);
					
					musictitle.setText("");
					singer.setText("");
					url.setText("");
					
					JOptionPane.showMessageDialog(null, "µî·Ï¼º°ø!");
					new StudentClass(ip);
					th.interrupt(); dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		button.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		button.setBounds(300, 20, 97, 23);
		panel_3.add(button);
		
		
		TitledBorder name5 = new TitledBorder("±â»ó¼Û");
		name5.setTitleFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(name5);
		panel_4.setBounds(443, 15, 436, 279);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 29, 284, 235);
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		String musiccn[]={"À½¾Ç","°¡¼ö","ÃßÃµ¼ö"};
		model = new DefaultTableModel(null, musiccn){
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};
		
		table = new JTable(model);
		table.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel_5.add(table, BorderLayout.CENTER);
		JScrollPane Scroll = new JScrollPane(table);
		Scroll.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg) {
					String source = arg.getSource().toString();
					source = source.substring(1300);
					
					source = source.substring(source.indexOf("lastPaintPosition=")+39,source.indexOf("]"));
					System.out.println(source);
					
					kk=Math.abs(Integer.parseInt(source))/30;
			}
		});
		Scroll.getVerticalScrollBar().setUnitIncrement(10);
		panel_5.add(Scroll);
		
		table.setRowHeight(30);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // Å×ÀÌºí¼¿ ·»´õ·¯ °´Ã¼¸¦ »ý¼º.
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);              // »ý¼ºÇÑ ·»´õ·¯ÀÇ °¡·ÎÁ¤·ÄÀ» CENTER·Î
		table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(296, 51, 136, 218);
		panel_4.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDb = new JLabel("\uC704\uC758 \uB0A0\uC9DC\uB294 DB\uAD00\uB9AC\uC790 \uD504\uB85C\uADF8\uB7A8\uC5D0\uC11C \uBCC0\uACBD \uAC00\uB2A5");
		lblDb.setBounds(16, 349, 293, 25);
		getContentPane().add(lblDb);
		
		JButton btnNewButton_3 = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btnNewButton_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new StudentClass(ip);
				th.interrupt(); dispose();
			}
		});
		btnNewButton_3.setBounds(329, 17, 97, 23);
		getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("<<<<<\uC218\uC775\uCC3D\uCD9C\uC744 \uC704\uD55C \uAD11\uACE0\uBC30\uB108\uC790\uB9AC>>>>>");
		lblNewLabel.setBounds(12, 370, 401, 56);
		getContentPane().add(lblNewLabel);
		
		
		int i=0;
		
		Statement stmtt;
		try {
			sql = "select * from music order by likenum desc";
			stmtt = conn.createStatement();
		
			ResultSet rs = stmtt.executeQuery(sql);
			while(rs.next() && i<100){
				
				String arr[] = new String[3];
				arr[0] = rs.getString("name");
				arr[1] = rs.getString("singer");
				arr[2] = rs.getString("likenum");
				model.addRow(arr);
				
				likearr[i]=rs.getInt("num");
				listenarr[i]=rs.getString("url");
				i++;
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int j=0;j<i && j<7;j++){
			
			likeButton[j] = new JButton("ÃßÃµ");
			likeButton[j].setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 12));
			likeButton[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg1) {
					String source = arg1.getSource().toString();
					System.out.println(source);
					source = source.substring(source.indexOf("7,")+2,source.indexOf(",58"));
					System.out.println(source);
					int k = (Integer.parseInt(source)-5)/30;
					System.out.println(k);
					try {
						int cnt =0;
						
						sql = "select * from music_"+likearr[k+kk]+";";
						Statement stmtt = conn.createStatement();
						ResultSet result = stmtt.executeQuery(sql);
						System.out.println(sql);
						while (result.next()) {
							cnt++;
						}
						
						if(cnt==1){
							JOptionPane.showMessageDialog(null, "ÀÌ¹Ì ÃßÃµÇÏ¼Ì½À´Ï´Ù.¤Ð¤Ð");
						}else{
							sql="INSERT INTO `dgsw`.`music_"+likearr[k+kk]+"` (`no`) VALUES ('"+no+"');";
							stmt.executeUpdate(sql);
							sql="update music set likenum = likenum+1 where num="+likearr[k+kk];
							stmt.executeUpdate(sql);
							table.setValueAt(Integer.parseInt((table.getValueAt(k+kk, 2).toString()))+1, k+kk, 2);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
					
				}
			});
			panel_6.add(likeButton[j]);
			
			listenButton[j] = new JButton("µè±â");
			listenButton[j].setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 12));
			
			listenButton[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg2) {
					
					String source = arg2.getSource().toString();
					source = source.substring(source.indexOf("70,")+3,source.indexOf(",58"));
					System.out.println(source);
					int k = (Integer.parseInt(source)-5)/30;
					System.out.println(k);
					
					System.out.println(listenarr[k+kk]);
					String url = listenarr[k+kk];
					System.out.println(url);
					try {	
						Desktop.getDesktop().browse(new URI(url));
					} catch (IOException e) { JOptionPane.showMessageDialog(null, "ÁöÁ¤µÈ ÁÖ¼Ò¸¦ Ã£À» ¼ö ¾ø½À´Ï´Ù."); e.printStackTrace(); } catch (URISyntaxException e) { JOptionPane.showMessageDialog(null, "ÁöÁ¤µÈ ÁÖ¼Ò¸¦ Ã£À» ¼ö ¾ø½À´Ï´Ù."); e.printStackTrace(); }
	 				
								
				}
			});
			panel_6.add(listenButton[j]);
			
		}
		
		getno();
		//getday();
		getoutingtime();
		getmylocation();
		
		
		setBounds(100, 100, 900, 560);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//addWindowListener(new CloseEvent());
		setVisible(true);
		th.start();
	}
	
	public void updatemynum(String nowlocation,String num){
		sql="update "+tabled+" set num='"+num+"', location='"+nowlocation+"' where no="+no;
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatemylocation(String string, JComboBox<String> box) {
		sql="update "+tabled+" set location='"+string+"', num='"+ box.getSelectedItem().toString()+"' where no="+no;
		System.out.println(sql);
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void getmylocation(){
		sql="select * from "+tabled+" where no="+no;
		System.out.println(sql);
		try {
			Statement stmtt = conn.createStatement();
			ResultSet rs = stmtt.executeQuery(sql);
			while(rs.next()){
				String location;
				location = rs.getString("location");
				num = rs.getString("num");
				
				if(location.equals("±³½Ç")){
					classroomButton.setSelected(true); 
					classromcomboBox.setSelectedItem(num);
				}
				else if(location.equals("·¾½Ç")){
					labButton.setSelected(true);
					labcomboBox.setSelectedItem(num);
				}
				else if(location.equals("ÇÁ½Ç")){
					programButton.setSelected(true);
					programcomboBox.setSelectedItem(num);
				}
				else if(location.equals("Ã¼À°°ü")){
					gymButton.setSelected(true);
				}
				else if(location.equals("Æ¯¼ö½Ç")){
					specialButton.setSelected(true);
					specialcomboBox.setSelectedItem(num);
				}
				else if(location.equals("±âÅ¸")){
					guitarButton.setSelected(true);
					guitarField.setText(num);
				}
				if(location.equals("¿ÜÃâ")){
					outingButton.setSelected(true);
					outingButton.setEnabled(true);
				}
				
				if(daw>=2 && daw<=6){
					System.out.println("µé¾î¿È");
					if(rs.getBoolean("night"))
						nighttruebutton.setSelected(true);
					else
						nightfalsebutton.setSelected(true);
					
					if(rs.getBoolean("gym"))
						gymtruebutton.setSelected(true);
					else
						gymfalsebutton.setSelected(true);
				}
				if(daw==3){
					if(rs.getBoolean("outing"))
						outingtruebutton.setSelected(true);
					else
						outingfalsebutton.setSelected(true);
				}
				
				if(daw==6){
					if(rs.getInt("bus")==1)
						busdaegukbottun.setSelected(true);
					else if(rs.getInt("bus")==2)
						busdongbutton.setSelected(true);
					else
						busnobutton.setSelected(true);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getoutingtime(){
		int cnt=0;
		sql="select * from outing where no="+no;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				System.out.println(rs.getInt("endmon") +": "+rs.getInt("endmon"));
				System.out.println(mon+": "+day);
				if(rs.getInt("endmon") <= mon)
					break;
				
				if(rs.getInt("endmon") <= mon && rs.getInt("endday") < day){
					break;
				}
				start.setText("½ÃÀÛ ½Ã°£ : "+rs.getString("stmon")+"¿ù "+rs.getString("stday")+"ÀÏ "+rs.getString("sthour")+"½Ã "+rs.getString("stmin")+"ºÐ");
				end.setText("Á¾·á ½Ã°£ : "+rs.getString("endmon")+"¿ù "+rs.getString("endday")+"ÀÏ "+rs.getString("endhour")+"½Ã "+rs.getString("endmin")+"ºÐ");
				cnt++;
				break;
			}
			if(cnt==0){
				start.setText("µî·ÏµÈ ¿ÜÃâ ¿¹Á¤ÀÌ ¾ø½À´Ï´Ù!");
				end.setText("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getday(){//³¯Â¥
		sql="select * from date";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				mon=rs.getInt("month");
				day=rs.getInt("day");
				hour=rs.getInt("hour");
				min=rs.getInt("min");
				daw=rs.getInt("daw");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabled=String.valueOf(mon)+"_"+String.valueOf(day);
		
		timelabel.setText(mon+"¿ù "+day+"ÀÏ "+hour+"½Ã "+min+"ºÐ");
	}

	
	public void getno(){
		
		String query = "select * from dgsw.student where ip=" + "'" + ip + "'";
		System.out.println(query);
		
		try {
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				no = rs.getInt("no");
				namelabel.setText(rs.getString("grade")+"ÇÐ³â "+rs.getString("class")+"¹Ý "+rs.getString("num")+"¹ø "+rs.getString("name"));
			}
		} catch (Exception e) {
		}
		
		System.out.println(no);
	}
	
	@Override
	public void run() {
		Calendar cal = Calendar.getInstance();
		
		try{
			while(true){
				cal = Calendar.getInstance();
				
				Thread.sleep(60000);
				if(cal.get(Calendar.SECOND)<4){
					Thread.sleep(5000);
				
					
				}
				System.out.println("´Ù½ÃºÒ·¯¿È");
					
					
				dispose();
				new StudentClass(ip); 
				break;	
					
				
			
			}
		} catch (InterruptedException e) {

			System.out.println("¾²·¹µå ÁßÁö ¿¹¿Ü");
		} finally {


		System.out.println("Thread is dead¡¦");

		}
	}
}
