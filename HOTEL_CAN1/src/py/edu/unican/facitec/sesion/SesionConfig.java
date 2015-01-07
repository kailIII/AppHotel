package py.edu.unican.facitec.sesion;

import com.progress.bd.ControladorBD;

import py.edu.unican.facitec.entidades.Config;

public class SesionConfig {

	public static void guardar(Config c) throws Exception {
		String sql = "insert into config(cfg_codigo, cfg_orga, cfg_telefono)"
				+ " values("+c.getCodigo()+", '"+c.getNombreHotel()+"','"+c.getTelHotel()+"')";
		ControladorBD.addSql(sql);
		ControladorBD.execute();
	}
	
	public static void delete() throws Exception {
		String sql = "delete from config";
		ControladorBD.addSql(sql);
		ControladorBD.execute();
	}

	public static Config recuperar() throws Exception {
		Config c = new Config();
		String sql = "select * from config where cfg_codigo = 1 ";
		ControladorBD.addSql(sql);
		ControladorBD.execute();
		if (ControladorBD.finRecordSet()) {
			c.setNombreHotel(ControladorBD.getString("cfg_orga"));
			c.setTelHotel(ControladorBD.getString("cfg_telefono"));
		}
		return c;
	}

}
