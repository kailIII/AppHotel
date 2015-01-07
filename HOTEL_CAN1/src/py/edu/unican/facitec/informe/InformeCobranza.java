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
import py.edu.unican.facitec.entidades.Cobranza;
import py.edu.unican.facitec.entidades.Deuda;
import py.edu.unican.facitec.entidades.Estadia;
import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.formulario.formPantallaP;
import py.edu.unican.facitec.sesion.SesionCliente;
import py.edu.unican.facitec.sesion.SesionCobranza;
import py.edu.unican.facitec.sesion.SesionEstadia;
import py.edu.unican.facitec.utilidades.ImprimirInforme;
import py.edu.unican.facitec.utilidades.Utilidad;

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

public class InformeCobranza extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodInicial;
	private JTextField tfCodFin;
	private JTextField tfCliInicial;
	private JTextField tfCliFin;
	private JTextField tfHabInicial;
	private JTextField tfHabFin;
	private JTextField tfFecEntradaInicial;
	private JTextField tfFecEntradaFin;
	private JTextField tfFecSalidaFin;
	private JTextField tfFecSalidaInicial;
	
	Map<String, Object> parm = new HashMap<String, Object>();
	
	private ArrayList<Cobranza> lista;
	
	private final JPanel contenPanel = new JPanel();

	
	private DefaultTableModel dtmCobranza = new DefaultTableModel(null, new String[]{"Código", "Cliente", "Monto pagado", "Fecha Pago"});


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
	public InformeCobranza() {
		setTitle("Informe Cobranza");
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
		
		JLabel lblCdigo = new JLabel("Cobranza");
		lblCdigo.setForeground(Color.BLUE);
		lblCdigo.setBackground(new Color(0, 0, 139));
		lblCdigo.setBounds(10, 11, 97, 20);
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
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setBackground(SystemColor.menu);
		btnProcesar.setForeground(new Color(0, 100, 0));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ImprimirListaCobranza();
			}
		});
		btnProcesar.setBounds(663, 73, 89, 23);
		contentPanel.add(btnProcesar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane.setBounds(10, 124, 848, 275);
		contentPanel.add(scrollPane);
		
		table = new JTable(dtmCobranza);
		 
		scrollPane.setViewportView(table);
		table.setModel(dtmCobranza); 
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setVisible(false);
		btnImprimir.setBackground(SystemColor.menu);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				cargarParametrosReporte();
				
				ImprimirInforme.imprimir(lista, parm, "InfromeCobranza");
				btnImprimir.setVisible(false);
			}
			
		});
		btnImprimir.setBounds(67, 403, 89, 23);
		contentPanel.add(btnImprimir);
		
		JLabel lblOrdenadoPor = new JLabel("Ordenado por");
		lblOrdenadoPor.setFont(new Font("Algerian", Font.PLAIN, 12));
		lblOrdenadoPor.setBounds(402, 77, 97, 14);
		contentPanel.add(lblOrdenadoPor);
		
		cbOrden = new JComboBox();
		cbOrden.setMaximumRowCount(9);
		cbOrden.setBackground(new Color(102, 204, 255));
		cbOrden.setToolTipText("");
		cbOrden.setForeground(new Color(0, 0, 0));
		cbOrden.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre"}));
		cbOrden.setBounds(501, 73, 122, 23);
		contentPanel.add(cbOrden);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(718, 403, 78, 23);
		contentPanel.add(btnSalir);
		
		JLabel lblHabitacion = new JLabel("Habitacion");
		lblHabitacion.setVisible(false);
		lblHabitacion.setForeground(Color.BLUE);
		lblHabitacion.setFont(new Font("Andalus", Font.BOLD, 14));
		lblHabitacion.setBackground(new Color(0, 0, 205));
		lblHabitacion.setBounds(10, 73, 78, 20);
		contentPanel.add(lblHabitacion);
		
		JLabel label_2 = new JLabel("Desde");
		label_2.setVisible(false);
		label_2.setFont(new Font("Andalus", Font.BOLD, 14));
		label_2.setBounds(94, 75, 46, 14);
		contentPanel.add(label_2);
		
		tfHabInicial = new JTextField();
		tfHabInicial.setVisible(false);
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
		label_3.setVisible(false);
		label_3.setFont(new Font("Andalus", Font.BOLD, 14));
		label_3.setBounds(235, 75, 46, 14);
		contentPanel.add(label_3);
		
		tfHabFin = new JTextField();
		tfHabFin.setVisible(false);
		tfHabFin.setColumns(10);
		tfHabFin.setBounds(288, 73, 86, 20);
		contentPanel.add(tfHabFin);
		
		JLabel lblFecEntrada = new JLabel("Fec Entrada");
		lblFecEntrada.setVisible(false);
		lblFecEntrada.setForeground(Color.BLUE);
		lblFecEntrada.setFont(new Font("Andalus", Font.BOLD, 14));
		lblFecEntrada.setBackground(new Color(0, 0, 139));
		lblFecEntrada.setBounds(402, 11, 86, 20);
		contentPanel.add(lblFecEntrada);
		
		JLabel label_5 = new JLabel("Desde");
		label_5.setVisible(false);
		label_5.setFont(new Font("Andalus", Font.BOLD, 14));
		label_5.setBounds(486, 13, 46, 14);
		contentPanel.add(label_5);
		
		tfFecEntradaInicial = new JTextField();
		tfFecEntradaInicial.setVisible(false);
		tfFecEntradaInicial.setColumns(10);
		tfFecEntradaInicial.setBounds(531, 11, 86, 20);
		contentPanel.add(tfFecEntradaInicial);
		
		JLabel label_6 = new JLabel("hasta");
		label_6.setVisible(false);
		label_6.setFont(new Font("Andalus", Font.BOLD, 14));
		label_6.setBounds(627, 13, 46, 14);
		contentPanel.add(label_6);
		
		tfFecEntradaFin = new JTextField();
		tfFecEntradaFin.setVisible(false);
		tfFecEntradaFin.setColumns(10);
		tfFecEntradaFin.setBounds(680, 11, 86, 20);
		contentPanel.add(tfFecEntradaFin);
		
		tfFecSalidaFin = new JTextField();
		tfFecSalidaFin.setVisible(false);
		tfFecSalidaFin.setColumns(10);
		tfFecSalidaFin.setBounds(680, 42, 86, 20);
		contentPanel.add(tfFecSalidaFin);
		
		JLabel label_4 = new JLabel("hasta");
		label_4.setVisible(false);
		label_4.setFont(new Font("Andalus", Font.BOLD, 14));
		label_4.setBounds(627, 44, 46, 14);
		contentPanel.add(label_4);
		
		tfFecSalidaInicial = new JTextField();
		tfFecSalidaInicial.setVisible(false);
		tfFecSalidaInicial.setColumns(10);
		tfFecSalidaInicial.setBounds(531, 42, 86, 20);
		contentPanel.add(tfFecSalidaInicial);
		
		JLabel label_7 = new JLabel("Desde");
		label_7.setVisible(false);
		label_7.setFont(new Font("Andalus", Font.BOLD, 14));
		label_7.setBounds(486, 44, 46, 14);
		contentPanel.add(label_7);
		
		JLabel lblFecSalida = new JLabel("Fec Salida");
		lblFecSalida.setVisible(false);
		lblFecSalida.setForeground(Color.BLUE);
		lblFecSalida.setFont(new Font("Andalus", Font.BOLD, 14));
		lblFecSalida.setBackground(new Color(0, 0, 139));
		lblFecSalida.setBounds(402, 42, 86, 20);
		contentPanel.add(lblFecSalida);
		
		tfMontoTotal = new JTextField();
		tfMontoTotal.setBounds(446, 404, 177, 20);
		contentPanel.add(tfMontoTotal);
		tfMontoTotal.setColumns(10);
		
		JLabel lblMontoTotalPago = new JLabel("Monto Total Pago");
		lblMontoTotalPago.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMontoTotalPago.setBounds(308, 403, 117, 23);
		contentPanel.add(lblMontoTotalPago);
		//para que cargue los datos en la grilla de forma automatica
		//ImprimirListaCliente();
	
	}
	
	private void cargarTabla2() {
		
		String campos[]=new String[] {null,null,null};
		int montoTotal = 0;
				
			for (int i = 0; i < lista.size(); i++) {
				
				dtmCobranza.addRow(campos);
				Cobranza cob= lista.get(i);
				dtmCobranza.setValueAt(cob.getCodigo(), i, 0);
				dtmCobranza.setValueAt(cob.getcodCliente().getNombre(), i, 1);
				dtmCobranza.setValueAt(cob.getcobmontopagado(), i, 2);
				dtmCobranza.setValueAt(Utilidad.getFechaDDMMAAAA(cob.getfechaCobro()), i, 3);
				
				montoTotal = montoTotal + cob.getcobmontopagado();
				
			}	
			
			tfMontoTotal.setText(String.valueOf(montoTotal));

	}
	
	
	private void ImprimirListaCobranza() {
		
		cargarFiltros();
		
		while (dtmCobranza.getRowCount()>0) {
			dtmCobranza.removeRow(0);
			
		}

		lista= new ArrayList<Cobranza>();
		
		try {
	
		lista = SesionCobranza.consultarInforme(codDes, codHas, clienteDes, clienteHas,cbOrden.getSelectedIndex()+1);
			
			
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
		
		parm.put("codIni", codDes);
		parm.put("codFin", codHas);
		
		parm.put("clienteIni", clienteDes);
		parm.put("clienteFin", clienteHas);
		
		parm.put("orden", cbOrden.getSelectedItem());
		parm.put("hotel", formPantallaP.c.getNombreHotel());
		
	}
}
