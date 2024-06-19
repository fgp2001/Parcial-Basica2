package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoPersonalizada extends CopaDelMundo {

	private static final double PRECIO_CAOBA = 0.5;
	private static final double PRECIO_CEDRO = 0.10;
	private static final double PRECIO_ROBLE_OSCURO = 0.15;
	private static final double MANO_DE_OBRA = 0.15;

	private Colores atril;

	public CopaDelMundoPersonalizada(Materiales tipo, Long id, Double precio, Colores atril) {
		super(tipo, id, precio);
		this.atril = atril;
	}

	@Override
	public Double obtenerPrecioFinal() {
		Double precioFinal = 0.0;

		//Agrega el 15 mas el valor del porcentaje del atril
		
		switch (this.atril) {
		case CAOBA:
			precioFinal = (this.getPrecio() * (MANO_DE_OBRA + PRECIO_CAOBA)) + this.getPrecio();
			break;
		case CEDRO:
			precioFinal = (this.getPrecio() * (MANO_DE_OBRA + PRECIO_CEDRO)) + this.getPrecio();
			break;

		case ROBLE_OSCURO:
			precioFinal = (this.getPrecio() * (MANO_DE_OBRA + PRECIO_ROBLE_OSCURO)) + this.getPrecio();
			break;
		default:
			break;
		}

		return precioFinal;
	}

}
