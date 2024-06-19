package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ar.edu.unlam.pb2.excepciones.ClienteDuplicadoException;
import ar.edu.unlam.pb2.excepciones.CopaDelMundoNoEncontradaException;

public class FabricaDeCopasDelMundo {

	private String nombre;
	private Set<Cliente> clientes;
	private List<CopaDelMundo> copas;
	private List<Venta> ventas;

	public FabricaDeCopasDelMundo(String nombre) {
		this.nombre = nombre;
		this.clientes = new HashSet<>();
		this.copas = new ArrayList<>();
		this.ventas = new ArrayList<>();
	}

	public Boolean agregarCopaDelMundo(CopaDelMundo copaDelMundo) throws Exception {

		Boolean agregada = false;
		if (copaDelMundo == null) {
			throw new Exception("Copa del Mundo Inexistente.");
		}

		if (!this.copas.contains(copaDelMundo)) {
			agregada = this.copas.add(copaDelMundo);

		}
		return agregada;
	}

	public Boolean agregarCliente(Cliente cliente) throws Exception {
		if (cliente == null) {
			throw new Exception("Cliente inexistente.");
		}

		if (this.clientes.contains(cliente)) {
			throw new ClienteDuplicadoException("El cliente ingresado es duplicado");
		}

		return this.clientes.add(cliente);
	}
	
	
	
	public List<CopaDelMundo> obtenerCopasDelMundoPersonalizadas() {

		List<CopaDelMundo> copasPersonalizadas = new ArrayList<>();
		for (CopaDelMundo c : this.copas) {
			if (c instanceof CopaDelMundoPersonalizada) {
				copasPersonalizadas.add(c);
			}
		}
		return copasPersonalizadas;
	}
	
	public List<CopaDelMundo> obtenerCopasDelMundoEstandar() {

		List<CopaDelMundo> copasEstandar = new ArrayList<>();
		for (CopaDelMundo c : this.copas) {
			if (c instanceof CopaDelMundoEstandar) {
				copasEstandar.add(c);
			}
		}
		return copasEstandar;
	}

	public CopaDelMundo obtenerCopaDelMundoPorId(Long id) throws Exception {

		for (CopaDelMundo c : this.copas) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		throw new CopaDelMundoNoEncontradaException("Copa del mundo No Encontrada");
	}

	public void agregarCopaDelMundoEstandarAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo,
			Integer cantidadAVender) throws Exception {

		CopaDelMundoEstandar copaRequerida = (CopaDelMundoEstandar) this.obtenerCopaDelMundoPorId(idCopaDelMundo);
		Venta ventaRealizada;

		if (!(this.obtenerVentaPorCliente(clienteDeVenta) == null)) {
			throw new Exception("Cliente no registrado");
		}
		
		if (cantidadAVender < copaRequerida.getStock()) {

			copaRequerida.setStock(copaRequerida.getStock() - cantidadAVender);
			ventaRealizada = new Venta(clienteDeVenta);

			this.ventas.add(ventaRealizada);
		}

	}

	public void agregarCopaDelMundoPersonalizadaAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo)
			throws Exception {

		CopaDelMundoPersonalizada copaRequerida = (CopaDelMundoPersonalizada) this
				.obtenerCopaDelMundoPorId(idCopaDelMundo);
		Venta ventaRealizada = new Venta(clienteDeVenta);
		
		if (!(this.obtenerVentaPorCliente(clienteDeVenta) == null)) {
			throw new Exception("Cliente no registrado");
		}
		
		this.copas.remove(copaRequerida);
		this.ventas.add(ventaRealizada);
		}
	

	public Double obtenerPrecioDeCopaDelMundoPersonalizada(Long id) throws Exception {

		for (CopaDelMundo c : copas) {
			if (c instanceof CopaDelMundoPersonalizada && obtenerCopaDelMundoPorId(id).equals(c)) {
				return c.obtenerPrecioFinal();
			}
		}
		throw new Exception("La copa buscada no fue encontrada ");
	}

	public Map<Cliente, Double> obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente() {
		return null;
	}

	private Venta obtenerVentaPorCliente(Cliente cliente) throws Exception {
		Venta venta = null;
		for (Venta v : this.ventas) {
			if (v.getCliente().equals(cliente)) {
				venta = v;
			}
		}
		return venta;
	}

}
