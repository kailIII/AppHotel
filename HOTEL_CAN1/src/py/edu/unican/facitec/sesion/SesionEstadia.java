package py.edu.unican.facitec.sesion;

import py.edu.unican.facitec.entidades.Detalle;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.entidades.Detalle;
import py.edu.unican.facitec.entidades.Deuda;
import py.edu.unican.facitec.entidades.Estadia;

import com.progress.bd.ControladorBD;

public class SesionEstadia {
	
	//recibe el objeto cargado
		public static void guardarEstadia (Estadia estadia) throws Exception{ //recive el objeto cargado mediante (Estadia estadia)
			
			//si es numerico es "++"
			//si es caracter, String es '"++"'
			String sql = "insert into estadia ( est_numero,est_fecha,est_codcli,est_codhab,"
				    + "est_monto,est_obse,est_activo) values ( "
				        + " "+estadia.getCodigo()+" , "
							+ " '"+estadia.getFechaEntrada()+"',"
								+ ""+estadia.getCodCliente().getCodigo()+" "
									+ ","+estadia.getCodHabitacion().getCodigo()+" "
											+ " , "+estadia.getMontoTotal()+" "
												+ " , '"+estadia.getObservacion()+"'"
														+ ", '"+estadia.getActivoInActivo()+"') ";
			
			//verifica si el sql esta escrito correctamente
			ControladorBD.addSql(sql);
			ControladorBD.execute();  // lo mandamos ejecutar en la base de datos
			
		}
public static void guardarDetalle (Detalle detalle) throws Exception{  //recive el objeto cargado mediante (Estadia estadia)
			
			//si es numerico es "++"
			//si es caracter, String es '"++"'
			String sql = "insert into detalle ( det_numero,det_nroest,det_codser,det_monto) values ( "
				        + " "+detalle.getCodigo()+" , "
							+ ""+detalle.getNumeroEstadia().getCodigo()+" "
								+ ","+detalle.getCodServicio().getCodigo()+" "
									+ " , "+detalle.getMonto()+") ";
			
			//verifica si el sql esta escrito correctamente
			ControladorBD.addSql(sql);
			ControladorBD.execute();  // lo mandamos ejecutar en la base de datos
			
		}
public static void generarDeuda (Deuda deuda) throws Exception{ //recive el objeto cargado mediante (Estadia estadia)
	
	String sql = "insert into deuda ( deu_numero,deu_nroest,deu_codcli,deu_monto,deu_pagado) values ( "
		        + " "+deuda.getCodigo()+" , "
					+ " "+deuda.getNroEstadia().getCodigo()+","
						+ " "+deuda.getCodCliente().getCodigo()+", "
							+ " "+deuda.getMontoDeuda()+ " , "
								+ " "+deuda.getMontoPagado()+ ") ";
	
	//verifica si el sql esta escrito correctamente
	ControladorBD.addSql(sql);
	ControladorBD.execute();  // lo mandamos ejecutar en la base de datos
	
}
	
		public  static int recupetarUltimoCodigo() throws Exception {
			
			int id = ControladorBD.maximoValorCampo("estadia", "est_numero");  
		return id;

		}
		
        public  static int recupetarUltimoCodigoDetalle() throws Exception {
			
			int id = ControladorBD.maximoValorCampo("detalle", "det_numero"); 
		return id;

		}
        
 public  static int recupetarUltimoCodigoDeuda() throws Exception {
			
			int id = ControladorBD.maximoValorCampo("deuda", "deu_numero"); 
		return id;

		}
		public static void modificarEstadia(Estadia e) throws Exception  {
			  	String sql="UPDATE estadia SET  "
				    + " est_fecha='"+e.getFechaEntrada()+"', "
						+ " est_codcli='"+e.getCodCliente().getCodigo()+"', "
							+ "est_codhab='"+e.getCodHabitacion().getCodigo()+"', "
								+ "est_fecsal='"+e.getFechaSalida()+"', "
									+ "est_monto="+e.getMontoTotal()+",  "
										+ "est_obse='"+e.getObservacion()+"', "
											+ "est_descu="+e.getDescuento()+","
													+ " est_activo='"+e.getActivoInActivo()+"' "
												+ " WHERE est_numero ="+e.getCodigo()+" ";
			

			
				ControladorBD.addSql(sql);
				ControladorBD.execute();

	}
		
		
		public static Estadia consultarEstadiaPorNro(int codigo) throws Exception {
			Estadia est = null;
			String sql="SELECT * FROM 	estadia WHERE est_numero="+codigo+"";
			
				try {
					ControladorBD.addSql(sql);
					ControladorBD.execute();
					
					if (ControladorBD.finRecordSet()) {
						est = new Estadia();
						est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
						est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
						est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
						est.setFechaSalida(ControladorBD.getDate("est_fecsal"));		
						est.setMontoTotal(ControladorBD.getInt("est_monto"));
						est.setObservacion(ControladorBD.getString("est_obse"));
						est.setDescuento(ControladorBD.getInt("est_descu"));
						est.setActivoInActivo(ControladorBD.getInt("est_activo"));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return est;

	}
		public static Estadia consultarPorNro(int codigo) throws Exception {
			Estadia est = null;
			String sql="SELECT * FROM 	vw_est_hab_cli WHERE est_numero="+codigo+"";

					ControladorBD.addSql(sql);
					ControladorBD.execute();
					
					if (ControladorBD.finRecordSet()) {
						est = new Estadia();
						est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
						est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
						est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
						est.getCodHabitacion().setMontoDia(ControladorBD.getInt("hab_mondia"));
						est.setFechaSalida(ControladorBD.getDate("est_fecsal"));		
						est.setMontoTotal(ControladorBD.getInt("est_monto"));
						est.setObservacion(ControladorBD.getString("est_obse"));
						//est.setActivoInActivo(ControladorBD.getString("est_activo"));
						
					}

				return est;
				

	}
		public static Detalle consultarDetallePorNro(int codigo) throws Exception {
			Detalle det = null;
			String sql="SELECT * FROM detalle WHERE det_numero="+codigo+"";
			
				try {
					ControladorBD.addSql(sql);
					ControladorBD.execute();
					
					if (ControladorBD.finRecordSet()) {
						det = new Detalle();
						det.setCodigo(ControladorBD.getInt("det_numero"));
						det.getCodServicio().setCodigo(ControladorBD.getInt("det_codser"));
						det.setMonto(ControladorBD.getInt("det_monto"));		
						det.getNumeroEstadia().setCodigo(ControladorBD.getInt("det_nroest"));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return det;
				

	}
		public static ArrayList<Estadia> consultarTodosPorFiltro(String filtro) throws Exception{
			
			String sql="select * from vw_est_hab_cli "
						+ " where cli_nombre like '%"+ filtro +"%' ";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			ArrayList<Estadia> lista = new ArrayList<Estadia>();
			Estadia est =null;//declaracionde varia refen
			  //estamos apuntando una  a nulo
			while (ControladorBD.finRecordSet()) {
				est = new Estadia();
				est.setCodigo(ControladorBD.getInt("est_numero"));
				est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
				est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
				est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
				est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
				est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
				est.setMontoTotal(ControladorBD.getInt("est_monto"));
				est.setObservacion(ControladorBD.getString("est_obse"));
				lista.add(est);//en cada interacion va agregar registro de la base de datos
				
			}
		return lista;

		}
		
        public static ArrayList<Estadia> consultarTodos() throws Exception{
			String sql="select * from vw_est_hab_cli order by est_numero ";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			ArrayList<Estadia> lista = new ArrayList<Estadia>();
			Estadia est =null;
			
			while (ControladorBD.finRecordSet()) {
				est = new Estadia();
				est.setCodigo(ControladorBD.getInt("est_numero"));
				est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
				est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
				est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
				est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
				est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
				est.setMontoTotal(ControladorBD.getInt("est_monto"));
				est.setObservacion(ControladorBD.getString("est_obse"));
				//est.setActivoInActivo(ControladorBD.getString(("est_activo")));
				
				lista.add(est);//en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}
        
        public static ArrayList<Estadia> consultarCerrado() throws Exception{
			String sql="select * from vw_est_hab_cli where est_activo = '0' order by est_numero";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			  //estamos creando una lista que solo puede contener datos de tipo cliente
			  //la expresion se llama generics apartir de JAVA 5 observacion
			ArrayList<Estadia> lista = new ArrayList<Estadia>();
			Estadia est =null;//declaracionde varia refen
			  //estamos apuntando una  a nulo
			while (ControladorBD.finRecordSet()) {
				est = new Estadia();
				est.setCodigo(ControladorBD.getInt("est_numero"));
				est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
				est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
				est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
				est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
				est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
				est.setMontoTotal(ControladorBD.getInt("est_monto"));
				est.setObservacion(ControladorBD.getString("est_obse"));
				//est.setActivoInActivo(ControladorBD.getString(("est_activo")));
				
				lista.add(est);//en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}
        
        public static ArrayList<Estadia> consultarAbierto() throws Exception{
			String sql="select * from vw_est_hab_cli where est_activo = '1' order by est_numero ";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			ArrayList<Estadia> lista = new ArrayList<Estadia>();
			Estadia est =null;//declaracionde varia refen
			  //estamos apuntando una  a nulo
			while (ControladorBD.finRecordSet()) {
				est = new Estadia();
				est.setCodigo(ControladorBD.getInt("est_numero"));
				est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
				est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
				est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
				est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
				est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
				est.setMontoTotal(ControladorBD.getInt("est_monto"));
				est.setObservacion(ControladorBD.getString("est_obse"));
				
				lista.add(est);//en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}

         public static ArrayList<Detalle> consultarTodosPorFiltroDetalle(String filtro) throws Exception{
        	 
        	 
        	 String sql="select * from vw_det_ser "
        	 		+ " where det_nroest like '"+ filtro +"' ";
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			  //estamos creando una lista que solo puede contener datos de tipo cliente
			  //la expresion se llama generics apartir de JAVA 5 observacion
			ArrayList<Detalle> list = new ArrayList<Detalle>();
			Detalle det =null;//declaracionde varia refen
			  //estamos apuntando una  a nulo
			
			
			while (ControladorBD.finRecordSet()) {
				
				det = new Detalle();
				det.setCodigo(ControladorBD.getInt("det_numero"));
				det.getCodServicio().setDescripServicio((ControladorBD.getString("ser_descri")));
				det.getNumeroEstadia().setCodigo(ControladorBD.getInt("det_nroest"));
				det.setMonto(ControladorBD.getInt("det_monto"));
				list.add(det);    //en cada interacion va agregar registro de la base de datos
				
			}
			
		return list;
			
		}
		
		public static void borrarEstadia (int codigo) { // Borrar Entidad de Prestamo
			String sql = null;
			sql = "DELETE FROM estadia WHERE  est_numero = "+codigo+"";
			
			
			try {
				ControladorBD.addSql(sql);
				ControladorBD.execute();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
				e.printStackTrace();
			}
		}
		public static void borrarDetalle (int codigo) { // Borrar Entidad de Prestamo
			String sql = null;
			sql = "DELETE FROM detalle WHERE  det_numero = "+codigo+"";
			
			
			try {
				ControladorBD.addSql(sql);
				ControladorBD.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		public static Estadia consultarHabitacion(int codhabitacion, boolean activo) throws Exception{
			String sql = "SELECT * FROM vw_est_hab_cli WHERE hab_codigo=? AND est_activo=?";
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			Estadia est = null;
			if(ControladorBD.finRecordSet()){
				est = new Estadia();
				est.setCodigo(ControladorBD.getInt("est_numero"));
				est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
				est.getCodCliente().setCodigo(ControladorBD.getInt("cli_codigo"));
				est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				est.getCodHabitacion().setCodigo(ControladorBD.getInt("hab_codigo"));
				est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
				est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
				est.setMontoTotal(ControladorBD.getInt("est_monto"));
				est.setDescuento(ControladorBD.getInt("est_descu"));
				est.setObservacion(ControladorBD.getString("est_obse"));
				est.setActivoInActivo(ControladorBD.getInt("est_activo"));
			}
			return est;
		}
		
		public static Estadia consultarHabitacion(int codhabitacion, int activo) throws Exception{
			String sql = "SELECT * FROM vw_est_hab_cli WHERE hab_codigo="+codhabitacion+" AND est_activo="+activo+"";
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			Estadia est = null;
			if(ControladorBD.finRecordSet()){
				est = new Estadia();
				est.setCodigo(ControladorBD.getInt("est_numero"));
				est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
				est.getCodCliente().setCodigo(ControladorBD.getInt("cli_codigo"));
				est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				est.getCodHabitacion().setCodigo(ControladorBD.getInt("hab_codigo"));
				est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
				est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
				est.setMontoTotal(ControladorBD.getInt("est_monto"));
				est.setObservacion(ControladorBD.getString("est_obse"));
				est.setActivoInActivo(ControladorBD.getInt("est_activo"));
			}
			return est;
		}
		  public static ArrayList<Estadia> consultarInformeTodos(int codDes, int codHas, String clienteDes, String clienteHas, String habitacionDes, String habitacionHas,int i) throws Exception{
			  String sql="select * from vw_est_cli_hab "
						+ "where est_numero between "+codDes+" and "+codHas+" "
								+ "and cli_nombre between '"+clienteDes+"' and '"+clienteHas+"' "
										+ " and hab_descri between '"+habitacionDes+"' and '"+habitacionHas+"' order by "+i ;
				ControladorBD.addSql(sql);
				ControladorBD.execute();

				ArrayList<Estadia> lista = new ArrayList<Estadia>();
				Estadia est =null;//declaracionde varia refen
				
				while (ControladorBD.finRecordSet()) {
					est = new Estadia();
					est.setCodigo(ControladorBD.getInt("est_numero"));
					est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
					est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
					est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
					est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
					est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
					est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
					est.setMontoTotal(ControladorBD.getInt("est_monto"));
					est.setDescuento(ControladorBD.getInt("est_descu"));
					est.setObservacion(ControladorBD.getString("est_obse"));
					//est.setActivoInActivo(ControladorBD.getString(("est_activo")));
					
					lista.add(est);//en cada interacion va agregar registro de la base de datos
					
				}
				
			return lista;
				
			}
		  
		  public static ArrayList<Estadia> consultarInformeCerrado(int codDes, int codHas, String clienteDes, String clienteHas, String habitacionDes, String habitacionHas,int i) throws Exception{
			  String sql="select * from vw_est_cli_hab "
						+ "where est_activo = '0' and est_numero between "+codDes+" and "+codHas+" "
								+ "and cli_nombre between '"+clienteDes+"' and '"+clienteHas+"' "
										+ " and hab_descri between '"+habitacionDes+"' and '"+habitacionHas+"' order by "+i ;
				ControladorBD.addSql(sql);
				ControladorBD.execute();
				
				ArrayList<Estadia> lista = new ArrayList<Estadia>();
				Estadia est =null;//declaracionde varia refen
				  //estamos apuntando una  a nulo
				while (ControladorBD.finRecordSet()) {
					est = new Estadia();
					est.setCodigo(ControladorBD.getInt("est_numero"));
					est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
					est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
					est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
					est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
					est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
					est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
					est.setMontoTotal(ControladorBD.getInt("est_monto"));
					est.setDescuento(ControladorBD.getInt("est_descu"));
					est.setObservacion(ControladorBD.getString("est_obse"));
					
					lista.add(est);//en cada interacion va agregar registro de la base de datos
					
				}
				
			return lista;
				
			}
		  public static ArrayList<Estadia> consultarInformeAbierto(int codDes, int codHas, String clienteDes, String clienteHas, String habitacionDes, String habitacionHas,int i) throws Exception{
			  String sql="select * from vw_est_cli_hab "
						+ "where est_activo = '1' and  est_numero between "+codDes+" and "+codHas+" "
								+ "and cli_nombre between '"+clienteDes+"' and '"+clienteHas+"' "
										+ " and hab_descri between '"+habitacionDes+"' and '"+habitacionHas+"' order by "+i ;
				ControladorBD.addSql(sql);
				ControladorBD.execute();
				ArrayList<Estadia> lista = new ArrayList<Estadia>();
				Estadia est =null;//declaracionde varia refen
				  //estamos apuntando una  a nulo
				while (ControladorBD.finRecordSet()) {
					est = new Estadia();
					est.setCodigo(ControladorBD.getInt("est_numero"));
					est.setFechaEntrada(ControladorBD.getDate("est_fecha"));
					est.getCodCliente().setCodigo(ControladorBD.getInt("est_codcli"));
					est.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
					est.getCodHabitacion().setCodigo(ControladorBD.getInt("est_codhab"));
					est.getCodHabitacion().setDescripHabitacion(ControladorBD.getString("hab_descri"));
					est.setFechaSalida(ControladorBD.getDate("est_fecsal"));
					est.setMontoTotal(ControladorBD.getInt("est_monto"));
					est.setDescuento(ControladorBD.getInt("est_descu"));
					est.setObservacion(ControladorBD.getString("est_obse"));
					
					lista.add(est);//en cada interacion va agregar registro de la base de datos
					
				}
				
			return lista;
				
			}



	}



