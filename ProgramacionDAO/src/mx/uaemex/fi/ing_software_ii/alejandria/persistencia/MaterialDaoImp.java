package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.LibroAbstracto;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Nombre;

public class MaterialDaoImp implements MaterialDAO{

	@Override
	public void crearMaterial(LibroAbstracto material) {
		
		try {
			Connection con = Conexion.getConexion();
			String query = "INSERT INTO material(titulo, autor) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			//ps.setLong(0, material.getId());            //Descomentar cuando se modifique la base de datos
			ps.setString(1, material.getTitulo());
			ps.setString(2, material.getAutor().getNombreDePila() + " "
						  + material.getAutor().getPrimerApellido() + " "
					      + material.getAutor().getSegundoApellido());
			ps.executeUpdate();
			
			System.out.println("Registro exitoso");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public LibroAbstracto consultarMaterial(long id) {
		
		LibroAbstracto libro = null;
		
		try {
			Connection con = Conexion.getConexion();
			String query = "SELECT * FROM material WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, id);
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
                libro = new LibroAbstracto(idm, titulo, nombre) {
                }; 
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return libro;
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
	public void modificarMaterial(long id, LibroAbstracto material) {
		try {
			Connection con = Conexion.getConexion();
			String query = "UPDATE material set titulo = ?, autor = ? WHERE id = " + id;
			PreparedStatement ps = con.prepareStatement(query);
			
			//ps.setLong(0, material.getId());            //Descomentar cuando se modifique la base de datos
			ps.setString(1, material.getTitulo());
			ps.setString(2, material.getAutor().getNombreDePila() + " "
						  + material.getAutor().getPrimerApellido() + " "
					      + material.getAutor().getSegundoApellido());
			ps.executeUpdate();
			
			System.out.println("Actualizacion exitosa");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public void eliminarMaterial(long id) {
		try {
			Connection con = Conexion.getConexion();
			String query = "DELETE FROM material WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			ps.executeUpdate();
			
			System.out.println("Eliminacion exitosa");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
