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
	TipoDeModo tipoDeModo;

	@BeforeEach
	void setUp() throws Exception {
		
		sem = mock(SEM.class);	
		tipoDeModo = mock(TipoDeModo.class);
		
		celularApp = new CelularApp(sem,35443543,tipoDeModo,"1s1-223");
		
	}

	
	@Test
	void testIniciarEstacionamientoYAlSEMLelLegaELMensajeDeRegistrarElInicioDelEstacionamientoViaApp() {
		
		celularApp.iniciarEstacionamiento();	
		
		verify(sem).registrarInicioEstacionamientoViaApp(celularApp.getNumero(),"1s1-223");
	}
	
	@Test
	void testFinalizarEstacionamientoYAlSEMLelLegaELMensajeDeRegistrarElFinDelEstacionamientoViaApp() {
		
		celularApp.finalizarEstacionamiento();	
		
		verify(sem).registrarFinEstacionamientoViaApp(celularApp.getNumero());
	}


}
