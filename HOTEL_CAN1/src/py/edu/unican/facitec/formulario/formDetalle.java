package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class formDetalle extends JDialog {

	private JPanel contentPane;
	private JTextField TfNroDetalle;
	private JTextField TfNroEstadia;
	private JTextField TfCodServicio;
	private JTextField TfMontoDetalle;
	private Panel panel;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	private JLabel lblBuscar;
	private JTextField tfBuscar;
	private JScrollPane scrollPane;
	private JTable table;

	public formDetalle() {
		setTitle("Detalle");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 799, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new Panel();
		panel.setBounds(451, 27, 322, 322);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TfNroDetalle = new JTextField();
		TfNroDetalle.setBounds(116, 13, 191, 24);
		panel.add(TfNroDetalle);
		TfNroDetalle.setColumns(10);
		
		JLabel lblNroDetalle = new JLabel("Nro. de Detalle");
		lblNroDetalle.setBounds(10, 11, 113, 24);
		panel.add(lblNroDetalle);
		lblNroDetalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNroDeEstada = new JLabel("Nro. de Estad\u00EDa");
		lblNroDeEstada.setBounds(10, 69, 113, 24);
		panel.add(lblNroDeEstada);
		lblNroDeEstada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TfNroEstadia = new JTextField();
		TfNroEstadia.setBounds(115, 71, 192, 24);
		panel.add(TfNroEstadia);
		TfNroEstadia.setColumns(10);
		
		JLabel lblCodDeServicio = new JLabel("C\u00F3digo Servicio");
		lblCodDeServicio.setBounds(10, 143, 113, 24);
		panel.add(lblCodDeServicio);
		lblCodDeServicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TfCodServicio = new JTextField();
		TfCodServicio.setBounds(116, 143, 191, 29);
		panel.add(TfCodServicio);
		TfCodServicio.setColumns(10);
		
		TfMontoDetalle = new JTextField();
		TfMontoDetalle.setBounds(116, 207, 191, 24);
		panel.add(TfMontoDetalle);
		TfMontoDetalle.setColumns(10);
		
		JLabel lblMontoDetalle = new JLabel("Monto Detalle");
		lblMontoDetalle.setBounds(10, 205, 102, 24);
		panel.add(lblMontoDetalle);
		lblMontoDetalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevo.setBounds(341, 40, 89, 32);
		contentPane.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(341, 95, 89, 32);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(341, 157, 89, 32);
		contentPane.add(btnEliminar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(448, 390, 89, 32);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(549, 390, 89, 32);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(662, 390, 89, 32);
		contentPane.add(btnSalir);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(20, 401, 46, 21);
		contentPane.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(73, 390, 247, 28);
		contentPane.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 305, 366);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
	}

}
