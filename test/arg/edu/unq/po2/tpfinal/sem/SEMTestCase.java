package arg.edu.unq.po2.tpfinal.sem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.celularApp.CelularApp;
import arg.edu.unq.po2.tpfinal.estacionamiento.EstacionamientoCompraPuntual;
import arg.edu.unq.po2.tpfinal.estacionamiento.EstacionamientoMedianteApp;
import arg.edu.unq.po2.tpfinal.inspector.Inspector;
import arg.edu.unq.po2.tpfinal.otros.IEntidad;
import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;
import arg.edu.unq.po2.tpfinal.sem.SEM;
import arg.edu.unq.po2.tpfinal.tiposDeModos.TipoDeModo;
import arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento.ZonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalTime;

class SEMTestCase {

	SEM sem;
	CelularApp celularApp;
	TipoDeModo tipoDeModo;
	EstacionamientoMedianteApp estacionamientoApp;
	PuntoDeVenta puntoDeVenta;
	Inspector inspector;
	ZonaDeEstacionamiento zonaDeEstacionamiento;
	IEntidad iEntidad;

	@BeforeEach
	void setUp() throws Exception {

		sem = new SEM();
		tipoDeModo = mock(TipoDeModo.class);
		celularApp = mock(CelularApp.class);
		estacionamientoApp = mock(EstacionamientoMedianteApp.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
		inspector = mock(Inspector.class);
		zonaDeEstacionamiento = mock(ZonaDeEstacionamiento.class);
		iEntidad = mock(IEntidad.class);

		when(celularApp.getPatente()).thenReturn("abc-123");
		when(celularApp.getNumero()).thenReturn(12345);
		when(estacionamientoApp.getHoraInicio()).thenReturn(LocalTime.of(15, 0));
		when(estacionamientoApp.getHorafin()).thenReturn(LocalTime.of(20, 0));
		when(estacionamientoApp.getPatente()).thenReturn("abc-123");

	}

	// INICIAR ESTACIONAMIENTOS VIA APP
	@Test
	void testUncelularRegistradoYConSaldoSuficienteIniciaCorrectamenteUnEstacionamientoViaAppYElEstacionamientoSeAgregaEnElSem()
			throws Exception {

		sem.registrarCelularApp(celularApp);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());

		assertEquals(1, sem.getEstacionamientosViaApp().size());
	}

	@Test
	void testUnCelularSinRegistrarNoPuedeIniciarUnEstacionamientoViaApp() {
		assertThrows(Exception.class,
				() -> sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente()),
				"Su celular no esta registrado");
	}

	@Test
	void testUnCelularRegistradoSinCreditoNoPuedeIniciarEstacionamientoViaApp() {
		sem.registrarCelularApp(celularApp);
		assertThrows(Exception.class,
				() -> sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente()),
				"Su saldo es insuficiente para iniciar el estacionamiento");
	}

	// FINALIZAR ESTACIONAMIENTOS VIA APP
	@Test
	void testSeFinalizaCorrectamenteUnEstacionamientoInicadoViaApp() throws Exception {

		sem.registrarCelularApp(celularApp);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());
		sem.registrarFinEstacionamientoViaApp(celularApp);

		EstacionamientoMedianteApp estacionamiento = sem.buscarEstacionamientoViaApp(celularApp.getPatente());

		assertFalse(estacionamiento.isValidez());
	}
	

	// INICAR ESTACIONAMIENTO DESDE PUNTO DE VENTA
	@Test
	void testSeIniciaUnEstacionamientoPorCompraPuntalCorrectamenteDesdeUnPuntoDeVenta() {
		sem.registrarInicioEstacionamientoCompraPuntual("abc-123", 4, puntoDeVenta);

		assertEquals(1, sem.getEstacionamientosCompraPuntual().size());
		assertEquals(1, sem.getComprasRealizadas().size());
	}
	// FINALIZAR ESTACIONAMIENTO POR HORARIO PUNTO DE VENTA
	@Test
	void testSeFinalizaUnEstacionamientoPorCompraPuntalCorrectamente() {
		sem.registrarInicioEstacionamientoCompraPuntual("abc-123", 4, puntoDeVenta);
		sem.finalizarEstacionamientoPorFinDeHorarioCompraPuntal("abc-123");
		
		EstacionamientoCompraPuntual estacionamiento = sem.buscarEstacionamientoPorCompraPuntual("abc-123");
		
		assertFalse(estacionamiento.isValidez());
	}
	
	
	// TEST CARGA CELULAR EN SEM
	@Test
	void testUnCelularSeRegistraEnElSemYTieneSaldoCero() {
		sem.registrarCelularApp(celularApp);

		assertEquals(0d, sem.consultaDeSaldoViaApp(celularApp.getNumero()));
		assertEquals(1, sem.getCelularesCredito().size());
		assertEquals(1, sem.getCelularesApp().size());
	}

	@Test
	void testUnCelularSinRegistroEnElSemNoPuedeCargarCredito() throws Exception {
		assertThrows(Exception.class, () -> sem.registrarCargaDeCredito(celularApp.getNumero(), 20d, puntoDeVenta),
				"Debe registrar su celular para cargar credito");
	}

	@Test
	void testUnCelularSeRegistraEnElSemYPuedeCargarCredito() throws Exception {
		sem.registrarCelularApp(celularApp);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 20d, puntoDeVenta);
		assertEquals(20d, sem.consultaDeSaldoViaApp(celularApp.getNumero()));
	}

	// EVENTOS DE ESTACIONAMIENTOS
	@Test
	void testSeIniciaUnEstacionamientoCorrectamenteYSeDisparaElEventoDeInicioEstacionamientoViaApp() throws Exception {

		sem.eventoInicioEstacionamientoViaApp(celularApp, estacionamientoApp);
		verify(celularApp)
				.notificarEventoEstacionamiento("Estacionamiento realizado. Hora de inicio: 15:00; Hora maxima: 20:00");
	}

	@Test
	void testSeFinalizaUnEstacionamientoCorrectamenteYSeDisparaElEventoDeFinEstacionamientoViaApp() {
		sem.agregarEstacionamientoViaApp(estacionamientoApp);
		sem.eventoFinEstacionamientoViaApp(celularApp);
		verify(celularApp).notificarEventoEstacionamiento(
				"Estacionamiento finalizado. Hora de inicio: 15:00; Hora de fin: 20:00; Duracion: 0; Costo: 0");
	}
	
	// INFRACCION
	@Test
	void testSeCreaCorrectamenteUnaInfraccion() {
		sem.agregarEstacionamientoViaApp(estacionamientoApp);
		sem.registrarAltaDeInfraccion("abc-123", inspector, zonaDeEstacionamiento);
		
		assertEquals(1, sem.getInfracionesLabradas().size());
	}
	
	// SUBSCRIPCION/DESUSCRIPCION DE ENTIDADES
	@Test
	void testSeSubscribeUnaNuevaEntidadAlSem() {
		sem.suscripcionEntidad(iEntidad);
		assertEquals(1, sem.getEntidadesSubscritas().size());
	}
	void testSeDesuscribeUnaEntidadYaSubscrita() {
		sem.suscripcionEntidad(iEntidad);
		sem.desuscripcionEntidad(iEntidad);
		assertEquals(0, sem.getEntidadesSubscritas().size());
	}
	
}

















