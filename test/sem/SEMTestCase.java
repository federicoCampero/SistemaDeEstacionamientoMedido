package sem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SEMTestCase {
	
	SEM sem;
	CelularApp celularApp;
	TipoDeModo tipoDeModo;

	@BeforeEach
	void setUp() throws Exception {
		
		sem = new SEM();	
		tipoDeModo = mock(TipoDeModo.class);
		celularApp = new CelularApp(sem,35443543,tipoDeModo,"1s1-223");
		
	}

	
	@Test
	void testEventoIncioEstacionamiento() {
		
		celularApp.iniciarEstacionamiento();
		
	}
}