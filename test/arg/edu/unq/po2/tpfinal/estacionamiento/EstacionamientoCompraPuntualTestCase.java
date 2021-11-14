package arg.edu.unq.po2.tpfinal.estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.estacionamiento.EstacionamientoCompraPuntual;

class EstacionamientoCompraPuntualTestCase {
	
	EstacionamientoCompraPuntual estacionamientoCompraPuntual;
	LocalTime horaInicio;
	LocalTime otraHoraInicio;
	LocalTime horaFin;
	LocalTime otraHoraFin;

	@BeforeEach
	void setUp() throws Exception {
		
		horaInicio = LocalTime.of(17, 32, 0);
		horaFin =  LocalTime.of(19, 32, 0);
		
		estacionamientoCompraPuntual = new EstacionamientoCompraPuntual("3s4-424",horaInicio,horaFin,true,4);
	}
	
	
	@Test
	void testGetterYSetterDeLaVariablePatente() {
		
		estacionamientoCompraPuntual.setPatente("s09-424");
		
		assertEquals("s09-424", estacionamientoCompraPuntual.getPatente());		
		
	}
	
	@Test
	void testGetterYSetterDeLaVariableHoraInicio() {
		
		otraHoraInicio = LocalTime.of(18, 10, 0);
		
		estacionamientoCompraPuntual.setHoraInicio(otraHoraInicio);
		
		assertEquals(otraHoraInicio, estacionamientoCompraPuntual.getHoraInicio());		
		
	}
	
	@Test
	void testGetterYSetterDeLaVariableHoraFin() {
		
		otraHoraFin = LocalTime.of(17, 40, 0);
		
		estacionamientoCompraPuntual.setHorafin(otraHoraFin);
		
		assertEquals(otraHoraFin, estacionamientoCompraPuntual.getHorafin());		
		
	}
	
	@Test
	void testGetterYSetterDeLaVariableValidez() {
		
		estacionamientoCompraPuntual.setValidez(false);
		
		assertEquals(false, estacionamientoCompraPuntual.isValidez());		
		
	}
	
	@Test
	void testGetterYSetterDeLaVariableHorasAEstacionar() {
		
		estacionamientoCompraPuntual.setHorasEstacionar(6);
		
		assertEquals(6, estacionamientoCompraPuntual.getHorasEstacionar());		
		
	}


}
