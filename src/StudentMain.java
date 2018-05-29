import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class StudentMain extends JFrame {

	private JPanel contentPane;
	JList listCourseNames;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMain frame = new StudentMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection conn = null;
	private JLabel lblAddCourrse;
	private JLabel lblStudentID;
	private JLabel lblCourseId;
	private JLabel lblCourseName;
	private JTextField textFieldSID;
	private JTextField textFieldCID;
	private JTextField textFieldCName;
	private JButton btnAddCourse;
	private JButton btnShowRegisteredCourses;
	private JTable table;
	private JButton btnBack;
	
	

	/**
	 * Create the frame.
	 */
	public StudentMain() {
		conn = SQLiteConnection.dbConnector();
		setTitle("Shariq");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listCourseNames = new JList();
		listCourseNames.setBounds(312, 82, 86, 195);
		contentPane.add(listCourseNames);
		
		JButton btnShowCourses = new JButton("Course List");
		btnShowCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
					try {
						String query = "select CName, CID  from Course";
						PreparedStatement pst = conn.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						DefaultListModel DLM = new DefaultListModel();
						
						while (rs.next()) {
							DLM.addElement(rs.getString("CName"));
							DLM.addElement(rs.getString("CID"));
							listCourseNames.setModel(DLM);
						}
						
						pst.close();
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				
				
		
			
		});
		btnShowCourses.setBounds(312, 48, 86, 23);
		contentPane.add(btnShowCourses);
		
		lblAddCourrse = new JLabel("Add Courrse");
		lblAddCourrse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddCourrse.setBounds(36, 16, 171, 40);
		contentPane.add(lblAddCourrse);
		
		lblStudentID = new JLabel("Student ID");
		lblStudentID.setBounds(36, 102, 93, 14);
		contentPane.add(lblStudentID);
		
		lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(36, 139, 65, 14);
		contentPane.add(lblCourseId);
		
		lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(36, 174, 77, 14);
		contentPane.add(lblCourseName);
		
		textFieldSID = new JTextField();
		textFieldSID.setBounds(147, 99, 86, 20);
		contentPane.add(textFieldSID);
		textFieldSID.setColumns(10);
		
		textFieldCID = new JTextField();
		textFieldCID.setBounds(147, 136, 86, 20);
		contentPane.add(textFieldCID);
		textFieldCID.setColumns(10);
		
		textFieldCName = new JTextField();
		textFieldCName.setBounds(147, 171, 86, 20);
		contentPane.add(textFieldCName);
		textFieldCName.setColumns(10);
		
		btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "insert into StudentCourse (SID, CID, CName) values (?, ?, ?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textFieldSID.getText());
					pst.setString(2,textFieldCID.getText());
					pst.setString(3,textFieldCName.getText());

					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Course Added");
					
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try {
					String query1 = "insert into AttendancePage ('Student ID', 'Course ID', 'Course Name') values (?, ?, ?)";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.setString(1,textFieldSID.getText());
					pst1.setString(2,textFieldCID.getText());
					pst1.setString(3,textFieldCName.getText());

					pst1.execute();
					

					
					pst1.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
		btnAddCourse.setBounds(118, 211, 115, 23);
		contentPane.add(btnAddCourse);
		
		btnShowRegisteredCourses = new JButton("Show Registered Courses");
		btnShowRegisteredCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select CID, CName, SID from StudentCourse";
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
		btnShowRegisteredCourses.setBounds(28, 277, 177, 23);
		contentPane.add(btnShowRegisteredCourses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 311, 245, 105);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentPortal frame = new StudentPortal();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(347, 405, 89, 23);
		contentPane.add(btnBack);
	}
}
