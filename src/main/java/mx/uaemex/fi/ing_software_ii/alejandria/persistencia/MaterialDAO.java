package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import java.util.ArrayList;
public interface MaterialDAO {
	void crear(MaterialBibliografico material);
    ArrayList<String> consulta(MaterialBibliografico material);
    ArrayList<String> consulta(int talla, int pagina);
    void actualiza(MaterialBibliografico material);
    void borra(MaterialBibliografico material); 
}
