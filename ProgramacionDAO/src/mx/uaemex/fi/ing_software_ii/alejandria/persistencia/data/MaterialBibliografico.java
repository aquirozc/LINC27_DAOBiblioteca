package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

public abstract class MaterialBibliografico extends ElementoConId{
	
	public MaterialBibliografico(long id) {
		super(id);
	}

	public String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
	    return getId() + " Titulo: " + titulo;
	}

}
