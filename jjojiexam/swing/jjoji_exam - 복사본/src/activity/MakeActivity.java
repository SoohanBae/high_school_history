package activity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class MakeActivity extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeActivity frame = new MakeActivity();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MakeActivity() {
		setDefaultCloseOperation(2);
		setBounds(0, 0, 1152, 648);
		setTitle("문제출제");
		
		fontSet();
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 15, 2, 15));
		panel.setBackground(new Color(204, 255, 255));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("/24");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setHgap(10);
		panel_2.setBackground(new Color(204, 255, 255));
		panel.add(panel_2);
		
		JButton prevBtn = new JButton("\uC774\uC804");
		prevBtn.setForeground(Color.WHITE);
		prevBtn.setBackground(new Color(0, 176, 240));
		panel_2.add(prevBtn);
		
		JButton nextBtn = new JButton("\uB2E4\uC74C");
		nextBtn.setForeground(Color.WHITE);
		nextBtn.setBackground(new Color(0, 176, 240));
		panel_2.add(nextBtn);
		
		JButton addBtn = new JButton("\uBB38\uC81C \uCD94\uAC00");
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(new Color(0, 176, 240));
		panel_2.add(addBtn);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(204, 255, 255));
		panel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		
		JButton closeBtn = new JButton("\uCDE8\uC18C");
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setBackground(new Color(0, 176, 240));
		panel_3.add(closeBtn);
		
		JButton saveBtn = new JButton("\uC800\uC7A5");
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setBackground(new Color(0, 176, 240));
		panel_3.add(saveBtn);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 0, 5, 10));
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("\uC0AD\uC81C");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 176, 240));
		panel_5.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uB2E8\uB2F5\uD615");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0, 176, 240));
		panel_5.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uC120\uB2E4\uD615");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(0, 176, 240));
		panel_5.add(btnNewButton_2);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public void fontSet() {
		Font deFont = new Font("나눔스퀘어라운드 Bold", Font.PLAIN, 16);
	
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

}
