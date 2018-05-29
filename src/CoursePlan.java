import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class CoursePlan extends JFrame {
	
	JList list;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursePlan frame = new CoursePlan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	private JButton btnSave;
	private JButton btnBack;
	private JLabel lblTotalQuizes;
	private JTextField textField_6;

	/**
	 * Create the frame.
	 */
	public CoursePlan() {
		setTitle("Shariq");
		conn = SQLiteConnection.dbConnector();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(10, 131, 72, 14);
		contentPane.add(lblCourseName);
		
		textField = new JTextField();
		textField.setBounds(120, 128, 72, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(10, 162, 72, 14);
		contentPane.add(lblCourseId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(120, 159, 72, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNoOfLectures = new JLabel("No. Of Lectures");
		lblNoOfLectures.setBounds(10, 193, 86, 14);
		contentPane.add(lblNoOfLectures);
		
		textField_2 = new JTextField();
		textField_2.setBounds(120, 190, 72, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTotalAssignments = new JLabel("Total Assignments");
		lblTotalAssignments.setBounds(10, 224, 102, 14);
		contentPane.add(lblTotalAssignments);
		
		textField_3 = new JTextField();
		textField_3.setBounds(120, 221, 72, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTeacherName = new JLabel("Teacher Name");
		lblTeacherName.setBounds(10, 103, 86, 14);
		contentPane.add(lblTeacherName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(120, 100, 72, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPassingMarks = new JLabel("Grading");
		lblPassingMarks.setBounds(10, 274, 72, 14);
		contentPane.add(lblPassingMarks);
		
		textField_5 = new JTextField();
		textField_5.setBounds(120, 271, 72, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblCoursePlan = new JLabel("Course PLan");
		lblCoursePlan.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoursePlan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCoursePlan.setBounds(78, 11, 261, 37);
		contentPane.add(lblCoursePlan);
		
		JButton btnCourses = new JButton("Courses");
		btnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select CName, CID  from Course";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					DefaultListModel DLM = new DefaultListModel();
					
					while (rs.next()) {
						DLM.addElement(rs.getString("CName"));
						DLM.addElement(rs.getString("CID"));
						list.setModel(DLM);
					}
					
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnCourses.setBounds(267, 68, 86, 23);
		contentPane.add(btnCourses);
		
		list = new JList();
		list.setBounds(266, 102, 87, 170);
		contentPane.add(list);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "insert into CoursePlan ( TName, CName, CID, 'Total Lectures', 'Total Assignments', 'Total Quizes', Grading) values (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textField_4.getText());
					pst.setString(2,textField.getText());
					pst.setString(3,textField_1.getText());
					pst.setString(4,textField_2.getText());
					pst.setString(5,textField_3.getText());
					pst.setString(6,textField_6.getText());
					pst.setString(7,textField_5.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSave.setBounds(78, 302, 89, 23);
		contentPane.add(btnSave);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherMain frame = new TeacherMain();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(335, 339, 89, 23);
		contentPane.add(btnBack);
		
		lblTotalQuizes = new JLabel("Total Quizes");
		lblTotalQuizes.setBounds(10, 249, 72, 14);
		contentPane.add(lblTotalQuizes);
		
		textField_6 = new JTextField();
		textField_6.setBounds(120, 246, 72, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
	}
}
