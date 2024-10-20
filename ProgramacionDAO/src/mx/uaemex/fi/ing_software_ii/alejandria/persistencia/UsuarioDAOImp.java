package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Nombre;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.TipoUsuario;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public class UsuarioDAOImp implements UsuarioDAO{

	@Override
	public void crearUsuario(Usuario usuario) {
		
		try {
			Connection con = Conexion.getConexion();
			String query = "INSERT INTO usuario(login, password, tipo, nombre) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			//ps.setLong(0, usuario.getId());
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getTipo().name());
			ps.setString(4, usuario.getNombre().getNombreDePila() + " "
						  + usuario.getNombre().getPrimerApellido() + " "
					      + usuario.getNombre().getSegundoApellido());
			ps.executeUpdate();
			
			System.out.println("\n REGISTRO EXITOSO");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	@Override
	public Usuario consultarUsuario(long id) {
		Usuario usuario = null;
		
		try {
			Connection con = Conexion.getConexion();
			String query = "SELECT * FROM usuario WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long idu = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String tipoString = rs.getString("tipo");
                TipoUsuario tipo = TipoUsuario.valueOf(tipoString);
                String nombreString = rs.getString("nombre");
                
                String[] partes = nombreString.split(" ");
                String nom = partes[0]; 
                String ap1 = partes[1];
                String ap2 = partes[2];
                
                Nombre nombre = new Nombre(nom, ap1, ap2);
                usuario = new Usuario(idu, login, password, tipo, nombre); 
            }

		} catch (SQLException e) {
			System.out.println(e);
		}
		return usuario;
	}

	@Override
	public ArrayList<Usuario> consultarUsuario() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		Usuario usuario = null;
		
		try {
			Connection con = Conexion.getConexion();
			String query = "SELECT * FROM usuario";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				long idu = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String tipoString = rs.getString("tipo");
                TipoUsuario tipo = TipoUsuario.valueOf(tipoString);
                String nombreString = rs.getString("nombre");
                
                String[] partes = nombreString.split(" ");
                String nom = partes[0]; 
                String ap1 = partes[1];
                String ap2 = partes[2];
                
                Nombre nombre = new Nombre(nom, ap1, ap2);
                usuario = new Usuario(idu, login, password, tipo, nombre); 
                usuarios.add(usuario);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return usuarios;
	}

	@Override
	public void modificarUsuario(long id, Usuario usuario) {
		try {
			Connection con = Conexion.getConexion();
			String query = "UPDATE usuario set login = ?, password = ?, tipo = ?, nombre = ? WHERE id = " + id;
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getTipo().name());
			ps.setString(4, usuario.getNombre().getNombreDePila() + " "
						  + usuario.getNombre().getPrimerApellido() + " "
					      + usuario.getNombre().getSegundoApellido());
			ps.executeUpdate();
			
			System.out.println("\n ACTUALIZACION EXITOSA");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void eliminarUsuario(long id) {
		try {
			Connection con = Conexion.getConexion();
			String query = "DELETE FROM usuario WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			ps.executeUpdate();
			
			System.out.println("\n ELIMINACION EXITOSA");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
