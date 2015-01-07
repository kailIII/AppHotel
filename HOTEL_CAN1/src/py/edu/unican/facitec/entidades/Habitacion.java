	package py.edu.unican.facitec.entidades;

	public class Habitacion extends General{

	//private int codHabitacion;
		private String descripHabitacion;
		private int montoDia;
		private String observacion;
		private int habActivo;
	
	public Habitacion() {
		super();
		//this.codHabitacion = 0;
		this.descripHabitacion = "";
		this.montoDia = 0;
		this.observacion = "";
		this.habActivo = 1;
	}

	public Habitacion(String descripHabitacion, int montoDia,
			String observacion, int habActivo) {
		super();
		this.descripHabitacion = descripHabitacion;
		this.montoDia = montoDia;
		this.observacion = observacion;
		this.habActivo = habActivo;
	}

	public String getDescripHabitacion() {
		return descripHabitacion;
	}

	public void setDescripHabitacion(String descripHabitacion) {
		this.descripHabitacion = descripHabitacion;
	}

	public int getMontoDia() {
		return montoDia;
	}

	public void setMontoDia(int montoDia) {
		this.montoDia = montoDia;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getHabActivo() {
		return habActivo;
	}

	public void setHabActivo(int habActivo) {
		this.habActivo = habActivo;
	}

	
	
}
