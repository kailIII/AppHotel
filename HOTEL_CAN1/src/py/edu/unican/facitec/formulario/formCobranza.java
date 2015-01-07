package py.edu.unican.facitec.formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import py.edu.unican.facitec.entidades.Cobranza;
import py.edu.unican.facitec.entidades.Deuda;
import py.edu.unican.facitec.entidades.Habitacion;
import py.edu.unican.facitec.sesion.SesionCobranza;
import py.edu.unican.facitec.sesion.SesionEstadia;
import py.edu.unican.facitec.utilidades.Utilidad;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class formCobranza extends JDialog {
    
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private static Integer MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	private JFormattedTextField tfFecCobro;
	private MaskFormatter formatoFechaEntrada;
	private JPanel contentPane;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	private Panel panel;
	private JLabel lblNroCobranza;
	private JTextField tfNroCobranza;
	private JLabel lblNroDeuda;
	private JTextField tfNroDeuda;
	private JLabel lblCodCliente;
	private JTextField tfCodCliente;
	private JLabel lblMontoPagado;
	private JTextField tfMontoPagado;
	private JLabel lblBuscar;
	private JTextField tfBuscar;
	private JTable table;
	private DefaultTableModel dtmCobranza = new DefaultTableModel(null, new String[]{"Nro.Deuda", "Cliente ", "Monto Pagado", "Monto Deuda"});
	private Boolean modoAgregar = true;    
	private String abrir;
	private ArrayList<Deuda> lista;
	private Cobranza cobranza;
	//private JTextField tfFecCobro;
	private Deuda deuda;
	private JTextField tfDeuNroEstadia;
	private JTextField tfSaldoRestante;
	private JTextField tfsumaTotalPagado;
	private JTextField tfDeuMontoTotal;
	private JLabel lblSaldoRestante;
	private JTextField tfnombcliente;
	private JTextField tfmontoTotalPagado;
	public formCobranza() {
		setTitle("Cobranza");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 785, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setVisible(false);
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevo.setBounds(341, 35, 89, 32);
		contentPane.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setVisible(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irParaModificar();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(341, 88, 89, 32);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setVisible(false);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(341, 147, 89, 32);
		contentPane.add(btnEliminar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(btnGuardar.getText().equals("Guardar")){
					 if (tfMontoPagado.getText().equals("0")) {
						JOptionPane.showMessageDialog(null,"No se puede pagar con monto 0 ", "aviso",1);
						limpiarCamposCobranza();
						LimpiarCodigo();
						tfMontoPagado.setEditable(false);
					}else
					
					    GenerarDescuento();
						guardarCobranza();
						actualizarDeuda();
						
						
				
					
				}else {
					modificarCobranza();
				
			}
			}
		});
		btnGuardar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnGuardar.getText().equals("Guardar")){
					 if (tfMontoPagado.getText().equals("0")) {
						JOptionPane.showMessageDialog(null,"No se puede pagar con monto 0 ", "aviso",1);
						limpiarCamposCobranza();
						LimpiarCodigo();
						tfMontoPagado.setEditable(false);
					}else
					
					    GenerarDescuento();
						guardarCobranza();
						actualizarDeuda();
						
						
				
					
				}else {
					modificarCobranza();
				
			}

				
			}
			//}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(436, 421, 89, 30);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposCobranza();
				LimpiarCodigo();
				tfMontoPagado.setEditable(false);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(559, 421, 89, 30);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(666, 421, 99, 30);
		contentPane.add(btnSalir);
		
		panel = new Panel();
		panel.setLayout(null);
		panel.setBounds(436, 35, 333, 373);
		contentPane.add(panel);
		
		lblNroCobranza = new JLabel("Nro Cobranza");
		lblNroCobranza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNroCobranza.setBounds(10, 21, 90, 22);
		panel.add(lblNroCobranza);
		
		tfNroCobranza = new JTextField();
		tfNroCobranza.setEditable(false);
		tfNroCobranza.setColumns(10);
		tfNroCobranza.setBounds(132, 19, 118, 30);
		panel.add(tfNroCobranza);
		
		lblNroDeuda = new JLabel("Nro Deuda");
		lblNroDeuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNroDeuda.setBounds(10, 122, 112, 22);
		panel.add(lblNroDeuda);
		
		tfNroDeuda = new JTextField();
		tfNroDeuda.setEditable(false);
		tfNroDeuda.setColumns(10);
		tfNroDeuda.setBounds(132, 122, 118, 30);
		panel.add(tfNroDeuda);
		
		lblCodCliente = new JLabel(" C\u00F3digo  Cliente");
		lblCodCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodCliente.setBounds(10, 177, 112, 22);
		panel.add(lblCodCliente);
		
		tfCodCliente = new JTextField();
		tfCodCliente.setEditable(false);
		tfCodCliente.setColumns(10);
		tfCodCliente.setBounds(132, 175, 118, 30);
		panel.add(tfCodCliente);
		
		lblMontoPagado = new JLabel("Monto a Pagar");
		lblMontoPagado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMontoPagado.setBounds(10, 233, 112, 22);
		panel.add(lblMontoPagado);
		
		tfMontoPagado = new JTextField();
		tfMontoPagado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					GenerarDescuento();
					btnGuardar.requestFocus();
				}
			}
		});
		tfMontoPagado.setEditable(false);
		tfMontoPagado.setColumns(10);
		tfMontoPagado.setBounds(132, 233, 145, 30);
		panel.add(tfMontoPagado);
		
		tfFecCobro = new JFormattedTextField(formatearFecha());
		tfFecCobro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					tfMontoPagado.requestFocus();
				}
			}
		});
		tfFecCobro.setEditable(false);
		tfFecCobro.setColumns(10);
		tfFecCobro.setBounds(132, 72, 118, 30);
		panel.add(tfFecCobro);
		
		JLabel lblFechaCobro = new JLabel("Fecha Cobro");
		lblFechaCobro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaCobro.setBounds(10, 72, 112, 22);
		panel.add(lblFechaCobro);
		
		tfDeuNroEstadia = new JTextField();
		tfDeuNroEstadia.setVisible(false);
		tfDeuNroEstadia.setBounds(116, 342, 42, 20);
		panel.add(tfDeuNroEstadia);
		tfDeuNroEstadia.setColumns(10);
		
		tfSaldoRestante = new JTextField();
		tfSaldoRestante.setEditable(false);
		tfSaldoRestante.setColumns(10);
		tfSaldoRestante.setBounds(132, 286, 145, 30);
		panel.add(tfSaldoRestante);
		
		tfsumaTotalPagado = new JTextField();
		tfsumaTotalPagado.setVisible(false);
		tfsumaTotalPagado.setColumns(10);
		tfsumaTotalPagado.setBounds(176, 342, 62, 20);
		panel.add(tfsumaTotalPagado);
		
		tfDeuMontoTotal = new JTextField();
		tfDeuMontoTotal.setVisible(false);
		tfDeuMontoTotal.setColumns(10);
		tfDeuMontoTotal.setBounds(246, 317, 64, 20);
		panel.add(tfDeuMontoTotal);
		
		lblSaldoRestante = new JLabel("Saldo Restante");
		lblSaldoRestante.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSaldoRestante.setBounds(10, 286, 112, 22);
		panel.add(lblSaldoRestante);
		
		tfnombcliente = new JTextField();
		tfnombcliente.setVisible(false);
		tfnombcliente.setBounds(14, 342, 86, 20);
		panel.add(tfnombcliente);
		tfnombcliente.setColumns(10);
		
		tfmontoTotalPagado = new JTextField();
		tfmontoTotalPagado.setVisible(false);
		tfmontoTotalPagado.setBounds(248, 342, 62, 20);
		panel.add(tfmontoTotalPagado);
		tfmontoTotalPagado.setColumns(10);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(31, 399, 46, 20);
		contentPane.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				consultarPorNombre();
			}
		});
		tfBuscar.setColumns(10);
		tfBuscar.setBounds(83, 395, 235, 32);
		contentPane.add(tfBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 326, 381);
		contentPane.add(scrollPane);
		
		
		table = new JTable(dtmCobranza){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
		};
		//table = new JTable(dtmCobranza);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (modoAgregar){
					
					setAbrir("N");
					 
			seleccionarRegistroTablaDeuda(abrir);		
					
			}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnConDeuda = new JButton("Con Deuda");
		btnConDeuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarConDeuda();
			}
		});
		btnConDeuda.setBounds(234, 441, 102, 23);
		contentPane.add(btnConDeuda);
		
		JButton btnSinDeuda = new JButton("Sin Deuda");
		btnSinDeuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarSinDeuda();
			}
		});
		btnSinDeuda.setBounds(125, 441, 99, 23);
		contentPane.add(btnSinDeuda);
		
		JButton btnTodos = new JButton("Todos");
		btnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarTodos();
			}
		});
		btnTodos.setBounds(10, 441, 99, 23);
		contentPane.add(btnTodos);
		//scrollPane.setColumnHeaderView(table);
		//table.setModel(dtmCobranza);
	}

	public String getAbrir() {
		return abrir;
	}

	public void setAbrir(String abrir) {
		this.abrir = abrir;
	}
	
	private void seleccionarRegistroTablaDeuda( String abrir ){
  
		ultimoCodigo();
		tfFecCobro.setText(Utilidad.getFechaActual());
		Integer fila = this.table.getSelectedRow();
		String dato = String.valueOf(this.table.getValueAt( fila, 0 ));
		tfNroDeuda.setText(dato);
		String dato1 = String.valueOf(this.table.getValueAt( fila, 1 ));
		tfnombcliente.setText(dato1);
		
		String dato2 = String.valueOf(this.table.getValueAt( fila, 2 ));
		tfmontoTotalPagado.setText(dato2);
		
		String dato3 = String.valueOf(this.table.getValueAt( fila, 3 ));
		tfMontoPagado.setText(dato3);
		tfDeuMontoTotal.setText(dato3);
		tfFecCobro.setEditable(true);
		      consultarConDeuda();
		   if (tfMontoPagado.getText().equals("0")) {
			   
			   consultarConDeuda();
			   JOptionPane.showMessageDialog(null, "El Cliente " +tfnombcliente.getText()+ " ,ya ha cancelado toda su Deuda ", "Atención ", 2);
			   
			   consultarConDeuda();
			   limpiarCamposCobranza();
			   LimpiarCodigo();
			   tfMontoPagado.setEditable(false);
				}else{
					GenerarDescuento();
			Deuda deu = new Deuda();
			try {
				deu = SesionCobranza.consultarDeudaPorNro(Integer.parseInt(tfNroDeuda.getText() ));
				
				tfCodCliente.setText(String.valueOf(deu.getCodCliente().getCodigo( )));
				tfDeuNroEstadia.setText(String.valueOf(deu.getNroEstadia().getCodigo()));
				tfMontoPagado.setEditable(true);
				tfMontoPagado.requestFocus();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			btnGuardar.setVisible(true);
			btnCancelar.setVisible(true);
		}
	
}
	

	private void cargarTablaDeuda() {
		
		String campos[]= new String[] {null,null,null,null};
				
			for (int i = 0; i < lista.size(); i++) {
				
				dtmCobranza.addRow(campos);
				
				
				Deuda cobra = lista.get(i);
				dtmCobranza.setValueAt(cobra.getCodigo(), i, 0);
				dtmCobranza.setValueAt(cobra.getCodCliente().getNombre(), i, 1);
				dtmCobranza.setValueAt(cobra.getMontoPagado(), i, 2);
				dtmCobranza.setValueAt(cobra.getMontoDeuda(), i, 3);
				
				}
					

	}
	
	private void ultimoCodigo() { 
		int id;
		try {
			id =  SesionCobranza.recupetarUltimoCodigo() + 1;
			tfNroCobranza.setText(String.valueOf(id));
		} catch (Exception e) {
			
			e.printStackTrace();
		}

}	
	
	private void guardarCobranza() {			     
		cobranza = new Cobranza(); //creamos el objeto en memoria //instanciar una clase
		
	    cobranza.setCodigo(Integer.parseInt(tfNroCobranza.getText()));//conversion de string a int > integer.parseInt(String);
	    cobranza.getnroDeuada().setCodigo(Integer.parseInt(tfNroDeuda.getText()));
	    cobranza.getcodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
	    cobranza.setcobmontopagado(Integer.parseInt(tfMontoPagado.getText()));
	    cobranza.setfechaCobro(Utilidad.retornarFecha(tfFecCobro.getText()));
	    
	    try{ //pasa por aca en caso q no exista un error por el camino
	    	//le pasamos el objeto con los valores recivido del formulario del libros
	    	
	    SesionCobranza.guardarCobranza(cobranza);
	    tfMontoPagado.setEditable(false);
	    consultarConDeuda();
	   // ocultarButtonCobranza();
	    btnGuardar.setVisible(false);
	    btnCancelar.setVisible(false);
	    btnNuevo.setEnabled(true);
		btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        tfFecCobro.setEditable(false);
		//JOptionPane.showMessageDialog(null, "hospedaje guardado correctamente", "Aviso",1);
		// el cursor se posiciona en el componente especificado
	    } catch (Exception e ){ //pasa por aca en caso q haya un error 
	    	e.printStackTrace();//imprime por consola el error ocurrido
	    }

	
	}
	
	private void LimpiarCodigo() {
		//le damos el valor vacio
		
          tfNroCobranza.setText("");
	}
	private void limpiarCamposCobranza() {
		//limpiamos los campos de textos
		tfNroDeuda.setText(""); //le damos el valor vacio
		tfCodCliente.setText("");
		tfMontoPagado.setText("");
		tfFecCobro.setText("");
		tfSaldoRestante.setText("");
		//tfNroDetalle.setText("");

	}
	
	 private void mostrarButtonDetalle() {
   		btnCancelar.setVisible(true);
   		btnGuardar.setVisible(true);
   		btnEliminar.setVisible(true);
   		
   	}
       
       private void ocultarButtonDetalle() {
     		btnCancelar.setVisible(false);
     		btnGuardar.setVisible(false);
     		btnEliminar.setVisible(false);
     		
     	}
       
       private void modificarCobranza() {
			
			btnGuardar.setText("actualizar");
			
			tfCodCliente.requestFocus();
				
			cobranza = new Cobranza(); //creamos el objeto en memoria, instanciar una lase
			
			cobranza.setCodigo(Integer.parseInt(tfNroCobranza.getText()));  //conversion de string a int > integer.parseInt(String);
			cobranza.setfechaCobro(Utilidad.retornarFecha(tfFecCobro.getText()));
			cobranza.getcodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
			cobranza.getnroDeuada().setCodigo(Integer.parseInt(tfNroDeuda.getText()));
			cobranza.setcobmontopagado(Integer.parseInt(tfMontoPagado.getText()));
			
							try { 
				 SesionCobranza.modificarCobranza(cobranza);
				 tfMontoPagado.setEditable(false);
			    	btnGuardar.setText("Guardar");
			    	
			             limpiarCamposCobranza();
			             LimpiarCodigo();
			             table.setEnabled(true);
			                btnNuevo.setEnabled(true);
							btnModificar.setEnabled(true);
			                btnEliminar.setEnabled(true);
				//JOptionPane.showMessageDialog(this, "registro modificado correctamente", "aviso",1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			}
       
       private void actualizarDeuda() {
				
			deuda = new Deuda();
			
			deuda.setCodigo(Integer.parseInt(tfNroDeuda.getText()));
	        deuda.getCodCliente().setCodigo(Integer.parseInt(tfCodCliente.getText()));
			deuda.getNroEstadia().setCodigo(Integer.parseInt(tfDeuNroEstadia.getText()));
			deuda.setMontoPagado(Integer.parseInt(tfsumaTotalPagado.getText()));
			deuda.setMontoDeuda(Integer.parseInt(tfSaldoRestante.getText()));
							try { 
				 SesionCobranza.ActualizaDeuda(deuda);
				 limpiarCamposCobranza();
				    LimpiarCodigo();
				    consultarConDeuda();
			} catch (Exception e) {
				e.printStackTrace();
				
			}
					}
			
       
       private void irParaModificar() {		
    		
    		
    		btnGuardar.setText("actualizar");
    		tfMontoPagado.setEditable(true);
    		table.setEnabled(false);
    		consultarConDeuda();
    		
    		}
    		
       private void GenerarDescuento() {
   		
   		String subTotal = tfDeuMontoTotal.getText();
   		
   		if (tfMontoPagado.getText().equals("")) {
   			
   			tfSaldoRestante.setText(subTotal);
   			
   			tfMontoPagado.setText("0");
   			
   		}else{
   		Integer restaDescuento= Integer.parseInt(tfDeuMontoTotal.getText()) - Integer.parseInt(tfMontoPagado.getText());
   		tfSaldoRestante.setText(String.valueOf(restaDescuento));
   		
   		Integer sumaPago=Integer.parseInt(tfmontoTotalPagado.getText())+Integer.parseInt(tfMontoPagado.getText());
   		tfsumaTotalPagado.setText(String.valueOf(sumaPago));
   	}
   	}
       
     public void consultarConDeuda() {
    	 
   		while (dtmCobranza.getRowCount()>0) {
   			dtmCobranza.removeRow(0);
   		}

   		lista = new ArrayList<Deuda>();
   		try {
   			lista = SesionCobranza.consultarConDeuda();
   			
   		} catch (Exception e) {
   			
   			e.printStackTrace();
   		}
   		
   		if (lista.size()> 0) { 
   			
   			cargarTablaDeuda(); 
   			
   		}
   		
   	} 
     public void consultarTodos() {
    	 
    		while (dtmCobranza.getRowCount()>0) {
    			dtmCobranza.removeRow(0);
    			
    		}

    		lista = new ArrayList<Deuda>();
    		try {
    			lista = SesionCobranza.consultarTodosDeuda();
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    		}
    		
    		if (lista.size()> 0) {
    			
    			cargarTablaDeuda();
    			
    		}
    		
    	} 
     public void consultarSinDeuda() {
    	 
    		while (dtmCobranza.getRowCount()>0) {
    			dtmCobranza.removeRow(0);
    			
    		}

    		lista = new ArrayList<Deuda>();
    		try {
    			lista = SesionCobranza.consultarSinDeuda();
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    		}
    		
    		if (lista.size()> 0) {
    			
    			cargarTablaDeuda();
    			
    		}
    		
    	} 
     
     
     public void consultarPorNombre() {
			
			while (dtmCobranza.getRowCount()>0) {
				dtmCobranza.removeRow(0);
				
			}

			lista= new ArrayList<Deuda>();
			try {
				lista = SesionCobranza.consultarTodosPorNombre(tfBuscar.getText());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if (lista.size()>0) {
				cargarTablaDeuda();
				
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
}
