import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;

public class DisplayCoursePlan extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayCoursePlan frame = new DisplayCoursePlan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection conn = null;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public DisplayCoursePlan() {
		
		setTitle("Shariq");
		conn = SQLiteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CID", "CName"}));
		comboBox.setBounds(10, 22, 84, 20);
		contentPane.add(comboBox);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					String selection = (String)comboBox.getSelectedItem();
					String query = "select * from CoursePlan where "+selection+"=? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invalid Data");
				}
				
				
			}
		});
		textFieldSearch.setBounds(102, 22, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 67, 414, 183);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 414, 183);
		panel.add(table);
	}
}
