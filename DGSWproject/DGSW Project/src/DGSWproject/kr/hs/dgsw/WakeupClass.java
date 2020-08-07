package DGSWproject.kr.hs.dgsw;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.ButtonGroup;


public class WakeupClass extends JFrame implements Runnable{
	
	
	Statement stmt = MainClass.stmt;
	Connection conn = MainClass.conn;
	String sql;
	int i =0,j=0,kk=0;
	int deletearr[] = new int[100];
	String downarr[] = new String[100];
	JButton deleteButton[] = new JButton[100];
	JButton downButton[] = new JButton[100];
	JTable musictable;
	JTable starttable;
	JPanel panel_2;
	JPanel panel_1;
	DefaultTableModel musicmodel;
	DefaultTableModel startmodel;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	int startdelarr[] = new int[2];
	String startdownarr[] = new String[2];
	JPanel panel_3;
	JPanel panel;
	JButton button_1;
	JButton button_2;
	JButton btnNewButton;
	JButton button;
	public WakeupClass() {
		Thread th = new Thread(this);
		
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(30, 53, 382, 85);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		
		
		JLabel lblNewLabel = new JLabel("곧 틀어야 하는 곡");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 10, 151, 33);
		getContentPane().add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(30, 193, 383, 295);
	
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		
		
		String musiccn[]={"음악","가수","추천수"};
		musicmodel = new DefaultTableModel(null, musiccn){
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};
		startmodel = new DefaultTableModel(null, musiccn){
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};
		
		

		panel_2 = new JPanel();
		panel_2.setBounds(417, 213, 143, 274);
		
		panel_3 = new JPanel();
		panel_3.setBounds(417, 74, 143, 64);
		
		
		
		
		JLabel label = new JLabel("노래 목록");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		label.setBounds(30, 150, 90, 33);
		
		
		JRadioButton daysort = new JRadioButton("\uCD5C\uC2E0\uC21C");
		daysort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				if(MainClass.buttonwake){
					MainClass.buttonwake=false;
					th.interrupt(); new WakeupClass(); dispose();
				}
			}
		});
		buttonGroup.add(daysort);
		daysort.setSelected(!MainClass.buttonwake);
		daysort.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		daysort.setBounds(210, 156, 73, 23);
		
		
		JRadioButton likesort = new JRadioButton("\uCD94\uCC9C\uC21C");
		likesort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!MainClass.buttonwake){
					MainClass.buttonwake=true;
					
					th.interrupt(); new WakeupClass(); dispose();
				}
			}
		});
		buttonGroup.add(likesort);
		likesort.setSelected(MainClass.buttonwake);
		likesort.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		likesort.setBounds(133, 156, 73, 23);
		
		getContentPane().add(label);
		getContentPane().add(likesort);
		getContentPane().add(daysort);
		
		starttable = new JTable(startmodel);
		starttable.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		
		
		starttable.setRowHeight(30);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 테이블셀 렌더러 객체를 생성.
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);              // 생성한 렌더러의 가로정렬을 CENTER로
		starttable.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		starttable.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		starttable.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		starttable.getColumnModel().getColumn(0).setPreferredWidth(200);
		starttable.getColumnModel().getColumn(1).setPreferredWidth(100);
		starttable.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		panel.add(starttable, BorderLayout.CENTER);
		panel.add(new JScrollPane(starttable));
		
		
		musictable = new JTable(musicmodel);
		musictable.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel_1.add(musictable, BorderLayout.CENTER);
		JScrollPane musicScroll = new JScrollPane(musictable);
		musicScroll.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg) {
					String source = arg.getSource().toString();
					source = source.substring(1300);
					
					source = source.substring(source.indexOf("lastPaintPosition=")+39,source.indexOf("]"));
					System.out.println(source);
					
					kk=Math.abs(Integer.parseInt(source))/30;
			}
		});
		musicScroll.getVerticalScrollBar().setUnitIncrement(10);
		panel_1.add(musicScroll);
		
		musictable.setRowHeight(30);
		musictable.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		musictable.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		musictable.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		musictable.getColumnModel().getColumn(0).setPreferredWidth(200);
		musictable.getColumnModel().getColumn(1).setPreferredWidth(100);
		musictable.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		
		
		
		
		
		
		btnNewButton = new JButton("\uC0AD\uC81C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "정말 삭제 하시겠습니까?","확인!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (check == JOptionPane.YES_OPTION) {
					try {
						sql="delete from music where num="+startdelarr[0];
						stmt.executeUpdate(sql);
						sql="drop table music_"+startdelarr[0];
						stmt.executeUpdate(sql);
					} catch (SQLException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
					
					th.interrupt(); new WakeupClass(); dispose();
					
				}
			}
		});
		btnNewButton.setFont(new Font("굴림체", Font.PLAIN, 12));
		
		
		button = new JButton("\uB2E4\uC6B4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = startdownarr[0];
				url = url.replace("youtube.com","youtube-audio.com");
				System.out.println(url);
				try {	
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException e12) { e12.printStackTrace(); } catch (URISyntaxException e13) { e13.printStackTrace(); }
			}
		});
		button.setFont(new Font("굴림체", Font.PLAIN, 12));
		
		
		button_1 = new JButton("\uC0AD\uC81C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "정말 삭제 하시겠습니까?","확인!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (check == JOptionPane.YES_OPTION) {
					try {
						sql="delete from music where num="+startdelarr[1];
						stmt.executeUpdate(sql);
						sql="drop table music_"+startdelarr[1];
						stmt.executeUpdate(sql);
					} catch (SQLException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
					panel_2.removeAll();
					th.interrupt(); new WakeupClass(); dispose();
					
				}
			}
		});
		button_1.setFont(new Font("굴림체", Font.PLAIN, 12));
		
		
		button_2 = new JButton("\uB2E4\uC6B4");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = startdownarr[1];
				url = url.replace("youtube.com","youtube-audio.com");
				System.out.println(url);
				try {	
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException e12) { e12.printStackTrace(); } catch (URISyntaxException e13) { e13.printStackTrace(); }
			}
		});
		button_2.setFont(new Font("굴림체", Font.PLAIN, 12));
		
		
		
		i=0;
		musicmodel.setRowCount(0);
		startmodel.setRowCount(0);
		
		revalidate();
		
		try {
			
			if(!MainClass.buttonwake){
				i=0;
				sql = "select * from music order by likenum desc";
				Statement stmtt = conn.createStatement();
				ResultSet rs = stmtt.executeQuery(sql);
			
				while(rs.next() && i<2){
				
					String arr[] = new String[3];
					arr[0] = rs.getString("name");
					arr[1] = rs.getString("singer");
					arr[2] = rs.getString("likenum");
					if(!MainClass.buttonwake && i<2){
						startmodel.addRow(arr);
						startdelarr[i]=rs.getInt("num");
						startdownarr[i]=rs.getString("url");
					}		
					
					i++;
					
				}
				System.out.println("최신순안");
			}
			
			i=0;
			if(MainClass.buttonwake)
				sql = "select * from music order by likenum desc";
			else
				sql = "select * from music";
			Statement stmtt = conn.createStatement();
			ResultSet rs = stmtt.executeQuery(sql);
		
			while(rs.next() && i<100){
			
				String arr[] = new String[3];
				arr[0] = rs.getString("name");
				arr[1] = rs.getString("singer");
				arr[2] = rs.getString("likenum");
				musicmodel.addRow(arr);
				if(MainClass.buttonwake && i<2){
					startmodel.addRow(arr);
					startdelarr[i]=rs.getInt("num");
					startdownarr[i]=rs.getString("url");
				}				
				deletearr[i]=rs.getInt("num");
				downarr[i]=rs.getString("url");
				i++;
				
			}
			
		
			
			if(i>=1){
				panel_3.add(btnNewButton);
				panel_3.add(button);
			}
			if(i>=2){
				panel_3.add(button_1);
				panel_3.add(button_2);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(j=0;j<i && j<9;j++){
			
			deleteButton[j] = new JButton("삭제");
			deleteButton[j].setFont(new Font("굴림체", Font.PLAIN, 12));
			deleteButton[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg1) {
					String source = arg1.getSource().toString();
					System.out.println(source);
					source = source.substring(source.indexOf("11,")+3,source.indexOf(",58"));
					System.out.println(source);
					int k = (Integer.parseInt(source)-5)/30;
					System.out.println(k);
					
					int check = JOptionPane.showConfirmDialog(null, "정말 삭제 하시겠습니까?","확인!",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					
					if (check == JOptionPane.YES_OPTION) {
						try {
							sql="delete from music where num="+deletearr[k+kk];
							stmt.executeUpdate(sql);
							sql="drop table music_"+deletearr[k+kk];
							stmt.executeUpdate(sql);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						th.interrupt(); new WakeupClass(); dispose();
						
					}
					
					
				}
			});
			panel_2.add(deleteButton[j]);
			
			downButton[j] = new JButton("다운");
			downButton[j].setFont(new Font("굴림체", Font.PLAIN, 12));
			
			downButton[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg2) {
					
					String source = arg2.getSource().toString();
					source = source.substring(source.indexOf("74,")+3,source.indexOf(",58"));
					System.out.println(source);
					int k = (Integer.parseInt(source)-5)/30;
					System.out.println(k);
					
					System.out.println(downarr[k+kk]);
					String url = downarr[k+kk];
					url = url.replace("youtube.com","youtube-audio.com");
					System.out.println(url);
					try {	
						Desktop.getDesktop().browse(new URI(url));
					} catch (IOException e) { JOptionPane.showMessageDialog(null, "지정된 주소를 찾을 수 없습니다."); e.printStackTrace(); } catch (URISyntaxException e) { JOptionPane.showMessageDialog(null, "지정된 주소를 찾을 수 없습니다."); e.printStackTrace(); }
		 				 				
								
				}
			});
			panel_2.add(downButton[j]);
			
		}
		getContentPane().add(panel_2);
		getContentPane().add(panel_3);
		getContentPane().add(panel_1);
		getContentPane().add(panel);
		
		
		setBounds(100, 100, 569, 544);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		th.start();
		
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
				System.out.println("다시불러옴");
					
					
				dispose();
				new WakeupClass(); 
				break;	
					
				
			
			}
		} catch (InterruptedException e) {

			System.out.println("쓰레드 중지 예외");
		} finally {


		System.out.println("Thread is dead…");

		}
	}
	
}
