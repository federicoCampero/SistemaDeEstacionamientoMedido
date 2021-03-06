package arg.edu.unq.po2.tpfinal.inspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.estacionamiento.Estacionamiento;
import arg.edu.unq.po2.tpfinal.inspector.Inspector;
import arg.edu.unq.po2.tpfinal.sem.SEM;
import arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento.ZonaDeEstacionamiento;

class InspectorTestCase {

	Inspector inspector;
	SEM sem;
	SEM otroSem;
	ZonaDeEstacionamiento zonaDeEstacionamiento;
	ZonaDeEstacionamiento otraZonaDeEstacionamiento;
	List<Estacionamiento> listaDeEstacionamiento;
	Estacionamiento estacionamientoConInfraccion;
	Estacionamiento estacionamientoSinInfracion;

	@BeforeEach
	void setUp() throws Exception {

		sem = mock(SEM.class);
		zonaDeEstacionamiento = mock(ZonaDeEstacionamiento.class);

		inspector = new Inspector(sem, zonaDeEstacionamiento);

		estacionamientoConInfraccion = mock(Estacionamiento.class);
		estacionamientoSinInfracion = mock(Estacionamiento.class);

	}
	
	@Test
	void testGetterYSetterDeLaVariableSem() {
		
		otroSem = mock(SEM.class);
		
		inspector.setSem(otroSem);
		
		assertEquals(otroSem, inspector.getSem());		
		
	}
	
	@Test
	void testGetterYSetterDeLaVariableAsignadoAZonaEstacionamiento() {
		
		otraZonaDeEstacionamiento = mock(ZonaDeEstacionamiento.class);
		
		inspector.setAsignadoAZonaEstacionamiento(otraZonaDeEstacionamiento);
		
		assertEquals(otraZonaDeEstacionamiento, inspector.getAsignadoAZonaEstacionamiento());		
		
	}

	@Test
	void testInspectorConsultaEstacionamientoVigente() {

		inspector.consultaEstacionamientoVigente("fd3-243");

		verify(sem).tieneEstacionamientoVigente("fd3-243");
	}

	@Test
	void testInspectorHaceUnaAltaDeEstacionamiento() {

		inspector.altaDeInfraccion("fd3-243");

		verify(sem).registrarAltaDeInfraccion("fd3-243",inspector,inspector.getAsignadoAZonaEstacionamiento());
	}

	@Test
	void testInspectorrecorreZonasDeEstacionamientoYNoEncuentraUnEstacionamientoConAltaDeInfraccion() {

		listaDeEstacionamiento = Arrays.asList(estacionamientoSinInfracion);

		when(estacionamientoSinInfracion.getPatente()).thenReturn("s34-6h6");

		when(zonaDeEstacionamiento.getEstacionamientosDeLaZona()).thenReturn(listaDeEstacionamiento);

		when(sem.tieneEstacionamientoVigente("s34-6h6")).thenReturn(false);

		inspector.recorrerZonasDeEstacionamiento();

		verify(sem, never()).registrarAltaDeInfraccion("s34-6h6",inspector,inspector.getAsignadoAZonaEstacionamiento());
	}

	@Test
	void testInspectorrecorreZonasDeEstacionamientoYEncuentraUnEstacionamientoConAltaDeInfraccion() {

		listaDeEstacionamiento = Arrays.asList(estacionamientoConInfraccion);

		when(estacionamientoConInfraccion.getPatente()).thenReturn("134-456");

		when(zonaDeEstacionamiento.getEstacionamientosDeLaZona()).thenReturn(listaDeEstacionamiento);

		when(sem.tieneEstacionamientoVigente("134-456")).thenReturn(true);

		inspector.recorrerZonasDeEstacionamiento();

		verify(sem).registrarAltaDeInfraccion("134-456",inspector,inspector.getAsignadoAZonaEstacionamiento());

	}

}
