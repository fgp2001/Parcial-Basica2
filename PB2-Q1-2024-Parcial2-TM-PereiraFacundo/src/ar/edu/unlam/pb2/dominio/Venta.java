package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;

public class Venta {
	

	private Cliente cliente;
	private ArrayList<CopaDelMundo> copas;
	
	
	public Venta(Cliente cliente) {

		this.cliente = cliente;
		this.copas = new ArrayList<>();
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
