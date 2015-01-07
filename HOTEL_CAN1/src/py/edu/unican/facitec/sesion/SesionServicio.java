package py.edu.unican.facitec.sesion;

import java.util.ArrayList;




import javax.swing.JOptionPane;

import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.entidades.Servicio;

import com.progress.bd.ControladorBD;

public class SesionServicio {
	

	//recibe el objeto cargado
		public static void guardarServicio (Servicio ser) throws Exception {//recive el objeto cargado mediante (Estadia estadia)
			
			//si es numerico es "++"
			//si es caracter, String es '"++"'
			String sql = "insert into servicio ( ser_codigo,ser_descri,ser_monto,ser_obser) values ("+ser.getCodigo()+" ,"
							+ " '"+ser.getDescripServicio()+"' , "+ser.getMontoServicio()+"  "
									+ ",'"+ser.getObservacion()+"' )";
	
			ControladorBD.addSql(sql);	//verifica si el sql esta escrito correctamente
			ControladorBD.execute();// lo mandamos ejecutar en la base de datos
			
		}

	public  static int recupetarUltimoCodigo() throws Exception {
			
			int id = ControladorBD.maximoValorCampo("servicio", "ser_codigo"); 
		return id;  

		}

public static void borrarServicio (int codigo) { // Borrar Entidad de Prestamo
String sql = null;
sql = "DELETE FROM servicio WHERE ser_codigo = "+codigo+"";


try {
	ControladorBD.addSql(sql);
	ControladorBD.execute();
} catch (Exception e) {
	e.printStackTrace();
	JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
}
}
		
		public static Servicio consultarServicioPorCodigo(int codigo) throws Exception{
			String sql = "SELECT ser_descri,ser_monto,ser_obser"
				+ " FROM servicio "
					+ "WHERE ser_codigo="+codigo+" ";
			
					ControladorBD.addSql(sql);
					ControladorBD.execute();//lo mandamos ejecutar en la base de datos
					
					Servicio ser = null;

					if (ControladorBD.finRecordSet()) {
						ser = new Servicio();//instanciamos un nuevo libro__________lib es un objeto para ser utilizado sus atributos 
						ser.setDescripServicio(ControladorBD.getString("ser_descri"));
						ser.setMontoServicio(ControladorBD.getInt("ser_monto"));
						ser.setObservacion(ControladorBD.getString("ser_obser"));
						
					}
					return ser;//retornamos el objeto libro cargando si la consulta tubo exito
					//sino retornamos null....
					
		}
		public static void modificarServicio(Servicio ser) throws Exception {
			String sql = "UPDATE servicio SET "
			+ "ser_descri= '" +ser.getDescripServicio()+"',"
			+ "ser_monto= "+ser.getMontoServicio()+" ,"
			+ "ser_obser= '"+ser.getObservacion()+"' "
					+ "WHERE "
			+ "ser_codigo="+ser.getCodigo()+" ";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
		

		}
		
		public static ArrayList<Servicio> consultarTodosServicio(Servicio ser) { // Metodo para recuperar Cliente
			ArrayList<Servicio> listServicio = new ArrayList<Servicio>();
			String sql = null;
			
			try {
				sql = "SELECT ser_codigo,ser_descri,ser_monto,ser_obser FROM cliente ORDER BY cli_codigo desc ";
				
				ControladorBD.addSql(sql);
				ControladorBD.execute();
				
				Servicio servicio;
				
				
				while (ControladorBD.finRecordSet()) {
			
					ser = new Servicio();
					ser.setCodigo(ControladorBD.getInt("ser_codigo"));
					ser.setDescripServicio(ControladorBD.getString("ser_descri"));
					ser.setMontoServicio(ControladorBD.getInt("ser_monto"));
					ser.setObservacion(ControladorBD.getString("ser_obser"));
					
					listServicio.add(ser);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listServicio;

		}
		
		public static ArrayList<Servicio> consultarTodosPorFiltro(String filtro) throws Exception{
			
			
			String sql="select ser_codigo ,ser_descri,ser_monto,ser_obser from servicio"
					+ " where ser_descri like '%"+ filtro +"%'";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			
			ArrayList<Servicio> lista = new ArrayList<Servicio>();
			Servicio ser =null;//declaracionde varia refen
			//estamos apuntando una  a nulo
			
			while (ControladorBD.finRecordSet()) {
				ser = new Servicio();//
				ser.setCodigo(ControladorBD.getInt("ser_codigo"));
				ser.setDescripServicio(ControladorBD.getString("ser_descri"));
				ser.setMontoServicio(ControladorBD.getInt("ser_monto"));
				
				lista.add(ser);//en cada interacion va agregar registro de la base de datos
				
			}
		return lista;

		}
		public static ArrayList<Servicio> Listado(int codDes, int codHas, String descriDes, String descriHas, int i) throws Exception{
				
				String sql="select	ser_codigo, ser_descri,ser_monto from servicio "
						+ "where ser_codigo Between "+codDes+" and "+codHas+" "
								+ "and ser_descri Between '"+descriDes+"' and '"+descriHas+"' order by  "+i;
				ControladorBD.addSql(sql);
				ControladorBD.execute();
				
				
				ArrayList<Servicio> lista = new ArrayList<Servicio>();
				Servicio ser =null;//declaracionde varia refen
				//estamos apuntando una  a nulo
				
				while (ControladorBD.finRecordSet()) {
					ser = new Servicio();//
					ser.setCodigo(ControladorBD.getInt("ser_codigo"));
					ser.setDescripServicio(ControladorBD.getString("ser_descri"));
					ser.setMontoServicio(ControladorBD.getInt("ser_monto"));
					
					
					lista.add(ser);//en cada interacion va agregar registro de la base de datos
					
				}
			return lista;

			}

}
