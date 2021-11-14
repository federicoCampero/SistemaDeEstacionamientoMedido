package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InfraccionTestCase {
	
	Infraccion infraccion;
	Inspector inspector;
	Inspector otroInspector;
	LocalDate fecha;
	LocalDate otraFecha;
	LocalTime hora;
	LocalTime otraHora;
	ZonaDeEstacionamiento zonaDeEstacionamiento;
	ZonaDeEstacionamiento otraZonaDeEstacionamiento;

	@BeforeEach
	void setUp() throws Exception {
		
		inspector = mock(Inspector.class);
		zonaDeEstacionamiento = mock(ZonaDeEstacionamiento.class);
		
		fecha = LocalDate.of(2021, 3, 3);
		hora = LocalTime.of(19, 3, 22);
		
		infraccion = new Infraccion(inspector, "234-324", fecha, hora, zonaDeEstacionamiento);
	}

	@Test
	void testGetterYSetterDeLaVariableInspector() {
		
		otroInspector = mock(Inspector.class);
		
		infraccion.setInspector(otroInspector);
		
		assertEquals(otroInspector, infraccion.getInspector());	
	}
	
	@Test
	void testGetterYSetterDeLaVariablePatente() {
		
		infraccion.setPatente("902-334");
		
		assertEquals("902-334", infraccion.getPatente());	
	}
	
	@Test
	void testGetterYSetterDeLaVariableFecha() {
		
		otraFecha = LocalDate.of(2021, 4, 2);
		
		infraccion.setFecha(otraFecha);
		
		assertEquals(otraFecha, infraccion.getFecha());	
	}
	
	@Test
	void testGetterYSetterDeLaVariableHorario() {
		
		otraHora =LocalTime.of(17, 4, 22);
		
		infraccion.setHorario(otraHora);
		
		assertEquals(otraHora, infraccion.getHorario());	
	}
	
	@Test
	void testGetterYSetterDeLaVariableZonaDeEstacionamiento() {
		
		otraZonaDeEstacionamiento = mock(ZonaDeEstacionamiento.class);
		
		infraccion.setZonaDeEstacionamiento(otraZonaDeEstacionamiento);
		
		assertEquals(otraZonaDeEstacionamiento, infraccion.getZonaDeEstacionamiento());	
	}

}
