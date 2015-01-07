package py.edu.unican.facitec.utilidades;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.util.jar.JarFile;

import javax.swing.ImageIcon;

import org.omg.CORBA.TCKind;

import py.edu.unican.facitec.formulario.formPantallaP;

public class Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField tPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Usuario() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Usuario.class.getResource("/py/facitec/imagen/img/cliente.jpg")));
		setTitle("CONEXION AL SISTEMA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 393, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClaveDeUsuario = new JLabel("CLAVE DE USUARIO");
		lblClaveDeUsuario.setBounds(109, 11, 210, 14);
		lblClaveDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblClaveDeUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(144, 55, 129, 20);
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tPass.requestFocus();
					
				}
			}
		});
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tPass = new JPasswordField();
		tPass.setBounds(144, 127, 129, 20);
		tPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()== KeyEvent.VK_ENTER) {
					
					ingresarSistema();
				}
			}
		});
		contentPane.add(tPass);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(69, 56, 111, 14);
		lblUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Clave");
		lblContrasea.setBounds(69, 127, 74, 14);
		lblContrasea.setFont(new Font("Arial Black", Font.PLAIN, 16));
		contentPane.add(lblContrasea);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(109, 205, 89, 23);
		btnIniciar.setMnemonic('I');
		btnIniciar.setFont(new Font("Aharoni", Font.PLAIN, 13));
		btnIniciar.setBackground(Color.GREEN);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ingresarSistema();
			}
		});
		contentPane.add(btnIniciar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 205, 89, 23);
		btnCancelar.setMnemonic('C');
		btnCancelar.setFont(new Font("Aharoni", Font.PLAIN, 13));
		btnCancelar.setBackground(Color.GREEN);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		contentPane.add(btnCancelar);
	}
	

	private void ingresarSistema(){
		String usuario;
		String password;
		
		usuario= tfUsuario.getText();
		password= new String(tPass.getPassword());
		
				
		if ((usuario.equals("NimioEver")&& password.equals("12345") )){
			
			formPantallaP menu = new formPantallaP();
			
			menu.setVisible(true);
			
			dispose();
			}else{	
				
           JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrecta");
		}
	}
}

