package py.edu.unican.facitec.entidades;

public class Usuario extends General {
	private String nombre;
	private String clave;
	
	public Usuario() {
		super();
		this.nombre = "";
		this.clave = "";
		
	}

	public Usuario(String nombre, String clave) {
		super();
		this.nombre = nombre;
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	
	
	
	
}

