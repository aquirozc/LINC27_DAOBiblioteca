package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.util.ArrayList;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaterialDAODerbyImp extends DAOAbstract implements MaterialDAO {

	public MaterialDAODerbyImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crear(MaterialBibliografico material) {
		String sql = "INSERT INTO biblioteca.materiales (titulo, autor_nombre, autor_primer_apellido, autor_segundo_apellido, tipo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, material.getTitulo());
            pstmt.setString(2, material.getAutor().getNombreDePila());
            pstmt.setString(3, material.getAutor().getPrimerApellido());
            pstmt.setString(4, material.getAutor().getSegundoApellido());
            pstmt.setString(5, material.getClass().getSimpleName());
            pstmt.executeUpdate();
            
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                material.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public ArrayList<String> consulta(MaterialBibliografico material) {
		ArrayList<String> resultados = new ArrayList<>();
        String sql = "SELECT * FROM biblioteca.materiales WHERE id = ? OR titulo LIKE ? OR autor_nombre LIKE ? OR autor_primer_apellido LIKE ? OR autor_segundo_apellido LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, material.getId());
            pstmt.setString(2, "%" + material.getTitulo() + "%");
            pstmt.setString(3, "%" + material.getAutor().getNombreDePila() + "%");
            pstmt.setString(4, "%" + material.getAutor().getPrimerApellido() + "%");
            pstmt.setString(5, "%" + material.getAutor().getSegundoApellido() + "%");
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultados.add(rs.getString("titulo") + " - " + rs.getString("autor_nombre") + " " + rs.getString("autor_primer_apellido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public ArrayList<String> consulta(int talla, int pagina) {
		ArrayList<String> resultados = new ArrayList<>();
        String sql = "SELECT * FROM biblioteca.materiales ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, (pagina - 1) * talla);
            pstmt.setInt(2, talla);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultados.add(rs.getString("titulo") + " - " + rs.getString("autor_nombre") + " " + rs.getString("autor_primer_apellido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
	}

	@Override
	public void actualiza(MaterialBibliografico material) {
		 String sql = "UPDATE biblioteca.materiales SET titulo = ?, autor_nombre = ?, autor_primer_apellido = ?, autor_segundo_apellido = ? WHERE id = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, material.getTitulo());
	            pstmt.setString(2, material.getAutor().getNombreDePila());
	            pstmt.setString(3, material.getAutor().getPrimerApellido());
	            pstmt.setString(4, material.getAutor().getSegundoApellido());
	            pstmt.setLong(5, material.getId());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	}

	@Override
	public void borra(MaterialBibliografico material) {
		String sql = "DELETE FROM biblioteca.materiales WHERE id = ? AND NOT EXISTS (SELECT 1 FROM biblioteca.prestamos WHERE material_id = ? AND estado = 'vigente') AND NOT EXISTS (SELECT 1 FROM biblioteca.acervo WHERE material_id = ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, material.getId());
            pstmt.setLong(2, material.getId());
            pstmt.setLong(3, material.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

}
