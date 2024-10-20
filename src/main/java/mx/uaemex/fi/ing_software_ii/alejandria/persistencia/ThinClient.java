package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Nombre;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.TipoUsuario;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public class ThinClient {

	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/dao01")) {
			probarUsuarioDAO(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	
	}
	
	public static void probarUsuarioDAO(Connection c) throws Exception {
		
		System.out.println("Probando DAO Usuario");
		
		UsuarioDAO dao = new UsuarioDAODerbyImp();
		((DAOAbstract) dao).setConnection(c);
		
		Usuario usr = new Usuario();
		usr.setLogin(System.currentTimeMillis() + "");
		usr.setNombre(new Nombre(System.currentTimeMillis() + ""));
		usr.setTipo(TipoUsuario.Alumno);
		usr.setPassword("AMLO1234");
		
		if (dao.consulta(usr).contains(usr)) {
			throw new Exception();
		}
		
		dao.crear(usr);
		
		if (!dao.consulta(usr).contains(usr)) {
			throw new Exception();
		}
		
		usr.setNombre(new Nombre(System.currentTimeMillis() + ""));
		dao.actualiza(usr);
		
		if (!dao.consulta(usr).contains(usr)) {
			throw new Exception();
		}
		
		
		System.out.println("Prueba terminada con exito");
		
	}

}
