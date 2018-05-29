import java.sql.*;
import javax.swing.*;
public class SQLiteConnection {
	Connection conn = null;
	public static Connection dbConnector () {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\dorset college\\java secend year\\second assingemnt web application\\web application\\Shariq_14832\\SchoolManagment.sqlite");
			return conn;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
