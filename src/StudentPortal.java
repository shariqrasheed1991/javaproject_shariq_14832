import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentPortal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPortal frame = new StudentPortal();
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
	public StudentPortal() {
		setTitle("Shariq");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMain studentMain = new StudentMain();
				studentMain.setVisible(true);
				dispose();
				
			}
		});
		btnAddCourse.setBounds(20, 40, 118, 23);
		contentPane.add(btnAddCourse);
		
		JButton btnViewTranscript = new JButton("Transcript");
		btnViewTranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transcript frame = new Transcript();
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewTranscript.setBounds(20, 77, 118, 23);
		contentPane.add(btnViewTranscript);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
			
		});
		btnExit.setBounds(210, 175, 74, 23);
		contentPane.add(btnExit);
		
		JButton btnNewButton = new JButton("Course PLan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DisplayCoursePlan frame = new DisplayCoursePlan();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(20, 111, 118, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Attendance");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayAttendance frame = new DisplayAttendance();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(170, 40, 114, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Course Plan");
		btnNewButton_2.setBounds(170, 77, 118, 23);
		contentPane.add(btnNewButton_2);
	}
}
