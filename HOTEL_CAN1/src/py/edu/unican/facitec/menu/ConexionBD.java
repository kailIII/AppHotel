package py.edu.unican.facitec.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {

	public static Connection conexion = null;
	private static PreparedStatement ps = null;
	
	public static Connection getConection() throws SQLException, ClassNotFoundException{
		String host = "jdbc:postgresql://localhost/Hotel_Can";
		String user = "postgres";
		String password = "memer";
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(host, user, password);
	}
	
	public static void inicializar() throws SQLException, ClassNotFoundException{
		conexion=getConection();
		String sql= "TRUNCATE TABLE cliente, cobranza, configuracion, detalle, deuda, estadia, habita, servicio RESTART IDENTITY ";
		ps = conexion.prepareStatement(sql);
		ps.executeUpdate();
	}
}
