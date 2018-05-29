import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
public class Attendance extends JFrame {

	private JPanel contentPane;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Attendance frame = new Attendance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField textField;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnSave;
	private JTextField textField_1;
	private JButton btnRefresh;
	private JButton btnId;
	private JTextField textField_2;
	
	/**
	 * Create the frame.
	 */
	public Attendance() {
		
		setTitle("Shariq");
		conn = SQLiteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(10, 75, 77, 14);
		contentPane.add(lblCourseName);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String query = "select * from AttendancePage where `Course Name` = ? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textField.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		textField.setBounds(10, 100, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 548, 206);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					int row = table.getSelectedRow();
					String SID_ = (table.getModel().getValueAt(row, 0).toString());
					
					String query = "select * from AttendancePage where [Student ID]= '"+ SID_+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while (rs.next()) {
						textField_1.setText(rs.getString("Attendance"));
						textField_2.setText(rs.getString("Student ID"));
					}
										
					pst.close();

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "error");
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		btnSave = new JButton("Attendance");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
		});
		btnSave.setBounds(365, 81, 89, 23);
		contentPane.add(btnSave);
		
		textField_1 = new JTextField();
		textField_1.setBounds(464, 82, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnRefresh = new JButton("Save");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "Update AttendancePage set Attendance='"+textField_1.getText() + "' "
							+ "  ,Date ='"+((JTextField)dateChooser.getDateEditor().getUiComponent()).getText()+ "'where [Student ID] ='"+textField_2.getText()+"'  ";
					PreparedStatement pst = conn.prepareStatement(query); 

					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated");
					
					pst.close();
				} catch (Exception exception) {
					exception.printStackTrace();
				}

								
				
			}
		});
		btnRefresh.setBounds(38, 407, 89, 23);
		contentPane.add(btnRefresh);
		
		btnId = new JButton("ID");
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnId.setBounds(365, 112, 89, 23);
		contentPane.add(btnId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(464, 113, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateChooser.setBounds(66, 131, 140, 20);
		contentPane.add(dateChooser);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 136, 46, 14);
		contentPane.add(lblDate);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(54, 62, 484, 2);
		contentPane.add(separator);
		
		JLabel lblAttendanceSheet = new JLabel("Attendance Sheet");
		lblAttendanceSheet.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttendanceSheet.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAttendanceSheet.setBounds(54, 11, 484, 36);
		contentPane.add(lblAttendanceSheet);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherMain frame = new TeacherMain();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(469, 407, 89, 23);
		contentPane.add(btnBack);
	}
}
