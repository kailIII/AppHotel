package py.edu.unican.facitec.informe;

import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


//import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.entidades.Servicio;
import py.edu.unican.facitec.formulario.formPantallaP;
//import py.edu.unican.facitec.sesion.SesionCliente;
import py.edu.unican.facitec.sesion.SesionServicio;
import py.edu.unican.facitec.utilidades.ImprimirInforme;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;




import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InformeServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodIni;
	private JTextField tfCodFin;
	private JTextField tfdescriDes;
	private JTextField tfdescriFin;
	private JButton btnProcesar;
	
	Map<String, Object> parm = new HashMap<String, Object>();
	private ArrayList<Servicio> lista;
	
	private final JPanel contenPanel = new JPanel();

	
	private DefaultTableModel dtmServicio = new DefaultTableModel(null, new String[]{"Código","Descripción","Monto del Servicio"});


	private JTable table;
	private int codDes;
	private int codHas;
	private String descriDes;
	private String descriHas;
	private JButton btnImprimir;
	private JComboBox cbOrden;
	public InformeServicio() {
		setTitle("Listado Servicio");
		setBounds(100, 100, 586, 488);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 255, 255));
		contentPanel.setForeground(new Color(0, 191, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(0, 0, 128));
		lblNombre.setBounds(212, 11, 71, 14);
		lblNombre.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 16));
		contentPanel.add(lblNombre);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDesde.setBounds(10, 37, 62, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
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
		tfCodIni.setBounds(64, 36, 86, 20);
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
		tfCodFin.setBounds(64, 61, 86, 20);
		tfCodFin.setColumns(10);
		contentPanel.add(tfCodFin);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setForeground(new Color(0, 0, 128));
		lblCdigo.setBounds(79, 8, 71, 20);
		lblCdigo.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 16));
		contentPanel.add(lblCdigo);
		
		JLabel lblDesde_1 = new JLabel("Desde:");
		lblDesde_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDesde_1.setBounds(173, 36, 62, 14);
		contentPanel.add(lblDesde_1);
		
		tfdescriDes = new JTextField();
		tfdescriDes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
         if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfdescriFin.requestFocus();
				}
			}
		});
		tfdescriDes.setBounds(232, 35, 86, 20);
		tfdescriDes.setColumns(10);
		contentPanel.add(tfdescriDes);
		
		JLabel lblHasta_1 = new JLabel("hasta:");
		lblHasta_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblHasta_1.setBounds(173, 64, 46, 14);
		contentPanel.add(lblHasta_1);
		
		tfdescriFin = new JTextField();
		tfdescriFin.setBounds(232, 62, 86, 20);
		tfdescriFin.setColumns(10);
		contentPanel.add(tfdescriFin);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnProcesar.setForeground(new Color(0, 0, 128));
		btnProcesar.setBackground(SystemColor.menu);
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ImprimirListaServicio();
				
			}
		});
		btnProcesar.setBounds(438, 64, 89, 23);
		contentPanel.add(btnProcesar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 536, 243);
		contentPanel.add(scrollPane);
		
		table = new JTable(dtmServicio);
		 
		scrollPane.setViewportView(table);
		table.setModel(dtmServicio); 
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setVisible(false);
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnImprimir.setBackground(SystemColor.menu);
		btnImprimir.setForeground(new Color(0, 0, 128));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarParametrosReporte();
				ImprimirInforme.imprimir(lista, parm, "ReporteServicio");
				btnImprimir.setVisible(false);
				
			}
		});
		btnImprimir.setBounds(65, 394, 89, 23);
		contentPanel.add(btnImprimir);
		
		cbOrden = new JComboBox();
		cbOrden.setForeground(new Color(0, 0, 128));
		cbOrden.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Descripcion"}));
		cbOrden.setBounds(427, 9, 112, 20);
		contentPanel.add(cbOrden);
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblOrdenarPor.setBounds(349, 13, 86, 14);
		contentPanel.add(lblOrdenarPor);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(new Color(255, 0, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(457, 406, 77, 23);
		contentPanel.add(btnSalir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			
		}
		//para que cargue los datos en la grilla de forma automatica
		//ImprimirListaServicio();
	}
	private void cargarTabla2() {
		
		String campos[]=new String[] {null,null,null};
				
			for (int i = 0; i < lista.size(); i++) {
				
				dtmServicio.addRow(campos);
				Servicio ser= lista.get(i);
				dtmServicio.setValueAt(ser.getCodigo(), i, 0);
				dtmServicio.setValueAt(ser.getDescripServicio(), i, 1);
				dtmServicio.setValueAt(ser.getMontoServicio(), i, 2);
				
				
			}	
		}	
		
		private void ImprimirListaServicio() {
			
			cargarFiltro();
		
		while (dtmServicio.getRowCount()>0) {
			dtmServicio.removeRow(0);
			
		}

		lista= new ArrayList<Servicio>();
		try {
			
			
			lista = SesionServicio.Listado(codDes, codHas, descriDes, descriHas,cbOrden.getSelectedIndex()+1);
			
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
		if (lista.size()>0) {
			cargarTabla2();
			btnImprimir.setVisible(true);
			
		}
			
		
		
	}

		private void cargarFiltro() {
			
			if (tfCodIni.getText().equals(""))
				codDes = 0;
			else
				codDes = Integer.parseInt(tfCodIni.getText());
			
			if (tfCodFin.getText().equals(""))
				codHas = 999999999;
			else
				codHas = Integer.parseInt(tfCodFin.getText());
			
			descriDes = tfdescriDes.getText();
			descriHas = tfdescriFin.getText()+"zzzz";
		}
		
		
		
		
		private void cargarParametrosReporte() {
			if (tfCodIni.getText().equals(""))
				codDes = 0;
			else
				codDes = Integer.parseInt(tfCodIni.getText());
			
			if (tfCodFin.getText().equals(""))
				codHas = 999999999;
			else
				codHas = Integer.parseInt(tfCodFin.getText());
			
			if (tfdescriDes.getText().equals(""))
				descriDes = "a";
			else
				descriDes = tfdescriDes.getText();
			
			if (tfdescriFin.getText().equals(""))
				descriHas = "z";
			else
				descriHas = tfdescriFin.getText();
			
			parm.put("codIni", codDes);
			parm.put("codFin", codHas);
			parm.put("descIni", descriDes );
			parm.put("descHas", descriHas);
			parm.put("orden", cbOrden.getSelectedItem());
			parm.put("hotel", formPantallaP.c.getNombreHotel());
		}
		}
	