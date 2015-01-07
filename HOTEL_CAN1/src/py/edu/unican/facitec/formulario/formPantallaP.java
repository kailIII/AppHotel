package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import py.edu.unican.facitec.entidades.Config;
import py.edu.unican.facitec.informe.InforEstadia;
import py.edu.unican.facitec.informe.InformeCli;
import py.edu.unican.facitec.informe.InformeCobranza;
import py.edu.unican.facitec.informe.InformeHabitacion;
//import py.edu.unican.facitec.informe.InformeHabita;
import py.edu.unican.facitec.informe.InformeServicio;
import py.edu.unican.facitec.interfaz.InterfazConf;
import py.edu.unican.facitec.sesion.SesionConfig;
import py.edu.unican.facitec.sesion.SesionInicializar;

import java.awt.SystemColor;

import javax.swing.JSeparator;
import javax.swing.JLabel;

public class formPantallaP extends JFrame implements InterfazConf{ 

	private JPanel contentPane;
	private SesionConfig sesionConfig;
	private SesionInicializar sesionInicializar;  
	public static Config c;                         

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formPantallaP frame = new formPantallaP();
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
	public formPantallaP() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/Hotel.jpg")));
		
		setTitle("HotelCan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		
		cargarConfig();
		
		try {
			c = SesionConfig.recuperar();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
		setTitle(c.getNombreHotel());
		
		//insertarConfiguracion(); //para luego llamar al metodo configuracion
		
                 
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProceso = new JMenu("Proceso       ");
		mnProceso.setForeground(Color.BLACK);
		mnProceso.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnProceso);
		
		JMenuItem mntmHospedaje = new JMenuItem("Estadia");
		mntmHospedaje.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/estadia.jpg")));
		mntmHospedaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmHospedaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarTablaEstadia();
				
			}
		});
		mnProceso.add(mntmHospedaje);
		
		JMenuItem mntmCobranza = new JMenuItem("Cobranza");
		mntmCobranza.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/cobranza.jpg")));
		mntmCobranza.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmCobranza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaCobranza();
			}
		});
		
		JSeparator separator = new JSeparator();
		mnProceso.add(separator);
		mnProceso.add(mntmCobranza);
		
		JMenu mnTablas = new JMenu("Tablas       ");
		mnTablas.setForeground(Color.BLACK);
		mnTablas.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnTablas);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/cliente.jpg")));
		mntmCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaCliente();
			}
		});
		mnTablas.add(mntmCliente);
		
		JMenuItem mntmHabitacin = new JMenuItem("Habitaci\u00F3n");
		mntmHabitacin.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/habitacion.jpg")));
		mntmHabitacin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmHabitacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaHabitacion();
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		mnTablas.add(separator_1);
		mnTablas.add(mntmHabitacin);
		
		JMenuItem mntmServicio = new JMenuItem("Servicio");
		mntmServicio.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/servicio.jpg")));
		mntmServicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaServicio();
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		mnTablas.add(separator_2);
		mnTablas.add(mntmServicio);
		
		JMenu mnInformes = new JMenu("Informes      ");
		mnInformes.setForeground(Color.BLACK);
		mnInformes.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnInformes);
		
		JMenuItem mntmListaDeCliente = new JMenuItem("Listado de Cliente");
		mntmListaDeCliente.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/listado.jpg")));
		mntmListaDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarListadoCliente();
				
			}
		});
		mntmListaDeCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnInformes.add(mntmListaDeCliente);
		
		JSeparator separator_3 = new JSeparator();
		mnInformes.add(separator_3);
		
		JMenuItem mntmListadoDeHabitacin = new JMenuItem("Listado de Habitaci\u00F3n");
		mntmListadoDeHabitacin.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/listado.jpg")));
		mntmListadoDeHabitacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 mostrarListadoHabitacion();
			}
		});
		mntmListadoDeHabitacin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnInformes.add(mntmListadoDeHabitacin);
		
		JSeparator separator_4 = new JSeparator();
		mnInformes.add(separator_4);
		
		JMenuItem mntmListadoDeServicios = new JMenuItem("Listado de Servicio");
		mntmListadoDeServicios.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/listado.jpg")));
		mntmListadoDeServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 mostrarListadoServicio();
			}
		});
		mntmListadoDeServicios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnInformes.add(mntmListadoDeServicios);
		
		JSeparator separator_5 = new JSeparator();
		mnInformes.add(separator_5);
		
		JMenuItem mntmInformeDeHospedaje = new JMenuItem("Informe de Estadia");
		mntmInformeDeHospedaje.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/listado.jpg")));
		mntmInformeDeHospedaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInformeEstadia();
			}
		});
		mntmInformeDeHospedaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnInformes.add(mntmInformeDeHospedaje);
		
		JSeparator separator_6 = new JSeparator();
		mnInformes.add(separator_6);
		
		JMenuItem mntmInformeDePago = new JMenuItem("Informe de Pago");
		mntmInformeDePago.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/listado.jpg")));
		mntmInformeDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarImformePago();
			}
		});
		mntmInformeDePago.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnInformes.add(mntmInformeDePago);
		
		JMenu mnUtilidades = new JMenu("Utilidades");
		mnUtilidades.setForeground(Color.BLACK);
		mnUtilidades.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnUtilidades);
		
		JMenuItem mntmInicializacinDeDatos = new JMenuItem("Inicializaci\u00F3n de Datos");
		mntmInicializacinDeDatos.setEnabled(false);
		mntmInicializacinDeDatos.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/reset.jpg")));
		mntmInicializacinDeDatos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmInicializacinDeDatos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				mostrarTablaInicializarDatos();
			}
		});
		
		mnUtilidades.add(mntmInicializacinDeDatos);
		
		JMenuItem mntmConfiguraciones = new JMenuItem("Configuraciones");
		mntmConfiguraciones.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/configuracion.jpg")));
		mntmConfiguraciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaConfig();
			}
		});
		mnUtilidades.add(mntmConfiguraciones);
		
		JSeparator separator_7 = new JSeparator();
		mnUtilidades.add(separator_7);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		formFondoP fondoPrincipal = new formFondoP();
		fondoPrincipal.setBounds(10, 34, 1362, 641);
		contentPane.add(fondoPrincipal);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 0, 551, 37);
		contentPane.add(toolBar);
		
		JButton btnHospedaje = new JButton("Estadia");
		btnHospedaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaEstadia();
			}
		});
		
		btnHospedaje.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/IconCliente.jpg")));
		
		btnHospedaje.setForeground(new Color(128, 128, 0));
		btnHospedaje.setFont(new Font("Aparajita", Font.BOLD, 28));
		toolBar.add(btnHospedaje);
		
		JButton btnCobranza = new JButton("Cobranza");
		btnCobranza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaCobranza();
			}
		});
		btnCobranza.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/iconcobranza.jpg")));
		
		btnCobranza.setForeground(new Color(128, 128, 0));
		btnCobranza.setFont(new Font("Aparajita", Font.BOLD, 28));
		toolBar.add(btnCobranza);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaCliente();
			}
		});
		btnCliente.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/iconusuario.png")));
		
		btnCliente.setForeground(new Color(128, 128, 0));
		btnCliente.setFont(new Font("Aparajita", Font.BOLD, 28));
		toolBar.add(btnCliente);
		
		JButton btnSalir = new JButton("Salir    ");
		btnSalir.setIcon(new ImageIcon(formPantallaP.class.getResource("/py/edu/unican/facitec/imagen/liberar-icono-5826-48.png")));
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Aparajita", Font.BOLD, 28));
		btnSalir.setForeground(new Color(128, 128, 0));
		toolBar.add(btnSalir);
	}
	private void insertarConfiguracion() {
		//JOptionPane.showMessageDialog(null, "estoy funcionando");
		
	}

	private void mostrarTablaEstadia(){
		formEstadia menu = new formEstadia();
		menu.consultarAbierto();
		menu.setVisible(true);
	}
	private void mostrarTablaHabitacion(){
		 formHabitacion menu = new formHabitacion();
		 menu.consultar();
		 menu.setVisible(true);
	}
	private void mostrarTablaCobranza(){
		 formCobranza menu = new formCobranza();
		 menu.consultarConDeuda();
		menu.setVisible(true);
	}
	private void mostrarTablaServicio(){
		 formServicio menu = new formServicio();
		 menu.consultar();
		 menu.setVisible(true);
	}
	private void mostrarTablaInicializarDatos(){
		int s=JOptionPane.showConfirmDialog(null, "Esta acci�n eliminara todos los datos del Sistema, desea continuar?", "Advertencia", JOptionPane.YES_NO_OPTION);
		
		if (s==JOptionPane.YES_OPTION) {
			sesionInicializar  = new SesionInicializar();
			sesionInicializar.truncate();
		}
	}
	private void mostrarTablaConfig(){
		FormConfig menu = new FormConfig();
		menu.setMenu(this);
		 menu.setVisible(true);
		 
	}
	
	private void mostrarTablaCliente(){
		
		 FormCliente menu = new FormCliente();
		 menu.consultar();
		 menu.setVisible(true);
		 
	}
	
	private void mostrarListadoCliente(){
		InformeCli menu = new InformeCli();
		 menu.setVisible(true);
	}
	
	private void mostrarListadoHabitacion(){
		InformeHabitacion menu = new InformeHabitacion();
		 menu.setVisible(true);
	}
	
	private void mostrarListadoServicio(){
		InformeServicio menu = new InformeServicio();
		 menu.setVisible(true);
	}
	
	private void mostrarInformeEstadia(){
		InforEstadia menu = new InforEstadia();
		 menu.setVisible(true);
	}
	private void mostrarImformePago(){
		InformeCobranza menu = new InformeCobranza();
		 menu.setVisible(true);
	}
	
	public void cargarConfig() {
		
		try{
			c = SesionConfig.recuperar();
			setTitle(c.getNombreHotel());
			
		}catch (Exception e1){
			e1.printStackTrace();
		}
	}

	@Override
	public void cargar() {
		cargarConfig();
		
	}
	
}
