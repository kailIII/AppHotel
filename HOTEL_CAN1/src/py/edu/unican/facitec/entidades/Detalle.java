	package py.edu.unican.facitec.entidades;

	public class Detalle extends General {
	
	//private int numerodetalle;
	private Estadia numeroEstadia;
	private Servicio codServicio;
	private int monto;
	
	public Detalle() {
		super();
		//this.numerodetalle = 0;
		this.numeroEstadia = new Estadia();
		this.codServicio = new Servicio();
		this.monto = 0;
	}

	public Detalle(Estadia numeroEstadia, Servicio codServicio, int monto) {
		super();
		this.numeroEstadia = numeroEstadia;
		this.codServicio = codServicio;
		this.monto = monto;
	}

	public Estadia getNumeroEstadia() {
		return numeroEstadia;
	}

	public void setNumeroEstadia(Estadia numeroEstadia) {
		this.numeroEstadia = numeroEstadia;
	}

	public Servicio getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(Servicio codServicio) {
		this.codServicio = codServicio;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}
	

}