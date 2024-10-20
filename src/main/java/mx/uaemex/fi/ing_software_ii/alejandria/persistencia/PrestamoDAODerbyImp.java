package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.EstadoPrestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Prestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public class PrestamoDAODerbyImp extends DAOAbstract implements PrestamoDAO {

	public PrestamoDAODerbyImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crear(MaterialBibliografico material, Usuario usuario) {
		String sql = "INSERT INTO biblioteca.prestamos (usuario_id, material_id, fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?, ?, 'vigente')";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getLogin());
            pstmt.setLong(2, material.getId());
            pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000L)); // 14 days loan period
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public ArrayList<String> consulta(MaterialBibliografico material) {
		ArrayList<String> resultados = new ArrayList<>();
        String sql = "SELECT p.*, u.login FROM biblioteca.prestamos p JOIN biblioteca.usuarios u ON p.usuario_id = u.id WHERE p.material_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, material.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultados.add("Usuario: " + rs.getString("login") + ", Fecha inicio: " + rs.getDate("fecha_inicio") + ", Estado: " + rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public ArrayList<String> consulta(Usuario usuario) {
        ArrayList<String> resultados = new ArrayList<>();
        String sql = "SELECT p.*, m.titulo FROM biblioteca.prestamos p " +
                     "JOIN biblioteca.materiales m ON p.material_id = m.id " +
                     "JOIN biblioteca.usuarios u ON p.usuario_id = u.id " +
                     "WHERE u.login = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getLogin());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultados.add("Material: " + rs.getString("titulo") + 
                               ", Fecha inicio: " + rs.getDate("fecha_inicio") + 
                               ", Fecha fin: " + rs.getDate("fecha_fin") + 
                               ", Estado: " + rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public ArrayList<String> consultaInicio(Date fIni) {
		ArrayList<String> resultados = new ArrayList<>();
        String sql = "SELECT p.*, m.titulo, u.login FROM biblioteca.prestamos p " +
                     "JOIN biblioteca.materiales m ON p.material_id = m.id " +
                     "JOIN biblioteca.usuarios u ON p.usuario_id = u.id " +
                     "WHERE p.fecha_inicio >= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(fIni.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String prestamo = String.format("ID: %d, Usuario: %s, Material: %s, Fecha inicio: %s, Fecha fin: %s, Estado: %s",
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("titulo"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("estado")
                );
                resultados.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public ArrayList<String> consultaFinal(Date fFin) {
		ArrayList<String> resultados = new ArrayList<>();
        String sql = "SELECT p.*, m.titulo, u.login FROM biblioteca.prestamos p " +
                     "JOIN biblioteca.materiales m ON p.material_id = m.id " +
                     "JOIN biblioteca.usuarios u ON p.usuario_id = u.id " +
                     "WHERE p.fecha_fin <= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(fFin.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String prestamo = String.format("ID: %d, Usuario: %s, Material: %s, Fecha inicio: %s, Fecha fin: %s, Estado: %s",
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("titulo"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("estado")
                );
                resultados.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public ArrayList<String> consulta(EstadoPrestamo estado) {
		ArrayList<String> resultados = new ArrayList<>();
        String sql = "SELECT p.*, m.titulo, u.login FROM biblioteca.prestamos p JOIN biblioteca.materiales m ON p.material_id = m.id JOIN biblioteca.usuarios u ON p.usuario_id = u.id WHERE p.estado = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, estado.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultados.add("Usuario: " + rs.getString("login") + ", Material: " + rs.getString("titulo") + ", Fecha inicio: " + rs.getDate("fecha_inicio") + ", Fecha fin: " + rs.getDate("fecha_fin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public void actualiza(Prestamo prestamo) {
		String sql = "UPDATE biblioteca.prestamos SET fecha_fin = ?, estado = ? WHERE usuario_id = ? AND material_id = ? AND fecha_inicio = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(prestamo.getFechaFin().getTime()));
            pstmt.setString(2, prestamo.getEstado().toString());
            pstmt.setString(3, prestamo.getUsuario().getLogin());
            pstmt.setLong(4, prestamo.getMaterial().getId());
            pstmt.setDate(5, new java.sql.Date(prestamo.getFechaIni().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

	}
        }

}
