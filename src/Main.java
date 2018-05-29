import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
public class Main {

	private JFrame frmShariq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmShariq.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField textFieldAllId;
	private JPasswordField passwordField_3;
	
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		conn = SQLiteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShariq = new JFrame();
		frmShariq.setTitle("Shariq");
		frmShariq.setBounds(100, 100, 254, 282);
		frmShariq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShariq.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Teacher", "Faculty", "Student"}));
		comboBox.setBounds(112, 72, 104, 20);
		frmShariq.getContentPane().add(comboBox);
		
		textFieldAllId = new JTextField();
		textFieldAllId.setBounds(130, 103, 86, 20);
		frmShariq.getContentPane().add(textFieldAllId);
		textFieldAllId.setColumns(10);
		
		JButton btnAllLogin = new JButton("Login");
		btnAllLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String t = "Teacher";
				String s = "Student";
				String f = "Faculty";
				
				String selection = (String)comboBox.getSelectedItem();
				
				try {
					if (selection.matches(t)) {
						
						String Id = textFieldAllId.getText();
						char [] P = passwordField_3.getPassword();
										
						String FacultyP = null;
						if (Id.equalsIgnoreCase(t) && FacultyP.valueOf(P).equalsIgnoreCase(t))  {
							TeacherMain teacherMain= new TeacherMain();
							 teacherMain.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Teacher Id OR Password is not Correct try Again");
						}
						
				}
					
					else if (selection.matches(s)) {
						try {
							
							String temp = textFieldAllId.getText();
							
							String query = "select * from Student where SID=? and password=?";
							PreparedStatement pst = conn.prepareStatement(query);
							pst.setString(1,textFieldAllId.getText());
							pst.setString(2,passwordField_3.getText());
							
							
							
							ResultSet rs = pst.executeQuery();
							
							int count =0;
							while(rs.next()) {
								count= count+1;
							}
							
							if(count ==1) {
								frmShariq.dispose();
								StudentPortal studentPortal= new StudentPortal();
								studentPortal.setVisible(true);
							}
							else {
								JOptionPane.showMessageDialog(null, "Student Id OR Password is not Correct try Again");
							}
								rs.close();
								pst.close();
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex);
						}
						
					}
						else if (selection.matches(f)) {
							String temp = "admin";
							String Id = textFieldAllId.getText();
							char [] P = passwordField_3.getPassword();
											
							String FacultyP = null;
							if (Id.equalsIgnoreCase(temp) && FacultyP.valueOf(P).equalsIgnoreCase(temp))  {
								FacultyMain facultyMain = new FacultyMain();
								facultyMain.setVisible(true);
							}else {
								JOptionPane.showMessageDialog(null, "Faculty Id OR Password is not Correct try Again");
							}
					}
				}catch(Exception ex) {
					
				}
				
				
			}
		});
		btnAllLogin.setBounds(130, 190, 89, 23);
		frmShariq.getContentPane().add(btnAllLogin);
		
		passwordField_3 = new JPasswordField();
		passwordField_3.setBounds(130, 148, 86, 20);
		frmShariq.getContentPane().add(passwordField_3);
		
		JLabel lblId_1 = new JLabel("ID");
		lblId_1.setBounds(22, 106, 46, 14);
		frmShariq.getContentPane().add(lblId_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 151, 73, 14);
		frmShariq.getContentPane().add(lblPassword);
	}
}
