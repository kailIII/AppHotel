		package py.edu.unican.facitec.entidades;

		import java.util.Date;

		public class Estadia extends General {
	             //atributos de la clase
	    	private Date fechaEntrada; // despues tengo que cambiar su tipo date no String
	    	private Cliente codCliente;
	    	private Habitacion codHabitacion;
	    	private Date fechaSalida;  // despues tengo que cambiar su tipo date no String
	    	private int montoTotal;   // tengo que revisar despues si es Double o otro tipo
	    	private String observacion;
	    	private int activoInActivo; //despues tengo que cambiar su tipo , no String
	    	private int descuento;
			
		public Estadia() {
			super();
			//this.nroEstadia = 0;
			this.fechaEntrada = new Date();
			this.codCliente = new Cliente();
			this.codHabitacion = new Habitacion();
			this.fechaSalida = new Date();
			this.montoTotal = 0;
			this.observacion = "";
			this.activoInActivo = 1;
			this.descuento = 0;
			//this.NombClient = "";
		}

		public Estadia(Date fechaEntrada, Cliente codCliente,
				Habitacion codHabitacion, Date fechaSalida, int montoTotal,
				String observacion, int activoInActivo, int descuento) {
			super();
			this.fechaEntrada = fechaEntrada;
			this.codCliente = codCliente;
			this.codHabitacion = codHabitacion;
			this.fechaSalida = fechaSalida;
			this.montoTotal = montoTotal;
			this.observacion = observacion;
			this.activoInActivo = activoInActivo;
			this.descuento = descuento;
		}

		public Date getFechaEntrada() {
			return fechaEntrada;
		}

		public void setFechaEntrada(Date fechaEntrada) {
			this.fechaEntrada = fechaEntrada;
		}

		public Cliente getCodCliente() {
			return codCliente;
		}

		public void setCodCliente(Cliente codCliente) {
			this.codCliente = codCliente;
		}

		public Habitacion getCodHabitacion() {
			return codHabitacion;
		}

		public void setCodHabitacion(Habitacion codHabitacion) {
			this.codHabitacion = codHabitacion;
		}

		public Date getFechaSalida() {
			return fechaSalida;
		}

		public void setFechaSalida(Date fechaSalida) {
			this.fechaSalida = fechaSalida;
		}

		public int getMontoTotal() {
			return montoTotal;
		}

		public void setMontoTotal(int montoTotal) {
			this.montoTotal = montoTotal;
		}

		public String getObservacion() {
			return observacion;
		}

		public void setObservacion(String observacion) {
			this.observacion = observacion;
		}

		public int getActivoInActivo() {
			return activoInActivo;
		}

		public void setActivoInActivo(int activoInActivo) {
			this.activoInActivo = activoInActivo;
		}

		public int getDescuento() {
			return descuento;
		}

		public void setDescuento(int descuento) {
			this.descuento = descuento;
		}

		
		
		
		
		

		
}