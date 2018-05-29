import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
public class Transcript extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transcript frame = new Transcript();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;

	/**
	 * Create the frame.
	 */
	public Transcript() {
		setTitle("Shariq");
		conn = SQLiteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(25, 11, 80, 14);
		contentPane.add(lblStudentId);
		
		textFieldSid = new JTextField();
		textFieldSid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			
				try {
					String temp = textFieldSid.getText();
					
					String query = "select SID, CID, CName, A1, A2, Q1, Q2, FinalExam from StudentCourse where SID = ? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textFieldSid.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			
			}
		});
		textFieldSid.setBounds(114, 8, 86, 20);
		contentPane.add(textFieldSid);
		textFieldSid.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 73, 545, 276);
		contentPane.add(scrollPane);
		
		table = new JTable();

	
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentPortal frame = new StudentPortal();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(431, 396, 89, 23);
		contentPane.add(btnBack);
	}
}
