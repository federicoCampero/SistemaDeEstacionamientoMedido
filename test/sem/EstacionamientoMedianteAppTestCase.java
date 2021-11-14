package sem;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstacionamientoMedianteAppTestCase {

	EstacionamientoMedianteApp estacionamientoMedianteApp;
	LocalTime horaInicio;
	LocalTime otraHoraInicio;
	LocalTime horaFin;
	LocalTime otraHoraFin;

	@BeforeEach
	void setUp() throws Exception {
		
		horaInicio = LocalTime.of(17, 32, 0);
		horaFin =  LocalTime.of(19, 32, 0);
		
		estacionamientoMedianteApp = new EstacionamientoMedianteApp("3s4-424",horaInicio,horaFin,true,1132431232);
	}
	
	@Test
	void testGetterYSetterDeLaVariableNumeroDeCelular() {
		
		estacionamientoMedianteApp.setNumeroCelular(1134124132);
		
		assertEquals(1134124132, estacionamientoMedianteApp.getNumeroCelular());		
		
	}

}
