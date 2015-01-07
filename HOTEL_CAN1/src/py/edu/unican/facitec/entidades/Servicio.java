	package py.edu.unican.facitec.entidades;

	public class Servicio extends General {
		private String descripServicio;
		private int montoServicio;
		private String observacion;
	public Servicio() {
		super();
		this.descripServicio = "";
		this.montoServicio = 0;
		this.observacion = "";
	}
	public Servicio(String descripServicio, int montoServicio,
			String observacion) {
		super();
		this.descripServicio = descripServicio;
		this.montoServicio = montoServicio;
		this.observacion = observacion;
	}

	public String getDescripServicio() {
		return descripServicio;
	}

	public void setDescripServicio(String descripServicio) {
		this.descripServicio = descripServicio;
	}

	public int getMontoServicio() {
		return montoServicio;
	}

	public void setMontoServicio(int montoServicio) {
		this.montoServicio = montoServicio;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
