package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

public abstract class LibroAbstracto extends MaterialBibliografico{

	public LibroAbstracto(long id, String titulo, Nombre autor) {
		super(id);
		this.autor = autor;
		this.titulo = titulo;
	}

	protected Nombre autor;

	public Nombre getAutor() {
		return autor;
	}

	public void setAutor(Nombre autor) {
		this.autor = autor;
	}
	
	@Override
	public String toString() {
	    return getId() + " Titulo: " + titulo + " Autor: " + (autor != null ? 
	    autor.getNombreDePila() + " " + autor.getPrimerApellido() + " " + autor.getSegundoApellido() 
	    : "Desconocido");
	}
}
