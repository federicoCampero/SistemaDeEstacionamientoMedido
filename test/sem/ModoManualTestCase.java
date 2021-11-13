package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModoManualTestCase {
	
	ModoManual modoManual;
	CelularApp celularApp;	

	@BeforeEach
	void setUp() throws Exception {
		
		modoManual = new ModoManual();
		celularApp = mock(CelularApp.class);
	}

	@Test
	void testModoAutomaticoEntraEntraAlEstacionamientoLeEnviaElMensajeAlCelularAppParaQueInicioElEstacionamiento() {
		
		modoManual.entrarAlEstacionamiento(celularApp);
		
		verify(celularApp, never()).iniciarEstacionamiento();
		verify(celularApp).alertaInicioEstacionamiento();
	}
	
	@Test
	void testModoAutomaticoSaleDelEstacionamientoLeEnviaElMensajeAlCelularAppParaQueFinalizeElEstacionamiento() {
		
		modoManual.salirDelEstacionamiento(celularApp);
		
		verify(celularApp, never()).finalizarEstacionamiento();
		verify(celularApp).alertaFinEstacionamiento();
	}

}
