package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InspectorTestCase {
	
	

	Inspector inspector;
	SEM sem;
	ZonaDeEstacionamiento zonaDeEstacionamiento;

	@BeforeEach
	void setUp() throws Exception {
		
		sem = mock(SEM.class);	
		zonaDeEstacionamiento = mock(ZonaDeEstacionamiento.class);
		
		inspector = new Inspector(sem,zonaDeEstacionamiento);
		
	}

	@Test
	void testInspectorConsultaEstacionamientoVigente() {
		
		inspector.consultaEstacionamientoVigente("fd3-243");
		
		verify(sem).tieneEstacionamientoVigente("fd3-243");
	}
	
	@Test
	void testInspectorHaceUnaAltaDeEstacionamiento() {
		
		inspector.altaDeInflaccion("fd3-243");
		
		verify(sem).realizarAltaDeInflaccion("fd3-243");
	}

}
