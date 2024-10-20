package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.util.ArrayList;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public interface UsuarioDAO {
	
	public abstract void crearUsuario(Usuario usuario);
	public abstract Usuario consultarUsuario(long id);
	public abstract ArrayList<Usuario> consultarUsuario();
	public abstract void modificarUsuario(long id, Usuario usuario);
	public abstract void eliminarUsuario(long id);
}