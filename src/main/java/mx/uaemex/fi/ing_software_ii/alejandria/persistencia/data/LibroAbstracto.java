package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

public abstract class LibroAbstracto extends MaterialBibliografico {
	protected Nombre autor;
	public LibroAbstracto(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Nombre getAutor() {
		return autor;
	}
	public void setAutor(Nombre autor) {
		this.autor = autor;
	}
	

}
