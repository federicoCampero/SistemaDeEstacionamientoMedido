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
import arg.edu.unq.po2.tpfinal.tiposDeModos.ModoAutomatico;
import arg.edu.unq.po2.tpfinal.tiposDeModos.ModoManual;
import arg.edu.unq.po2.tpfinal.tiposDeModos.TipoDeModo;
import arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento.ZonaDeEstacionamiento;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalTime;

class SEMTestCase {

	SEM sem;
	CelularApp celularApp;
	CelularApp celularApp2;
	TipoDeModo tipoDeModo;
	EstacionamientoMedianteApp estacionamientoApp;
	PuntoDeVenta puntoDeVenta;
	Inspector inspector;
	ZonaDeEstacionamiento zonaDeEstacionamiento;
	IEntidad iEntidad;
	ModoAutomatico tipoModoAutomatico;
	ModoManual tipoModoManual;

	@BeforeEach
	void setUp() throws Exception {

		sem = new SEM();
		tipoDeModo = mock(TipoDeModo.class);
		celularApp = mock(CelularApp.class);
		celularApp2 = mock(CelularApp.class);
		estacionamientoApp = mock(EstacionamientoMedianteApp.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
		inspector = mock(Inspector.class);
		zonaDeEstacionamiento = mock(ZonaDeEstacionamiento.class);
		iEntidad = mock(IEntidad.class);
		tipoModoAutomatico = mock(ModoAutomatico.class);
		tipoModoManual = mock(ModoManual.class);

		when(celularApp.getPatente()).thenReturn("abc-123");
		when(celularApp.getNumero()).thenReturn(12345);
		when(celularApp.getModo()).thenReturn(tipoModoAutomatico);
		
		when(celularApp2.getPatente()).thenReturn("def-456");
		when(celularApp2.getNumero()).thenReturn(67890);
		when(celularApp2.getModo()).thenReturn(tipoModoManual);
		
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

	// FINALIZAR TOTALIDAD ESTACIONAMIENTOS VIA APP
	@Test
	void testSeFinalizanCorrectamenteTodosLosEstacionamientosViaApp() throws Exception {
		sem.registrarCelularApp(celularApp);
		sem.registrarCelularApp(celularApp2);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);
		sem.registrarCargaDeCredito(celularApp2.getNumero(), 40d, puntoDeVenta);
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());
		sem.registrarInicioEstacionamientoViaApp(celularApp2, celularApp2.getPatente());
		
		sem.finalizarTotalidadEstacionamientosViaApp();
		Boolean resultadoDeValidez = sem.getEstacionamientosViaApp().stream().allMatch(estacionamientos -> estacionamientos.isValidez() == false);
		
		assertTrue(resultadoDeValidez);
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
	// FINALIZAR TOTALIDAD ESTACIONAMIENTOS PUNTO DE VENTA
	@Test
	void testSeFinalizanCorrectamenteLaTotalidadDeLosEstacionamientosDePuntoDeVenta() {
		
		sem.registrarCelularApp(celularApp);
		sem.registrarInicioEstacionamientoCompraPuntual("abc-123", 4, puntoDeVenta);
		sem.registrarInicioEstacionamientoCompraPuntual("abc-124", 4, puntoDeVenta);
		sem.finalizarTotalidadEstacionamientoCompraPuntual();
		
		Boolean resultadoDeValidez = sem.getEstacionamientosCompraPuntual().stream().allMatch(estacionamientos -> estacionamientos.isValidez() == false);
		
		assertTrue(resultadoDeValidez);
	}
	
	
	// FINALIZAR POR FRANJA HORARIA
	@Test
	void testSeRealizaCorrectamenteLaFinalizacionDeTodosLosEstacionamientosPorFranjaHoraria() throws Exception {
		sem.registrarCelularApp(celularApp);
		sem.registrarInicioEstacionamientoCompraPuntual("abc-123", 4, puntoDeVenta);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());
		
		sem.finalizarEstacionamientosPorFinDeFranjaHoraria();
		
		Boolean resultadoDeValidezCP = sem.getEstacionamientosCompraPuntual().stream().allMatch(estacionamientos -> estacionamientos.isValidez() == false);
		Boolean resultadoDeValidezVA = sem.getEstacionamientosViaApp().stream().allMatch(estacionamientos -> estacionamientos.isValidez() == false);
		
		assertTrue(resultadoDeValidezCP);
		assertTrue(resultadoDeValidezVA);
	}
	// BUSCAR TOTALIDAD DE ESTACIONAMIENTOS ACTIVOS
	@Test
	void testElSemEncuentraCorrectamenteLaTotalidadDeLosEstacionamientosActivos() throws Exception {
		sem.registrarCelularApp(celularApp);
		sem.registrarCelularApp(celularApp2);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);
		sem.registrarCargaDeCredito(celularApp2.getNumero(), 40d, puntoDeVenta);
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp2.getPatente());
		
		assertEquals(sem.getCelularesApp().size(), sem.totalidadDeEstacionamientosActivosViaApp().size());
	}
	
	// BUSCAR ESTACIONAMIENTO MEDIANTE NUMERO DE TELEFONO
	@Test
	void testElSemEncuentraCorrectamenteElEstacionamientoMedianteElNumeroDeTelefono() throws Exception {
		
		sem.registrarCelularApp(celularApp);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());
		EstacionamientoMedianteApp estacionamiento = sem.buscarEstacionamientoViaApp(celularApp.getPatente());
		
		assertTrue(sem.getEstacionamientosViaApp().contains(estacionamiento));
	
	}

	// CARGA CELULAR EN SEM
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

	@Test
	void testSeDesuscribeUnaEntidadYaSubscrita() {
		sem.getEntidadesSubscritas().add(iEntidad);
		sem.desuscripcionEntidad(iEntidad);
		assertEquals(0, sem.getEntidadesSubscritas().size());

	}
	@Test
	void testSeNotificaALasEntidadesSuscriptasElInicioDeUnEstacionamientoConPocoSaldo() throws Exception {
		sem.registrarCelularApp(celularApp);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);

		sem.suscripcionEntidad(iEntidad);
		sem.verificarYNotificarSobreUsuarioConPocoSaldo(celularApp);
		
		verify(iEntidad).usuarioConPocoSaldoIniciaEstacionamiento(celularApp.getNumero());
	}
	@Test
	void testSeNotificaALasEntidadesSuscriptasLaCantidadDeEstacionamientosActivos() throws Exception {
		sem.registrarCelularApp(celularApp);
		sem.registrarCargaDeCredito(celularApp.getNumero(), 40d, puntoDeVenta);
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());
		sem.suscripcionEntidad(iEntidad);
		sem.informarEntidadesCantidadEstacionamientosActivos();
		verify(iEntidad).cantidadEstacionamientosActivos(1);
	}
	
	// TIPOS DE MODO
	@Test 
	void testUnCelularSeRegistraYSeGuardaEnElMapCelularTipoDeModo(){
		sem.registrarCelularApp(celularApp);
		assertEquals(1,sem.getCelularTipoDeModo().size());
		assertEquals(tipoModoAutomatico, sem.getCelularTipoDeModo().get(celularApp));
	}
	@Test
	void testUnCelularCambiaAModoManualYElSemLoRegistraCorrectamente() {
		sem.registrarCelularApp(celularApp);
		when(celularApp.getModo()).thenReturn(tipoModoManual);
		sem.cambiarModoCelular(celularApp);
		assertEquals(tipoModoManual, sem.getCelularTipoDeModo().get(celularApp));
	}
	@Test
	void testUnCelularCambiaAModoAutomaticoYElSemLoRegistraCorrectamente() {
		sem.registrarCelularApp(celularApp2);
		when(celularApp2.getModo()).thenReturn(tipoModoAutomatico);
		sem.cambiarModoCelular(celularApp);
		assertEquals(tipoModoAutomatico, sem.getCelularTipoDeModo().get(celularApp));
	}
}
