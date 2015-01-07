package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import net.sf.jasperreports.engine.util.JRStyledText.Run;
import py.edu.unican.facitec.entidades.Config;
import py.edu.unican.facitec.interfaz.InterfazConf;
import py.edu.unican.facitec.sesion.SesionConfig;
import py.edu.unican.facitec.sesion.SesionConfigu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormConfig extends JDialog {

	private JPanel contentPane;
	private JTextField tfNombreHotel;
	private JTextField tfTelefono;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	InterfazConf menu;
	
	public InterfazConf getMenu() {
		return menu;
	}

	public void setMenu(InterfazConf menu) {
		this.menu = menu;
	}


	private SesionConfig sesionConfig;
	private Config c = new Config();

	public FormConfig() {
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("Configuracion");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelHotel = new JLabel("Nombre del Hotel");
		lblNombreDelHotel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDelHotel.setBounds(10, 82, 115, 25);
		contentPane.add(lblNombreDelHotel);
		
		tfNombreHotel = new JTextField();
		tfNombreHotel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfTelefono.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
		});
		tfNombreHotel.setBounds(141, 78, 260, 36);
		contentPane.add(tfNombreHotel);
		tfNombreHotel.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(10, 129, 101, 25);
		contentPane.add(lblTelefono);
		
		tfTelefono = new JTextField();
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnGuardar.requestFocus();
				}
			}
		});
		tfTelefono.setBounds(141, 125, 260, 36);
		contentPane.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				guardarConf();
				
				menu.cargar();
				dispose();
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarConf();
				menu.cargar();
				dispose();
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(85, 203, 89, 36);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimpiarCampos();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(200, 203, 89, 36);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(312, 203, 89, 36);
		contentPane.add(btnSalir);
		cargarConfiguracion();
	}

	public void cargarConfiguracion() {
		try {
			c = SesionConfig.recuperar();
			tfNombreHotel.setText(c.getNombreHotel());
			tfTelefono.setText(c.getTelHotel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void guardarConf() {
		
		c = new Config();
		c.setCodigo(1);
		c.setNombreHotel(tfNombreHotel.getText());
		c.setTelHotel(tfTelefono.getText());
		try { 
			if(SesionConfig.recuperar() != null)
			SesionConfig.delete();
			SesionConfig.guardar(c);
			dispose();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	private void LimpiarCampos() {
		tfNombreHotel.setText("");
		tfTelefono.setText("");
		tfNombreHotel.requestFocus();
	}
	
	
	
		
}
