package py.edu.unican.facitec.sesion;

//import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;





import py.edu.unican.facitec.entidades.Config;
import py.edu.unican.facitec.menu.ConexionBD;

public class SesionConfigu {
	
	private static Statement stm = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static Connection conexion;
	
	public static Connection conectar() {
		try {
			conexion = ConexionBD.getConection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}
	
	public static void guardarConfiguracion(Config config) throws Exception{
		conectar();
		String sql= "INSERT INTO configuracion(cfg_orga, cfg_telefono) VALUES(?,?)";
		ps = conexion.prepareStatement(sql);
		ps.setString(1, config.getNombreHotel());
		ps.setString(2, config.getTelHotel());
		ps.executeUpdate();
		ps.close();
		conexion.close();
		conectar().close();
		
	}
	
	public static void eliminar(int codigo) throws Exception{
		conectar();
		String sql= "DELETE FROM configuracion WHERE cfg_codigo=?";
		ps= conexion.prepareStatement(sql);
		ps.setInt(1, codigo);
		ps.executeUpdate();
		ps.close();
		conexion.close();
		
	}
	public static void modificar(Config config) throws Exception{
		conectar();
		String sql = "UPDATE configuracion SET cfg_orga=?, cfg_telefono=? WHERE cfg_codigo=?";
		ps= conexion.prepareStatement(sql);
		ps.setString(1, config.getNombreHotel());
		ps.setString(2, config.getTelHotel());
		ps.setInt(3, 1);
		ps.executeUpdate();
		ps.close();
		conexion.close();
		conectar().close();
	}
	
	public static ArrayList<Config> configuraciones() throws SQLException{
		conectar();
		String sql= "SELECT cfg_orga, cfg_telefono FROM configuracion";
		stm = conexion.createStatement();
		rs = stm.executeQuery(sql);
		ArrayList<Config> listaConfiguraciones = new ArrayList<Config>();
		Config config = null;
		while(rs.next()){
			config = new Config();
			config.setNombreHotel(rs.getString("cfg_orga"));
			config.setTelHotel(rs.getString("cfg_telefono"));
			listaConfiguraciones.add(config);
		}
		return listaConfiguraciones;
	}
	
	public static Config consultaConfiguraciones(int codigo) throws Exception{
		conectar();
		String sql= "SELECT cfg_orga, cfg_telefono FROM configuracion WHERE cfg_codigo = ?";	
		ps = conexion.prepareStatement(sql);
		ps.setInt(1, codigo);
		rs = ps.executeQuery();
		
		Config config = null;
		if(rs.next()){
			config = new Config();
			config.setNombreHotel(rs.getString("cfg_orga"));
			config.setNombreHotel(rs.getString("cfg_telefono"));
		}
		return config;
	}
	
	
}
