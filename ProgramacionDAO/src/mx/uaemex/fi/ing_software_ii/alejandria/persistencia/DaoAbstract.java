package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoAbstract {
	
	public static Connection getConexion() {
		String url = "jdbc:derby://localhost:1527/dao";
		
		try {
			Connection connect = DriverManager.getConnection(url);
			return connect;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
}
