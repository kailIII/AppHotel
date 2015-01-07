package py.edu.unican.facitec.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import py.edu.unican.facitec.entidades.Estadia;

public class Utilidad {
	
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private static Integer MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	
	
	   public static String getFechaAAAAMMDD(Date date){
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(date);
	}
	   
	   public static Date retornarFecha(Object object) {
			String text = (String) object;
			if (text == null) {
				return null;
			}
			GregorianCalendar gc = new GregorianCalendar();
			gc.setLenient(false);
			int i1 = text.indexOf('/');
			int i2 = text.indexOf('/', i1 + 1);
			String sdia = text.substring(0, i1);
			String smes = text.substring(i1 + 1, i2);
			String sanho = text.substring(i2 + 1, text.length());
			if (sdia.trim().equals("") || smes.equals("") || sanho.equals("")) {
				return null;
			} else {
				int dia = Integer.valueOf(sdia.replaceAll(" ", ""));
				int mes = Integer.valueOf(smes.replaceAll(" ", ""));
				int anho = Integer.valueOf(sanho.replaceAll(" ", ""));
				//if (anho < 80) {
				//	anho = 2000 + anho;
				//}
				if (anho >= 1 && anho < 100) {
					//Dos digitos
					//anho = 1900 + anho;
					anho = 2000 + anho;
				}
				gc.set(Calendar.DAY_OF_MONTH, dia);
				gc.set(Calendar.MONTH, mes - 1);
				gc.set(Calendar.YEAR, anho);
				//gc.set(Calendar.HOUR_OF_DAY, 0);
				//gc.set(Calendar.MINUTE, 0);
				//gc.set(Calendar.SECOND, 0);
				//gc.set(Calendar.MILLISECOND, 0);
				Date date = new Date(gc.getTimeInMillis());			
				return date;
			}
			
		}
		
	
	public static Date retornarFechaa(Object f) {
		String[] aux =((String)f).split("/");
		int dia= Integer.parseInt(aux[0]);
		int mes= Integer.parseInt(aux[1]);
		int anho= Integer.parseInt(aux[2]);
		Calendar auxCalendar= Calendar.getInstance();
		auxCalendar.add(Calendar.DATE,dia );
		auxCalendar.add(Calendar.MONTH,mes );
		auxCalendar.add(Calendar.YEAR,anho );
		return auxCalendar.getTime();
	}
	
	public static Date retornarFechaDeBD(Object f) {
		String[] aux =((String)f).split("-");
		int dia= Integer.parseInt(aux[0]);
		int mes= Integer.parseInt(aux[1]);
		int anho= Integer.parseInt(aux[2]);
		Calendar auxCalendar= Calendar.getInstance();
		auxCalendar.add(Calendar.DATE,dia );
		auxCalendar.add(Calendar.MONTH,mes );
		auxCalendar.add(Calendar.YEAR,anho );
		return auxCalendar.getTime();
	}
	
	
	public static String getFechaDDMMAAAA(Date date){
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	public static String calculaMontoHabitacion(String fecha1, String fecha2, Estadia estadia){
		  String monto = String.valueOf(estadia.getCodHabitacion().getMontoDia());		
		  System.out.println(monto);
		  String fechaHoy = getFechaActual();
			 if(fecha2.equals("__/__/____")){
				  fecha2 = fechaHoy;
			//	  System.out.println(fecha2+" fecha2");
			  }
				  Date fechaEntrada = retornarFecha(fecha1);
			  	Date fechaSalida = retornarFecha(fecha2);
			  	int diferencia = (int) ((fechaSalida.getTime()-fechaEntrada.getTime())/MILLSECS_PER_DAY)+1;
			  	
			  	if(diferencia>=0){
				  	monto = String.valueOf(estadia.getCodHabitacion().getMontoDia()*diferencia);
				 }
			  
			return monto;
	    }
	public static String getFechaActual() {
      Date ahora = new Date();
      return formato.format(ahora);
  }
}
