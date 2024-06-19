package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

import ar.edu.unlam.pb2.enums.Materiales;

public abstract class CopaDelMundo {

	private Materiales tipo;
	private Long id; // HashCode
	private Double precio;

	public CopaDelMundo(Materiales tipo, Long id, Double precio) {
		this.tipo = tipo;
		this.id = id;
		this.precio = precio;
	}
	

	public abstract Double obtenerPrecioFinal();

	public Materiales getTipo() {
		return tipo;
	}

	public void setTipo(Materiales tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopaDelMundo other = (CopaDelMundo) obj;
		return Objects.equals(id, other.id);
	}

}
