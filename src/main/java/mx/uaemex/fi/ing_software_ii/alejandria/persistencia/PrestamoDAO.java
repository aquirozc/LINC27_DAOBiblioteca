package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;
import java.util.ArrayList;
import java.util.Date;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.EstadoPrestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Prestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;


public interface PrestamoDAO {
	void crear(MaterialBibliografico material, Usuario usuario);
    ArrayList<String> consulta(MaterialBibliografico material);
    ArrayList<String> consulta(Usuario usuario);
    ArrayList<String> consultaInicio(Date fIni);
    ArrayList<String> consultaFinal(Date fFin);
    ArrayList<String> consulta(EstadoPrestamo estado);
    void actualiza(Prestamo prestamo);
}
