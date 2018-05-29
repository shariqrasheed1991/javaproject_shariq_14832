import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FacultyMain extends JFrame {

	private JPanel contentPane;
	private JFrame frame1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyMain frame = new FacultyMain();
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
	public FacultyMain() {
		setTitle("Shariq");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 274, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent addStudent = new AddStudent();
				addStudent.setVisible(true);
				dispose();
			}
		});
		btnAddStudent.setBounds(60, 53, 145, 48);
		contentPane.add(btnAddStudent);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourse addCourse = new AddCourse();
				addCourse.setVisible(true);
				dispose();
				
			}
		});
		btnAddCourse.setBounds(60, 123, 145, 48);
		contentPane.add(btnAddCourse);
		
		JButton btnLogOut = new JButton("Exit");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogOut.setBounds(146, 226, 89, 23);
		contentPane.add(btnLogOut);
	}
}
