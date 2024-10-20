package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.EstadoPrestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.LibroAbstracto;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Nombre;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Prestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.TipoUsuario;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public class PrestamoDaoImp implements PrestamoDAO{

	@Override
	public Prestamo crearPrestamo(Prestamo prestamo) {
		try {
			Connection con = Conexion.getConexion();
			String query = "INSERT INTO prestamo(id_usuario, id_material, fechainicio, fechafin, fechaentrega, estado, numerorefrendos)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			//ps.setLong(0, prestamo.getId());
			ps.setLong(1, prestamo.getUsuario().getId());
			ps.setLong(2, prestamo.getMaterial().getId());
			ps.setDate(3, (java.sql.Date) prestamo.getFechaInicio());
			ps.setDate(4, (java.sql.Date) prestamo.getFechaFin());
			ps.setDate(5, (java.sql.Date) prestamo.getFechaEntrega());
			ps.setString(6, prestamo.getEstado().name());
			ps.setInt(7, prestamo.getNumeroRefrendos());
			ps.executeUpdate();
			
			System.out.println("Registro exitoso");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public ArrayList<MaterialBibliografico> consultarMaterial() {
		
		ArrayList<MaterialBibliografico> materiales = new ArrayList<>();
		
		try {
			Connection con = Conexion.getConexion();
			String query = "SELECT * FROM material";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				long idm = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autorString = rs.getString("autor");
                
                String[] partes = autorString.split(" ");
                String nom = partes[0]; 
                String ap1 = partes[1];
                String ap2 = partes[2];
                
                Nombre nombre = new Nombre(nom, ap1, ap2);
                LibroAbstracto libro = new LibroAbstracto(idm, titulo, nombre) {
                }; 
                materiales.add(libro);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return materiales;
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
	public ArrayList<Prestamo> consultarFInicio(Date fInicio) {
		ArrayList<Prestamo> prestamoIn = new ArrayList<>();
		
		UsuarioDAOImp usuarioDaoImp = new UsuarioDAOImp();
		MaterialDaoImp materialDaoImp = new MaterialDaoImp();
		
		Prestamo prestamo = null;
		
		try {
			Connection con = Conexion.getConexion();
			String query = "SELECT * FROM prestamo";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				long idp = rs.getLong("id");
				long idu = rs.getLong("id_usuario");
				long idm = rs.getLong("id_material");
				Date fin = rs.getDate("fechainicio");
				Date ffn = rs.getDate("fechafin");
				Date fen = rs.getDate("fechaentrega");
                String estadoString = rs.getString("estado");
                EstadoPrestamo estado = EstadoPrestamo.valueOf(estadoString);
                int refrendos = rs.getInt("numerorefrendos");
                
                Usuario usuario = usuarioDaoImp.consultarUsuario(idu);
                LibroAbstracto libro = materialDaoImp.consultarMaterial(idm);
                
                prestamo = new Prestamo(idp, usuario, libro, fin, ffn, fen, estado, refrendos);
                prestamoIn.add(prestamo);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return prestamoIn;
	}

	@Override
	public ArrayList<Prestamo> consultarFfin(Date fFin) {
		ArrayList<Prestamo> prestamoFm = new ArrayList<>();
		
		UsuarioDAOImp usuarioDaoImp = new UsuarioDAOImp();
		MaterialDaoImp materialDaoImp = new MaterialDaoImp();
		
		Prestamo prestamo = null;
		
		try {
			Connection con = Conexion.getConexion();
			String query = "SELECT * FROM prestamo";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				long idp = rs.getLong("id");
				long idu = rs.getLong("id_usuario");
				long idm = rs.getLong("id_material");
				Date fin = rs.getDate("fechainicio");
				Date ffn = rs.getDate("fechafin");
				Date fen = rs.getDate("fechaentrega");
                String estadoString = rs.getString("estado");
                EstadoPrestamo estado = EstadoPrestamo.valueOf(estadoString);
                int refrendos = rs.getInt("numerorefrendos");
                
                Usuario usuario = usuarioDaoImp.consultarUsuario(idu);
                LibroAbstracto libro = materialDaoImp.consultarMaterial(idm);
                
                prestamo = new Prestamo(idp, usuario, libro, fin, ffn, fen, estado, refrendos);
                prestamoFm.add(prestamo);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return prestamoFm;
	}

	@Override
	public ArrayList<Prestamo> consultarEstado() {
		ArrayList<Prestamo> estados = new ArrayList<>();
		
		UsuarioDAOImp usuarioDaoImp = new UsuarioDAOImp();
		MaterialDaoImp materialDaoImp = new MaterialDaoImp();
		
		Prestamo prestamo = null;
		
		try {
			Connection con = Conexion.getConexion();
			String query = "SELECT * FROM prestamo";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				long idp = rs.getLong("id");
				long idu = rs.getLong("id_usuario");
				long idm = rs.getLong("id_material");
				Date fin = rs.getDate("fechainicio");
				Date ffn = rs.getDate("fechafin");
				Date fen = rs.getDate("fechaentrega");
                String estadoString = rs.getString("estado");
                EstadoPrestamo estado = EstadoPrestamo.valueOf(estadoString);
                int refrendos = rs.getInt("numerorefrendos");
                
                Usuario usuario = usuarioDaoImp.consultarUsuario(idu);
                LibroAbstracto libro = materialDaoImp.consultarMaterial(idm);
                
                prestamo = new Prestamo(idp, usuario, libro, fin, ffn, fen, estado, refrendos);
                estados.add(prestamo);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return estados;
	}

	@Override
	public void modificarPrestamo(long id, Prestamo prestamo) {
		try {
			Connection con = Conexion.getConexion();
			String query = "UPDATE prestamo SET id_usuario = ?, id_material = ?, fechainicio = ?, fechafin = ?, fechaentrega = ?, estado = ?, "
					+ "numerorefrendos = ? WHERE id = "+id;
			PreparedStatement ps = con.prepareStatement(query);
			
			//ps.setLong(0, prestamo.getId());
			ps.setLong(1, prestamo.getUsuario().getId());
			ps.setLong(2, prestamo.getMaterial().getId());
			ps.setDate(3, (java.sql.Date) prestamo.getFechaInicio());
			ps.setDate(4, (java.sql.Date) prestamo.getFechaFin());
			ps.setDate(5, (java.sql.Date) prestamo.getFechaEntrega());
			ps.setString(6, prestamo.getEstado().name());
			ps.setInt(7, prestamo.getNumeroRefrendos());
			ps.executeUpdate();
			
			System.out.println("Actualizacion exitosa");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
