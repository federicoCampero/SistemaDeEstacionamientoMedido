package sem;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

		celularApp = new CelularApp(sem,11443543,tipoDeModo,"1s1-223");
		
	}
	
	@Test
	void testGetterYSetterDeLaVaribleSem() {
		
		otroSem = mock(SEM.class);
		
		celularApp.setSem(otroSem);
		
		assertEquals(otroSem, celularApp.getSem());	
	}
	
	@Test
	void testGetterYSetterDeLaVaribleNumero() {
	
		celularApp.setNumero(11433593);
		
		assertEquals(11433593, celularApp.getNumero());	
	}
	
	@Test
	void testGetterYSetterDeLaVaribleModo() {
		
		otroTipoDeModo = mock(TipoDeModo.class);
		
		celularApp.setModo(otroTipoDeModo);
		
		assertEquals(otroTipoDeModo, celularApp.getModo());
	}
	
	@Test
	void testGetterYSetterDeLaVariblePatente() {
		
		celularApp.setPatente("f12-34k");
		
		assertEquals("f12-34k", celularApp.getPatente());	
	}

	
	@Test
	void testIniciarEstacionamientoYAlSEMLelLegaELMensajeDeRegistrarElInicioDelEstacionamientoViaApp() throws Exception {
		
		celularApp.iniciarEstacionamiento();	
		
		verify(sem).registrarInicioEstacionamientoViaApp(celularApp,"1s1-223");
	}
	
	@Test
	void testFinalizarEstacionamientoYAlSEMLelLegaELMensajeDeRegistrarElFinDelEstacionamientoViaApp() throws Exception {
		
		celularApp.finalizarEstacionamiento();	
		
		verify(sem).registrarFinEstacionamientoViaApp(celularApp);
	}


}

