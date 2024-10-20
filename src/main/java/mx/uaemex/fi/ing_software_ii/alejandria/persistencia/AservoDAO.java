package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;

public interface AservoDAO {
	int consulta(MaterialBibliografico material);
    void altaMaterial(MaterialBibliografico material);
    void bajaMaterial(MaterialBibliografico material);
    void ajuste(MaterialBibliografico material, int cantidad);
}
