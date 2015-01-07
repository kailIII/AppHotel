package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Panel;

import javax.swing.JButton;

import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.sesion.SesionHabitacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class formHabitacion extends JDialog {

	private JPanel contentPane;
	private JTextField TfcodHabitacion;
	private JTextField TfdescripHabitacion;
	private JTextField TfMontoDia;
	private JTextField TfObservacion;
	private JTextField TfBuscar;
	private Panel panel;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnSalir;
	private Habitacion hab;
	private ArrayList<Habitacion> lista;
	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel dtmHabita = new DefaultTableModel(null, new String[]{"codigo","Descripcion","Monto Por Dia"});
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblCampoNumerico;
	private Boolean modoAgregar = true;
	private String abrir;
	private JTextField tfActivo;
	public formHabitacion() {
		setTitle("Habitaci\u00F3n");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 535);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 439, 52, 23);
		contentPane.add(lblBuscar);
		
		TfBuscar = new JTextField();
		TfBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		TfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				consultar();
			
			}
		});
		TfBuscar.setBounds(67, 437, 223, 30);
		contentPane.add(TfBuscar);
		TfBuscar.setColumns(10);
		
		panel = new Panel();
		panel.setBounds(419, 11, 350, 395);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TfcodHabitacion = new JTextField();
		TfcodHabitacion.setEnabled(false);
		TfcodHabitacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
					if (e.getKeyCode()== KeyEvent.VK_ENTER) {
						//TfdescripHabitacion.requestFocus();
						consultar();
					}
			}
		});
		TfcodHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
				buscarHabitacionPorCodigo();
			}
		});
		TfcodHabitacion.setBounds(155, 9, 188, 30);
		panel.add(TfcodHabitacion);
		TfcodHabitacion.setColumns(10);
		
		JLabel lblCodHabitacion = new JLabel("C\u00F3digo Habitaci\u00F3n");
		lblCodHabitacion.setBounds(10, 11, 120, 23);
		panel.add(lblCodHabitacion);
		lblCodHabitacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDescipHabita = new JLabel("Descripci\u00F3n Habitaci\u00F3n");
		lblDescipHabita.setBounds(10, 83, 145, 23);
		panel.add(lblDescipHabita);
		lblDescipHabita.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMontoPorDa = new JLabel("Monto por d\u00EDa");
		lblMontoPorDa.setBounds(10, 150, 101, 23);
		panel.add(lblMontoPorDa);
		lblMontoPorDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblObservacin = new JLabel("Observaci\u00F3n");
		lblObservacin.setBounds(10, 220, 82, 23);
		panel.add(lblObservacin);
		lblObservacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TfObservacion = new JTextField();
		TfObservacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		TfObservacion.setEnabled(false);
		TfObservacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnGuardar.requestFocus();
				}
			}
		});
		TfObservacion.setBounds(155, 205, 188, 56);
		panel.add(TfObservacion);
		TfObservacion.setColumns(10);
		
		TfMontoDia = new JTextField();
		TfMontoDia.setFont(new Font("Tahoma", Font.BOLD, 11));
		TfMontoDia.setEnabled(false);
		TfMontoDia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					TfObservacion.requestFocus();
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
		TfMontoDia.setBounds(154, 148, 189, 30);
		panel.add(TfMontoDia);
		TfMontoDia.setColumns(10);
		
		TfdescripHabitacion = new JTextField();
		TfdescripHabitacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		TfdescripHabitacion.setEnabled(false);
		TfdescripHabitacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					TfMontoDia.requestFocus();
				}
			}
		});
		TfdescripHabitacion.setBounds(155, 68, 188, 56);
		panel.add(TfdescripHabitacion);
		TfdescripHabitacion.setColumns(10);
		
		lblCampoNumerico = new JLabel("CampoNumerico");
		lblCampoNumerico.setVisible(false);
		lblCampoNumerico.setForeground(Color.RED);
		lblCampoNumerico.setBounds(155, 135, 136, 14);
		panel.add(lblCampoNumerico);
		
		tfActivo = new JTextField();
		tfActivo.setVisible(false);
		tfActivo.setBounds(155, 289, 86, 20);
		panel.add(tfActivo);
		tfActivo.setColumns(10);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoCliente();
				LimpiarCampos();
				setEstadoEdicion(true);
				consultar();
				modoAgregar = false;
			}
		});
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevo.setBounds(304, 27, 89, 32);
		contentPane.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (TfcodHabitacion.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "No hay nada para Modificar", "aviso",1);
				}else{
					  
					if (tfActivo.getText().equals("0") ) {
				    		
				    		JOptionPane.showMessageDialog(null, "No se puede Modificar por que ya tiene un cierre", "aviso",1 );
				    	}else{
				irParaModificar();
				setEstadoEdicion2(true);
				setEstadoEdicion3(false);
				setEstadoEdicion4(true);
				}
			}
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(304, 93, 89, 32);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (TfcodHabitacion.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "No hay nada para Elininar", "aviso",1);
				}else{
				borrarHabitacion();
				}
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(304, 154, 89, 32);
		contentPane.add(btnEliminar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setVisible(false);
		btnGuardar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			
				if (TfdescripHabitacion.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "La descripcion  es Obligatorio","Aviso",1);
		            TfdescripHabitacion.requestFocus();
		            return;
		        }
				if (TfMontoDia.getText().length() == 0) {
					 JOptionPane.showMessageDialog(null, "el monto por dia es Obligatorio","Aviso",1);
		            TfMontoDia.requestFocus();
		            return;
		        }
				
		    		if (formHabitacion.this.btnGuardar.getText().compareTo("Guardar")==0) {
						guardarHabitacion();
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
				
				if (TfdescripHabitacion.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "La descripcion  es Obligatorio","Aviso",1);
		            TfdescripHabitacion.requestFocus();
		            return;
		        }
				if (TfMontoDia.getText().length() == 0) {
					 JOptionPane.showMessageDialog(null, "el monto por dia es Obligatorio","Aviso",1);
		            TfMontoDia.requestFocus();
		            return;
		        }
		    		if (formHabitacion.this.btnGuardar.getText().compareTo("Guardar")==0) {
						guardarHabitacion();
						LimpiarCampos();
						deshabilitarCampos();
						habilitarCodigo();
					}else {
						
						modificar();	
					}	
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(429, 431, 99, 30);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardar.setText("Guardar");
				LimpiarCampos();
				habilitarCodigo();
				deshabilitarCampos();
				LimpiarCodigo();
				setEstadoEdicion(false);
				setEstadoEdicion3(true);
				TfcodHabitacion.requestFocus();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(541, 431, 99, 30);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(657, 431, 99, 30);
		contentPane.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 11, 280, 414);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table = new JTable(dtmHabita){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
		};
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				if (modoAgregar){
					setAbrir("N");
					 
					seleccionarRegistroTabla(abrir);	
			}
				
			}
		});
		table.setModel(dtmHabita);
		scrollPane.setViewportView(table);
	}
	private void nuevoCliente() {
		ultimoCodigo();
	}
	private void LimpiarCampos() {
		TfdescripHabitacion.setText("");
		TfMontoDia.setText("");
		TfObservacion.setText("");
		
		modoAgregar = true;
	}
	private void LimpiarCodigo() {
          TfcodHabitacion.setText("");
	}
	private void habilitarCodigo() {
		
		TfcodHabitacion.setEnabled(true);
		TfdescripHabitacion.requestFocus();
			
			}
			private void deshabilitarCodigo() {
				
				TfcodHabitacion.setEnabled(false);
			}
			
			private void deshabilitarCampos() {
				
				TfdescripHabitacion.setEnabled(false);
				TfMontoDia.setEnabled(false);
				TfObservacion.setEnabled(false);
			}
			
			private void habilitarCampos() {
				TfdescripHabitacion.setEnabled(true);
				TfMontoDia.setEnabled(true);
				TfObservacion.setEnabled(true);
			}
			private void irParaModificar() {
				deshabilitarCodigo();
				habilitarCampos();
				btnGuardar.setText("actualizar");
				TfdescripHabitacion.requestFocus();
				

			}
			private void ultimoCodigo() { 
				int id;
		        
				try {
					id = SesionHabitacion.recupetarUltimoCodigo() + 1;
					TfcodHabitacion.setText(String.valueOf(id));
					habilitarCampos();//habilita todos los campos para la carga 
					deshabilitarCodigo();
					TfdescripHabitacion.requestFocus();

				} catch (Exception e) {
					
					e.printStackTrace();
				}
		}
			private void guardarHabitacion() {
				hab = new Habitacion(); //creamos el objeto en memoria //instanciar una clase
			    hab.setCodigo(Integer.parseInt(TfcodHabitacion.getText()));//conversion de string a int > integer.parseInt(String);
			    hab.setMontoDia(Integer.parseInt(TfMontoDia.getText()));
			    hab.setDescripHabitacion(TfdescripHabitacion.getText());
			    hab.setObservacion(TfObservacion.getText());
			    hab.setHabActivo(1);
			    
			    try{ //pasa por aca en caso q no exista un error por el camino
			    	//le pasamos el objeto con los valores recivido del formulario del libros
			    	
			    	SesionHabitacion.guardarHabitacion(hab);
			    LimpiarCampos();
			    LimpiarCodigo();
				habilitarCodigo();
				consultar();
				TfcodHabitacion.requestFocus();
				setEstadoEdicion(false);
				modoAgregar = true;
				TfcodHabitacion.requestFocus(); 
			    } catch (Exception e ){ 
			    	e.printStackTrace();
			    }

			}
			
			private void modificar() {
				deshabilitarCodigo();
				habilitarCampos();
				btnGuardar.setText("actualizar");
				hab = new Habitacion();//creamos el objeto en memoria, instanciar una lase
				hab.setCodigo(Integer.parseInt(TfcodHabitacion.getText()));//conversion de String a int > Integer.parseint(string)
				hab.setMontoDia(Integer.parseInt(TfMontoDia.getText()));
				hab.setDescripHabitacion(TfdescripHabitacion.getText());
				hab.setObservacion(TfObservacion.getText());
				hab.setHabActivo(1);
				
				try {
					SesionHabitacion.modificarHabitacion(hab);
					
					btnGuardar.setText("Guardar");
					
					deshabilitarCampos();
					habilitarCodigo();
					LimpiarCampos();
					LimpiarCodigo();
					
					//JOptionPane.showMessageDialog(this, "registro modificado correctamente", "aviso",1);
					modoAgregar = true;
					setEstadoEdicion3(true);
					setEstadoEdicion4(false);
					consultar();
					
					TfcodHabitacion.requestFocus();
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			private void buscarHabitacionPorCodigo() {
				hab = new Habitacion();//creamos el objeto en memoria, instanciar una lase
				
				int codigo = Integer.parseInt(TfcodHabitacion.getText());
				try {
					hab = SesionHabitacion.consultarHabitacionPorCodigo(codigo);
					if (hab != null) {
					
					TfdescripHabitacion.setText(hab.getDescripHabitacion());
					TfMontoDia.setText(String.valueOf(hab.getMontoDia()));
					TfObservacion.setText(hab.getObservacion());
					deshabilitarCampos();
					TfcodHabitacion.requestFocus();
					}else {
						JOptionPane.showMessageDialog(this,"el codigo de la habitacion no existe","Aviso",1);
						TfcodHabitacion.requestFocus();
						LimpiarCodigo();
						LimpiarCampos();
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				private void borrarHabitacion() { //Borrar prestamo
				
				
				
				int seleccion = JOptionPane.showOptionDialog(this, "Desea eliminar la habitacion: "+TfcodHabitacion.getText()+", con codigo: "+TfcodHabitacion.getText(), "Borrar", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "Si");
				TfcodHabitacion.requestFocus();
				LimpiarCampos();
				
				if(seleccion==0){
					try {
						SesionHabitacion.borrarHabitacion(Integer.parseInt(TfcodHabitacion.getText()));
						//cargarTabla();
						//JOptionPane.showMessageDialog(this, "registro Eliminado ", "aviso",1);
						LimpiarCodigo();
						consultar();
						
					} catch (NumberFormatException e) {
						
						e.printStackTrace();
					} catch (Exception e) {
						
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "No se puede eliminar porque el registro está en uso", "Aviso", 2);
						TfcodHabitacion.requestFocus();
					}
					
				  }
				}
				public void consultar() {
					while (dtmHabita.getRowCount()>0) {
						dtmHabita.removeRow(0);
						
					}

					lista= new ArrayList<Habitacion>();
					try {
						lista = SesionHabitacion.consultarTodosPorFiltro(TfBuscar.getText());
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
							
							dtmHabita.addRow(campos);
							Habitacion cli= lista.get(i);
							dtmHabita.setValueAt(cli.getCodigo(), i, 0);
							dtmHabita.setValueAt(cli.getDescripHabitacion(), i, 1);
							dtmHabita.setValueAt(cli.getMontoDia(), i, 2);
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
						btnNuevo.setVisible(isEdicion);
					}
					
					private void setEstadoEdicion4(boolean isEdicion) {
						btnEliminar.setVisible(!isEdicion);
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
						TfcodHabitacion.setText(dato); //cargamos el valor de dato en el campo codigo
							if(fila>=0){
								if(abrir.equals("S")){
									
									btnModificar.setEnabled(false);
									
									btnEliminar.setEnabled(false);
									
								}else{
									
									btnModificar.setEnabled(true);
									
									btnEliminar.setEnabled(true);
								}
							}
							Habitacion habitacion = new Habitacion();
							try {
								habitacion = SesionHabitacion.consultarHabitacionPorCodigo(Integer.parseInt(TfcodHabitacion.getText()));
								TfdescripHabitacion.setText(habitacion.getDescripHabitacion());
								TfMontoDia.setText(String.valueOf(habitacion.getMontoDia()));
								TfObservacion.setText(habitacion.getObservacion());
								tfActivo.setText(String.valueOf(habitacion.getHabActivo()));
							} catch (Exception e) {
								e.printStackTrace();
							}
					}
}
