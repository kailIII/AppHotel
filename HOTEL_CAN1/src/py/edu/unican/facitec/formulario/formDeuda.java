package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

	public class formDeuda extends JDialog {
		private JPanel contentPane;
		private JTextField TfNroDeuda;
		private JTextField TfNroEstadia;
		private JTextField TfCodCliente;
		private JTextField TfMontoDeuda;
		private JTextField TfMontoPagado;
		private JButton btnNuevo;
		private JButton btnModificar;
		private JButton btnEliminar;
		private JButton btnDetalle;
		private JButton btnCierre;
		private JButton btnGuardar;
		private JButton btnCancelar;
		private JButton btnSalir;
		private JLabel lblBuscar;
		private JTextField tfBuscar;
		private Panel panel;
		private JScrollPane scrollPane;
		private JTable tableDeuda;
	public formDeuda() {
		setTitle("Deuda");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 794, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevo.setBounds(332, 47, 89, 32);
		contentPane.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(332, 124, 89, 32);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(332, 197, 89, 32);
		contentPane.add(btnEliminar);
		
		btnDetalle = new JButton("Detalle");
		btnDetalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDetalle.setBounds(332, 259, 89, 32);
		contentPane.add(btnDetalle);
		
		btnCierre = new JButton("Cierre");
		btnCierre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCierre.setBounds(332, 321, 89, 34);
		contentPane.add(btnCierre);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(435, 408, 89, 30);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(550, 408, 89, 30);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(649, 408, 99, 30);
		contentPane.add(btnSalir);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(26, 410, 46, 20);
		contentPane.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.setColumns(10);
		tfBuscar.setBounds(78, 406, 245, 32);
		contentPane.add(tfBuscar);
		
		panel = new Panel();
		panel.setLayout(null);
		panel.setBounds(435, 33, 333, 344);
		contentPane.add(panel);
		
		JLabel lblNroDeuda = new JLabel("Nro. Deuda");
		lblNroDeuda.setBounds(19, 11, 103, 26);
		panel.add(lblNroDeuda);
		lblNroDeuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TfNroDeuda = new JTextField();
		TfNroDeuda.setBounds(132, 16, 191, 26);
		panel.add(TfNroDeuda);
		TfNroDeuda.setColumns(10);
		
		TfNroEstadia = new JTextField();
		TfNroEstadia.setBounds(132, 75, 191, 31);
		panel.add(TfNroEstadia);
		TfNroEstadia.setColumns(10);
		
		JLabel lblNroEstadia = new JLabel("Nro. Estad\u00EDa");
		lblNroEstadia.setBounds(19, 70, 87, 26);
		panel.add(lblNroEstadia);
		lblNroEstadia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCodigoCliente = new JLabel("C\u00F3digo Cliente");
		lblCodigoCliente.setBounds(19, 125, 103, 26);
		panel.add(lblCodigoCliente);
		lblCodigoCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TfCodCliente = new JTextField();
		TfCodCliente.setBounds(132, 130, 191, 31);
		panel.add(TfCodCliente);
		TfCodCliente.setColumns(10);
		
		JLabel lblMontoDeuda = new JLabel("Monto Deuda");
		lblMontoDeuda.setBounds(19, 183, 87, 26);
		panel.add(lblMontoDeuda);
		lblMontoDeuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TfMontoDeuda = new JTextField();
		TfMontoDeuda.setBounds(132, 183, 191, 31);
		panel.add(TfMontoDeuda);
		TfMontoDeuda.setColumns(10);
		
		TfMontoPagado = new JTextField();
		TfMontoPagado.setBounds(132, 237, 191, 31);
		panel.add(TfMontoPagado);
		TfMontoPagado.setColumns(10);
		
		JLabel lblMontoPagado = new JLabel("Monto Pagado");
		lblMontoPagado.setBounds(19, 237, 103, 26);
		panel.add(lblMontoPagado);
		lblMontoPagado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(297, 11, -285, 330);
		contentPane.add(scrollPane);
		
		tableDeuda = new JTable();
		tableDeuda.setBounds(11, 25, 311, 370);
		contentPane.add(tableDeuda);
	}

}
