package py.edu.unican.facitec.sesion;

import java.util.ArrayList;




import javax.swing.JOptionPane;

import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.entidades.Cobranza;
import py.edu.unican.facitec.entidades.Detalle;
import py.edu.unican.facitec.entidades.Deuda;
import py.edu.unican.facitec.entidades.Estadia;

import com.progress.bd.ControladorBD;

public class SesionCobranza {
	
	
	
	public static void guardarCobranza (Cobranza cobranza) throws Exception{  //recive el objeto cargado mediante (Estadia estadia)
		
		//si es numerico es "++"
		//si es caracter, String es '"++"'
		String sql = "insert into cobranza ( cob_numero,cob_nrodeu,cob_codcli,cob_pagado,cob_feccobro) values ( "
			        + " "+cobranza.getCodigo()+" , "
						+ " "+cobranza.getnroDeuada().getCodigo()+", "
							+ " "+cobranza.getcodCliente().getCodigo()+" , "
								+ "  "+cobranza.getcobmontopagado()+" , "
									+ " '"+cobranza.getfechaCobro()+"' ) ";
		
		//verifica si el sql esta escrito correctamente
		ControladorBD.addSql(sql);
		ControladorBD.execute();  // lo mandamos ejecutar en la base de datos
		
	}
	
	public static Deuda consultarDeudaPorNro(int codigo) throws Exception {
		
		Deuda deu = null;
		String sql="SELECT * FROM deuda WHERE deu_numero="+codigo+"";
			try {
				ControladorBD.addSql(sql);
				ControladorBD.execute();
				
				if (ControladorBD.finRecordSet()) {
					deu = new Deuda();
					deu.getCodCliente().setCodigo(ControladorBD.getInt("deu_codcli"));
			        deu.getCodCliente().setNombre(ControladorBD.getString("cli_nombre"));
			        deu.setMontoPagado(ControladorBD.getInt("deu_pagado"));
			        deu.getNroEstadia().setCodigo(ControladorBD.getInt("deu_nroest"));
					//det.set(ControladorBD.getString("est_obse"));
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return deu;

}
	
	
	public  static int recupetarUltimoCodigo() throws Exception {
		
		int id = ControladorBD.maximoValorCampo("cobranza","cob_numero");  
	return id;

	}
	
	public static void modificarCobranza(Cobranza cob) throws Exception  {
	  	String sql="UPDATE cobranza SET  "
	  					+ " cob_feccobro='"+cob.getfechaCobro()+"', "
	  						+ " est_codcli='"+cob.getcodCliente().getCodigo()+"', "
	  							+ "cob_nrodeuda='"+cob.getnroDeuada().getCodigo()+"', "
	  								+ "est_monto="+cob.getcobmontopagado()+",  "
	  									+ " WHERE cob_numero ="+cob.getCodigo()+" ";
	
		ControladorBD.addSql(sql);
		ControladorBD.execute();

}
	public static void ActualizaDeuda(Deuda deu) throws Exception  {
	  	String sql="UPDATE deuda SET  "
	  						+ " deu_codcli='"+deu.getCodCliente().getCodigo()+"', "
	  							+ "deu_nroest='"+deu.getNroEstadia().getCodigo()+"', "
	  								+ "deu_monto="+deu.getMontoDeuda()+",  "
	  									+ "deu_pagado="+deu.getMontoPagado()+" "
	  											+ " WHERE deu_numero ="+deu.getCodigo()+" ";
	
		ControladorBD.addSql(sql);
		ControladorBD.execute();
	}	
	public static ArrayList<Deuda> consultarTodosDeuda() throws Exception{
	   	 
	   	 String sql="select * from vw_deu_cli_cob ";
	   	
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			ArrayList<Deuda> lista = new ArrayList<Deuda>();
			Deuda deu =null;
			
			while (ControladorBD.finRecordSet()) {
				
				deu = new Deuda();
				deu.setCodigo(ControladorBD.getInt("deu_numero"));
				deu.getCodCliente().setNombre((ControladorBD.getString("cli_nombre")));
				deu.getNroEstadia().setCodigo(ControladorBD.getInt("deu_nroest"));
				deu.setMontoDeuda(ControladorBD.getInt("deu_monto"));
				deu.setMontoPagado(ControladorBD.getInt("deu_pagado"));
				lista.add(deu);    //en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}
		
	public static ArrayList<Deuda> consultarConDeuda() throws Exception{
	   	 
	   	 String sql="select * from vw_deu_cli_cob "
	   	 		+ "  where deu_monto >0 ";
	   	
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			
			  //estamos creando una lista que solo puede contener datos de tipo cliente
			  //la expresion se llama generics apartir de JAVA 5 observacion
			ArrayList<Deuda> lista = new ArrayList<Deuda>();
			Deuda deu =null;//declaracionde varia refen
			  //estamos apuntando una  a nulo
			
			
			while (ControladorBD.finRecordSet()) {
				
				deu = new Deuda();
				deu.setCodigo(ControladorBD.getInt("deu_numero"));
				deu.getCodCliente().setNombre((ControladorBD.getString("cli_nombre")));
				deu.getNroEstadia().setCodigo(ControladorBD.getInt("deu_nroest"));
				deu.setMontoDeuda(ControladorBD.getInt("deu_monto"));
				deu.setMontoPagado(ControladorBD.getInt("deu_pagado"));
				lista.add(deu);    //en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}
	
	public static ArrayList<Deuda> consultarSinDeuda() throws Exception{
	   	 
	   	 String sql="select * from vw_deu_cli_cob where deu_monto =0 ";
	   	
			
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			ArrayList<Deuda> lista = new ArrayList<Deuda>();
			Deuda deu =null;//declaracionde varia refen
			
			while (ControladorBD.finRecordSet()) {
				
				deu = new Deuda();
				deu.setCodigo(ControladorBD.getInt("deu_numero"));
				deu.getCodCliente().setNombre((ControladorBD.getString("cli_nombre")));
				deu.getNroEstadia().setCodigo(ControladorBD.getInt("deu_nroest"));
				deu.setMontoDeuda(ControladorBD.getInt("deu_monto"));
				deu.setMontoPagado(ControladorBD.getInt("deu_pagado"));
				lista.add(deu);    //en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}
		
	public static ArrayList<Cobranza> consultarInforme(int codDes, int codHas, String clienteDes, String clienteHas,int i) throws Exception{
		  String sql="select * from vw_cob_deu_cli "
					+ " where cob_numero between "+codDes+" and "+codHas+" "
							+ " and cli_nombre between '"+clienteDes+"' and '"+clienteHas+"'"
									+ " order by "+i ;
		  
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			ArrayList<Cobranza> lista = new ArrayList<Cobranza>();
			Cobranza cob =null;//declaracionde varia refen
			while (ControladorBD.finRecordSet()) {
				cob = new Cobranza();
				cob.setCodigo(ControladorBD.getInt("cob_numero")); 
				cob.getcodCliente().setCodigo(ControladorBD.getInt("cob_codcli"));
				cob.getcodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				cob.setfechaCobro(ControladorBD.getDate("cob_feccobro"));
				cob.getnroDeuada().setCodigo(ControladorBD.getInt("cob_nrodeu"));
				cob.setcobmontopagado(ControladorBD.getInt("cob_pagado"));
				
				//est.setActivoInActivo(ControladorBD.getString(("est_activo")));
				
				lista.add(cob);//en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}

	public static ArrayList<Cobranza> consultarInformeCobranza(int codDes, int codHas, String clienteDes, String clienteHas,int i) throws Exception{
		  String sql="select sum(cob_pagado)cob_pagado,max(cob_numero)cob_numero,cob_codcli,cli_codigo,cli_nombre,cob_nrodeu,deu_monto,cob_feccobro from vw_cob_deu_cli "
					+ " where cob_numero between "+codDes+" and "+codHas+" "
							+ " and cli_nombre between '"+clienteDes+"' and '"+clienteHas+"'"
									+ " group by cob_numero,cob_codcli,cli_codigo,cli_nombre,cob_nrodeu,deu_monto,cob_feccobro  order by "+i ;
		  
			ControladorBD.addSql(sql);
			ControladorBD.execute();
			ArrayList<Cobranza> lista = new ArrayList<Cobranza>();
			Cobranza cob =null;//declaracionde varia refen
			  //estamos apuntando una  a nulo
			while (ControladorBD.finRecordSet()) {
				cob = new Cobranza();
				cob.setCodigo(ControladorBD.getInt("cob_numero")); 
				cob.getcodCliente().setCodigo(ControladorBD.getInt("cob_codcli"));
				cob.getcodCliente().setNombre(ControladorBD.getString("cli_nombre"));
				cob.getnroDeuada().setCodigo(ControladorBD.getInt("(cob_nrodeu"));
				cob.getnroDeuada().setMontoDeuda(ControladorBD.getInt("deu_monto"));
				cob.setcobmontopagado(ControladorBD.getInt("cob_pagado"));
				cob.setfechaCobro(ControladorBD.getDate("cob_feccobro"));
				//est.setActivoInActivo(ControladorBD.getString(("est_activo")));
				
				lista.add(cob);//en cada interacion va agregar registro de la base de datos
				
			}
			
		return lista;
			
		}
	
	public static ArrayList<Deuda> consultarTodosPorNombre(String filtro) throws Exception{
		
		
		String sql="select * from vw_deu_cli_cob"
				+ " where deu_monto >0 and cli_nombre like '%"+ filtro +"%'";
		
		ControladorBD.addSql(sql);
		ControladorBD.execute();
		ArrayList<Deuda> lista = new ArrayList<Deuda>();
		Deuda deu =null;//declaracionde varia refen
		while (ControladorBD.finRecordSet()) {
			deu = new Deuda();
			deu.setCodigo(ControladorBD.getInt("deu_numero"));
			deu.getCodCliente().setNombre((ControladorBD.getString("cli_nombre")));
			deu.getNroEstadia().setCodigo(ControladorBD.getInt("deu_nroest"));
			deu.setMontoDeuda(ControladorBD.getInt("deu_monto"));
			deu.setMontoPagado(ControladorBD.getInt("deu_pagado"));
			lista.add(deu);    //en cada interacion va agregar registro de la base de datos
			
		}
		
	return lista;
		
	}

}
