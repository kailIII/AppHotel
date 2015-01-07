package py.edu.unican.facitec.formulario;


import java.awt.BorderLayout;
import py.edu.unican.facitec.utilidades.*;
import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.entidades.Deuda;
import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.entidades.Servicio;
import py.edu.unican.facitec.entidades.Detalle;
import py.edu.unican.facitec.entidades.Estadia;
import py.edu.unican.facitec.entidades.Servicio;
import py.edu.unican.facitec.interfaz.BuscarCliente;
import py.edu.unican.facitec.interfaz.BuscarHabitacion;
import py.edu.unican.facitec.interfaz.BuscarServicio;
import py.edu.unican.facitec.sesion.SesionEstadia;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

           @SuppressWarnings("serial")
    public class formEstadia extends JDialog implements BuscarCliente,BuscarHabitacion,BuscarServicio {
    private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private static Integer MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
    private Estadia estadia;
    private Detalle detalle;
    private Deuda deuda;
	private JFormattedTextField tfFechaEntrada; // para un formato de fecha..
	private JFormattedTextField tfFechaSalida;
	private MaskFormatter formatoFechaEntrada;
	private MaskFormatter formatoFechaSalida;
	private MaskFormatter formatoFecha;
	private ArrayList<Estadia> lista;
	private ArrayList<Detalle> list;
	private JTextField tfNroEstadia;
	private JTextField tfCodCliente;
	private JTextField tfCodHabitacion;
	private JTextField tfMontoTotalCierre;
	private JTextField tfBuscar;
	private JTextField tfObsEstadia;
	private DefaultTableModel modeloTabla;
	private static final long serialVersionUID = 1L;
	private final JPanel contenPanel = new JPanel();
	private DefaultTableModel dtmEstadia = new DefaultTableModel(null, new String[]{"Nro.Estadía","Cliente","Habitación"});
	private DefaultTableModel dtmServicio = new DefaultTableModel(null, new String[]{"Nro.Detalle","Nro.Estadía","Servicio","Monto"});
     ;//declaramos un array de tipo Estadia
	private Boolean modoAgregar = true;    
	private String abrir;
	private JTable tableEstadia;
	private JScrollPane scrollPane_1;
	private JTable tableDetalle;
	private JPanel panel_2;
	private JTextField tfActivo;
	private JTextField tfMontoServicio;
	private JTextField tfMontoTotalServicio;
	private JTextField tfNroServicio;
	private JTextField tfServicioCierre;
	private JTextField tfMontoDiariaCierre;
	private JTextField tfDescuento;
	private JTextField tfBuscarDetalle;
	private  TextField tfNroDetalle;
	private JLabel  lblCampoNumerico;
	private JLabel  lblCampoNumerico_1;
	private JPanel  contentPane;
	private JLabel  lblServicioCierre;
	private JLabel  MontoDiaria;
	private JLabel  lblDescuento;
	private JLabel  lblFechaEntrada;
	private JLabel  lblServicioDetalle;
	private JLabel  lblMontoTotal_1;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnSalir;
	private JButton btnGuardarEstadia;
	private JButton btnCancelarEstadia;
	private JButton btnCierre;
	private JButton btnDetalle;
	private JButton btnGuardarDetalle;
	private JButton btnCancelarDetalle;
	private JButton btnEliminarDetalle;
	private JButton btnGuardarCierre;
	private JButton btnCancelarCierre;
	private JButton btnSalir_1;
	private JButton btnBuscarCliente;
	private JButton btnBuscarHabitacion;
	private JButton btnBuscarServicio;
	private JTextField tfSubTotal;
	//private JComboBox   cbOrden;
	private String dato = "0";
	private JTextField tfhabactivo;
	private JTextField tfNrodeuda;
	private JTextField tfhab;
	
	public formEstadia() {
		setVisible(true);
		setTitle("Estad\u00EDa");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
                      nuevo();
                      tfFechaEntrada.setText(Utilidad.getFechaActual());
                      limpiarCamposDetalle();
                      tfMontoTotalServicio.setText("");
                      consultarDetalle();
                      btnModificar.setEnabled(false);
                      btnEliminar.setEnabled(false);
                      btnCierre.setEnabled(false);
                      btnDetalle.setEnabled(false);
                      btnGuardarEstadia.setText("Guardar");
                      tableEstadia.setEnabled(false);
                      tfNroDetalle.setText("");
                      tfBuscarDetalle.setText("");
                      consultarAbierto();
                      consultarDetalle();
			}
	    });
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNuevo.setBounds(315, 11, 84, 30);
		contentPane.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				irParaModificar();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModificar.setBounds(315, 52, 84, 30);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				if (tfActivo.getText().equals("0") ) {
		    		JOptionPane.showMessageDialog(null, " No se puede Eliminar Estadía, ya cuenta con un Cierre!", "aviso ",1 );
		     }else{ 
				borrarEstadia();
			}
		  }
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminar.setBounds(315, 93, 84, 30);
		contentPane.add(btnEliminar);
		
		btnDetalle = new JButton("Detalle");
		btnDetalle.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				if (tfNroEstadia.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "se debe seleccionar primero un Estadía", "aviso",1);
			  }else{
					if (tfActivo.getText().equals("0") ) {
			    		
			    		JOptionPane.showMessageDialog(null, "No se puede insertar Detalle, ya cuenta con un Cierre!", "aviso",1 );
			   
			  }else{	
				habilitarCamposDetalle();
				consultarAbierto();
				mostrarButtonDetalle();
				deshabilitarCamposCierre();
				ocultarButtonCierre();
				deshabilitarCamposEstadia();
				ocultarButtonEstadia();
				tableEstadia.setEnabled(false);
			    btnNuevo.setEnabled(false);
			    btnModificar.setEnabled(false);
	            btnEliminar.setEnabled(false);
	            btnCierre.setEnabled(false);
	            tableEstadia.setEnabled(false);
		   }
		  }
		 }	
		});
		btnDetalle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDetalle.setBounds(315, 134, 84, 30);
		contentPane.add(btnDetalle);
		
		btnCierre = new JButton("Cierre");
		btnCierre.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
            if (tfNroEstadia.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "se debe seleccionar primero un estadía", "aviso",1);
					
			  }else{
					
		    if (tfActivo.getText().equals("0") ) {
				    		
				    		JOptionPane.showMessageDialog(null, "La estadía seleccionada ya cuenta con un cierre", "aviso",1 );
			  }else{
				 ultimoCodigoDeuda();
				habilitarCamposCierre();
				mostrarButtonCierre();
				tfFechaSalida.setText(Utilidad.getFechaActual());
				cargarCierre();
				tfFechaSalida.requestFocus();
				tableEstadia.setEnabled(false);
				GenerarDescuentoCierre();
				consultarAbierto();
				
				ocultarButtonDetalle();
				deshabilitarCamposDetalle();
				ocultarButtonEstadia();
				deshabilitarCamposEstadia();
				
				btnNuevo.setEnabled(false);
		    	btnModificar.setEnabled(false);
	            btnEliminar.setEnabled(false);
	            btnDetalle.setEnabled(false);
                btnCancelarEstadia.setVisible(false);
		      }
			}
		  }
		});
		btnCierre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCierre.setBounds(315, 175, 84, 30);
		contentPane.add(btnCierre);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setFont(new Font("Dialog", Font.ITALIC, 12));
		panel.setBounds(405, 0, 365, 204);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNroEstada = new JLabel("Nro Estad\u00EDa");
		lblNroEstada.setBounds(10, 11, 84, 22);
		lblNroEstada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNroEstada);
		
		tfNroEstadia = new JTextField();
		tfNroEstadia.setEditable(false);
		tfNroEstadia.setBounds(110, 11, 84, 24);
		tfNroEstadia.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
				buscarEstadiaPorNro();
			}
		});
		tfNroEstadia.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent arg0) {
				
		  }
		});
		tfNroEstadia.setColumns(10);
		panel.add(tfNroEstadia);
		
		lblFechaEntrada = new JLabel("Fecha Entrada");
		lblFechaEntrada.setBounds(10, 46, 90, 22);
		lblFechaEntrada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblFechaEntrada);
		
		tfFechaEntrada = new JFormattedTextField(formatearFecha());
		tfFechaEntrada.setEditable(false);
		tfFechaEntrada.setBounds(110, 46, 84, 24);
		tfFechaEntrada.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnBuscarCliente.requestFocus();
				}
			}
		});
		tfFechaEntrada.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				
			}
		});
		tfFechaEntrada.setColumns(10);
		panel.add(tfFechaEntrada);
		
		JLabel lblCdigoHabitacin = new JLabel("C\u00F3d Habitaci\u00F3n");
		lblCdigoHabitacin.setBounds(10, 116, 96, 22);
		lblCdigoHabitacin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblCdigoHabitacin);
		
		tfCodHabitacion = new JTextField();
		tfCodHabitacion.setEditable(false);
		tfCodHabitacion.setBounds(110, 116, 111, 24);
		tfCodHabitacion.addKeyListener(new KeyAdapter() {
			@Override
	 public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfObsEstadia.requestFocus();
			}
		  }
		});
		tfCodHabitacion.setColumns(10);
		panel.add(tfCodHabitacion);
		
		JLabel lblObsEstadia = new JLabel("Obs Estad\u00EDa ");
		lblObsEstadia.setBounds(10, 150, 84, 22);
		lblObsEstadia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblObsEstadia);
		
		tfObsEstadia = new JTextField();
		tfObsEstadia.setEditable(false);
		tfObsEstadia.setBounds(110, 147, 234, 31);
		tfObsEstadia.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnGuardarEstadia.requestFocus();
					
				}
			}
		});
		panel.add(tfObsEstadia);
		tfObsEstadia.setColumns(10);
		
		tfCodCliente = new JTextField();
		tfCodCliente.setEditable(false);
		tfCodCliente.setBounds(110, 81, 111, 24);
		panel.add(tfCodCliente);
		tfCodCliente.addKeyListener(new KeyAdapter() {
			
			@Override
	public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
				if(tfCodCliente.getText().trim().length()==0){
					       JOptionPane.showMessageDialog(null, "El campo de texto no puede están vacío");
					       tfCodCliente.requestFocus();
			     }
				 else{
					       
					       tfCodHabitacion.requestFocus();
				}
			  }
			}
			@Override
	public void keyTyped(KeyEvent e) {
				
				if ((e.getKeyChar()<'0' || e.getKeyChar()>'9')&&(e.getKeyChar() != KeyEvent.VK_BACK_SPACE)&&(e.getKeyChar() != KeyEvent.VK_ENTER)){
					
					e.consume();
					
					 JOptionPane.showMessageDialog(null, "El campo de texto tiene que ser numerico" );
				}else {	
			  }
			}
			
		});
		tfCodCliente.setColumns(10);
		
		JLabel lblCdigoCliente = new JLabel("C\u00F3digo Cliente");
		lblCdigoCliente.setBounds(10, 79, 96, 22);
		panel.add(lblCdigoCliente);
		lblCdigoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnGuardarEstadia = new JButton("Guardar");
		btnGuardarEstadia.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if(tfCodCliente.getText().trim().length()==0)
				 {
				       JOptionPane.showMessageDialog(null, "El campo de texto no puede estár vacío");
				       tfCodCliente.requestFocus();
				 }
				 else{
					 
					 if(btnGuardarEstadia.getText().equals("Guardar")){
							if(validarHabitacion()==1){
								guardarEstadia();
								deshabilitarCamposEstadia();
								habilitarCodigo();
							}
							
						}else{
							if(btnGuardarEstadia.getText().equals("actualizar")){
								if (Integer.parseInt(tfhab.getText()) == Integer.parseInt(tfCodHabitacion.getText())) {
									modificarEstadia();
									
								}else
									
							   if(validarHabitacion()==1){
								
							    modificarEstadia();
							    
					     }
					   }	
			         }
				   }
			     }
		});
		btnGuardarEstadia.setVisible(false);
		btnGuardarEstadia.setBounds(110, 182, 90, 22);
		panel.add(btnGuardarEstadia);
		btnGuardarEstadia.addActionListener(new ActionListener() {
			
	public void actionPerformed(ActionEvent e) {
				
		if(tfCodCliente.getText().trim().length()==0)
		 {
		       JOptionPane.showMessageDialog(null, "El campo de texto no puede estár vacío");
		       tfCodCliente.requestFocus();
		 }
		 else{
			 
			 if(btnGuardarEstadia.getText().equals("Guardar")){
					if(validarHabitacion()==1){
						guardarEstadia();
						deshabilitarCamposEstadia();
						habilitarCodigo();
					}
					
				}else{
					if(btnGuardarEstadia.getText().equals("actualizar")){
						if (Integer.parseInt(tfhab.getText()) == Integer.parseInt(tfCodHabitacion.getText())) {
							modificarEstadia();
							
						}else
							
					   if(validarHabitacion()==1){
						
					    modificarEstadia();
					    
			     }
			   }	
	         }
		   }
	     }
		});
		btnGuardarEstadia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnCancelarEstadia = new JButton("Cancelar");
		btnCancelarEstadia.setVisible(false);
		btnCancelarEstadia.setBounds(210, 182, 89, 22);
		panel.add(btnCancelarEstadia);
		btnCancelarEstadia.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				limpiarCamposEstadia();
				deshabilitarCamposEstadia();
				LimpiarCodigo();
				ocultarButtonDetalle();
				tfMontoTotalServicio.setText("");
				//habilitarCodigo();
				ocultarButtonEstadia();
				limpiarCamposDetalle();
				tfBuscarDetalle.setText("");
				limpiarCodigoDetalle();
				tableEstadia.setEnabled(true);
				 btnNuevo.setEnabled(true);
			   	btnModificar.setEnabled(true);
	            btnEliminar.setEnabled(true);
	            btnCierre.setEnabled(true);
                btnDetalle.setEnabled(true);
                consultarDetalle();
	            consultarAbierto();
				  //deshabilitarCampos();
	            deshabilitarCamposDetalle();
		  }
		});
		btnCancelarEstadia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnBuscarCliente = new JButton("+");
		btnBuscarCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				verbuscaCliente();
			}
		});
		btnBuscarCliente.setVisible(false);
		btnBuscarCliente.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				verbuscaCliente();
		  }
		});
		btnBuscarCliente.setBounds(231, 82, 41, 23);
		panel.add(btnBuscarCliente);
		
		btnBuscarHabitacion = new JButton("+");
		btnBuscarHabitacion.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				verbuscaHabitacion();
			}
		});
		btnBuscarHabitacion.setVisible(false);
		btnBuscarHabitacion.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				verbuscaHabitacion();
			}
		});
		btnBuscarHabitacion.setBounds(231, 117, 41, 23);
		panel.add(btnBuscarHabitacion);
		
		tfhabactivo = new JTextField();
		tfhabactivo.setVisible(false);
		tfhabactivo.setBounds(284, 117, 41, 20);
		panel.add(tfhabactivo);
		tfhabactivo.setColumns(10);
		
		tfhab = new JTextField();
		tfhab.setVisible(false);
		tfhab.setBounds(269, 13, 55, 20);
		panel.add(tfhab);
		tfhab.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(20, 491, 46, 20);
		contentPane.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				consultarPorFiltro();
				//consultarTodos();
				
			}
		});
		tfBuscar.setColumns(10);
		tfBuscar.setBounds(72, 487, 233, 32);
		contentPane.add(tfBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 11, 295, 477);
		contentPane.add(scrollPane);
		
		tableEstadia = new JTable(dtmEstadia){
	private static final long serialVersionUID = 1L;
	public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
		};
		tableEstadia.addMouseListener(new MouseAdapter() {
	public void mouseClicked(MouseEvent e) {
				if (modoAgregar){
					setAbrir("N");
					 
					seleccionarRegistroTablaEstadia(abrir);		
					
			 }	
		  }
		});
		scrollPane.setColumnHeaderView(tableEstadia);
		scrollPane.setViewportView(tableEstadia);
		tableEstadia.setModel(dtmEstadia);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(328, 207, 442, 180);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(93, 42, 349, 102);
		panel_1.add(scrollPane_1);
		
		
		tableDetalle = new JTable(dtmServicio );
		tableDetalle.setEnabled(false);
		tableDetalle.addMouseListener(new MouseAdapter() {
	public void mouseClicked(MouseEvent e) {
				
				if (modoAgregar){
					setAbrir("N");
					 
					seleccionarRegistroTablaDetalle(abrir);		
					
			}
			}
		});
		scrollPane_1.setColumnHeaderView(tableDetalle);
		scrollPane_1.setViewportView(tableDetalle);
		tableDetalle.setModel(dtmServicio );
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMonto.setBounds(268, 14, 46, 14);
		panel_1.add(lblMonto);
		
		tfMontoServicio = new JTextField();
		tfMontoServicio.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnGuardarDetalle.requestFocus();
				}
			}
			@Override
	public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar()<'0' || e.getKeyChar()>'9')&&(e.getKeyChar() != KeyEvent.VK_BACK_SPACE)&&(e.getKeyChar() != KeyEvent.VK_ENTER)){
					e.consume();		
		  }
		 }
		});
		tfMontoServicio.setEditable(false);
		tfMontoServicio.setBounds(300, 11, 89, 20);
		panel_1.add(tfMontoServicio);
		tfMontoServicio.setColumns(10);
		
		lblMontoTotal_1 = new JLabel("Monto Total");
		lblMontoTotal_1.setBounds(258, 158, 77, 14);
		panel_1.add(lblMontoTotal_1);
		
		tfMontoTotalServicio = new JTextField();
		tfMontoTotalServicio.setEditable(false);
		tfMontoTotalServicio.setBounds(331, 155, 111, 20);
		panel_1.add(tfMontoTotalServicio);
		tfMontoTotalServicio.setColumns(10);
		
		lblServicioDetalle = new JLabel("Servicio");
		lblServicioDetalle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblServicioDetalle.setBounds(93, 17, 53, 14);
		panel_1.add(lblServicioDetalle);
		
		tfNroServicio = new JTextField();
		tfNroServicio.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfMontoServicio.requestFocus();
				}
			}
		});
		tfNroServicio.setEditable(false);
		tfNroServicio.setBounds(133, 14, 69, 20);
		panel_1.add(tfNroServicio);
		tfNroServicio.setColumns(10);
		
		JLabel lblNroEstadia = new JLabel("Nro  Estad\u00EDa");
		lblNroEstadia.setVisible(false);
		lblNroEstadia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNroEstadia.setBounds(25, 42, 58, 17);
		panel_1.add(lblNroEstadia);
		
		btnGuardarDetalle = new JButton("Guardar");
		btnGuardarDetalle.addKeyListener(new KeyAdapter() {

			
			@Override
	public void keyPressed(KeyEvent e) {
				if(tfNroServicio.getText().trim().length()==0)
				 {
				       JOptionPane.showMessageDialog(null, "El campo codigo Servicio no puede estár vacío");
				       tfNroServicio.requestFocus();
				 }
				
				 else{
					 
				if (formEstadia.this.btnGuardarDetalle.getText().compareTo("Guardar")==0) {
					
					
					guardarDetalle();
					//LimpiarCampos();
					deshabilitarCamposDetalle();
					habilitarCodigo();
					
				}else {
					
					modificarDetalle();
				}
			     	}
			}
		});
		btnGuardarDetalle.setVisible(false);
		btnGuardarDetalle.addActionListener(new ActionListener() {
			
	public void actionPerformed(ActionEvent e) {
				if(tfNroServicio.getText().trim().length()==0){
				       JOptionPane.showMessageDialog(null, "El campo codigo Servicio no puede estár vacío");
				       tfNroServicio.requestFocus();
				 }
				if(tfMontoServicio.getText().trim().length()==0){
				       JOptionPane.showMessageDialog(null, "El campo Monto Servicio no puede estár vacío");
				       tfMontoServicio.requestFocus();
				 }
				if (tfMontoServicio.getText().equals("0")){
					JOptionPane.showMessageDialog(null, " Monto no puede ser igual a '0' ");
					tfMontoServicio.requestFocus();
			}else{
					 
				if (formEstadia.this.btnGuardarDetalle.getText().compareTo("Guardar")==0) {	
						guardarDetalle();
						//LimpiarCampos();
						deshabilitarCamposDetalle();
						habilitarCodigo();
					
			      }	
				}
			}
		});
		btnGuardarDetalle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuardarDetalle.setBounds(6, 58, 77, 22);
		panel_1.add(btnGuardarDetalle);
		
		btnCancelarDetalle = new JButton("Cancelar");
		btnCancelarDetalle.setVisible(false);
		btnCancelarDetalle.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				tfMontoServicio.setText(""); //le damos el valor vacio
				tfNroServicio.setText("");
				    btnNuevo.setEnabled(true);
					btnModificar.setEnabled(true);
	                btnEliminar.setEnabled(true);
	                btnCierre.setEnabled(true);
	                btnDetalle.setEnabled(true);
	                tableEstadia.setEnabled(true);
	                btnCancelarEstadia.setVisible(true);
	                ocultarButtonDetalle();
	                consultarDetalle();
	                tfNroServicio.setEditable(false);
	                tfMontoServicio.setEditable(false);
	                btnBuscarServicio.setVisible(false);
	            tableDetalle.setEnabled(false);
				tfNroServicio.requestFocus();
			}
		});
		btnCancelarDetalle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelarDetalle.setBounds(6, 91, 77, 22);
		panel_1.add(btnCancelarDetalle);
		
		tfNroDetalle = new TextField();
		tfNroDetalle.setVisible(false);
		tfNroDetalle.setEditable(false);
		tfNroDetalle.setBounds(12, 14, 18, 22);
		panel_1.add(tfNroDetalle);
		
		 btnBuscarServicio = new JButton("+");
		 btnBuscarServicio.setVisible(false);
		btnBuscarServicio.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				/* formServici menu = new formServici();
				 menu.consultar();
				 menu.setVisible(true);*/
				verbuscaServicio();
			}
		});
		btnBuscarServicio.setBounds(203, 14, 43, 21);
		panel_1.add(btnBuscarServicio);
		
		btnEliminarDetalle = new JButton("Eliminar");
		btnEliminarDetalle.setVisible(false);
		btnEliminarDetalle.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				 borrarDetalle();
			}
		});
		btnEliminarDetalle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminarDetalle.setBounds(6, 124, 77, 20);
		panel_1.add(btnEliminarDetalle);
		
		tfBuscarDetalle = new JTextField();
		tfBuscarDetalle.setVisible(false);
		tfBuscarDetalle.setBounds(191, 155, 33, 20);
		panel_1.add(tfBuscarDetalle);
		tfBuscarDetalle.setColumns(10);
		
		JLabel lblCampoNumerico_1 = new JLabel("Campo Numerico");
		lblCampoNumerico_1.setVisible(false);
		lblCampoNumerico_1.setForeground(Color.RED);
		lblCampoNumerico_1.setBounds(302, 0, 87, 14);
		panel_1.add(lblCampoNumerico_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(326, 388, 444, 165);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(135, 206, 235));
		panel_2.setLayout(null);
		
		JLabel lblFechaSalida = new JLabel("Fecha Cierre");
		lblFechaSalida.setBounds(129, 2, 84, 22);
		panel_2.add(lblFechaSalida);
		lblFechaSalida.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblMontoTotal = new JLabel("Monto Total");
		lblMontoTotal.setBounds(129, 138, 84, 17);
		panel_2.add(lblMontoTotal);
		lblMontoTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblActivo = new JLabel("Activo");
		lblActivo.setVisible(false);
		lblActivo.setBounds(369, 15, 46, 14);
		panel_2.add(lblActivo);
		lblActivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfActivo = new JTextField();
		tfActivo.setVisible(false);
		tfActivo.setBounds(369, 40, 46, 22);
		panel_2.add(tfActivo);
		tfActivo.setColumns(10);
		
		tfMontoTotalCierre = new JTextField();
		tfMontoTotalCierre.setEditable(false);
		tfMontoTotalCierre.setBounds(223, 138, 122, 17);
		panel_2.add(tfMontoTotalCierre);
		tfMontoTotalCierre.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfObsEstadia.requestFocus();
				}
			}
		});
		
		tfMontoTotalCierre.setColumns(10);
		
		tfFechaSalida = new JFormattedTextField(formatearFecha());
		tfFechaSalida.setEditable(false);
		tfFechaSalida.setBounds(223, 3, 76, 17);
		panel_2.add(tfFechaSalida);
		tfFechaSalida.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					cerrar1();
					
					
				}
			}
		});
		tfFechaSalida.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
			}
		});
		
		tfFechaSalida.setColumns(10);
		
		lblServicioCierre = new JLabel("Servicio");
		lblServicioCierre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblServicioCierre.setBounds(129, 66, 46, 14);
		panel_2.add(lblServicioCierre);
		
		tfServicioCierre = new JTextField();
		tfServicioCierre.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfDescuento.requestFocus();
				}
			
			}
		});
		
		tfServicioCierre.setEditable(false);
		tfServicioCierre.setBounds(223, 59, 122, 17);
		panel_2.add(tfServicioCierre);
		tfServicioCierre.setColumns(10);
		
		tfMontoDiariaCierre = new JTextField();
		tfMontoDiariaCierre.addKeyListener(new KeyAdapter() {
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfServicioCierre.requestFocus();
				}
			
			}
		});
		
		tfMontoDiariaCierre.setEditable(false);
		tfMontoDiariaCierre.setColumns(10);
		tfMontoDiariaCierre.setBounds(223, 31, 95, 17);
		panel_2.add(tfMontoDiariaCierre);
		
		MontoDiaria = new JLabel("Monto Diaria");
		MontoDiaria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MontoDiaria.setBounds(129, 35, 84, 17);
		panel_2.add(MontoDiaria);
		
		tfDescuento = new JTextField();
		tfDescuento.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){
					GenerarDescuento();
				}
			
			}
		});
		tfDescuento.setEditable(false);
		tfDescuento.setColumns(10);
		tfDescuento.setBounds(223, 110, 122, 17);
		panel_2.add(tfDescuento);
		
		lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDescuento.setBounds(129, 110, 84, 17);
		panel_2.add(lblDescuento);
		
		btnGuardarCierre = new JButton("Guardar");
		btnGuardarCierre.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				cerrar();
				ocultarButtonCierre();
				
			}

		});
		btnGuardarCierre.setVisible(false);
		btnGuardarCierre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuardarCierre.setBounds(10, 27, 80, 22);
		panel_2.add(btnGuardarCierre);
		
		btnCancelarCierre = new JButton("Cancelar");
		btnCancelarCierre.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				limpiarCamposCierre();
				ocultarButtonCierre();
			}
		});
		btnCancelarCierre.setVisible(false);
		btnCancelarCierre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelarCierre.setBounds(10, 60, 80, 22);
		panel_2.add(btnCancelarCierre);
		
		btnSalir_1 = new JButton("Salir");
		btnSalir_1.setBounds(355, 119, 76, 25);
		panel_2.add(btnSalir_1);
		btnSalir_1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfSubTotal = new JTextField();
		tfSubTotal.setEditable(false);
		tfSubTotal.setColumns(10);
		tfSubTotal.setBounds(223, 83, 122, 17);
		panel_2.add(tfSubTotal);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSubTotal.setBounds(129, 85, 76, 14);
		panel_2.add(lblSubTotal);
		
		tfNrodeuda = new JTextField();
		tfNrodeuda.setVisible(false);
		tfNrodeuda.setBounds(369, 74, 30, 20);
		panel_2.add(tfNrodeuda);
		tfNrodeuda.setColumns(10);
		
		JButton btnTodos = new JButton("Todos");
		btnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarTodos();
			}
		});
		btnTodos.setBounds(216, 528, 89, 23);
		contentPane.add(btnTodos);
		
		JButton btnAbierto = new JButton("Abierto");
		btnAbierto.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				consultarAbierto();
			}
		});
		btnAbierto.setBounds(123, 528, 89, 23);
		contentPane.add(btnAbierto);
		
		JButton btnCerrado = new JButton("cerrado");
		btnCerrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarCerrado();
			}
		});
		btnCerrado.setBounds(30, 530, 89, 23);
		contentPane.add(btnCerrado);
	   }
	
	private void habilitarCamposEstadia() {
		tfFechaEntrada.setEditable(true);
		tfCodCliente.setEditable(true);
		tfCodHabitacion.setEditable(true);
		tfFechaSalida.setEditable(true);
		tfMontoTotalCierre.setEditable(true);
		tfObsEstadia.setEditable(true);
	  }
	
    private void deshabilitarCamposEstadia() {
		tfNroEstadia.setEditable(false);
		tfFechaEntrada.setEditable(false);
		tfCodCliente.setEditable(false);
		tfCodHabitacion.setEditable(false);
		//tfFechaSalida.setEnabled(false);
		//tfMontoTotal.setEnabled(false);
		tfObsEstadia.setEditable(false);
		
		
	}
	
	private void habilitarCamposDetalle() {
		tfMontoServicio.setEditable(true);
		//tfNroServicio.setEnabled(true);
		btnBuscarServicio.setEnabled(true);
		btnCancelarDetalle.setEnabled(true);
		btnGuardarDetalle.setEnabled(true);
		btnEliminarDetalle.setEnabled(true);
		tableDetalle.setEnabled(true);
		//tfNroServicio.requestFocus();
	
	}
	
	
	 private void deshabilitarCamposDetalle() {
			
			
		    tfMontoServicio.setEditable(false);
			tfNroServicio.setEditable(false);
			btnBuscarServicio.setEnabled(false);
			btnCancelarDetalle.setEnabled(false);
			btnGuardarDetalle.setEnabled(false);
			btnEliminarDetalle.setEnabled(false);
			tableDetalle.setEnabled(false);
			
		}
	 
	 private void habilitarCamposCierre() {
			//tfMontoTotalCierre.setEditable(true);
			tfFechaSalida.setEditable(true);
			tfDescuento.setEditable(true);
			//tfServicioCierre.setEditable(true);
			//tfMontoDiariaCierre.setEditable(true);
			//tfSubTotal.setEditable(true);
		
		}
		
		
	 private void deshabilitarCamposCierre() {
				
				
			    tfFechaSalida.setEditable(false);
				tfMontoDiariaCierre.setEditable(false);
				tfMontoTotalCierre.setEditable(false);
				tfDescuento.setEditable(false);
				tfServicioCierre.setEditable(false);
				
			}
		
	
	
	 private void habilitarCodigo() {
		
	        	tfNroEstadia.setEditable(true);
	         	tfCodCliente.requestFocus();
			
			}
	 private void deshabilitarCodigo() {
				
				tfNroEstadia.setEnabled(false);
				//tfFechaEntrada.requestFocus();
			}
			
	 private void LimpiarCodigo() {
				//le damos el valor vacio
				
	              tfNroEstadia.setText("");
			}
			//metodo "limpiar campos"
	 private void limpiarCamposEstadia() {
				//limpiamos los campos de textos
				tfCodCliente.setText(""); //le damos el valor vacio
				tfCodHabitacion.setText("");
				tfFechaEntrada.setText("");
				tfFechaSalida.setText("");
				tfMontoTotalCierre.setText("");
				tfNroEstadia.setText("");
				tfObsEstadia.setText("");
				

			}
	  private void limpiarCamposDetalle() {
				//limpiamos los campos de textos
				tfMontoServicio.setText(""); //le damos el valor vacio
				tfNroServicio.setText("");
				//tfBuscarDetalle.setText("");
				//tfNroEstadia2.setText("");
				//tfNroDetalle.setText("");

			}
	  private void limpiarCamposCierre() {
				//limpiamos los campos de textos
				tfFechaSalida.setText(""); //le damos el valor vacio
				tfMontoDiariaCierre.setText("");
				tfServicioCierre.setText("");
				tfDescuento.setText("");
				tfSubTotal.setText("");
				tfMontoTotalCierre.setText("");
				tfFechaSalida.requestFocus();
				
				deshabilitarCamposCierre();
				tableEstadia.setEnabled(true);
				    btnNuevo.setEnabled(true);
					btnModificar.setEnabled(true);
	                btnEliminar.setEnabled(true);
	                btnCierre.setEnabled(true);
	                btnDetalle.setEnabled(true);
				    btnCancelarEstadia.setVisible(true);

			}
	 private void guardarEstadia() {			     
				estadia = new Estadia(); //creamos el objeto en memoria //instanciar una clase
				
			    estadia.setCodigo(Integer.parseInt(tfNroEstadia.getText()));//conversion de string a int > integer.parseInt(String);
			    estadia.setFechaEntrada(Utilidad.retornarFecha(tfFechaEntrada.getText()));
			    estadia.getCodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
			    estadia.getCodHabitacion().setCodigo(Integer.parseInt(tfCodHabitacion.getText()));
			    estadia.setObservacion(tfObsEstadia.getText());
			    estadia.setActivoInActivo(1);
			 // estadia.setCantidad(Double.valueOf(tfCantidad.getText())); //conversion de string a Decimal > Double.valueof
			    
			     	
			    
			    try{ //pasa por aca en caso q no exista un error por el camino
			    	//le pasamos el objeto con los valores recivido del formulario del libros
			    	
			    	
			    SesionEstadia.guardarEstadia(estadia);//
			    
			    limpiarCamposEstadia() ; 
			    deshabilitarCamposEstadia();
			     ocultarButtonEstadia();
			        btnNuevo.setEnabled(true);
					btnModificar.setEnabled(true);
	                btnEliminar.setEnabled(true);
	                btnCierre.setEnabled(true);
	                btnDetalle.setEnabled(true);
				tfNroEstadia.requestFocus();
				tableEstadia.setEnabled(true);
				consultarAbierto();
			    } catch (Exception e ){ //pasa por aca en caso q haya un error 
			    	e.printStackTrace();//imprime por consola el error ocurrido
			    }
			}
			
	  private MaskFormatter formatearFecha() {
				if (formatoFechaEntrada==null) {
					try {
						formatoFechaEntrada = new MaskFormatter("##/##/####");
						formatoFechaEntrada.setPlaceholderCharacter('_');
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					
					
				}
			return formatoFechaEntrada;
			}
	  private void guardarDetalle() {			     
				detalle = new Detalle(); //creamos el objeto en memoria //instanciar una clase
				
			    detalle.setCodigo(Integer.parseInt(tfNroDetalle.getText()));
			    detalle.getCodServicio().setCodigo(Integer.parseInt(tfNroServicio.getText()));
			    detalle.getNumeroEstadia().setCodigo(Integer.parseInt(tfNroEstadia.getText()));
			    detalle.setMonto(Integer.parseInt(tfMontoServicio.getText()));
			    
			    try{ 
			    	
			    SesionEstadia.guardarDetalle(detalle);//
			    consultarDetalle();
			    limpiarCamposDetalle(); 
			    ocultarButtonDetalle();
			    nuevoCodigoDetalle();
			    btnNuevo.setEnabled(true);
				btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCierre.setEnabled(true);
                btnDetalle.setEnabled(true);
                tableEstadia.setEnabled(true);
                btnCancelarEstadia.setVisible(true);
                
			    } catch (Exception e ){ 
			    	e.printStackTrace();
			    }

			}
			
	  private void modificarEstadia() {
				
			    deshabilitarCodigo();
				if (tfCodCliente.getText().equals(" ") || tfCodHabitacion.getText().equals(" ")) {
					
					JOptionPane.showMessageDialog (null," Campos codigo del cliente y codigo de la habitacion son obligatorios ");
				}
				
				else {
				estadia = new Estadia();
				
				estadia.setCodigo(Integer.parseInt(tfNroEstadia.getText()));
				estadia.setFechaEntrada(Utilidad.retornarFecha(tfFechaEntrada.getText()));
				estadia.getCodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
				estadia.getCodHabitacion().setCodigo(Integer.parseInt(tfCodHabitacion.getText()));
				estadia.setObservacion(tfObsEstadia.getText());
				estadia.setActivoInActivo(1);
				
								try { 
					 SesionEstadia.modificarEstadia(estadia);
					
				    	btnGuardarEstadia.setText("Guardar");
					
				        	deshabilitarCamposEstadia();
				             limpiarCamposEstadia();
				             ocultarButtonEstadia();
				             tableEstadia.setEnabled(true);
				                btnNuevo.setEnabled(true);
								btnModificar.setEnabled(true);
				                btnEliminar.setEnabled(true);
				                btnCierre.setEnabled(true);
				                btnDetalle.setEnabled(true);
					           tfNroEstadia.requestFocus();
					           consultarAbierto();
					           
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				}
				
			}
	  private void modificarDetalle() {
				
			    deshabilitarCodigo();
				btnGuardarEstadia.setText("actualizar");
				tfCodCliente.requestFocus();
				if (tfCodCliente.getText().equals(" ") || tfCodHabitacion.getText().equals(" ")) {
					JOptionPane.showMessageDialog (null," Campos codigo del cliente y codigo de la habitacion son obligatorios ");
				}
				else {	
				estadia = new Estadia(); 
				estadia.setCodigo(Integer.parseInt(tfNroEstadia.getText()));
				estadia.setFechaEntrada(Utilidad.retornarFecha(tfFechaEntrada.getText()));
				estadia.getCodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
				estadia.getCodHabitacion().setCodigo(Integer.parseInt(tfCodHabitacion.getText()));
				estadia.setObservacion(tfObsEstadia.getText());
				
								try { 
					 SesionEstadia.modificarEstadia(estadia);
					
				    	btnGuardarEstadia.setText("Guardar");
					
				        	deshabilitarCamposEstadia();
				             habilitarCodigo();
					           tfCodCliente.requestFocus();
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				}
				
			}
			
	  private void actualizaCierre() {
	
	      if (tfCodCliente.getText().equals(" ") || tfCodHabitacion.getText().equals(" ")) {
		
		JOptionPane.showMessageDialog (null," Campos codigo del cliente y codigo de la habitacion son obligatorios ");
	  }
	      else {
	             estadia = new Estadia(); //creamos el objeto en memoria, instanciar una lase
	             estadia.setCodigo(Integer.parseInt(tfNroEstadia.getText()));  //conversion de string a int > integer.parseInt(String);
	             estadia.setFechaEntrada(Utilidad.retornarFecha(tfFechaEntrada.getText()));
	             estadia.getCodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
	             estadia.getCodHabitacion().setCodigo(Integer.parseInt(tfCodHabitacion.getText()));
	             estadia.setObservacion(tfObsEstadia.getText());
	             estadia.setCodigo(Integer.parseInt(tfNroEstadia.getText()));  //conversion de string a int > integer.parseInt(String);
	             estadia.setFechaSalida(Utilidad.retornarFecha(tfFechaSalida.getText()));
	             estadia.setMontoTotal(Integer.parseInt(tfMontoTotalCierre.getText()));
	             estadia.setDescuento(Integer.parseInt(tfDescuento.getText()));
	             estadia.setActivoInActivo(0);
	
					try { 
		 SesionEstadia.modificarEstadia(estadia);
		           tableEstadia.setEnabled(true);
	               limpiarCamposEstadia();
	               limpiarCamposCierre();
	               deshabilitarCamposCierre();
	               consultarAbierto();
	               btnNuevo.setEnabled(true);
				   btnModificar.setEnabled(true);
	               btnEliminar.setEnabled(true);
	               btnCierre.setEnabled(true);
	               btnDetalle.setEnabled(true);
		           btnCancelarEstadia.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
		
					}
	      		}
	
	  		}
	  public void buscarEstadiaPorNro() {
				estadia = new Estadia();
				
				int codigo = Integer.parseInt(tfNroEstadia.getText());
				try {
				estadia = SesionEstadia.consultarEstadiaPorNro(codigo);
					if (estadia != null) {
					
					tfFechaEntrada.setText(String.valueOf(estadia.getFechaEntrada()));
					tfCodCliente.setText(String.valueOf(estadia.getCodCliente()));
					tfCodHabitacion.setText(String.valueOf(estadia.getCodHabitacion()));
					tfFechaSalida.setText(String.valueOf(estadia.getFechaSalida()));
					
					tfMontoTotalCierre.setText(String.valueOf(estadia.getMontoTotal()));
					tfObsEstadia.setText(estadia.getObservacion());
					}else {
						JOptionPane.showMessageDialog(this," el codigo del Usuario no existe","Aviso" ,1);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
	  private void nuevo() {
				limpiarCamposEstadia();
				ultimoCodigo();
				deshabilitarCodigo();
				habilitarCamposEstadia();
				mostrarButtonEstadia();
				tfFechaEntrada.requestFocus();
				consultarAbierto();	
			}
	  private void nuevoCodigoDetalle() {
				ultimoCodigoDetalle();
			}
	  private void ultimoCodigo() { 
				int id;
				try {
					id =  SesionEstadia.recupetarUltimoCodigo() + 1;
					tfNroEstadia.setText(String.valueOf(id));					

				} catch (Exception e) {
					
					e.printStackTrace();
				}
		  }	
	  private void ultimoCodigoDetalle() { 
				int id;
				try {
					id =  SesionEstadia.recupetarUltimoCodigoDetalle() + 1;
					tfNroDetalle.setText(String.valueOf(id));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		}	
			
	  private void ultimoCodigoDeuda() { 
				int id;
				try {
					id =  SesionEstadia.recupetarUltimoCodigoDeuda() + 1;
					tfNrodeuda.setText(String.valueOf(id));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		}
			
				private void cargarTablaDetalle() {
				
				String campos[]=new String[] {null,null,null};
				
				int montoTotal = 0;
						
					for (int i = 0; i < list.size(); i++) {
						
						dtmServicio.addRow(campos);
						
						     Detalle det= list.get(i);
						  dtmServicio.setValueAt(det.getCodigo(), i, 0);
						dtmServicio.setValueAt(det.getNumeroEstadia().getCodigo(), i, 1);
						
						dtmServicio.setValueAt(det.getCodServicio().getDescripServicio(), i, 2);
						
						dtmServicio.setValueAt(det.getMonto(), i, 3);
						
						montoTotal = montoTotal + det.getMonto();
						
					}	

					tfMontoTotalServicio.setText(String.valueOf(montoTotal));
			}
			
			
			
			public void consultarTodos() {
					while (dtmEstadia.getRowCount( )>0) {
						dtmEstadia.removeRow(0);
					}

					lista= new ArrayList<Estadia>( );
					
					try {
						
					  	 lista = SesionEstadia.consultarTodos();
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
					if (lista.size()>0) {
						
						cargarTablaEstadia();
					}
					
				} 
			public void consultarCerrado() {
				while (dtmEstadia.getRowCount( )>0) {
					dtmEstadia.removeRow(0);
				}

				lista= new ArrayList<Estadia>( );
				
				try {
					
				  	 lista = SesionEstadia.consultarCerrado();
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				if (lista.size()>0) {
					
					cargarTablaEstadia();
				}
				
			}  
			
			public void consultarAbierto() {
				while (dtmEstadia.getRowCount( )>0) {
					dtmEstadia.removeRow(0);
				}

				lista= new ArrayList<Estadia>( );
				
				try {
					
				  	 lista = SesionEstadia.consultarAbierto();
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				if (lista.size()>0) {
					
					cargarTablaEstadia();
				}
				
			}  
			
			public void consultarPorFiltro() {
				while (dtmEstadia.getRowCount( )>0) {
					dtmEstadia.removeRow(0);
					
				}

				lista= new ArrayList<Estadia>();
				try {
					lista = SesionEstadia.consultarTodosPorFiltro(tfBuscar.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (lista.size()>0) {
					cargarTablaEstadia();
					
				}
				
			}  
			private void cargarTablaEstadia() {
				
				String campos[]=new String[] {null,null,null};
						
					for (int i = 0; i < lista.size(); i++) {
						
						dtmEstadia.addRow(campos);
						Estadia est= lista.get(i);
						dtmEstadia.setValueAt(est.getCodigo(), i, 0);
						dtmEstadia.setValueAt(est.getCodCliente().getNombre(), i, 1);
						dtmEstadia.setValueAt(est.getCodHabitacion().getDescripHabitacion(), i, 2);
						
						
					}	

			}
						
						
				
				
			
			public void consultarDetalle() {
				
				tfMontoTotalServicio.setText("");
				
				while (dtmServicio.getRowCount()>0) {
					dtmServicio.removeRow(0);
					
				}

				list= new ArrayList<Detalle>();
				try {
					
					list = SesionEstadia.consultarTodosPorFiltroDetalle(tfBuscarDetalle.getText());
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				if (list.size()>0) {
					
					cargarTablaDetalle();
					
				}	
			}
			public String getAbrir() {
				return abrir;
			}

			public void setAbrir( String abrir ) {
				this.abrir = abrir;
			}
			
			private void seleccionarRegistroTablaEstadia( String abrir ){
				
				Integer fila = this.tableEstadia.getSelectedRow();
				dato = String.valueOf(this.tableEstadia.getValueAt( fila, 0 ));
				nuevoCodigoDetalle();
				tfNroEstadia.setText(dato);  //cargamos el valor de dato en el campo codigo
				tfNroEstadia.setText(dato);
				tfBuscarDetalle.setText(dato);
				consultarDetalle();
				btnCancelarEstadia.setVisible(true);
					Estadia estadia = new Estadia(); 
					try {
							 estadia = SesionEstadia.consultarEstadiaPorNro(Integer.parseInt( tfNroEstadia.getText() )); 
							 tfFechaEntrada.setText(Utilidad.getFechaDDMMAAAA(estadia.getFechaEntrada( )));
								tfCodCliente.setText(String.valueOf(estadia.getCodCliente().getCodigo( )));
								tfCodHabitacion.setText(String.valueOf(estadia.getCodHabitacion().getCodigo( )));
								tfhab.setText(String.valueOf(estadia.getCodHabitacion().getCodigo( )));
								tfObsEstadia.setText(estadia.getObservacion( ));
								tfActivo.setText(String.valueOf(estadia.getActivoInActivo( )));
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			
				}
			private void seleccionarRegistroTablaDetalle( String abrir ){
				
				Integer fila = this.tableDetalle.getSelectedRow();
				String dato = String.valueOf(this.tableDetalle.getValueAt( fila, 0 ));
				tfNroDetalle.setText(dato);
					Detalle det = new Detalle();
					
					try {
						det = SesionEstadia.consultarDetallePorNro(Integer.parseInt(tfNroDetalle.getText() ));
						tfMontoServicio.setText(String.valueOf(det.getMonto()));
						tfNroServicio.setText(String.valueOf(det.getCodServicio().getCodigo()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			private void irParaModificar() {
				if (tfNroEstadia.getText().equals("")) {
		
		        JOptionPane.showMessageDialog(null, "No hay nada para Modificar", "aviso",1);
		
	           }else{
	        	   
	        	   if (tfActivo.getText().equals("0") ) {
    		
    		    JOptionPane.showMessageDialog(null, "No se puede Modificar por que ya tiene un cierre", "aviso",1 );
    		    
	        	   }else{
    		
	        		   deshabilitarCodigo();
	        		   mostrarButtonEstadia();
	        		   habilitarCamposEstadia();
	        		   btnNuevo.setEnabled(false);
	        		   btnEliminar.setEnabled(false);
	        		   btnCierre.setEnabled(false);
	        		   btnDetalle.setEnabled(false);
	        		   btnGuardarEstadia.setText("actualizar");
	        		   tfFechaEntrada.requestFocus();
	        		   tableEstadia.setEnabled(false);
	        		   consultarAbierto();
	   }
	  }
     }
     private void borrarEstadia() { //Borrar prestamo
	
            	if (tfNroEstadia.getText().equals("")) {
            		
            		JOptionPane.showMessageDialog(null, "No hay nada para Eliminar", "aviso",1);
            	}else{
	
	int seleccion = JOptionPane.showOptionDialog(this, "Desea eliminar al cliente: "+tfNroEstadia.getText()+", con codigo: "+tfNroEstadia.getText(), "Borrar", JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "Si");
	
	    if(seleccion==0){
		    try {
			    SesionEstadia.borrarEstadia(Integer.parseInt(tfNroEstadia.getText()));
			
			    limpiarCamposEstadia();
			    limpiarCamposDetalle();
			    tfBuscarDetalle.setText("");
			    tfMontoTotalServicio.setText("");
			    consultarDetalle();
			    LimpiarCodigo();
			    consultarAbierto();
			
		  } catch (NumberFormatException e) {
			
			    e.printStackTrace();
			
		  } catch (Exception e) {
			
			    e.printStackTrace();
			
			    JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
			
			 }
		    }
           }
         }
            
            private void borrarDetalle() { //Borrar prestamo
            	
            	if (tfNroDetalle.getText().equals("")) {
            		
            		JOptionPane.showMessageDialog(null, "No hay nada para Eliminar", "aviso",1);
            	}else{
	
	int seleccion = JOptionPane.showOptionDialog(this, "Desea eliminar el detalle: "+tfNroDetalle.getText()+", con codigo: "+tfNroDetalle.getText(), "Borrar", JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "Si");
	
	if(seleccion==0){
		try {
			SesionEstadia.borrarDetalle(Integer.parseInt(tfNroDetalle.getText()));
			
			limpiarCamposDetalle();
			consultarDetalle();
		    ultimoCodigoDetalle();
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
			
						}
					}
            	}
            }
            
            private void generarDeuda() {
        		
   		     deuda = new Deuda();
   		
   		deuda.setCodigo(Integer.parseInt(tfNrodeuda.getText()));
   		deuda.getNroEstadia().setCodigo(Integer.parseInt(tfNroEstadia.getText()));
   		deuda.getCodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
   		deuda.setMontoDeuda(Integer.parseInt(tfMontoTotalCierre.getText()));
   		
   	     	
   	    
   	    try{
   	    	
   	    	
   	    SesionEstadia.generarDeuda(deuda);
   	
   	    } catch (Exception e ){ 
   	    	e.printStackTrace();
   	    }
   	}
   	private void cerrar() {
   		if (!Utilidad.retornarFecha(tfFechaEntrada.getText()).after(Utilidad.retornarFecha(tfFechaSalida.getText()))) {
   			cargarCierre();
   			GenerarDescuento();
   			generarDeuda();
   			actualizaCierre();
   		}else
   			JOptionPane.showMessageDialog(null, "La fecha de salida no puede ser menor a la fecha de Entrada!");
   		    tfFechaSalida.requestFocus();
   		
   	}
   	private void cerrar1() {
   		if (!Utilidad.retornarFecha(tfFechaEntrada.getText()).after(Utilidad.retornarFecha(tfFechaSalida.getText()))) {
   			tfDescuento.requestFocus();
   			 cargarCierre();
   			
   		}else
   			JOptionPane.showMessageDialog(null, "La fecha de salida no puede ser menor a la fecha de Entrada!");
   		
   	}
                  
     private void mostrarButtonDetalle() {
              		btnCancelarDetalle.setVisible(true);
              		btnGuardarDetalle.setVisible(true);
              		btnEliminarDetalle.setVisible(true);
              		btnBuscarServicio.setVisible(true);
              		
              	}
                  
     private void ocultarButtonDetalle() {
                	btnCancelarDetalle.setVisible(false);
                	btnGuardarDetalle.setVisible(false);
               		btnEliminarDetalle.setVisible(false);
                		
                	}
                  
    private void mostrarButtonEstadia() {
               		btnCancelarEstadia.setVisible(true);
               		btnGuardarEstadia.setVisible(true);
               		btnBuscarCliente.setVisible(true);
               		btnBuscarHabitacion.setVisible(true);
                		
                	}
                  
    private void ocultarButtonEstadia() {
                  	btnCancelarEstadia.setVisible(false);
                  	btnGuardarEstadia.setVisible(false);
               		btnBuscarCliente.setVisible(false);
                	btnBuscarHabitacion.setVisible(false);
                  		
                  		
                  		
                  	}
    private void mostrarButtonCierre() {
               		btnCancelarCierre.setVisible(true);
               		btnGuardarCierre.setVisible(true);
                  		
                  	}
                    
    private void ocultarButtonCierre() {
                    btnCancelarCierre.setVisible(false);
                    btnGuardarCierre.setVisible(false);
                    		
                    	}
                      
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			
	public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
	private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private void verbuscaCliente() {
		 FormClient menu = new FormClient();
		 menu.consultar();
		 menu.setBuscarCliente(this);
		 menu.setVisible(true);				
	}
	
	private void verbuscaServicio() {
		 formServici menu = new formServici();
		 menu.consultar();
		 menu.setBuscarservicio(this);
		 menu.setVisible(true);
	}
	@Override
	public void BuscarClienteLista(Cliente cliente) {
		tfCodCliente.setText(String.valueOf(cliente.getCodigo()));
		btnBuscarHabitacion.requestFocus();
		
		
	}
	
	private void verbuscaHabitacion() {
		 formHabita menu = new formHabita();
		 menu.consultar();
		 menu.setBuscarHabicion(this);
		 menu.setVisible(true);				
	}
	@Override
	public void BuscarHabitacionLista(Habitacion habitacion) {
		tfCodHabitacion.setText(String.valueOf(habitacion.getCodigo()));
		tfhabactivo.setText(String.valueOf(habitacion.getHabActivo()));
		tfObsEstadia.requestFocus();
		
		}

	@Override
	public void BuscarServicioLista(Servicio servicio) {
		tfNroServicio.setText(String.valueOf(servicio.getCodigo()));
		tfMontoServicio.setText(String.valueOf(servicio.getMontoServicio()));
		 tfMontoServicio.requestFocus();
		
	}
	private void limpiarCodigoDetalle() {
		tfNroEstadia.setText("");
		
	}
	
private void GenerarDescuentoCierre(){
		
		String subTotal = tfSubTotal.getText();
		
		if (tfDescuento.getText().equals("")){
			
			tfMontoTotalCierre.setText(subTotal);
		}else{
		Integer restaDescuento=Integer.parseInt(tfSubTotal.getText())-Integer.parseInt(tfDescuento.getText());
		tfMontoTotalCierre.setText(String.valueOf(restaDescuento));
	}
	}
	
	private void cargarCierre() {
		estadia = new Estadia();
		detalle = new Detalle();
		
		Integer suma = 0;
		
		String sumaDetalle = tfMontoTotalServicio.getText();
		
		try {
			System.out.println(dato);
			estadia = SesionEstadia.consultarPorNro(Integer.parseInt(dato));
			System.out.println(estadia.getCodHabitacion().getMontoDia());
			tfMontoDiariaCierre.setText(Utilidad.calculaMontoHabitacion(tfFechaEntrada.getText(),tfFechaSalida.getText(), estadia));
			tfServicioCierre.setText(sumaDetalle);
			
			if (tfMontoDiariaCierre.getText().equals("")) {
				tfMontoDiariaCierre.setText("0");
			}
			
			if (tfServicioCierre.getText().equals("")) {
				tfServicioCierre.setText("0");
				
			}
			
			suma = Integer.parseInt(tfMontoDiariaCierre.getText())+Integer.parseInt(tfServicioCierre.getText());
			tfSubTotal.setText(String.valueOf(suma));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	private void GenerarDescuento(){
		
		String subTotal = tfSubTotal.getText();
		
		if (tfDescuento.getText().equals("")){
			
			tfMontoTotalCierre.setText(subTotal);
			
			tfDescuento.setText("0");
			
		}else{
		Integer restaDescuento=Integer.parseInt(tfSubTotal.getText())-Integer.parseInt(tfDescuento.getText());
		tfMontoTotalCierre.setText(String.valueOf(restaDescuento));
	}
	}
	
	private int validarHabitacion() {
		int numero = Integer.parseInt(tfNroEstadia.getText());
		estadia = new Estadia();
			try {
				estadia = SesionEstadia.consultarHabitacion(Integer.parseInt(tfCodHabitacion.getText()),1);//true
				if (estadia!=null){
					System.out.println("estadianum "+ estadia.getCodigo());
					if(estadia.getCodHabitacion().getCodigo() == Integer.parseInt(tfCodHabitacion.getText())){
						if(estadia.getActivoInActivo()==1){
						    if(estadia.getCodigo()!= numero){ 
								JOptionPane.showMessageDialog(this, "Esta habitación ya está siendo utilizada");
								   tfCodHabitacion.requestFocus();
								   
								return 0;
							}else{
								return 0;
							}
						     }else{
							    return 1;
						}  
					}	
				}
				
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			} return 1;
	}
	
	
}