package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import py.edu.unican.facitec.entidades.Cliente;
import py.edu.unican.facitec.entidades.Servicio;
import py.edu.unican.facitec.interfaz.BuscarServicio;
import py.edu.unican.facitec.sesion.SesionServicio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class formServici extends JDialog {

	private JPanel contentPane;
	private JTextField tfCodServicio;
	private JTextField tfMontoServicio;
	private JTextField tfDescripServicio;
	private JTextField tfObservacion;
	private JTextField tfBuscar;
	private Servicio ser;
	private ArrayList<Servicio> lista;
	private JButton btnGuardar;
	private JButton btnModificar;
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnSalir;
	private JTable table;
	private DefaultTableModel dtmServicio =  new  DefaultTableModel(null, new String[]{"Codigo","Descripcion","Monto Por Dia"});
	private JLabel lblCampoNumerico;
	private Boolean modoAgregar = true;
	private String abrir;
	private BuscarServicio buscarservicio;
	public formServici() {
		setTitle("Servicio");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoAgregar = false;
				nuevoServicio();
				setEstadoEdicion2(true);
				
				btnGuardar.setText("Guardar");
			}
		});
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevo.setBounds(317, 24, 89, 32);
		contentPane.add(btnNuevo);
		
		 btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfCodServicio.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "No hay nada para Modificar", "aviso",1);
				}else{
				irParaModificar();
				
				setEstadoEdicion2(true);
				setEstadoEdicion3(false);
				setEstadoEdicion4(true);
				}
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(317, 88, 89, 32);
		contentPane.add(btnModificar);
		
		 btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfCodServicio.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "No hay nada para Eliminar", "aviso",1);
				}else{
				borrarCliente();
				}
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(317, 151, 89, 32);
		contentPane.add(btnEliminar);
		
	    btnGuardar = new JButton("Guardar");
	    btnGuardar.setVisible(false);
	    btnGuardar.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		
	    		if (tfMontoServicio.getText().length() == 0) {
					 JOptionPane.showMessageDialog(null, "el monto de Servicio es Obligatorio","Aviso",1);
		            tfMontoServicio.requestFocus();
		            return;
		        }
				
				if (tfDescripServicio.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "La descripcion es Obligatorio","Aviso",1);
		            tfDescripServicio.requestFocus();
		            return;
		        }
	    		if (formServici.this.btnGuardar.getText().compareTo("Guardar")==0) {
					guardarServicio();
					LimpiarCampos();
					deshabilitarCampos();
					habilitarCodigo();
				}else {
					
					modificar();
				}
				
		
	    	}
	    });
		btnGuardar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (tfMontoServicio.getText().length() == 0) {
					 JOptionPane.showMessageDialog(null, "el monto de Servicio es Obligatorio","Aviso",1);
		            tfMontoServicio.requestFocus();
		            return;
		        }
				
				if (tfDescripServicio.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "La descripcion es Obligatorio","Aviso",1);
		            tfDescripServicio.requestFocus();
		            return;
		        }
				
					if (formServici.this.btnGuardar.getText().compareTo("Guardar")==0) {
						guardarServicio();
						LimpiarCampos();
						deshabilitarCampos();
						habilitarCodigo();
					}else {
						
						modificar();
					}
					
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(422, 428, 99, 30);
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
				setEstadoEdicion3(true);
				tfCodServicio.requestFocus();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(542, 428, 89, 30);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(656, 428, 99, 30);
		contentPane.add(btnSalir);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBounds(422, 12, 333, 360);
		contentPane.add(panel);
		
		JLabel lblCdigoServicio = new JLabel("C\u00F3digo Servicio");
		lblCdigoServicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigoServicio.setBounds(10, 21, 100, 22);
		panel.add(lblCdigoServicio);
		
		tfCodServicio = new JTextField();
		tfCodServicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfMontoServicio.requestFocus();
					LimpiarCampos();
					deshabilitarCampos();
					tfCodServicio.requestFocus();
					consultar();
					
					
				}
			}
		});
		tfCodServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				consultar();
				buscarServicioPorCodigo();
			}
		});
		tfCodServicio.setColumns(10);
		tfCodServicio.setBounds(120, 19, 203, 30);
		panel.add(tfCodServicio);
		
		JLabel lblDescripServicio = new JLabel("Descrip Servicio");
		lblDescripServicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripServicio.setBounds(10, 126, 100, 22);
		panel.add(lblDescripServicio);
		
		tfMontoServicio = new JTextField();
		tfMontoServicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfMontoServicio.setEnabled(false);
		tfMontoServicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfDescripServicio.requestFocus();
				}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar()<'0' || e.getKeyChar()>'9')&&(e.getKeyChar() != KeyEvent.VK_BACK_SPACE)&&(e.getKeyChar() != KeyEvent.VK_ENTER)){
					e.consume();
					lblCampoNumerico.setVisible(true);
				}else 
					lblCampoNumerico.setVisible(false);
				
				
				
			}
		});
		tfMontoServicio.setColumns(10);
		tfMontoServicio.setBounds(120, 71, 203, 30);
		panel.add(tfMontoServicio);
		
		JLabel lblMontoServicio = new JLabel("Monto Servicio");
		lblMontoServicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMontoServicio.setBounds(10, 73, 100, 22);
		panel.add(lblMontoServicio);
		
		tfDescripServicio = new JTextField();
		tfDescripServicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfDescripServicio.setEnabled(false);
		tfDescripServicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					tfObservacion.requestFocus();
				}
			}
		});
		tfDescripServicio.setColumns(10);
		tfDescripServicio.setBounds(120, 124, 203, 49);
		panel.add(tfDescripServicio);
		
		JLabel lblObservacin = new JLabel("Observaci\u00F3n");
		lblObservacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblObservacin.setBounds(10, 203, 100, 22);
		panel.add(lblObservacin);
		
		tfObservacion = new JTextField();
		tfObservacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfObservacion.setEnabled(false);
		tfObservacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnGuardar.requestFocus();
				}
			}
		});
		tfObservacion.setColumns(10);
		tfObservacion.setBounds(120, 201, 203, 49);
		panel.add(tfObservacion);
		
		lblCampoNumerico = new JLabel("Campo Numerico");
		lblCampoNumerico.setForeground(Color.RED);
		lblCampoNumerico.setVisible(false);
		lblCampoNumerico.setBounds(120, 60, 157, 14);
		panel.add(lblCampoNumerico);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 295, 46, 20);
		contentPane.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				consultar();
			}
		});
		tfBuscar.setColumns(10);
		tfBuscar.setBounds(57, 291, 172, 32);
		contentPane.add(tfBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 312, 287);
		contentPane.add(scrollPane);
		
		table = new JTable(dtmServicio){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
		};
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					for (int i : table.getSelectedRows()) {
				        buscarservicio.BuscarServicioLista(lista.get(i));
				        System.out.println(buscarservicio);
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
				        buscarservicio.BuscarServicioLista(lista.get(i));
				        System.out.println(buscarservicio);
					}
			            dispose();
					
				}
			}
		});

		table.setFillsViewportHeight(true);
		table.setModel(dtmServicio);   //le agregue por parte del listado
		scrollPane.setViewportView(table);//le agregue por parte del listado
		
		JButton btnmas = new JButton("+");
		btnmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				formServicio menu = new formServicio();
				 menu.consultar();
				 menu.setVisible(true);
			}
		});
		btnmas.setBounds(238, 296, 52, 23);
		contentPane.add(btnmas);
	}
	
	private void nuevoServicio() {
		ultimoCodigo();
		setEstadoEdicion(true);
		
	}
	private void ultimoCodigo() { 
		int id;
        
		try {
			id = SesionServicio.recupetarUltimoCodigo() + 1;
			tfCodServicio.setText(String.valueOf(id));
			habilitarCampos();//habilita todos los campos para la carga 
			deshabilitarCodigo();
			tfMontoServicio.requestFocus();

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

}	
	private void LimpiarCampos() {
		tfDescripServicio.setText("");
		tfMontoServicio.setText("");
		tfObservacion.setText("");
		modoAgregar = true;

	}
	private void LimpiarCodigo() {
		
          tfCodServicio.setText("");
	}
	private void habilitarCodigo() {
		
		tfCodServicio.setEnabled(true);
		tfDescripServicio.requestFocus();
			
			}
			private void deshabilitarCodigo() {
				
				tfCodServicio.setEnabled(false);
			}
			
			private void deshabilitarCampos() {
				
				tfDescripServicio.setEnabled(false);
				tfMontoServicio.setEnabled(false);
				tfObservacion.setEnabled(false);
			}
			
			private void habilitarCampos() {
				tfDescripServicio.setEnabled(true);
				tfMontoServicio.setEnabled(true);
				tfObservacion.setEnabled(true);
			}
			private void irParaModificar() {
				deshabilitarCodigo();
				habilitarCampos();
				btnGuardar.setText("actualizar");
				tfMontoServicio.requestFocus();

			}
			
			private void guardarServicio() {
				ser = new Servicio(); //creamos el objeto en memoria //instanciar una clase
			    ser.setCodigo(Integer.parseInt(tfCodServicio.getText()));//conversion de string a int > integer.parseInt(String);
			    ser.setMontoServicio(Integer.parseInt(tfMontoServicio.getText()));
			    ser.setDescripServicio(tfDescripServicio.getText());
			    ser.setObservacion(tfObservacion.getText());
			    
			    try{ 
			    	SesionServicio.guardarServicio(ser);
			    LimpiarCampos();
			    LimpiarCodigo();
			   //habilitarCampos();
				habilitarCodigo();
				tfCodServicio.requestFocus();
				modoAgregar = true;
				consultar();
				setEstadoEdicion(false);
				
				tfMontoServicio.requestFocus(); 
			    } catch (Exception e ){ //pasa por aca en caso q haya un error 
			    	e.printStackTrace();//imprime por consola el error ocurrido
			    }

			}
			
			private void modificar() {
				deshabilitarCodigo();
				habilitarCampos();
				btnGuardar.setText("actualizar");
				tfMontoServicio.requestFocus();
				ser = new Servicio();//creamos el objeto en memoria, instanciar una lase
				ser.setCodigo(Integer.parseInt(tfCodServicio.getText()));//conversion de String a int > Integer.parseint(string)
				ser.setMontoServicio(Integer.parseInt(tfMontoServicio.getText()));
				ser.setDescripServicio(tfDescripServicio.getText());
				ser.setObservacion(tfObservacion.getText());
				
				try {
					SesionServicio.modificarServicio(ser);
					
					btnGuardar.setText("Guardar");
					
					deshabilitarCampos();
					habilitarCodigo();
					LimpiarCampos();
					LimpiarCodigo();
					tfCodServicio.requestFocus();
					//JOptionPane.showMessageDialog(this, "registro modificado correctamente", "aviso",1);
					modoAgregar = true;
					setEstadoEdicion3(true);
					setEstadoEdicion4(false);
					setEstadoEdicion(false);
					
					consultar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
				
			}
		
			private void buscarServicioPorCodigo() {
				ser = new Servicio();//creamos el objeto en memoria, instanciar una lase
				
				int codigo = Integer.parseInt(tfCodServicio.getText());
				try {
					ser = SesionServicio.consultarServicioPorCodigo(codigo);
					if (ser != null) {
					
					tfDescripServicio.setText(ser.getDescripServicio());
					tfMontoServicio.setText(String.valueOf(ser.getMontoServicio()));
					tfObservacion.setText(ser.getObservacion());
					}else { 
						JOptionPane.showMessageDialog(this,"el codigo del Servicio no existe","Aviso",1);
						LimpiarCodigo();
					
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			
			private void borrarCliente() { //Borrar prestamo
				
				
				
				int seleccion = JOptionPane.showOptionDialog(this, "Desea eliminar al Servicio: "+tfCodServicio.getText()+", con codigo: "+tfCodServicio.getText(), "Borrar", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "Si");
				LimpiarCampos();
				deshabilitarCampos();
				tfCodServicio.requestFocus();
				
				if(seleccion==0){
					try {
						SesionServicio.borrarServicio(Integer.parseInt(tfCodServicio.getText()));
						//JOptionPane.showMessageDialog(this, "registro Eliminado ", "aviso",1);
						LimpiarCodigo();
						consultar();
					
						habilitarCodigo();
						tfCodServicio.requestFocus();
						
					} catch (NumberFormatException e) {
						
						e.printStackTrace();
					} catch (Exception e) {
						
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
					}
					
				}
				
			}
			
			private void cargarTabla() { //Metodo para cargar la tabla
				
				lista = SesionServicio.consultarTodosServicio(ser);
				LimpiarCampos();
				LimpiarCodigo();
		
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
				btnNuevo.setVisible(isEdicion);
			}
			
			private void setEstadoEdicion4(boolean isEdicion) {
				btnEliminar.setVisible(!isEdicion);
			}
			
			public void consultar() {
				while (dtmServicio.getRowCount()>0) {
					dtmServicio.removeRow(0);
					
				}

				lista= new ArrayList<Servicio>();
				try {
					lista = SesionServicio.consultarTodosPorFiltro(tfBuscar.getText());
				} catch (Exception e) {
					
					
					e.printStackTrace();
				}
				
				if (lista.size()>0) {
					cargarTabla2();
					
				}
				
			}  
				private void cargarTabla2() {
				
				String campos[]=new String[] {null,null,null,null};
						
					for (int i = 0; i < lista.size(); i++) {
						
						dtmServicio.addRow(campos);
						Servicio ser= lista.get(i);
						dtmServicio.setValueAt(ser.getCodigo(), i, 0);
						dtmServicio.setValueAt(ser.getDescripServicio(), i, 1);
						dtmServicio.setValueAt(ser.getMontoServicio(), i, 2);
						//dtmCliente.setValueAt(cli.getAutor().getCodigo(), i, 3);
						
						
					}	
				}
				
				public String getAbrir() {
					return abrir;
				}

				public void setAbrir(String abrir) {
					this.abrir = abrir;
				}
				private void seleccionarRegistroTabla(String abrir){
					
					Integer fila = this.table.getSelectedRow();
					String dato = String.valueOf(this.table.getValueAt(fila, 0));
					tfCodServicio.setText(dato); //cargamos el valor de dato en el campo codigo
						if(fila>=0){
							if(abrir.equals("S")){
								
								btnModificar.setEnabled(false);
								
								btnEliminar.setEnabled(false);
								
							}else{
								
								btnModificar.setEnabled(true);
								
								btnEliminar.setEnabled(true);
							}
						}
						Servicio habitacion = new Servicio();
						try {
							habitacion = SesionServicio.consultarServicioPorCodigo(Integer.parseInt(tfCodServicio.getText()));
							tfDescripServicio.setText(habitacion.getDescripServicio());
							tfMontoServicio.setText(String.valueOf(habitacion.getMontoServicio()));
							tfObservacion.setText(habitacion.getObservacion());
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
				
				public BuscarServicio getBuscarservicio() {
					return buscarservicio;
				}

				public void setBuscarservicio(BuscarServicio buscarservicio) {
					this.buscarservicio = buscarservicio;
				}
}


