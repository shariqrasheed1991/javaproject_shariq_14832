import java.sql.*;
import javax.swing.*;
public class SQLiteConnection {
	Connection conn = null;
	public static Connection dbConnector () {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\hasna\\eclipse-workspace\\Shariq\\SchoolManagment.sqlite");
			return conn;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
