	package py.edu.unican.facitec.formulario;

	import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.interfaz.BuscarCliente;
import py.edu.unican.facitec.sesion.SesionCliente;

/**
 * 
 * @author Cosme Damian
 *
 */
	public class FormClient extends JDialog {

		public formEstadia estadia;
		private static final long serialVersionUID = 1L;
		private final JPanel contenPanel = new JPanel();
		private DefaultTableModel dtmCliente = new DefaultTableModel(null, new String[] {" Código", "Nombre", "Nro.Cedula "});
		private JTable table;
		private JPanel contentPane;
		private JTextField tfCodigo;
		private JTextField tfNombreApellido;
		private JTextField tfCedula;
		private JTextField tfRuc;
		private JTextField tfDireccion;
		private JTextField tfTelefono;
		private JTextField tfBuscar;
		private BuscarCliente buscarCliente;
		private Cliente cliente;
		private ArrayList<Cliente> lista;//declaramos un array de tipo Cliente
		private JLabel lblCampoNumerico;
		private Boolean modoAgregar = true;
		private String abrir;
		private JButton btnGuardar = new JButton();
		private JButton btnModificar = new JButton();
		private JButton btnEliminar = new JButton();
		private JButton btnCancelar = new JButton();
		private JButton btnNuevo = new JButton();
		private JButton btnSalir = new JButton();
	
	public FormClient() {
		setBackground(new Color(0, 128, 128));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle("Cliente");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 307, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Panel panel = new Panel();
		panel.setBounds(402, 23, 362, 322);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(10, 21, 53, 22);
		panel.add(lblCodigo);
		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfCodigo.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e ) {
				consultar();
				buscarClientePorCodigo();	
			}
		});
		tfCodigo.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
			
			}
		});
		tfCodigo.setBounds(125, 19, 139, 30);
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblNombreApellido = new JLabel("Nombre y Apellido");
		lblNombreApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreApellido.setBounds(10, 71, 123, 22);
		panel.add(lblNombreApellido);
		
		tfNombreApellido = new JTextField();
		tfNombreApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfNombreApellido.setEnabled(false);
		tfNombreApellido.addKeyListener(new KeyAdapter() {
			
	public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfCedula.requestFocus();
				}
			}
		});
		tfNombreApellido.setToolTipText("Digite el nombre del cliente");
		tfNombreApellido.setBounds(125, 71, 227, 30);
		panel.add(tfNombreApellido);
		tfNombreApellido.setColumns(10);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCedula.setBounds(10, 126, 90, 22);
		panel.add(lblCedula);
		
		tfCedula = new JTextField();
		tfCedula.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfCedula.setEnabled(false);
		tfCedula.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfRuc.requestFocus();
				}
			}
			@Override
	public void keyTyped(KeyEvent e) {
				if ( (e.getKeyChar()<'0' || e.getKeyChar()>'9' ) && ( e.getKeyChar()
						!= KeyEvent.VK_BACK_SPACE ) && ( e.getKeyChar() != KeyEvent.VK_ENTER )){
					e.consume();
					
					lblCampoNumerico.setVisible(true);
				}else 
					lblCampoNumerico.setVisible(false);
			}
		});
		tfCedula.setToolTipText(" Digite el nro de cedula del cliente ");
		tfCedula.setBounds(125, 124, 227, 30);
		panel.add(tfCedula);
		tfCedula.setColumns(10);
		
		JLabel lblRuc = new JLabel("Ruc");
		lblRuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRuc.setBounds(10, 182, 90, 22);
		panel.add(lblRuc);
		
		tfRuc = new JTextField();
		tfRuc.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfRuc.setEnabled(false);
		tfRuc.addKeyListener(new KeyAdapter() {
			
			
	public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfDireccion.requestFocus();
				}
			}
		});
		tfRuc.setToolTipText("Digite el ruc del cliente");
		tfRuc.setBounds(125, 180, 227, 30);
		panel.add(tfRuc);
		tfRuc.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(10, 233, 65, 22);
		panel.add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfDireccion.setEnabled(false);
		tfDireccion.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					tfTelefono.requestFocus();
				}
			}
		});
		tfDireccion.setToolTipText("Digite la direccion del cliente");
		tfDireccion.setBounds(125, 231, 227, 30);
		panel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono ");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(10, 289, 65, 17);
		panel.add(lblTelefono);
		
		tfTelefono = new JTextField();
		tfTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfTelefono.setEnabled(false);
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnGuardar.requestFocus();
				}
				
			}
		});
		tfTelefono.setToolTipText("Digite el telefono del cliente");
		tfTelefono.setBounds(125, 284, 227, 30);
		panel.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		lblCampoNumerico = new JLabel("Campo Numerico");
		lblCampoNumerico.setVisible(false);
		lblCampoNumerico.setForeground(Color.RED);
		lblCampoNumerico.setBounds(135, 112, 173, 14);
		panel.add(lblCampoNumerico);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(654, 374, 99, 30);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnSalir);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				nuevoCliente();
				consultar();
				btnGuardar.setText("Guardar");
				setEstadoEdicion(true);
				modoAgregar = false;	
			}
		});
		btnNuevo.setBounds(307, 66, 89, 32);
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		//btnModificar.setSelectedIcon(new ImageIcon(FormClient.class.getResource ("/py/edu/unican/facitec/imagen/iconServicio.jpg")));
		btnModificar.setBounds(307, 130, 89, 30);
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				
			if (tfCodigo.getText().equals("")) {
				
				JOptionPane.showMessageDialog (null, "No hay nada para Modificar", "aviso",1);
			}else{
				irParaModificar();
				setEstadoEdicion2(true);
				setEstadoEdicion3(true);
				setEstadoEdicion4(false);
				}	
			}
		});
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent arg0) {
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				
				if (tfCodigo.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "No hay nada para Eliminar", "aviso",1);
				}else{	
				borrarCliente();
				
				}
			}
		});
		btnEliminar.setBounds(307, 193, 89, 32);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnEliminar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setVisible(false);
		btnGuardar.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				
				if (tfNombreApellido.getText().length() == 0) {
					
					JOptionPane.showMessageDialog(null, "Nombre y Apellido son Obligatorio", "Aviso",1);
		            tfNombreApellido.requestFocus();
		            return;
		        }
				if (tfCedula.getText().length() == 0) {
					 JOptionPane.showMessageDialog(null, "Número de Cédula es Obligatorio", "Aviso",1);
		            tfCedula.requestFocus();
		            return;
		        }
				if (FormClient.this.btnGuardar.getText().compareTo("Guardar")==0) {
					guardarCliente();
					LimpiarCampos();
					deshabilitarCampos();
					habilitarCodigo();
					consultar();
					setEstadoEdicion(false);
				}else {
					
					modificar();
				}
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			
	public void actionPerformed(ActionEvent e) {
				
				if (tfNombreApellido.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Nombre y Apellido son Obligatorio", "Aviso",1);
		            tfNombreApellido.requestFocus();
		            return;
		        }
				if (tfCedula.getText().length() == 0) {
					 JOptionPane.showMessageDialog(null, "Número de Cédula es Obligatorio", "Aviso",1);
		            tfCedula.requestFocus();
		            return;
		        }
				
				if (FormClient.this.btnGuardar.getText().compareTo("Guardar")==0) {
					guardarCliente();
					setEstadoEdicion(false);
					LimpiarCampos();
					deshabilitarCampos();
					habilitarCodigo();
					consultar();
				}else {
					modificar();
				}
				
			}
		});
		btnGuardar.setBounds(420, 374, 99, 30);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				LimpiarCampos();
				habilitarCodigo();
				deshabilitarCampos();
				LimpiarCodigo();
				setEstadoEdicion(false);
				setEstadoEdicion5(true);
				btnGuardar.setText("Guardar");
			
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(541, 374, 89, 30);
		contentPane.add(btnCancelar);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(0, 0, 297, 272);
		contentPane.add(scrollPane);
		
		table = new JTable(dtmCliente){
	private static final long serialVersionUID = 1L;
	public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
		};
		table.addKeyListener(new KeyAdapter() {
	public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					for (int i : table.getSelectedRows()) {
				        buscarCliente.BuscarClienteLista(lista.get(i));
				        System.out.println(buscarCliente);
					}
			            dispose();
					
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
					dispose();
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
	public void mousePressed(MouseEvent e) {
				if (e.getClickCount()==2) {
					for (int i : table.getSelectedRows()) {
				        buscarCliente.BuscarClienteLista(lista.get(i));
				        System.out.println(buscarCliente);
					}
			            dispose();
					
				}
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 11));
		table.setFillsViewportHeight(true);
		table.setModel(dtmCliente); 
		scrollPane.setViewportView(table);
	
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setEnabled(false);
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 281, 46, 20);
		contentPane.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
	public void keyPressed(KeyEvent e) {
				if ((e.getKeyChar()<'0' || e.getKeyChar()>'9')&&(e.getKeyChar()
						!= KeyEvent.VK_BACK_SPACE)&&(e.getKeyChar() != KeyEvent.VK_ENTER)){ 
					e.consume();
					//consultar();
					//consultar2();
					consultar3();
				}else {
					consultar();
			}	
			
		}
		
		});
		tfBuscar.setBounds(66, 277, 231, 32);
		contentPane.add(tfBuscar);
		tfBuscar.setColumns(10);
	}
	private void modificar() {
		deshabilitarCodigo();
		habilitarCampos();
		btnGuardar.setText("actualizar");
		
		tfNombreApellido.requestFocus();
		cliente = new Cliente();
		cliente.setCodigo(Integer.parseInt(tfCodigo.getText())); 
		cliente.setNombre(tfNombreApellido.getText());
		cliente.setRuc(tfRuc.getText());
		cliente.setCedula(Integer.parseInt(tfCedula.getText()));
		cliente.setDireccion(tfDireccion.getText());
		cliente.setTelefono(tfTelefono.getText());
		try {
			SesionCliente.modificarCliente(cliente);
			
			btnGuardar.setText("Guardar");
			deshabilitarCampos();
			habilitarCodigo();
			LimpiarCampos();
			LimpiarCodigo();
			tfCodigo.requestFocus();
			setEstadoEdicion5(true);
			setEstadoEdicion(false);
			modoAgregar = true;
			tfBuscar.setText("");
			consultar();
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}
	
	private void nuevoCliente() {
		LimpiarCampos();
		ultimoCodigo();
		deshabilitarCodigo();
		
	}
	private void guardarCliente() {
		cliente = new Cliente();
	    cliente.setCodigo(Integer.parseInt(tfCodigo.getText()));
	    cliente.setNombre(tfNombreApellido.getText());
	    cliente.setCedula(Integer.parseInt(tfCedula.getText()));
	    cliente.setRuc(tfRuc.getText());
	    cliente.setDireccion(tfDireccion.getText());
	    cliente.setTelefono(tfTelefono.getText());
	    
	    try{ 
	    	SesionCliente.guardarCliente(cliente);//
	    	
	    LimpiarCampos();  
		habilitarCodigo();
		LimpiarCodigo();
		tfCodigo.requestFocus();
		modoAgregar = true;
		tfBuscar.setText("");
		tfNombreApellido.requestFocus();
	    } catch (Exception e ){  
	    	e.printStackTrace();
	    }

	}
	
	private void ultimoCodigo() { 
		int id;
        
		try {
			id = SesionCliente.recupetarUltimoCodigo() + 1;
			tfCodigo.setText(String.valueOf(id));
			habilitarCampos(); //habilita todos los campos para la carga 
			tfNombreApellido.requestFocus();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
}
	
	private void buscarClientePorCodigo() {
		cliente = new Cliente();
		
		int codigo = Integer.parseInt(tfCodigo.getText());
		try {
			cliente = SesionCliente.consultarClintePorCodigo(codigo);
			if (cliente != null) {
			tfNombreApellido.setText(cliente.getNombre());
			tfCedula.setText(String.valueOf(cliente.getCedula()));
			tfRuc.setText(cliente.getRuc());
			tfDireccion.setText(cliente.getDireccion());
			tfTelefono.setText(String.valueOf(cliente.getTelefono()));
			}else {
				JOptionPane.showMessageDialog(this,"el codigo del Cliente no existe","Aviso",1);
				LimpiarCodigo();
				LimpiarCampos();
				deshabilitarCampos();
			}
			
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		}
		private void LimpiarCampos() { 
			tfNombreApellido.setText("");
			tfCedula.setText("");
			tfDireccion.setText("");
			tfRuc.setText("");
			tfTelefono.setText("");
			modoAgregar = true;

		}
		private void LimpiarCodigo() {
              tfCodigo.setText("");
		}
		private void habilitarCodigo() {
			
			tfCodigo.setEnabled(true);
			tfCodigo.requestFocus();
				
				}
		private void deshabilitarCodigo() {
					
					tfCodigo.setEnabled(false);
				}
				
		private void deshabilitarCampos() {
					
					tfNombreApellido.setEnabled(false);
					tfCedula.setEnabled(false);
					tfRuc.setEnabled(false);
					tfDireccion.setEnabled(false);
					tfTelefono.setEnabled(false);
				}
				
		private void habilitarCampos() {
					tfNombreApellido.setEnabled(true);
					tfCedula.setEnabled(true);
					tfRuc.setEnabled(true);
					tfDireccion.setEnabled(true);
					tfTelefono.setEnabled(true);
				}
				
		private void irParaModificar() {
					deshabilitarCodigo();
					habilitarCampos();
					btnGuardar.setText("actualizar");
					tfNombreApellido.requestFocus();
				}
				
		private void cargarTabla2() {
					
					String campos[]=new String[] {null,null,null};
							
						for (int i = 0; i < lista.size(); i++) {
							
							dtmCliente.addRow(campos);
							Cliente cli = lista.get(i);  
							dtmCliente.setValueAt(cli.getCodigo(), i, 0);
							dtmCliente.setValueAt(cli.getNombre(), i, 1);
							dtmCliente.setValueAt(cli.getCedula(), i, 2);		
						}	
				}
		private void setEstadoEdicion(boolean isEdicion) {
					
					btnModificar.setVisible(!isEdicion);
					btnEliminar.setVisible(!isEdicion);
				    btnGuardar.setVisible(isEdicion);
					btnCancelar.setVisible(isEdicion);

				}
				
		private void setEstadoEdicion2(boolean isEdicion) {
					btnGuardar.setVisible(isEdicion);
					btnCancelar.setVisible(isEdicion);

				}
		private void setEstadoEdicion3(boolean isEdicion) {
					btnEliminar.setVisible(!isEdicion);
					btnNuevo.setVisible(isEdicion);
				}
				
		private void setEstadoEdicion4(boolean isEdicion) {
					btnNuevo.setVisible(isEdicion);
				}
		private void setEstadoEdicion5(boolean isEdicion) {
					btnNuevo.setVisible(isEdicion);
				}
		private void borrarCliente() {
					
					int seleccion = JOptionPane.showOptionDialog(this, "Desea eliminar al cliente: "+tfCodigo.getText()+","
							+ "con codigo: "+tfCodigo.getText(), "Borrar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "Si");
					if(seleccion==0){
						try {
							SesionCliente.borrarCliente(Integer.parseInt(tfCodigo.getText()));
							LimpiarCodigo();
							consultar();
							LimpiarCampos();
							tfCodigo.requestFocus();
							//JOptionPane.showMessageDialog(this, "registro Eliminado ", "aviso",1);
							
						} catch (NumberFormatException e) {
							
							e.printStackTrace();
						} catch (Exception e) {
							
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
						}
						
					}
					
				}
				
				
					
	public String getAbrir() {
				return abrir;
			}

	public void setAbrir( String abrir ) {
				this.abrir = abrir;
			}
			
			
	private void seleccionarRegistroTabla( String abrir ){
				
				Integer fila = this.table.getSelectedRow();
				String dato = String.valueOf(this.table.getValueAt( fila, 0 ));
				tfCodigo.setText( dato );  //cargamos el valor de dato en el campo codigo
					if(fila>=0){
						if( abrir.equals ("S") ){
							
							btnModificar.setEnabled(false);
							
							btnEliminar.setEnabled(false);
						}else{
							btnModificar.setEnabled(true);
							
							btnEliminar.setEnabled(true);
						}
						
					}
					
					Cliente cliente = new Cliente(); 
					
					try {
						cliente = SesionCliente.consultarClintePorCodigo(Integer.parseInt( tfCodigo.getText() ));
						tfNombreApellido.setText(cliente.getNombre());
						tfCedula.setText(String.valueOf(cliente.getCedula()));
						tfRuc.setText(cliente.getRuc());
						tfDireccion.setText(cliente.getDireccion());
						tfTelefono.setText(cliente.getRuc());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			
	public void consultar() {
				
				while (dtmCliente.getRowCount()>0) {
					dtmCliente.removeRow(0);
					
				}

				lista= new ArrayList<Cliente>();
				try {
					lista = SesionCliente.consultarTodosPorNombre(tfBuscar.getText());
				} catch (Exception e) {
					
					
					e.printStackTrace();
				}
				
				if (lista.size()>0) {
					cargarTabla2();
					
				}	
				
			}  
	public void consultar2() {
				
				while (dtmCliente.getRowCount()>0) {
					dtmCliente.removeRow(0);
					
				}

				lista= new ArrayList<Cliente>();
				try {
					lista = SesionCliente.consultarTodosPorCedula(tfBuscar.getText());
				} catch (Exception e) {
					
					
					e.printStackTrace();
				}
				
				if (lista.size()>0) {
					cargarTabla2();
					
				}
				
			}  
	public void consultar3() {
				
				while (dtmCliente.getRowCount()>0) {
					dtmCliente.removeRow(0);
					
				}

				lista= new ArrayList<Cliente>();
				try {
					lista = SesionCliente.consultarTodosPorNumeroTel(tfBuscar.getText());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				if (lista.size()>0) {
					cargarTabla2();	
				}
			}

	public BuscarCliente getBuscarCliente() {
				return buscarCliente;
			}

	public void setBuscarCliente(BuscarCliente buscarCliente) {
				this.buscarCliente = buscarCliente;
			}  
}
