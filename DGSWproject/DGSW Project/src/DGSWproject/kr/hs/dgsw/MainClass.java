package DGSWproject.kr.hs.dgsw;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MainClass extends JFrame implements ActionListener {

	static Connection conn = null;
	static Statement stmt = null;
	static boolean buttonwake = true;
	String ip;
	
	public MainClass() {

		Connect(); // mysql����
		
		
		getContentPane().setLayout(null);

		JLabel titlename = new JLabel("DGSW ����ȭ �ý���");
		titlename.setFont(new Font("����ǹ��� ����", Font.PLAIN, 30));
		getContentPane().add(titlename);
		titlename.setHorizontalAlignment(SwingConstants.CENTER);
		titlename.setBounds(12, 10, 660, 39);

		JButton studentBotton = new JButton("�л���");
		studentBotton.setBounds(50, 98, 240, 100);
		studentBotton.addActionListener(this);
		getContentPane().add(studentBotton);

		JButton teacherBotton = new JButton("�����Կ�");
		teacherBotton.setBounds(369, 98, 240, 100);
		teacherBotton.addActionListener(this);
		getContentPane().add(teacherBotton);

		JButton DBadminBotton = new JButton("DB�����ڿ�");
		DBadminBotton.setBounds(50, 269, 240, 100);
		DBadminBotton.addActionListener(this);
		getContentPane().add(DBadminBotton);

		JButton wakeupBotton = new JButton("���� �����ڿ�");
		wakeupBotton.setBounds(369, 269, 240, 100);
		wakeupBotton.addActionListener(this);
		getContentPane().add(wakeupBotton);
		
		JLabel lblNewLabel = new JLabel("\uD14C\uC774\uBE14 \uC0DD\uC131\uC744 \uC704\uD574 DB\uAD00\uB9AC\uC790\uC6A9 \uD504\uB85C\uADF8\uB7A8\uC744 \uBA3C\uC800 \uC2E4\uD589\uC2DC\uCF1C \uC8FC\uC138\uC694");
		lblNewLabel.setBounds(56, 389, 584, 29);
		getContentPane().add(lblNewLabel);

		setBounds(100, 100, 700, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	public static void main(String[] args) {

		new MainClass();
	}

	public void actionPerformed(ActionEvent name) {
		System.out.println(name.getActionCommand());
		
		switch (name.getActionCommand()) {
		case "�л���":
			if(ipcheck() == false)
				new Ipinsert(ip);
			else
				new StudentClass(ip);

			break;
		case "�����Կ�":
			new Login("teacher", "������");
			break;
		case "DB�����ڿ�":
			new Login("admin", "DB������");
			break;
		case "���� �����ڿ�":
			new Login("wakeup", "���� ������");
			break;

		default:
			break;
		}
		dispose();

	}

	public boolean ipcheck() {
		
		int cnt = 0;

		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(ip);

		String query = "select ip from dgsw.student where ip=" + "'" + ip + "'";
		System.out.println(query);

		try {
			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				cnt++;
			}
		} catch (Exception e) {
		}

		if (cnt >= 1)
			return true;
		else
			return false;
	}

	public void Connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.80.162.111:3306/dgsw", "DGSWuser", "dgsw102");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dgsw", "DGSWuser", "dgsw102");
			stmt = conn.createStatement();
			System.out.println("���� ����");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}
}
