package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;
import ar.edu.unlam.pb2.excepciones.ClienteDuplicadoException;

public class FabricaCopasDelMundoTest {
	private static final String NOMBRE_FABRICA = "Fabrica Pereira";
	private FabricaDeCopasDelMundo fabrica;

	@Before
	public void init() {
		this.fabrica = new FabricaDeCopasDelMundo(NOMBRE_FABRICA);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoEstandar() throws Exception {

		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 12;
		Double precioBase = 200.0;
		Integer stock = 20;

		CopaDelMundo copa = new CopaDelMundoEstandar(tipo, id, precioBase, stock);

		Boolean agregada = fabrica.agregarCopaDelMundo(copa);

		assertTrue(agregada);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoPersonalizada() throws Exception {

		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 12;
		Double precioBase = 200.0;
		Colores atril = Colores.CAOBA;

		CopaDelMundo copa = new CopaDelMundoPersonalizada(tipo, id, precioBase, atril);

		Boolean agregada = fabrica.agregarCopaDelMundo(copa);
		assertTrue(agregada);

	}

	@Test(expected = ClienteDuplicadoException.class)
	public void dadoQueExisteUnaFabricaDeCopasDelMundoAlAgregarUnClienteExistenteSeLanzaUnaClienteDuplicadoException()
			throws Exception {

		Integer dni = 43102959;
		String nombre = "Facundo";
		String apellido = "Pereira";

		// Se agrega el mismo cliente 2 veces
		Cliente cliente = new Cliente(dni, nombre, apellido);
		fabrica.agregarCliente(cliente);
		fabrica.agregarCliente(cliente);

	}

	@Test
	public void dadoQueExisteUnaFabricaQuePoseeCopasDelMundoSePuedenObtenerLasCopasDelMundoEstandar() throws Exception {
		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 13;
		Double precioBase = 200.0;
		Colores atril = Colores.CAOBA;

		CopaDelMundo copa = new CopaDelMundoPersonalizada(tipo, id, precioBase, atril);
		// Se agrega la primera Copa Estandar

		Materiales tipo2 = Materiales.PLASTICO;
		Long id2 = (long) 29;
		Double precioBase2 = 200.0;
		Integer stock = 20;

		CopaDelMundo copaEstandar = new CopaDelMundoEstandar(tipo2, id2, precioBase2, stock);
		// Se agrega la segunda Copa Estandar

		Materiales tipo3 = Materiales.PLASTICO;
		Long id3 = (long) 30;
		Double precioBase3 = 200.0;
		Integer stock2 = 20;

		CopaDelMundo copaEstandar2 = new CopaDelMundoEstandar(tipo3, id3, precioBase3, stock2);

		List<CopaDelMundo> copasEstandar = new ArrayList<>();

		fabrica.agregarCopaDelMundo(copa);
		fabrica.agregarCopaDelMundo(copaEstandar);
		fabrica.agregarCopaDelMundo(copaEstandar2);

		copasEstandar = fabrica.obtenerCopasDelMundoEstandar();

		assertEquals(2, copasEstandar.size());

	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPuedoObtenerUnaCopaDelMundoPorSuId()
			throws Exception {
		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 13;
		Double precioBase = 200.0;
		Colores atril = Colores.CAOBA;

		CopaDelMundo copa = new CopaDelMundoPersonalizada(tipo, id, precioBase, atril);
		fabrica.agregarCopaDelMundo(copa);

		CopaDelMundo copaEncontrada = fabrica.obtenerCopaDelMundoPorId(id);

		assertEquals(id, copaEncontrada.getId());

	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarCincoCopasDelMundoAUnaVentaDeCopasDelMundoEstandarParaUnClienteSeDescuentanCincoUnidadesDelStockDeCopasDelMundoEstandar() throws Exception {
		
		
		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 29;
		Double precioBase = 200.0;
		Integer stock = 20;

		Cliente cliente = new Cliente(43102959, "Facundo", "Pereira");
		
		CopaDelMundo copaEstandar = new CopaDelMundoEstandar(tipo, id, precioBase, stock);
		fabrica.agregarCopaDelMundo(copaEstandar);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente, id, 5);
		
		CopaDelMundoEstandar copaEstandarVendida = (CopaDelMundoEstandar) fabrica.obtenerCopaDelMundoPorId(id);
		Integer stockEsperado = 15;
		
		assertEquals(stockEsperado, copaEstandarVendida.getStock());
	
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarUnaVentaDeCopasDelMundoPersonalizadaParaUnClienteSeRemueveLaCopaDelMundoPersonalizadaDeLaFabrica() throws Exception {
		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 13;
		Double precioBase = 200.0;
		Colores atril = Colores.CAOBA;


		Cliente cliente = new Cliente(43102959, "Facundo", "Pereira");
		
		CopaDelMundo copa = new CopaDelMundoPersonalizada(tipo, id, precioBase, atril);
		fabrica.agregarCopaDelMundo(copa);
		fabrica.agregarCopaDelMundoPersonalizadaAVentaDeCliente(cliente, id);
		
		List<CopaDelMundo> copasPerso = new ArrayList<>();
		
		copasPerso = fabrica.obtenerCopasDelMundoPersonalizadas();
		
		assertEquals(0, copasPerso.size());
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPersonalizadasSePuedeObtenerElPrecioDeUnaCopaDelMundoPersonalizada() throws Exception {
		
		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 13;
		Double precioBase = 200.0;
		Colores atril = Colores.CAOBA;

		CopaDelMundo copa = new CopaDelMundoPersonalizada(tipo, id, precioBase, atril);
		
		fabrica.agregarCopaDelMundo(copa);
		Double precio = fabrica.obtenerPrecioDeCopaDelMundoPersonalizada(id);
		Double precioEsperadoMasAgregados = 330.0;
		
		
		assertEquals(precioEsperadoMasAgregados, precio);
	
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConVentasDeCopasDelMundoEstandarYPersonalizadasVendidasAClientesSePuedeObtenerUnMapaConClaveClienteYTotalDeVentasDeCopasEstandarOrdenadoPorCliente() throws Exception {
		Materiales tipo = Materiales.PLASTICO;
		Long id = (long) 13;
		Double precioBase = 200.0;
		Colores atril = Colores.CAOBA;

		CopaDelMundo copa = new CopaDelMundoPersonalizada(tipo, id, precioBase, atril);
		
		Materiales tipo2 = Materiales.PLASTICO;
		Long id2 = (long) 29;
		Double precioBase2 = 200.0;
		Integer stock = 20;

		Cliente cliente = new Cliente(43102959, "Facundo", "Pereira");
		Cliente cliente2 = new Cliente(55323345, "Facundo", "Pereira");
		
		CopaDelMundo copaEstandar = new CopaDelMundoEstandar(tipo2, id2, precioBase2, stock);
		
		fabrica.agregarCopaDelMundo(copa);
		fabrica.agregarCopaDelMundo(copaEstandar);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente2, id2, stock);
		fabrica.agregarCopaDelMundoPersonalizadaAVentaDeCliente(cliente, id);
	
		
	
	}
}
