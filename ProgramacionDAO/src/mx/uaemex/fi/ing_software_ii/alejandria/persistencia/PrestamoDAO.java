package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.util.ArrayList;
import java.util.Date;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Prestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public interface PrestamoDAO {
	
	public abstract Prestamo crearPrestamo(Prestamo prestamo);
	public abstract ArrayList<MaterialBibliografico> consultarMaterial();
	public abstract ArrayList<Usuario> consultarUsuario();
	
	public abstract ArrayList<Prestamo> consultarFInicio(Date fInicio);
	public abstract ArrayList<Prestamo> consultarFfin(Date fFin);
	
	public abstract ArrayList<Prestamo> consultarEstado();
	public abstract void modificarPrestamo(long id, Prestamo prestamo);
}
