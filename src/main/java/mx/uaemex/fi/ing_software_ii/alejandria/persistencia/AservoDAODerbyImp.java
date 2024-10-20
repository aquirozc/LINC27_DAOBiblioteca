package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AservoDAODerbyImp extends DAOAbstract implements AservoDAO {

	public AservoDAODerbyImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int consulta(MaterialBibliografico material) {
		 String sql = "SELECT cantidad FROM biblioteca.acervo WHERE material_id = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setLong(1, material.getId());
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("cantidad");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	}

	@Override
	public void altaMaterial(MaterialBibliografico material) {
		String sql = "INSERT INTO biblioteca.acervo (material_id, cantidad) VALUES (?, 1)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, material.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void bajaMaterial(MaterialBibliografico material) {
		String sql = "DELETE FROM biblioteca.acervo WHERE material_id = ? AND NOT EXISTS (SELECT 1 FROM biblioteca.prestamos WHERE material_id = ? AND estado = 'vigente')";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, material.getId());
            pstmt.setLong(2, material.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void ajuste(MaterialBibliografico material, int cantidad) {
		String sql = "UPDATE biblioteca.acervo SET cantidad = cantidad + ? WHERE material_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cantidad);
            pstmt.setLong(2, material.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

}
