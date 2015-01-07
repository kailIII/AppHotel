package py.edu.unican.facitec.sesion;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.entidades.Servicio;

import com.progress.bd.ControladorBD;

public class SesionHabitacion {
	//recibe el objeto cargado
	public static void guardarHabitacion (Habitacion habita) throws Exception {//recive el objeto cargado mediante (Habitacion habita)
		
		//si es numerico es "++"
		//si es caracter, String es '"++"'
		String sql = "insert into habita (hab_codigo,hab_descri,hab_mondia,hab_obse,hab_activo) values ("+habita.getCodigo()+" ,"
				+ " '"+habita.getDescripHabitacion()+"' , "+habita.getMontoDia()+"  "
						+ ",'"+habita.getObservacion()+"',"
								+ " "+habita.getHabActivo()+" )";
		

ControladorBD.addSql(sql);	//verifica si el sql esta escrito correctamente
ControladorBD.execute();// lo mandamos ejecutar en la base de datos

}

	public  static int recupetarUltimoCodigo() throws Exception {
		
		int id = ControladorBD.maximoValorCampo("habita", "hab_codigo"); 
	return id;  

	}

public static void borrarHabitacion (int codigo) { // Borrar Entidad de Prestamo
String sql = null;
sql = "DELETE FROM habita WHERE hab_codigo = "+codigo+"";


try {
ControladorBD.addSql(sql);
ControladorBD.execute();
} catch (Exception e) {
	JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
e.printStackTrace();
}
}		
	public static Habitacion consultarHabitacionPorCodigo(int codigo) throws Exception{
		String sql = "SELECT hab_descri,hab_mondia,hab_obse,hab_activo FROM habita "
				+ "WHERE hab_codigo ="+codigo+" ";
		
		//verifica si el sql está escrito correctamente
				ControladorBD.addSql(sql);
				ControladorBD.execute();//lo mandamos ejecutar en la base de datos
				
				Habitacion habitacion = null;//declarando e inicializando un objeto de tipo lib
				
				//vamos a verificar si la consulata tuvo exito

				if (ControladorBD.finRecordSet()) {
					habitacion = new Habitacion();//instanciamos un nuevo libro__________lib es un objeto para ser utilizado sus atributos 
					habitacion.setDescripHabitacion(ControladorBD.getString("hab_descri"));
					habitacion.setMontoDia(ControladorBD.getInt("hab_mondia"));
					habitacion.setObservacion(ControladorBD.getString("hab_obse"));
					habitacion.setHabActivo(ControladorBD.getInt("hab_activo"));
					
				}
				return habitacion;//retornamos el objeto libro cargando si la consulta tubo exito
				//sino retornamos null....
				
	}
	public static void modificarHabitacion(Habitacion hab) throws Exception {
		String sql = "UPDATE habita SET "
		+ "hab_descri= '" +hab.getDescripHabitacion()+"',"
		+ "hab_mondia= "+hab.getMontoDia()+" ,"
		+ "hab_obse= '"+hab.getObservacion()+"',"
				+ " hab_activo="+hab.getHabActivo()+ " "
				+ "WHERE "
		+ "hab_codigo="+hab.getCodigo()+" ";
		
		ControladorBD.addSql(sql);
		ControladorBD.execute();
	

	}
	
	public static ArrayList<Habitacion> consultarTodosHabitacion(Habitacion hab) { // Metodo para recuperar Cliente
		ArrayList<Habitacion> listHabitacion = new ArrayList<Habitacion>();
		String sql = null;
		
		try {
			sql = "SELECT hab_codigo,hab_codigo,hab_descri,hab_mondia,hab_obse FROM habita ORDER BY ser_codigo desc ";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			Habitacion habitacion;
			
			
			while (ControladorBD.finRecordSet()) {
		
				hab = new Habitacion();
				hab.setCodigo(ControladorBD.getInt("hab_codigo"));
				hab.setDescripHabitacion(ControladorBD.getString("hab_descri"));
				hab.setMontoDia(ControladorBD.getInt("hab_monto"));
				hab.setObservacion(ControladorBD.getString("hab_obse"));
				
				listHabitacion.add(hab);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listHabitacion;

	}
	public static ArrayList<Habitacion> consultarTodosPorFiltro(String filtro) throws Exception{
		
		
		String sql="select hab_codigo,hab_descri,hab_mondia,hab_obse from habita"
				+ " where hab_descri like '%"+ filtro +"%'";
		
		ControladorBD.addSql(sql);
		ControladorBD.execute();

		ArrayList<Habitacion> lista = new ArrayList<Habitacion>();
		Habitacion hab =null;//declaracionde varia refen
		//estamos apuntando una  a nulo
		
		while (ControladorBD.finRecordSet()) {
			hab = new Habitacion();//
			hab.setCodigo(ControladorBD.getInt("hab_codigo"));
			hab.setDescripHabitacion(ControladorBD.getString("hab_descri"));
			hab.setMontoDia(ControladorBD.getInt("hab_mondia"));
			
			lista.add(hab);//en cada interacion va agregar registro de la base de datos
			
		}
	return lista;

	}
	
public static ArrayList<Habitacion> consultarTodosPorActivo(int filtro) throws Exception{
		
		
		String sql="select hab_codigo,hab_descri,hab_mondia,hab_obse hab_activofrom habita"
				+ " where hab_activo like "+ filtro +" ";
		
		ControladorBD.addSql(sql);
		ControladorBD.execute();
		
		ArrayList<Habitacion> lista = new ArrayList<Habitacion>();
		Habitacion hab =null;//declaracionde varia refen
		//estamos apuntando una  a nulo
		
		while (ControladorBD.finRecordSet()) {
			hab = new Habitacion();//
			hab.setCodigo(ControladorBD.getInt("hab_codigo"));
			hab.setDescripHabitacion(ControladorBD.getString("hab_descri"));
			hab.setMontoDia(ControladorBD.getInt("hab_mondia"));
			
			lista.add(hab);//en cada interacion va agregar registro de la base de datos
			
		}
	return lista;

	}
	

public static ArrayList<Habitacion> listado(int codDes, int codHas, String descriDes, String descriHast, int i) throws Exception{
	
	String sql="select hab_codigo,hab_descri,hab_mondia,hab_obse from habita "
			+ "where hab_codigo between "+codDes+" and "+codHas+" "
					+ " and hab_descri between '"+descriDes+"' and '"+descriHast+"'order by "+i;
	

	

	ControladorBD.addSql(sql);
	ControladorBD.execute();
	
	
	ArrayList<Habitacion> lista = new ArrayList<Habitacion>();
	
	Habitacion hab =null;//declaracion de variable refen
	//estamos apuntando una  a nulo
	
	while (ControladorBD.finRecordSet()) {
		hab = new Habitacion();//
		hab.setCodigo(ControladorBD.getInt("hab_codigo"));
		hab.setDescripHabitacion(ControladorBD.getString("hab_descri"));
		hab.setMontoDia(ControladorBD.getInt("hab_mondia"));
		hab.setObservacion(ControladorBD.getString("hab_obse"));
		//hab.getAutor().setCodigo(ControladorBD.getInt("autor"));
		
		
		
		lista.add(hab);//en cada interacion va agregar registro de la base de datos
		
	}
return lista;

}
	

}
