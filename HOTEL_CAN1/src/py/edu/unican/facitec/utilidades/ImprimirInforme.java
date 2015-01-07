package py.edu.unican.facitec.utilidades;

import jasper.ConfiguracaoReport;
import jasper.SwingExporterService;

import java.io.File;
import java.util.List;
import java.util.Map;

import py.edu.unican.facitec.formulario.formPantallaP;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class ImprimirInforme {
	//@SuppressWarnings("unchecked")
	
	public static void imprimir(List<?> lista, Map<String, Object> parametros,String nombreReporte) {
		try {
			JasperReport jr = (JasperReport) JRLoader.loadObject(new File("C://reporte//"+nombreReporte+".jasper"));
			JasperPrint jp = JasperFillManager.fillReport(jr, parametros, new JRBeanCollectionDataSource(lista));
			JasperViewer v = new JasperViewer(jp,false);
			v.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
