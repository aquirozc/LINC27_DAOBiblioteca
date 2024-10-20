package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

import java.util.Objects;

public class Usuario {
	private String login;
    private String password;
    private Nombre nombre;
    private TipoUsuario tipo;
    
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Nombre getNombre() {
		return nombre;
	}
	public void setNombre(Nombre nombre) {
		this.nombre = nombre;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(login, nombre, password, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(login, other.login) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && tipo == other.tipo;
	}
	
	
    
}
