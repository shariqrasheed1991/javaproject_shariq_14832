import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;

public class StudentMarks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JList listSID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMarks frame = new StudentMarks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	private JTextField textFieldA1;
	private JTextField textFieldA2;
	private JTextField textFieldQ1;
	private JTextField textFieldQ2;
	private JTextField textFieldFinalExam;
	private JTextField textFieldSID;
	private JTextField textFieldCID;
	

	/**
	 * Create the frame.
	 */
	public StudentMarks() {
		conn = SQLiteConnection.dbConnector();
		setTitle("Shariq");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadStudentSheet = new JButton("Load Student Sheet");
		btnLoadStudentSheet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select SID, CID, CName, A1, A2, Q1, Q2, FinalExam from StudentCourse";
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
		btnLoadStudentSheet.setBounds(414, 250, 153, 23);
		contentPane.add(btnLoadStudentSheet);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 284, 533, 141);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					int row = table.getSelectedRow();
					String SID_ = (table.getModel().getValueAt(row, 0).toString());
					
					String query = "select * from StudentCourse where SID= '"+SID_+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while (rs.next()) {
					    textFieldSID.setText(rs.getString("SID"));
					    textFieldCID.setText(rs.getString("CID"));
						textFieldA1.setText(rs.getString("A1"));
						textFieldA2.setText(rs.getString("A2"));
						textFieldQ1.setText(rs.getString("Q1"));
						textFieldQ2.setText(rs.getString("Q2"));
						textFieldFinalExam.setText(rs.getString("FinalExam"));
					}
										
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblAssignment = new JLabel("Assignment 1");
		lblAssignment.setBounds(34, 44, 72, 14);
		contentPane.add(lblAssignment);
		
		JLabel lblAssignment_1 = new JLabel("Assignment 2");
		lblAssignment_1.setBounds(34, 69, 72, 14);
		contentPane.add(lblAssignment_1);
		
		JLabel lblAssignment_2 = new JLabel("Quiz 1");
		lblAssignment_2.setBounds(34, 111, 72, 14);
		contentPane.add(lblAssignment_2);
		
		JLabel lblQuiz = new JLabel("Quiz 2");
		lblQuiz.setBounds(34, 136, 46, 14);
		contentPane.add(lblQuiz);
		
		textFieldA1 = new JTextField();
		textFieldA1.setBounds(146, 41, 86, 20);
		contentPane.add(textFieldA1);
		textFieldA1.setColumns(10);
		
		textFieldA2 = new JTextField();
		textFieldA2.setBounds(146, 66, 86, 20);
		contentPane.add(textFieldA2);
		textFieldA2.setColumns(10);
		
		textFieldQ1 = new JTextField();
		textFieldQ1.setBounds(146, 108, 86, 20);
		contentPane.add(textFieldQ1);
		textFieldQ1.setColumns(10);
		
		textFieldQ2 = new JTextField();
		textFieldQ2.setBounds(146, 133, 86, 20);
		contentPane.add(textFieldQ2);
		textFieldQ2.setColumns(10);
		
		JLabel lblFinalExam = new JLabel("Final Exam");
		lblFinalExam.setBounds(34, 172, 72, 14);
		contentPane.add(lblFinalExam);
		
		textFieldFinalExam = new JTextField();
		textFieldFinalExam.setBounds(146, 169, 86, 20);
		contentPane.add(textFieldFinalExam);
		textFieldFinalExam.setColumns(10);
		
		JButton btnAddMarks = new JButton("Add Marks");
		btnAddMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update StudentCourse set A1='"+textFieldA1.getText() + "' ,A2='"+textFieldA2.getText()+ "',Q1='"+textFieldQ1.getText()+ "', Q2='"+textFieldQ2.getText()+ "', FinalExam='"+textFieldFinalExam.getText()+ "' where SID ='"+textFieldSID.getText()+"' AND CID ='"+textFieldCID.getText()+"' ";
					PreparedStatement pst = conn.prepareStatement(query);

					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated");
					
					pst.close();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
				
				
				
			}
		});
		btnAddMarks.setBounds(90, 232, 98, 23);
		contentPane.add(btnAddMarks);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(34, 11, 72, 14);
		contentPane.add(lblStudentId);
		
		textFieldSID = new JTextField();
		textFieldSID.setBounds(146, 8, 86, 20);
		contentPane.add(textFieldSID);
		textFieldSID.setColumns(10);
		
		JButton btnLoadStudentIds = new JButton("Load Student IDs");
		btnLoadStudentIds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select * from StudentCourse";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					DefaultListModel DLM = new DefaultListModel();
					
					while (rs.next()) {
						DLM.addElement(rs.getString("SID"));
						listSID.setModel(DLM);
					}
					
					pst.close();
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				
				
			}
		});
		btnLoadStudentIds.setBounds(344, 21, 135, 23);
		contentPane.add(btnLoadStudentIds);
		
		listSID = new JList();
		listSID.setBounds(344, 44, 135, 177);
		contentPane.add(listSID);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(34, 207, 72, 14);
		contentPane.add(lblCourseId);
		
		textFieldCID = new JTextField();
		textFieldCID.setBounds(146, 201, 86, 20);
		contentPane.add(textFieldCID);
		textFieldCID.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherMain frame = new TeacherMain();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(479, 436, 89, 23);
		contentPane.add(btnBack);
	}

}
