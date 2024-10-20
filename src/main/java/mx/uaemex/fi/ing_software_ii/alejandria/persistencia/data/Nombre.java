package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

import java.util.Objects;

public class Nombre {
	
	 private String primerApellido;
	 private String segundoApellido;
	 private String nombreDePila;
	 
	 public Nombre() {}
	 
	 public Nombre(String nombreDePila) {
		 this.nombreDePila = nombreDePila;
		 this.primerApellido = nombreDePila;
		 this.segundoApellido = nombreDePila;
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
	public String getNombreDePila() {
		return nombreDePila;
	}
	public void setNombreDePila(String nombreDePila) {
		this.nombreDePila = nombreDePila;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreDePila, primerApellido, segundoApellido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nombre other = (Nombre) obj;
		return Objects.equals(nombreDePila, other.nombreDePila) && Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(segundoApellido, other.segundoApellido);
	}
	
	
	
	
	    
}
