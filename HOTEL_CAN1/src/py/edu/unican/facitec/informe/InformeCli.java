package py.edu.unican.facitec.informe;

import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.formulario.formPantallaP;
import py.edu.unican.facitec.sesion.SesionCliente;
import py.edu.unican.facitec.utilidades.ImprimirInforme;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InformeCli extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodIni;
	private JTextField tfCodFin;
	private JTextField tfNombIni;
	private JTextField tfNombFin;
	
	Map<String, Object> parm = new HashMap<String, Object>();
	
	private ArrayList<Cliente> lista;
	
	private final JPanel contenPanel = new JPanel();

	
	private DefaultTableModel dtmCliente = new DefaultTableModel(null, new String[]{"Código","Nombre","Ruc","Telefono","Cedula"});


	private JTable table;
	private int codDes;
	private int codHas;
	private String nombDes;
	private String nombHas;
	private JButton btnImprimir;
	private JComboBox cbOrden;
	public InformeCli() {
		setTitle("Listado de Cliente");
		setForeground(new Color(0, 0, 139));
		setBounds(100, 100, 822, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(173, 255, 47));
		contentPanel.setBackground(new Color(0, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.BLUE);
		lblNombre.setBackground(new Color(0, 0, 205));
		lblNombre.setBounds(384, 11, 89, 20);
		lblNombre.setFont(new Font("Algerian", Font.BOLD, 18));
		contentPanel.add(lblNombre);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setFont(new Font("Andalus", Font.BOLD, 14));
		lblDesde.setBounds(169, 38, 46, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("hasta");
		lblHasta.setFont(new Font("Andalus", Font.BOLD, 14));
		lblHasta.setBounds(169, 63, 46, 14);
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
		tfCodIni.setBounds(217, 38, 86, 20);
		contentPanel.add(tfCodIni);
		tfCodIni.setColumns(10);
		
		tfCodFin = new JTextField();
		tfCodFin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
          if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfNombIni.requestFocus();
				}
			}
		});
		tfCodFin.setBounds(217, 65, 86, 20);
		tfCodFin.setColumns(10);
		contentPanel.add(tfCodFin);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setForeground(Color.BLUE);
		lblCdigo.setBackground(new Color(0, 0, 139));
		lblCdigo.setBounds(195, 11, 71, 20);
		lblCdigo.setFont(new Font("Algerian", Font.BOLD, 18));
		contentPanel.add(lblCdigo);
		
		JLabel label = new JLabel("Desde");
		label.setFont(new Font("Andalus", Font.BOLD, 14));
		label.setBounds(347, 44, 46, 14);
		contentPanel.add(label);
		
		tfNombIni = new JTextField();
		tfNombIni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
          if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfNombFin.requestFocus();
				}
			}
		});
		tfNombIni.setBounds(403, 40, 86, 20);
		tfNombIni.setColumns(10);
		contentPanel.add(tfNombIni);
		
		JLabel label_1 = new JLabel("hasta");
		label_1.setFont(new Font("Andalus", Font.BOLD, 14));
		label_1.setBounds(348, 68, 46, 14);
		contentPanel.add(label_1);
		
		tfNombFin = new JTextField();
		tfNombFin.setBounds(403, 65, 86, 20);
		tfNombFin.setColumns(10);
		contentPanel.add(tfNombFin);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setBackground(SystemColor.menu);
		btnProcesar.setForeground(new Color(0, 100, 0));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ImprimirListaCliente();
			}
		});
		btnProcesar.setBounds(630, 61, 89, 23);
		contentPanel.add(btnProcesar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane.setBounds(4, 97, 792, 295);
		contentPanel.add(scrollPane);
		
		table = new JTable(dtmCliente);
		 
		scrollPane.setViewportView(table);
		table.setModel(dtmCliente); 
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setVisible(false);
		btnImprimir.setBackground(SystemColor.menu);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cargarParametrosReporte();
				ImprimirInforme.imprimir(lista, parm, "ReporteCliente");
				btnImprimir.setVisible(false);
			}
			
		});
		btnImprimir.setBounds(67, 403, 89, 23);
		contentPanel.add(btnImprimir);
		
		JLabel lblOrdenadoPor = new JLabel("Ordenado por");
		lblOrdenadoPor.setFont(new Font("Algerian", Font.PLAIN, 12));
		lblOrdenadoPor.setBounds(521, 25, 97, 14);
		contentPanel.add(lblOrdenadoPor);
		
		cbOrden = new JComboBox();
		cbOrden.setMaximumRowCount(9);
		cbOrden.setBackground(new Color(102, 204, 255));
		cbOrden.setToolTipText("");
		cbOrden.setForeground(new Color(0, 0, 0));
		cbOrden.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Nombre"}));
		cbOrden.setBounds(620, 21, 122, 23);
		contentPanel.add(cbOrden);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(718, 403, 78, 23);
		contentPanel.add(btnSalir);
		//para que cargue los datos en la grilla de forma automatica
		//ImprimirListaCliente();
	
	}
	
	private void cargarTabla2() {
		
		String campos[]=new String[] {null,null,null};
				
			for (int i = 0; i < lista.size(); i++) {
				
				dtmCliente.addRow(campos);
				Cliente cli= lista.get(i);
				dtmCliente.setValueAt(cli.getCodigo(), i, 0);
				dtmCliente.setValueAt(cli.getNombre(), i, 1);
				dtmCliente.setValueAt(cli.getRuc(), i, 2);
				dtmCliente.setValueAt(cli.getTelefono(), i, 3);
				dtmCliente.setValueAt(cli.getCedula(), i, 4);
				
			}	

	}
	
	
	private void ImprimirListaCliente() {
		
		cargarFiltros();
		
		while (dtmCliente.getRowCount()>0) {
			dtmCliente.removeRow(0);
			
		}

		lista= new ArrayList<Cliente>();
		try {
	
		lista = SesionCliente.consultarListado(codDes, codHas, nombDes, nombHas, cbOrden.getSelectedIndex()+1);
			
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
		if (lista.size()>0) {
			cargarTabla2();
			btnImprimir.setVisible(true);
			
		}
			
		
		
	}

	private void cargarFiltros() {
	
			
		
		if (tfCodIni.getText().equals(""))
			codDes = 0;
		else
			codDes = Integer.parseInt(tfCodIni.getText());
		
		if (tfCodFin.getText().equals(""))
			codHas = 999999999;
		else
			codHas = Integer.parseInt(tfCodFin.getText());
		
		nombDes = tfNombIni.getText();
		nombHas = tfNombFin.getText()+"zzzz";
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
		if (tfNombIni.getText().equals(""))
			nombDes = "a";
		else
			nombDes = tfNombIni.getText();
		if (tfNombFin.getText().equals(""))
			nombHas = "z";
		else
			nombHas = tfNombFin.getText();
		
		parm.put("codIni", codDes);
		parm.put("codFin", codHas);
		parm.put("nombIni", nombDes);
		parm.put("nombFin", nombHas);
		parm.put("orden", cbOrden.getSelectedItem());
		parm.put("hotel", formPantallaP.c.getNombreHotel());
	}
}
