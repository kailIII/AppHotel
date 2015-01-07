package py.edu.unican.facitec.sesion;

import com.progress.bd.ControladorBD;



public class SesionInicializar {
	
	
String[] Tablas = {"cobranza","deuda","detalle","servicio","estadia","cliente","habita","config"} ;
//
	
	public void truncate() {
		for (int i = 0; i < Tablas.length; i++) {
			String sql = "DELETE FROM "+Tablas[i]+" WHERE '1' ";		
		try {
			ControladorBD.addSql(sql);
			ControladorBD.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		}


		}

		
	}
}
		