package arg.edu.unq.po2.tpfinal.celularApp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import arg.edu.unq.po2.tpfinal.celularApp.CelularApp;
import arg.edu.unq.po2.tpfinal.sem.SEM;
import arg.edu.unq.po2.tpfinal.tiposDeModos.ModoAutomatico;
import arg.edu.unq.po2.tpfinal.tiposDeModos.ModoManual;
import arg.edu.unq.po2.tpfinal.tiposDeModos.TipoDeModo;


class CelularAppTestCase {

	CelularApp celularApp;
	SEM sem;
	SEM otroSem;
	TipoDeModo tipoDeModo;
	TipoDeModo otroTipoDeModo;

	@BeforeEach
	void setUp() throws Exception {

		sem = mock(SEM.class);
		tipoDeModo = mock(TipoDeModo.class);

		celularApp = new CelularApp(sem, 11443543, "1s1-223");

	}

	@Test
	void testGetterYSetterDeLaVariableSem() {

		otroSem = mock(SEM.class);

		celularApp.setSem(otroSem);

		assertEquals(otroSem, celularApp.getSem());
	}

	@Test
	void testGetterYSetterDeLaVariableNumero() {

		celularApp.setNumero(11433593);

		assertEquals(11433593, celularApp.getNumero());
	}

	@Test
	void testGetterYSetterDeLaVariableModo() {

		otroTipoDeModo = mock(TipoDeModo.class);

		celularApp.setModo(otroTipoDeModo);

		assertEquals(otroTipoDeModo, celularApp.getModo());
	}

	@Test
	void testGetterYSetterDeLaVariablePatente() {

		celularApp.setPatente("f12-34k");

		assertEquals("f12-34k", celularApp.getPatente());
	}

	@Test
	void testIniciarEstacionamientoYAlSEMLelLegaELMensajeDeRegistrarElInicioDelEstacionamientoViaApp()
			throws Exception {

		celularApp.iniciarEstacionamiento();

		verify(sem).registrarInicioEstacionamientoViaApp(celularApp, "1s1-223");
	}

	@Test
	void testFinalizarEstacionamientoYAlSEMLelLegaELMensajeDeRegistrarElFinDelEstacionamientoViaApp() throws Exception {

		celularApp.finalizarEstacionamiento();

		verify(sem).registrarFinEstacionamientoViaApp(celularApp);
	}

	@Test
	void testCelularAppLeLLegaElMensajeDrivingYElEstacionamientoEstaActivo() {

		when(sem.estacionamientoActivo("1s1-223")).thenReturn(true);
		celularApp.setModo(tipoDeModo);
		celularApp.driving();
		
		verify(tipoDeModo).salirDelEstacionamiento(celularApp);

	}

	@Test
	void testCelularAppLeLLegaElMensajeDrivingYElEstacionamientoNoEstaActivo() {

		when(sem.estacionamientoActivo("1s1-223")).thenReturn(false);
		celularApp.setModo(tipoDeModo);
		celularApp.driving();

		verify(tipoDeModo, never()).salirDelEstacionamiento(celularApp);

	}
	
	@Test
	void testCelularAppLeLLegaElMensajeWalkingYElEstacionamientoNoEstaActivo() {

		when(sem.estacionamientoActivo("1s1-223")).thenReturn(false);
		celularApp.setModo(tipoDeModo);
		celularApp.walking();

		verify(tipoDeModo).entrarAlEstacionamiento(celularApp);

	}

	@Test
	void testCelularAppLeLLegaElMensajeWalkingYElEstacionamientoEstaActivo() {

		when(sem.estacionamientoActivo("1s1-223")).thenReturn(true);

		celularApp.walking();

		verify(tipoDeModo, never()).entrarAlEstacionamiento(celularApp);

	}
	
	@Test
	void testCelularAppLlegeUnaNotificacionDelEvento(){
		
		String mensajeDeEvento = celularApp.notificarEventoEstacionamiento("inicioEstacionamiento ....");
		
		assertEquals("inicioEstacionamiento ....", mensajeDeEvento);
		
	}
	
	@Test
	void testCelularAppConsultaSaldoAlSEM() {
		
		when(sem.consultaDeSaldoViaApp(celularApp.getNumero())).thenReturn(400d); 
		
		double mensajeConsultaDeSaldo = celularApp.consultarSaldo();
		
		assertEquals(400d, mensajeConsultaDeSaldo);
	}
	
	@Test
	void testCelularAppAlertaDeInicioDeEstacionamiento() {
		
		assertEquals("Asegurese de inicar su estacionamiento", celularApp.alertaIncioEstacionamiento());
	}
	
	@Test
	void testCelularAppAlertaDeFinDeEstacionamiento() {
		
		assertEquals("Asegurese de finalizar su estacionamiento", celularApp.alertaFinEstacionamiento());
	}
	
	// CAMBIOS DE MODO
	@Test
	void testCelularAppEmpiezaConUnModoManual() {
		assertEquals(ModoManual.class, celularApp.getModo().getClass());
	}
	
	@Test
	void testCelularAppCambiaAModoAutomatico() throws Exception {
		celularApp.cambiarAModoAutomatico();
		assertEquals(ModoAutomatico.class, celularApp.getModo().getClass());
	}
	@Test
	void testCelularAppCambiaAModoManual() throws Exception {
		celularApp.cambiarAModoAutomatico();
		celularApp.cambiarAModoManual();
		assertEquals(ModoManual.class, celularApp.getModo().getClass());
	}
	@Test
	void testCelularEnModoManualDeclaraExceptionCuandoQuiereCambiarAlMismoModo() throws Exception {
		assertThrows(Exception.class, () -> celularApp.cambiarAModoManual());
	}
	@Test
	void testCelularEnModoAutomaticoDeclaraExceptionCuandoQuiereCambiarAlMismoModo() throws Exception {
		celularApp.cambiarAModoAutomatico();
		assertThrows(Exception.class, () -> celularApp.cambiarAModoAutomatico());
	}
}
