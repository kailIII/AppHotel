	package py.edu.unican.facitec.entidades;

	public class Config extends General{
	
		private String NombreHotel;
		private String telHotel;
		
		public Config() {
			super();
			
			this.NombreHotel = "";
			this.telHotel = "";
		}

		public Config(String nombreHotel, String telHotel) {
			super();
			NombreHotel = nombreHotel;
			this.telHotel = telHotel;
		}

		public String getNombreHotel() {
			return NombreHotel;
		}

		public void setNombreHotel(String nombreHotel) {
			NombreHotel = nombreHotel;
		}

		public String getTelHotel() {
			return telHotel;
		}

		public void setTelHotel(String telHotel) {
			this.telHotel = telHotel;
		}
		

}
