package aplicativo.lotofacil.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseLoteriaMySQL {

	public static Connection getConnection(){
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/lotofacil_database","root","");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
