import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TeacherMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMain frame = new TeacherMain();
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
	public TeacherMain() {
		setTitle("Shariq");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 237, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddMarks = new JButton("Add Marks");
		btnAddMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 StudentMarks studentMarks = new StudentMarks();
				 studentMarks.setVisible(true);
				dispose();
				
			}
		});
		btnAddMarks.setBounds(10, 88, 126, 23);
		contentPane.add(btnAddMarks);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(144, 246, 67, 23);
		contentPane.add(btnExit);
		
		JButton btnCoursePlan = new JButton("Course Plan");
		btnCoursePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoursePlan frame = new CoursePlan();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCoursePlan.setBounds(10, 122, 126, 23);
		contentPane.add(btnCoursePlan);
		
		JButton btnNewButton = new JButton("Attendance");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attendance frame = new Attendance();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 156, 126, 23);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 55, 197, 2);
		contentPane.add(separator);
		
		JLabel lblTeacherPortal = new JLabel("Teacher Portal");
		lblTeacherPortal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherPortal.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTeacherPortal.setBounds(10, 11, 197, 33);
		contentPane.add(lblTeacherPortal);
	}
}
