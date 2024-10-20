package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

public abstract class ElementoConId {
	protected long id;
    
    public ElementoConId(int id) {
        this.id = id;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    
    
    
}
