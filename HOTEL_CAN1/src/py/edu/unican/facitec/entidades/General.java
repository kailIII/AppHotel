	package py.edu.unican.facitec.entidades;

	public class General {
	
	//atributo de la clase
		private int codigo;
	
	//contructor por defectp
	public General() {
		super();
		this.codigo = 0;
	}
	
	//contructor por parametro
	public General(int codigo, String nombre) {
		super();
		this.codigo = codigo;
	}

   //metodo getter and setter
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setNombre(String nombre) {
		
	}
}
