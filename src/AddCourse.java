import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

public class AddCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCID;
	private JTextField textFieldCourseName;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
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
	public AddCourse() {
		setTitle("Shariq");
		conn = SQLiteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddCourse = new JLabel("Add Course");
		lblAddCourse.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddCourse.setBounds(57, 11, 184, 49);
		contentPane.add(lblAddCourse);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(24, 92, 72, 14);
		contentPane.add(lblCourseId);
		
		textFieldCID = new JTextField();
		textFieldCID.setBounds(141, 89, 86, 20);
		contentPane.add(textFieldCID);
		textFieldCID.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(24, 135, 86, 14);
		contentPane.add(lblCourseName);
		
		textFieldCourseName = new JTextField();
		textFieldCourseName.setBounds(141, 132, 86, 20);
		contentPane.add(textFieldCourseName);
		textFieldCourseName.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Course (CID, CName, ExamDate) values (?, ?, ?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textFieldCID.getText());
					pst.setString(2,textFieldCourseName.getText());
					pst.setString(3,((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());

					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Course Added");
					
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(68, 241, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnShowAllCourses = new JButton("Show All Courses");
		btnShowAllCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select CID, CName, ExamDate from Course";
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
		btnShowAllCourses.setBounds(369, 37, 151, 23);
		contentPane.add(btnShowAllCourses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 92, 223, 191);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblExamDate = new JLabel("Exam Date");
		lblExamDate.setBounds(24, 172, 72, 14);
		contentPane.add(lblExamDate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacultyMain frame = new FacultyMain();
				frame.setVisible(true);
				dispose();
}
		});
		btnBack.setBounds(431, 325, 89, 23);
		contentPane.add(btnBack);
		
	    dateChooser = new JDateChooser();
		dateChooser.setBounds(141, 166, 91, 20);
		contentPane.add(dateChooser);
	}
}
