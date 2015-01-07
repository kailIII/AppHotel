	package py.edu.unican.facitec.entidades;

	public class Cliente extends General {
	
	//atributos de la clase
		private String nombre;
		private int cedula;
		private String ruc ;
		private String direccion;
		private String telefono;

	//constructor por defecto
	public Cliente() {
		super();
		this.nombre = "";
		this.cedula = 0;
		this.ruc = "";
		this.direccion = "";
		this.telefono = "";
		
	}

	public Cliente(String nombre, int cedula, String ruc, String direccion,
			String telefono) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.ruc = ruc;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	

}
