package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
		private static final String URL="insert bbdd address here";
		public static Connection conexion = new DBConnection().getConexion();
		
	public DBConnection() {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection(URL,"usuario","s2dam18");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public Connection getConexion() {
			return conexion;
		}
}
