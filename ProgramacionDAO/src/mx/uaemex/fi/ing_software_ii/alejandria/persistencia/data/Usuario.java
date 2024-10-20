package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

public class Usuario extends ElementoConId{
	private String login;
	private String password;
	private TipoUsuario tipo;
	private Nombre nombre;

	public Usuario(long id, String login, String password, TipoUsuario tipo, Nombre nombre) {
		super(id);
		this.login = login;
		this.password = password;
		this.tipo = tipo;
		this.nombre = nombre;
	}

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
	public String toString() {
	    return id + " login: " + login + " password: " + password + " Tipo: " + tipo + " " + nombre;
	}

}
