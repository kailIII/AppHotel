	package py.edu.unican.facitec.entidades;

	import java.util.Date;

	public class Cobranza extends General{
		private int nroCobranza;
		private Deuda nroDeuada;
		private Cliente codCliente;
		private int cobmontopagado;
		private Date fechaCobro;
	
	public Cobranza() {
		super();
		
		this.nroCobranza = 0;
		this.nroDeuada = new Deuda ();
		this.codCliente = new Cliente();
		this.cobmontopagado = 0;
		this.fechaCobro = new Date();
	}

	

	public Cobranza(int nroCobranza, Deuda nroDeuada, Cliente codCliente,
			int cobPagado, Date fechaCobro) {
		super();
		this.nroCobranza = nroCobranza;
		this.nroDeuada = nroDeuada;
		this.codCliente = codCliente;
		this.cobmontopagado = cobPagado;
		this.fechaCobro = fechaCobro;
	}



	public int getnroCobranza() {
		return nroCobranza;
	}

	public void setnroCobranza(int nroCobranza) {
		this.nroCobranza = nroCobranza;
	}

	public Deuda getnroDeuada() {
		return nroDeuada;
	}

	public void setnroDeuada(Deuda nroDeuada) {
		this.nroDeuada = nroDeuada;
	}

	public Cliente getcodCliente() {
		return codCliente;
	}

	public void setcodCliente(Cliente codCliente) {
		this.codCliente = codCliente;
	}

	public int getcobmontopagado() {
		return cobmontopagado;
	}

	public void setcobmontopagado(int cobPagado) {
		this.cobmontopagado = cobPagado;
	}



	public Date getfechaCobro() {
		return fechaCobro;
	}



	public void setfechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	

}
