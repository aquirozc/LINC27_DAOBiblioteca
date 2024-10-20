package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;
import java.util.Date;

public class Prestamo {
	 private Usuario usuario;
	 private MaterialBibliografico material;
	 private Date fechaIni;
	 private Date fechaFin;
	 private EstadoPrestamo estado;
	 private int numeroRefrendos;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public MaterialBibliografico getMaterial() {
		return material;
	}
	public void setMaterial(MaterialBibliografico material) {
		this.material = material;
	}
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public EstadoPrestamo getEstado() {
		return estado;
	}
	public void setEstado(EstadoPrestamo estado) {
		this.estado = estado;
	}
	public int getNumeroRefrendos() {
		return numeroRefrendos;
	}
	public void setNumeroRefrendos(int numeroRefrendos) {
		this.numeroRefrendos = numeroRefrendos;
	}
	 
	
	 
}

