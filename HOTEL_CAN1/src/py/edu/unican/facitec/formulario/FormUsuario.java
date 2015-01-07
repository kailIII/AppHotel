package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class FormUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormUsuario dialog = new FormUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormUsuario() {
		setBounds(100, 100, 477, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNuevo = new JButton("Nuevo");
			btnNuevo.setBounds(196, 22, 74, 23);
			contentPanel.add(btnNuevo);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 176, 204);
		contentPanel.add(scrollPane);
		{
			table = new JTable();
			scrollPane.setViewportView(table);
		}
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setBounds(196, 74, 74, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		textField = new JTextField();
		textField.setBounds(338, 22, 74, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id");
		lblCodigo.setBounds(280, 25, 35, 14);
		contentPanel.add(lblCodigo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(280, 66, 46, 14);
		contentPanel.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(280, 108, 46, 14);
		contentPanel.add(lblClave);
		
		textField_1 = new JTextField();
		textField_1.setBounds(338, 63, 115, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(338, 105, 115, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(199, 119, 74, 23);
		contentPanel.add(btnGuardar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
