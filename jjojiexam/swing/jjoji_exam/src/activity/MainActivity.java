package activity;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.paragraphadder.ParagraphAdder;
import kr.dogfoot.hwplib.writer.HWPWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class MainActivity extends BaseActivity {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainActivity frame = new MainActivity();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void fontSet() {
			Font deFont = new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 16);
		
		UIManager.put("Button.font",deFont);
		UIManager.put("ToggleButton.font",deFont);
		UIManager.put("RadioButton.font",deFont);
		UIManager.put("CheckBox.font",deFont);
		UIManager.put("ColorChooser.font",deFont);
		UIManager.put("ComboBox.font",deFont);
		UIManager.put("Label.font",deFont);
		UIManager.put("List.font",deFont);
		UIManager.put("MenuBar.font",deFont);
		UIManager.put("MenuItem.font",deFont);
		UIManager.put("RadioButtonMenuItem.font",deFont);
		UIManager.put("CheckBoxMenuItem.font",deFont);
		UIManager.put("Menu.font",deFont);
		UIManager.put("PopupMenu.font",deFont);
		UIManager.put("OptionPane.font",deFont);
		UIManager.put("Panel.font",deFont);
		UIManager.put("ProgressBar.font",deFont);
		UIManager.put("ScrollPane.font",deFont);
		UIManager.put("Viewport.font",deFont);
		UIManager.put("TabbedPane.font",deFont);
		UIManager.put("Table.font",deFont);
		UIManager.put("TableHeader.font",deFont);
		UIManager.put("TextField.font",deFont);
		UIManager.put("PasswordField.font",deFont);
		UIManager.put("TextArea.font",deFont);
		UIManager.put("TextPane.font",deFont);
		UIManager.put("EditorPane.font",deFont);
		UIManager.put("TitledBorder.font",deFont);
		UIManager.put("ToolBar.font",deFont);
		UIManager.put("ToolTip.font",deFont);
		UIManager.put("Tree.font",deFont);
	}
	
	
	/**
	 * Create the frame.
	 */
	public MainActivity() {

		
		UIManager.getLookAndFeelDefaults()
        .put("defaultFont", new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.ITALIC, 18));
		
		rest.Rest.dbs();
		
	
		
		
		fontSet();
		
		setTitle("ÂÊÁö½ºÄð");
		setDefaultCloseOperation(3);
		setBounds(0, 0, 1152, 648);
		setLocationRelativeTo(null);
		CardLayout c1 = new CardLayout(0,0);
		
		
		
		getContentPane().setLayout(c1);
		ClassActivity classActivity = new ClassActivity();
		getContentPane().add(classActivity, "classActivity");
		
		LoginActivity loginActivity = new LoginActivity();
		getContentPane().add(loginActivity, "loginActivity");
		
		
		loginActivity.loginLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(loginActivity.loginFun()) {
					
					
					classActivity.updateClassPanel();
					classActivity.updateClass();
					
					c1.show(getContentPane(), "classActivity");
				}
							
				
			}
		});
		
		
		
		
		c1.show(getContentPane(),"loginActivity");

		
		

		

		
		

		
	}

}
