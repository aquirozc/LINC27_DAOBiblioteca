package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.util.ArrayList;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.LibroAbstracto;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;

public interface MaterialDAO {

	public abstract void crearMaterial(LibroAbstracto material);
	public abstract LibroAbstracto consultarMaterial(long id);
	public abstract ArrayList<MaterialBibliografico> consultarMaterial();
	public abstract void modificarMaterial(long id, LibroAbstracto material);
	public abstract void eliminarMaterial(long id);
}
