package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

public class Nombre {
	
	private String nombreDePila;
	private String primerApellido;
	private String segundoApellido;
	
	public Nombre(String nombreDePila, String primerApellido, String segundoApellido) {
		super();
		this.nombreDePila = nombreDePila;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
	}

	public String getNombreDePila() {
		return nombreDePila;
	}
	
	public void setNombreDePila(String nombreDePila) {
		this.nombreDePila = nombreDePila;
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	@Override
	public String toString() {
	    return "Nombre: " + nombreDePila + " " + primerApellido + " " + segundoApellido;
	}
}
