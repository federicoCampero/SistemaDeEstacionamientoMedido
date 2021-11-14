package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModoAutomaticoTestCase {
	
	ModoAutomatico modoAutomatico;
	CelularApp celularApp;
	
	
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		modoAutomatico = new ModoAutomatico();
		celularApp = mock(CelularApp.class);
	}

	@Test
	void testModoAutomaticoEntraEntraAlEstacionamientoLeEnviaElMensajeAlCelularAppParaQueInicioElEstacionamiento() throws Exception {
		
		modoAutomatico.entrarAlEstacionamiento(celularApp);
		
		verify(celularApp).iniciarEstacionamiento();
	}
	
	@Test
	void testModoAutomaticoSaleDelEstacionamientoLeEnviaElMensajeAlCelularAppParaQueFinalizeElEstacionamiento() throws Exception {
		
		modoAutomatico.salirDelEstacionamiento(celularApp);
		
		verify(celularApp).finalizarEstacionamiento();
	}

}
