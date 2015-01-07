package py.edu.unican.facitec.informe;

import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.entidades.Config;
import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.entidades.Servicio;
import py.edu.unican.facitec.formulario.FormConfig;
import py.edu.unican.facitec.formulario.formPantallaP;
import py.edu.unican.facitec.sesion.SesionCliente;
import py.edu.unican.facitec.sesion.SesionHabitacion;
import py.edu.unican.facitec.sesion.SesionServicio;
import py.edu.unican.facitec.utilidades.ImprimirInforme;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InformeHabitacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodIni;
	private JComboBox cbOrden;
	private JTextField tfCodFin;
	private JTextField tfdescriDes;
	private JTextField tfdescriFin;
	private JButton btnImprimir;
	private ArrayList<Habitacion> lista;
	Map<String, Object> parm = new HashMap<String, Object>();
	private final JPanel contenPanel = new JPanel();
	private DefaultTableModel dtmHabitacion = new DefaultTableModel(null, new String[]{"Código", "Descripción", "Monto Por Día", "Observación"});
	private JTable table;
	private String descriDes;
	private String descriHast;
	private int codDes;
	private int codHas;
	private String i;
	public InformeHabitacion() {
		setTitle("Listado Habitacion");
		setForeground(Color.RED);
		setBounds(100, 100, 572, 488);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 255, 127));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(10, 39, 62, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("hasta");
		lblHasta.setBounds(10, 64, 46, 14);
		contentPanel.add(lblHasta);
		
		tfCodIni = new JTextField();
		tfCodIni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
              if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfCodFin.requestFocus();
				}
			}
		});
		tfCodIni.setBounds(51, 36, 86, 20);
		contentPanel.add(tfCodIni);
		tfCodIni.setColumns(10);
		
		tfCodFin = new JTextField();
		tfCodFin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfdescriDes.requestFocus();
				}
			}
		});
		tfCodFin.setBounds(51, 61, 86, 20);
		tfCodFin.setColumns(10);
		contentPanel.add(tfCodFin);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setForeground(Color.BLUE);
		lblCdigo.setBounds(43, 11, 71, 14);
		lblCdigo.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 14));
		contentPanel.add(lblCdigo);
		
		JLabel label = new JLabel("Desde");
		label.setBounds(168, 39, 40, 14);
		contentPanel.add(label);
		
		tfdescriDes = new JTextField();
		tfdescriDes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
             if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfdescriFin.requestFocus();
				}
			}
		});
		tfdescriDes.setBounds(207, 36, 86, 20);
		tfdescriDes.setColumns(10);
		contentPanel.add(tfdescriDes);
		
		JLabel label_1 = new JLabel("hasta");
		label_1.setBounds(167, 64, 46, 14);
		contentPanel.add(label_1);
		
		tfdescriFin = new JTextField();
		tfdescriFin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                  if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					//btnProcesar.requestFocus();
				}
			}
		});
		tfdescriFin.setBounds(207, 61, 86, 20);
		tfdescriFin.setColumns(10);
		contentPanel.add(tfdescriFin);
		
		cbOrden = new JComboBox();
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre"}));
		cbOrden.setBounds(409, 25, 86, 20);
		contentPanel.add(cbOrden);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnProcesar.setToolTipText("");
		btnProcesar.setBackground(new Color(135, 206, 250));
		btnProcesar.setForeground(new Color(0, 0, 0));
		btnProcesar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ImprimirListaHabitacion();
			}
		});
		btnProcesar.setBounds(406, 73, 89, 23);
		contentPanel.add(btnProcesar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 124, 531, 267);
		contentPanel.add(scrollPane);
		table = new JTable(dtmHabitacion);
		scrollPane.setViewportView(table);
		JLabel lblOrdenadoPor = new JLabel("Ordenado POr");
		lblOrdenadoPor.setBounds(328, 28, 86, 14);
		contentPanel.add(lblOrdenadoPor);
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setVisible(false);
		btnImprimir.setBackground(new Color(211, 211, 211));
		btnImprimir.setForeground(new Color(0, 0, 0));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarParametrosReporte();
				ImprimirInforme.imprimir(lista, parm, "RepoteHabitacion");
				btnImprimir.setVisible(false);
			}
		});
		btnImprimir.setBounds(30, 402, 103, 26);
		contentPanel.add(btnImprimir);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.BLUE);
		lblNombre.setFont(new Font("Algerian", Font.BOLD, 14));
		lblNombre.setBounds(207, 11, 73, 14);
		contentPanel.add(lblNombre);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(440, 402, 81, 23);
		contentPanel.add(btnSalir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);	
		}
	}
	private void cargarTabla() {
		
		String campos[]=new String[] {null,null,null};
				
			for (int i = 0; i < lista.size(); i++) {
				
				dtmHabitacion.addRow(campos);
				Habitacion hab= lista.get(i);
				dtmHabitacion.setValueAt(hab.getCodigo(), i, 0);
				dtmHabitacion.setValueAt(hab.getDescripHabitacion(), i, 1);
				dtmHabitacion.setValueAt(hab.getMontoDia(), i, 2);
				dtmHabitacion.setValueAt(hab.getObservacion(), i, 3);
					
			}	
		}	
		
		private void ImprimirListaHabitacion() {
			
			CargarFiltros();
		
		while (dtmHabitacion.getRowCount()>0) {
			dtmHabitacion.removeRow(0);
			
		}
		

		lista= new ArrayList<Habitacion>();
		try {
		
			lista = SesionHabitacion.listado(codDes, codHas, descriDes, descriHast, cbOrden.getSelectedIndex()+1);
					
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
		if (lista.size()>0) {
			cargarTabla();
			
			btnImprimir.setVisible(true);	
		}
			
		
	}
		private void CargarFiltros() {
			
			
			if (!tfCodIni.getText().isEmpty()){
				codDes = Integer.parseInt(tfCodIni.getText());
			}else{
				
				codDes = 0;
			}
				
			if (!tfCodFin.getText().isEmpty()){ //si no es vacio, el signo de admiracion representa una negacion
				codHas = Integer.parseInt(tfCodFin.getText());
			} else{
				codHas = 999999999;
			}
			
				descriDes = tfdescriDes.getText();
				descriHast = tfdescriFin.getText()+"zzzz";	

		}
		private void cargarParametrosReporte() {
		
			if (tfCodIni.getText().equals(""))
				codDes = 0;
			else
				codDes = (Integer.parseInt(tfCodIni.getText()));
			if (tfCodFin.getText().equals(""))
				codHas = 999999999;
			else
				codHas =(Integer.parseInt(tfCodFin.getText()));
			if (tfdescriDes.getText().equals(""))
				descriDes = "a";
			else
				descriDes = tfdescriDes.getText();
			
			if (tfdescriFin.getText().equals(""))
				descriHast = "z";
			else			
				descriHast= tfdescriFin.getText();
				
			parm.put("codIni", codDes);
			parm.put("codFin", codHas);
			parm.put("descriIni", descriDes);
			parm.put("descriFin",descriHast);
			parm.put("orden", cbOrden.getSelectedItem());
			parm.put("hotel", formPantallaP.c.getNombreHotel());
	
}
}
