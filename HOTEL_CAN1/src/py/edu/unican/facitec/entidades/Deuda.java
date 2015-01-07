	package py.edu.unican.facitec.entidades;

	public class Deuda extends General {

	//private int numeroDeuda;
		private Estadia nroEstadia;
		private Cliente codCliente;
		private int montoDeuda;
		private int montoPagado;
	
	public Deuda() {
		super();
		//this.numeroDeuda = 0;
		this.nroEstadia = new Estadia();
		this.codCliente = new Cliente();
		this.montoDeuda = 0;
		this.montoPagado = 0;
	}

	public Deuda(Estadia nroEstadia, Cliente codCliente, int montoDeuda,
			int montoPagado, int activo) {
		super();
		this.nroEstadia = nroEstadia;
		this.codCliente = codCliente;
		this.montoDeuda = montoDeuda;
		this.montoPagado = montoPagado;
	}

	public Estadia getNroEstadia() {
		return nroEstadia;
	}

	public void setNroEstadia(Estadia nroEstadia) {
		this.nroEstadia = nroEstadia;
	}

	public Cliente getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Cliente codCliente) {
		this.codCliente = codCliente;
	}

	public int getMontoDeuda() {
		return montoDeuda;
	}

	public void setMontoDeuda(int montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public int getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(int montoPagado) {
		this.montoPagado = montoPagado;
	}

	
	
}
