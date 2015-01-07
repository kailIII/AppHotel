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
import py.edu.unican.facitec.entidades.Estadia;
import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.formulario.formPantallaP;
import py.edu.unican.facitec.sesion.SesionCliente;
import py.edu.unican.facitec.sesion.SesionEstadia;
import py.edu.unican.facitec.utilidades.ImprimirInforme;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InforEstadia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodInicial;
	private JTextField tfCodFin;
	private JTextField tfCliInicial;
	private JTextField tfCliFin;
	private JTextField tfHabInicial;
	private JTextField tfHabFin;
	
	
	Map<String, Object> parm = new HashMap<String, Object>();
	
	private ArrayList<Estadia> lista;
	
	private final JPanel contenPanel = new JPanel();

	
	private DefaultTableModel dtmEstadia = new DefaultTableModel(null, new String[]{"Nro","Cliente","Habitación","Entrada","Salida","monto","Descuento"});


	private JTable table;
	private int codDes;
	private int codHas;
	private String clienteDes;
	private String clienteHas;
	private String habitacionDes;
	private String habitacionHas;
	private Date fecEntradaDes;
	private Date fecEntradaHas;
	private Date fecSalidaDes;
	private Date fecSalidaHas;
	
	private JButton btnImprimir;
	private JComboBox cbOrden;
	private JTextField tfMontoTotal;
	public InforEstadia() {
		setTitle("Informe Estadia");
		setForeground(new Color(0, 0, 139));
		setBounds(100, 100, 878, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(173, 255, 47));
		contentPanel.setBackground(new Color(0, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Cliente");
		lblNombre.setForeground(Color.BLUE);
		lblNombre.setBackground(new Color(0, 0, 205));
		lblNombre.setBounds(10, 42, 61, 20);
		lblNombre.setFont(new Font("Andalus", Font.BOLD, 14));
		contentPanel.add(lblNombre);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setFont(new Font("Andalus", Font.BOLD, 14));
		lblDesde.setBounds(94, 13, 46, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("hasta");
		lblHasta.setFont(new Font("Andalus", Font.BOLD, 14));
		lblHasta.setBounds(235, 13, 46, 14);
		contentPanel.add(lblHasta);
		
		tfCodInicial = new JTextField();
		tfCodInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfCodFin.requestFocus();
				}
			}
		});
		tfCodInicial.setBounds(139, 11, 86, 20);
		contentPanel.add(tfCodInicial);
		tfCodInicial.setColumns(10);
		
		tfCodFin = new JTextField();
		tfCodFin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
          if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfCliInicial.requestFocus();
				}
			}
		});
		tfCodFin.setBounds(288, 11, 86, 20);
		tfCodFin.setColumns(10);
		contentPanel.add(tfCodFin);
		
		JLabel lblCdigo = new JLabel("Estad\u00EDa");
		lblCdigo.setForeground(Color.BLUE);
		lblCdigo.setBackground(new Color(0, 0, 139));
		lblCdigo.setBounds(10, 11, 61, 20);
		lblCdigo.setFont(new Font("Andalus", Font.BOLD, 14));
		contentPanel.add(lblCdigo);
		
		JLabel label = new JLabel("Desde");
		label.setFont(new Font("Andalus", Font.BOLD, 14));
		label.setBounds(94, 44, 46, 14);
		contentPanel.add(label);
		
		tfCliInicial = new JTextField();
		tfCliInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
          if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfCliFin.requestFocus();
				}
			}
		});
		tfCliInicial.setBounds(139, 42, 86, 20);
		tfCliInicial.setColumns(10);
		contentPanel.add(tfCliInicial);
		
		JLabel label_1 = new JLabel("hasta");
		label_1.setFont(new Font("Andalus", Font.BOLD, 14));
		label_1.setBounds(235, 44, 46, 14);
		contentPanel.add(label_1);
		
		tfCliFin = new JTextField();
		tfCliFin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfHabInicial.requestFocus();
				}
			}
		});
		tfCliFin.setBounds(288, 42, 86, 20);
		tfCliFin.setColumns(10);
		contentPanel.add(tfCliFin);
		
		JButton btnProcesar = new JButton("Todos");
		btnProcesar.setBackground(SystemColor.menu);
		btnProcesar.setForeground(new Color(0, 100, 0));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				             ImprimirListaEstadiaTodos();
 			             
			}
		});
		btnProcesar.setBounds(477, 73, 78, 23);
		contentPanel.add(btnProcesar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane.setBounds(4, 117, 848, 265);
		contentPanel.add(scrollPane);
		
		table = new JTable(dtmEstadia);
		 
		scrollPane.setViewportView(table);
		table.setModel(dtmEstadia); 
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setVisible(false);
		btnImprimir.setBackground(SystemColor.menu);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cargarParametrosReporte();
				ImprimirInforme.imprimir(lista, parm, "InfromeEstadia");
				btnImprimir.setVisible(false);
			}
			
		});
		btnImprimir.setBounds(67, 403, 89, 23);
		contentPanel.add(btnImprimir);
		
		JLabel lblOrdenadoPor = new JLabel("Ordenado por");
		lblOrdenadoPor.setFont(new Font("Algerian", Font.PLAIN, 12));
		lblOrdenadoPor.setBounds(397, 28, 89, 14);
		contentPanel.add(lblOrdenadoPor);
		
		cbOrden = new JComboBox();
		cbOrden.setMaximumRowCount(9);
		cbOrden.setBackground(new Color(102, 204, 255));
		cbOrden.setToolTipText("");
		cbOrden.setForeground(new Color(0, 0, 0));
		cbOrden.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre"}));
		cbOrden.setBounds(485, 23, 122, 23);
		contentPanel.add(cbOrden);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(774, 403, 78, 23);
		contentPanel.add(btnSalir);
		
		JLabel lblHabitacion = new JLabel("Habitaci\u00F3n");
		lblHabitacion.setForeground(Color.BLUE);
		lblHabitacion.setFont(new Font("Andalus", Font.BOLD, 14));
		lblHabitacion.setBackground(new Color(0, 0, 205));
		lblHabitacion.setBounds(10, 73, 78, 20);
		contentPanel.add(lblHabitacion);
		
		JLabel label_2 = new JLabel("Desde");
		label_2.setFont(new Font("Andalus", Font.BOLD, 14));
		label_2.setBounds(94, 75, 46, 14);
		contentPanel.add(label_2);
		
		tfHabInicial = new JTextField();
		tfHabInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
       if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfHabFin.requestFocus();
				}
			}
		});
		tfHabInicial.setColumns(10);
		tfHabInicial.setBounds(139, 73, 86, 20);
		contentPanel.add(tfHabInicial);
		
		JLabel label_3 = new JLabel("hasta");
		label_3.setFont(new Font("Andalus", Font.BOLD, 14));
		label_3.setBounds(235, 75, 46, 14);
		contentPanel.add(label_3);
		
		tfHabFin = new JTextField();
		tfHabFin.setColumns(10);
		tfHabFin.setBounds(288, 73, 86, 20);
		contentPanel.add(tfHabFin);
		
		tfMontoTotal = new JTextField();
		tfMontoTotal.setBounds(608, 382, 122, 20);
		contentPanel.add(tfMontoTotal);
		tfMontoTotal.setColumns(10);
		
		JLabel lblMontoTotal = new JLabel("Monto Total:");
		lblMontoTotal.setBounds(546, 385, 61, 14);
		contentPanel.add(lblMontoTotal);
		
		JLabel lblTodos = new JLabel("Procesar");
		lblTodos.setFont(new Font("Algerian", Font.PLAIN, 12));
		lblTodos.setBounds(397, 77, 70, 14);
		contentPanel.add(lblTodos);
		
		JButton btnCerrado = new JButton("Cerrado");
		btnCerrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImprimirListaEstadiaCerrado();
			}
		});
		btnCerrado.setForeground(new Color(0, 100, 0));
		btnCerrado.setBackground(SystemColor.menu);
		btnCerrado.setBounds(565, 73, 92, 23);
		contentPanel.add(btnCerrado);
		
		JButton btnAbierto = new JButton("Abierto");
		btnAbierto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImprimirListaEstadiaAbierto();
			}
		});
		btnAbierto.setForeground(new Color(0, 100, 0));
		btnAbierto.setBackground(SystemColor.menu);
		btnAbierto.setBounds(667, 73, 86, 23);
		contentPanel.add(btnAbierto);
		//para que cargue los datos en la grilla de forma automatica
		//ImprimirListaCliente();
	
	}
	
	private void cargarTabla2() {
		
		String campos[]=new String[] {null,null,null};
		
		int montoTotal = 0;
				
			for (int i = 0; i < lista.size(); i++) {
				
				dtmEstadia.addRow(campos);
				Estadia est= lista.get(i);
				dtmEstadia.setValueAt(est.getCodigo(), i, 0);
				dtmEstadia.setValueAt(est.getCodCliente().getNombre(), i, 1);
				dtmEstadia.setValueAt(est.getCodHabitacion().getDescripHabitacion(), i, 2);
				dtmEstadia.setValueAt(est.getFechaEntrada(), i, 3);
				dtmEstadia.setValueAt(est.getFechaSalida(), i, 4);
				dtmEstadia.setValueAt(est.getMontoTotal(), i, 5);
				dtmEstadia.setValueAt(est.getDescuento(), i, 6);
				
        montoTotal = montoTotal + est.getMontoTotal();
				
			}	
			
			tfMontoTotal.setText(String.valueOf(montoTotal));

	}
	
	
	private void ImprimirListaEstadiaTodos() {
		
		cargarFiltros();
		
		while (dtmEstadia.getRowCount()>0) {
			dtmEstadia.removeRow(0);
			
		}

		lista= new ArrayList<Estadia>();
		try {
	
		lista = SesionEstadia.consultarInformeTodos(codDes, codHas, clienteDes, clienteHas,habitacionDes,habitacionHas,cbOrden.getSelectedIndex()+1);
			
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
		if (lista.size()>0) {
			cargarTabla2();
			btnImprimir.setVisible(true);
		}
			
		
		
	}
	
private void ImprimirListaEstadiaCerrado() {
		
		cargarFiltros();
		
		while (dtmEstadia.getRowCount()>0) {
			dtmEstadia.removeRow(0);
			
		}

		lista= new ArrayList<Estadia>();
		try {
	
		lista = SesionEstadia.consultarInformeCerrado(codDes, codHas, clienteDes, clienteHas,habitacionDes,habitacionHas,cbOrden.getSelectedIndex()+1);
			
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
		if (lista.size()>0) {
			cargarTabla2();
			btnImprimir.setVisible(true);
		}
			
		
		
	}

private void ImprimirListaEstadiaAbierto() {
	
	cargarFiltros();
	
	while (dtmEstadia.getRowCount()>0) {
		dtmEstadia.removeRow(0);
		
	}

	lista= new ArrayList<Estadia>();
	try {

	lista = SesionEstadia.consultarInformeAbierto(codDes, codHas, clienteDes, clienteHas,habitacionDes,habitacionHas,cbOrden.getSelectedIndex()+1);
		
		
	} catch (Exception e) {
		
		
		e.printStackTrace();
	}
	
	if (lista.size()>0) {
		cargarTabla2();
		btnImprimir.setVisible(true);
	}
		
	
	
}

	private void cargarFiltros() {
	
			
		
		if (tfCodInicial.getText().equals(""))
			codDes = 0;
		else
			codDes = Integer.parseInt(tfCodInicial.getText());
		
		if (tfCodFin.getText().equals(""))
			codHas = 999999999;
		else
			codHas = Integer.parseInt(tfCodFin.getText());
		
		clienteDes = tfCliInicial.getText();
		clienteHas = tfCliFin.getText()+"zzzz";
		habitacionDes = tfHabInicial.getText();
		habitacionHas = tfHabFin.getText()+"zzzz";
		
	}

	
	private void cargarParametrosReporte() {
		if (tfCodInicial.getText().equals(""))
			codDes = 0;
		else
			codDes = Integer.parseInt(tfCodInicial.getText());
		
		if (tfCodFin.getText().equals(""))
			codHas = 999999999;
		else
			codHas = Integer.parseInt(tfCodFin.getText());
		
		if (tfCliInicial.getText().equals(""))
			clienteDes = "a";
		else
			clienteDes = tfCliInicial.getText();
		
		if (tfCliFin.getText().equals(""))
			clienteHas = "z";
		else
			clienteHas = tfCliFin.getText();
		
		if (tfHabInicial.getText().equals(""))
			habitacionDes = "a";
		else
			habitacionDes = tfCliInicial.getText();
		
		if (tfHabFin.getText().equals(""))
			habitacionHas = "z";
		else
			habitacionHas = tfCliFin.getText();
		
		parm.put("codIni", codDes);
		parm.put("codFin", codHas);
		parm.put("clienteIni", clienteDes);
		parm.put("clienteFin", clienteHas);
		parm.put("habitacionIni", habitacionDes);
		parm.put("habitacionFin", habitacionHas);
		parm.put("orden", cbOrden.getSelectedItem());
		parm.put("hotel", formPantallaP.c.getNombreHotel());
		
	}
}
