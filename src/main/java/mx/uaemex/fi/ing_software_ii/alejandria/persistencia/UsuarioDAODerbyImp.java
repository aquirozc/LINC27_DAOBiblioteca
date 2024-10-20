package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Nombre;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.TipoUsuario;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public class UsuarioDAODerbyImp extends DAOAbstract implements UsuarioDAO {

	public UsuarioDAODerbyImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crear(Usuario usuario) {
		String sql = "INSERT INTO biblioteca.usuarios (login, password, nombre, primer_apellido, segundo_apellido, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, usuario.getLogin());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getNombre().getNombreDePila());
            pstmt.setString(4, usuario.getNombre().getPrimerApellido());
            pstmt.setString(5, usuario.getNombre().getSegundoApellido());
            pstmt.setString(6, usuario.getTipo().toString());
            pstmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public ArrayList<Usuario> consulta(Usuario usuario) {
		ArrayList<Usuario> resultados = new ArrayList<>();
        String sql = "SELECT * FROM biblioteca.usuarios WHERE login LIKE ? OR nombre LIKE ? OR primer_apellido LIKE ? OR segundo_apellido LIKE ? OR tipo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + usuario.getLogin() + "%");
            pstmt.setString(2, "%" + usuario.getNombre().getNombreDePila() + "%");
            pstmt.setString(3, "%" + usuario.getNombre().getPrimerApellido() + "%");
            pstmt.setString(4, "%" + usuario.getNombre().getSegundoApellido() + "%");
            pstmt.setString(5, usuario.getTipo() != null ? usuario.getTipo().toString() : "%");
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));
                Nombre nombre = new Nombre();
                nombre.setNombreDePila(rs.getString("nombre"));
                nombre.setPrimerApellido(rs.getString("primer_apellido"));
                nombre.setSegundoApellido(rs.getString("segundo_apellido"));
                u.setNombre(nombre);
                u.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
                resultados.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public void actualiza(Usuario usuario) {
		String sql = "UPDATE biblioteca.usuarios SET password = ?, nombre = ?, primer_apellido = ?, segundo_apellido = ?, tipo = ? WHERE login = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getPassword());
            pstmt.setString(2, usuario.getNombre().getNombreDePila());
            pstmt.setString(3, usuario.getNombre().getPrimerApellido());
            pstmt.setString(4, usuario.getNombre().getSegundoApellido());
            pstmt.setString(5, usuario.getTipo().toString());
            pstmt.setString(6, usuario.getLogin());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void borra(Usuario usuario) {String sql = "DELETE FROM biblioteca.usuarios WHERE login = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, usuario.getLogin());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

}
