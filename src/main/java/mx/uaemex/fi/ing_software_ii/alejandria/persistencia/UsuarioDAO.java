package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.util.ArrayList;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public interface UsuarioDAO{
	 void crear(Usuario usuario);
	 ArrayList<Usuario> consulta(Usuario usuario);
	 void actualiza(Usuario usuario);
	 void borra(Usuario usuario);
}
