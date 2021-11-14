package arg.edu.unq.po2.tpfinal.tiposDeModo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.celularApp.CelularApp;
import arg.edu.unq.po2.tpfinal.tiposDeModos.ModoManual;

class ModoManualTestCase {
	
	ModoManual modoManual;
	CelularApp celularApp;	

	@BeforeEach
	void setUp() throws Exception {
		
		modoManual = new ModoManual();
		celularApp = mock(CelularApp.class);
	}

	@Test
	void testModoAutomaticoEntraEntraAlEstacionamientoLeEnviaElMensajeAlCelularAppParaQueInicioElEstacionamiento() throws Exception {
		
		modoManual.entrarAlEstacionamiento(celularApp);
		
		verify(celularApp, never()).iniciarEstacionamiento();
		verify(celularApp).alertaIncioEstacionamiento();
	}
	
	@Test
	void testModoAutomaticoSaleDelEstacionamientoLeEnviaElMensajeAlCelularAppParaQueFinalizeElEstacionamiento() throws Exception {
		
		modoManual.salirDelEstacionamiento(celularApp);
		
		verify(celularApp, never()).finalizarEstacionamiento();
		verify(celularApp).alertaFinEstacionamiento();
	}

}
