package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

public class MaterialBibliografico extends ElementoConId {
	private String titulo;
    private Nombre autor;
	public MaterialBibliografico(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Nombre getAutor() {
		return autor;
	}
	public void setAutor(Nombre autor) {
		this.autor = autor;
	}
	
}
