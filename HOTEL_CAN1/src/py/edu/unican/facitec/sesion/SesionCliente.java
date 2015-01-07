package py.edu.unican.facitec.sesion;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import py.edu.unican.facitec.entidades.Cliente;

import com.progress.bd.ControladorBD;


public class SesionCliente {
		
		//recibe el objeto cargado
			public static void guardarCliente (Cliente cliente) throws Exception {//recive el objeto cargado mediante (Estadia estadia)
				
				//si es numerico es "++"
				//si es caracter, String es '"++"'
				String sql = "insert into cliente ( cli_codigo,cli_nombre,cli_cedula,cli_ruc,"
					+ "cli_direccion,cli_telefono) values ("+cliente.getCodigo()+" ,"
								+ " '"+cliente.getNombre()+"' , "+cliente.getCedula()+" "
										+ ",'"+cliente.getRuc()+"' "
												+ ", '"+cliente.getDireccion()+"' "
														+ ", '"+cliente.getTelefono()+"')";
				
				ControladorBD.addSql(sql);	//verifica si el sql esta escrito correctamente
				ControladorBD.execute();// lo mandamos ejecutar en la base de datos
				
			}
	
			public  static int recupetarUltimoCodigo() throws Exception {
				
				int id = ControladorBD.maximoValorCampo("cliente", "cli_codigo"); 
			return id;  

			}

public static void borrarCliente (int codigo) { // Borrar Entidad de Prestamo
	String sql = null;
	sql = "DELETE FROM cliente WHERE cli_codigo = "+codigo+"";
	
	
	try {
		ControladorBD.addSql(sql);
		ControladorBD.execute();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
		e.printStackTrace();
	}
}			
			public static Cliente consultarClintePorCodigo(int codigo) throws Exception{
				String sql = "SELECT cli_nombre,cli_cedula,cli_ruc,"
					+ "cli_direccion,cli_telefono FROM cliente "
						+ "WHERE cli_codigo="+codigo+" ";
				
						ControladorBD.addSql(sql);
						ControladorBD.execute();//lo mandamos ejecutar en la base de datos
						
						Cliente cliente = null;//declarando e inicializando un objeto de tipo lib

						if (ControladorBD.finRecordSet()) {
							cliente = new Cliente();//instanciamos un nuevo libro__________lib es un objeto para ser utilizado sus atributos 
							cliente.setNombre(ControladorBD.getString("cli_nombre"));
							cliente.setCedula(ControladorBD.getInt("cli_cedula"));
							cliente.setRuc(ControladorBD.getString("cli_ruc"));
							cliente.setDireccion(ControladorBD.getString("cli_direccion"));
							cliente.setTelefono(ControladorBD.getString("cli_telefono"));
							
						}
						return cliente;
						
			}
			public static void modificarCliente(Cliente cliente) throws Exception {
				String sql = "UPDATE cliente SET "
				+ "cli_nombre= '" +cliente.getNombre()+"',"
					+ "cli_cedula= "+cliente.getCedula()+" ,"
						+ "cli_ruc= '"+cliente.getRuc()+"' ,"
							+ "cli_direccion= '"+cliente.getDireccion()+"' ,"
								+ "cli_telefono= '"+cliente.getTelefono()+"' "
									+ " WHERE  cli_codigo =" +cliente.getCodigo()+" ";
				
				ControladorBD.addSql(sql);
				ControladorBD.execute();
			

			}

			public static ArrayList<Cliente> consultarTodosPorNombre(String filtro) throws Exception{
				
				
				String sql="select * from cliente"
						+ " where cli_nombre like '%"+ filtro +"%'  order by cli_codigo asc";
				
				ControladorBD.addSql(sql);
				ControladorBD.execute();
				ArrayList<Cliente> lista = new ArrayList<Cliente>();
				Cliente cli =null;
				while (ControladorBD.finRecordSet()) {
					cli = new Cliente();//
					cli.setCodigo(ControladorBD.getInt("cli_codigo"));
					cli.setNombre(ControladorBD.getString("cli_nombre"));
					cli.setCedula(ControladorBD.getInt("cli_cedula"));
					
					
					lista.add(cli);
				}
			return lista;

			}
			
public static ArrayList<Cliente> consultarTodosPorCedula(String filtro) throws Exception{
				
				
				String sql="select cli_codigo, cli_nombre, cli_cedula , cli_ruc , cli_direccion,"
						+ " cli_telefono from cliente"
						+ " where cli_cedula like '"+ filtro +"%' order by cli_codigo  ";
				
				ControladorBD.addSql(sql);
				ControladorBD.execute();
				ArrayList<Cliente> lista = new ArrayList<Cliente>();
				Cliente cli =null;
				
				while (ControladorBD.finRecordSet()) {
					cli = new Cliente();//
					cli.setCodigo(ControladorBD.getInt("cli_codigo"));
					cli.setNombre(ControladorBD.getString("cli_nombre"));
					cli.setCedula(ControladorBD.getInt("cli_cedula"));
					lista.add(cli);
					
				}
			return lista;

			}
			
public static ArrayList<Cliente> consultarTodosPorNumeroTel(String filtro) throws Exception{
	
	
	String sql="select cli_codigo, cli_nombre, cli_cedula , cli_ruc , cli_direccion,"
			+ " cli_telefono from cliente "
			+ " where cli_telefono like '"+ filtro +"%' order by cli_codigo ";
	
	ControladorBD.addSql(sql);
	ControladorBD.execute();
	ArrayList<Cliente> lista = new ArrayList<Cliente>();
	Cliente cli =null;//declaracionde varia refen
	while (ControladorBD.finRecordSet()) {
		cli = new Cliente();//
		cli.setCodigo(ControladorBD.getInt("cli_codigo"));
		cli.setNombre(ControladorBD.getString("cli_nombre"));
		cli.setCedula(ControladorBD.getInt("cli_cedula"));
		cli.setTelefono(ControladorBD.getString("cli_telefono"));		
		lista.add(cli);
		
	}
return lista;

}
	

public static ArrayList<Cliente> consultarListado(int codDes, int codHas, String nombDes, String nombHas, int i) throws Exception{

	String sql="select * from cliente "
			+ "where cli_codigo between "+codDes+" and "+codHas+" "
					+ "and cli_nombre between '"+nombDes+"' and '"+nombHas+"' order by "+i;
	
	ControladorBD.addSql(sql);
	ControladorBD.execute();
	ArrayList<Cliente> lista = new ArrayList<Cliente>();
	Cliente cli =null;
	while (ControladorBD.finRecordSet()){
		cli = new Cliente();//
		cli.setCodigo(ControladorBD.getInt("cli_codigo"));
		cli.setNombre(ControladorBD.getString("cli_nombre"));
		cli.setCedula(ControladorBD.getInt("cli_cedula"));
		cli.setRuc(ControladorBD.getString("cli_ruc"));
		cli.setDireccion(ControladorBD.getString("cli_direccion"));
		cli.setTelefono(ControladorBD.getString("cli_telefono"));
		lista.add(cli);//en cada interacion va agregar registro de la base de datos
		
	    }
		return lista;

	}	

}
