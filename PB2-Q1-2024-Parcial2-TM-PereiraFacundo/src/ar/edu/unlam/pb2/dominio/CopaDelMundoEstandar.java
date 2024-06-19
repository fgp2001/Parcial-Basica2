package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoEstandar extends CopaDelMundo {
	
	private static final double MANO_DE_OBRA = 0.20;
	private Integer stock;
	
	
	public CopaDelMundoEstandar(Materiales tipo, Long id, Double precio, Integer stock) {
		super(tipo, id, precio);
		this.stock = stock;
		
	}


	@Override
	public Double obtenerPrecioFinal() {
		//Agregar el 20%
		Double precioFinal = (this.getPrecio() * MANO_DE_OBRA) + this.getPrecio();
		return precioFinal;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	
}
