import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSID;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNo;
	private JTextField textFieldAddress;
	private JComboBox comboBoxSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldFee;
	private JTable table;
	private JTextField textFieldSearch;

	/**
	 * Create the frame.
	 */
	public AddStudent() {
		setTitle("Shariq");
		conn = SQLiteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(10, 80, 66, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 105, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 130, 66, 14);
		contentPane.add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 155, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setBounds(10, 180, 66, 14);
		contentPane.add(lblPhoneNo);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 205, 66, 14);
		contentPane.add(lblAddress);
		
		textFieldSID = new JTextField();
		textFieldSID.setBounds(101, 77, 86, 20);
		contentPane.add(textFieldSID);
		textFieldSID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(101, 102, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setBounds(101, 127, 86, 20);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(101, 152, 86, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldPhoneNo = new JTextField();
		textFieldPhoneNo.setBounds(101, 177, 86, 20);
		contentPane.add(textFieldPhoneNo);
		textFieldPhoneNo.setColumns(10);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setBounds(101, 202, 86, 20);
		contentPane.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Student (SID, Name, Surname, Email, PhoneNo, Address, Username, Password, Fee) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textFieldSID.getText());
					pst.setString(2,textFieldName.getText());
					pst.setString(3,textFieldSurname.getText());
					pst.setString(4,textFieldEmail.getText());
					pst.setString(5,textFieldPhoneNo.getText());
					pst.setString(6,textFieldAddress.getText());
					pst.setString(7,textFieldUsername.getText());
					pst.setString(8,textFieldPassword.getText());
					pst.setString(9,textFieldFee.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSave.setBounds(10, 359, 76, 23);
		contentPane.add(btnSave);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 230, 66, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 255, 66, 14);
		contentPane.add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(101, 227, 86, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(101, 252, 86, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblFee = new JLabel("Fee");
		lblFee.setBounds(10, 280, 66, 14);
		contentPane.add(lblFee);
		
		textFieldFee = new JTextField();
		textFieldFee.setBounds(101, 277, 86, 20);
		contentPane.add(textFieldFee);
		textFieldFee.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 105, 876, 343);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table.getSelectedRow();
					String SID_ = (table.getModel().getValueAt(row, 0).toString());
					
					String query = "select * from Student where SID= '"+ SID_+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while (rs.next()) {
						textFieldSID.setText(rs.getString("SID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldSurname.setText(rs.getString("Surname"));
						textFieldEmail.setText(rs.getString("Email"));
						textFieldPhoneNo.setText(rs.getString("PhoneNo"));
						textFieldAddress.setText(rs.getString("Address"));
						textFieldUsername.setText(rs.getString("Username"));
						textFieldPassword.setText(rs.getString("Password"));
						textFieldFee.setText(rs.getString("Fee"));
					}
										
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnShowRecords = new JButton("Show Records");
		btnShowRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select SID, Name, Surname, Email, Address, PhoneNo, Username, Password, Fee from Student";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				
			}
		});
		btnShowRecords.setBounds(946, 76, 127, 23);
		contentPane.add(btnShowRecords);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update Student set SID='"+textFieldSID.getText() + "' "
							+ ",name='"+textFieldName.getText()+ "'"
									+ ",surname='"+textFieldSurname.getText()+ "'"
											+ ", email='"+textFieldEmail.getText()+ "'" 
												 + ", address ='"+textFieldAddress.getText()+ "'"
													  + ", phoneno ='"+textFieldPhoneNo.getText()+ "' "
															+ ", username ='"+textFieldUsername.getText()+ "' "
																	+ ", password ='"+textFieldPassword.getText()+ "' "
																			+ ", fee ='"+textFieldFee.getText()+ "'  where SID ='"+textFieldSID.getText()+"'  ";
					PreparedStatement pst = conn.prepareStatement(query); 

					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated");
					
					pst.close();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
				
			}
			
		});
		btnUpdate.setBounds(111, 359, 76, 23);
		contentPane.add(btnUpdate);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String selection = (String)comboBoxSearch.getSelectedItem();
					String query = "select * from Student where "+selection+"=? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		textFieldSearch.setBounds(361, 77, 127, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSearch.setBounds(251, 52, 46, 14);
		contentPane.add(lblSearch);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"SID", "Name", "Username"}));
		comboBoxSearch.setBounds(251, 77, 76, 20);
		contentPane.add(comboBoxSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacultyMain frame = new FacultyMain();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(976, 468, 89, 23);
		contentPane.add(btnBack);
	}

}
