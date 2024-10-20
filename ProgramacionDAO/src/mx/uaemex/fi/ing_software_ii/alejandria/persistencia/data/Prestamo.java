package mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data;

import java.util.Date;

public class Prestamo extends ElementoConId{
	
	private Usuario usuario;
	private LibroAbstracto material;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaEntrega;
	private EstadoPrestamo estado;
	private int numeroRefrendos;

	public Prestamo(long id, Usuario usuario, LibroAbstracto material, Date fechaInicio, Date fechaFin,
			Date fechaEntrega, EstadoPrestamo estado, int numeroRefrendos) {
		super(id);
		this.usuario = usuario;
		this.material = material;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.numeroRefrendos = numeroRefrendos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LibroAbstracto getMaterial() {
		return material;
	}

	public void setMaterial(LibroAbstracto material) {
		this.material = material;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
