package arg.edu.unq.po2.tpfinal.sem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.celularApp.CelularApp;
import arg.edu.unq.po2.tpfinal.estacionamiento.EstacionamientoMedianteApp;
import arg.edu.unq.po2.tpfinal.sem.SEM;
import arg.edu.unq.po2.tpfinal.tiposDeModos.TipoDeModo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SEMTestCase {
	
	SEM sem;
	CelularApp celularApp;
	TipoDeModo tipoDeModo;
	EstacionamientoMedianteApp estacionamientoApp;

	@BeforeEach
	void setUp() throws Exception {
		
		sem = new SEM();	
		tipoDeModo = mock(TipoDeModo.class);
		celularApp = mock(CelularApp.class);
		estacionamientoApp = mock(EstacionamientoMedianteApp.class);
		
	}

	
	@Test
	void testSeIniciaUnEstacionamientoYElSEMRespondeCorrectamente() {
		
		
		when(celularApp.getPatente()).thenReturn("abc-123");
		sem.registrarInicioEstacionamientoViaApp(celularApp, celularApp.getPatente());
		
		verify(sem).eventoInicioEstacionamientoViaApp(estacionamientoApp);
	}
	
}