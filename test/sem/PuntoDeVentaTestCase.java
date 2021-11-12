package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntoDeVentaTestCase {
	
	PuntoDeVenta puntoDeVenta;
	SEM sem;

	@BeforeEach
	void setUp() throws Exception {
		
		sem = mock(SEM.class);	
		
		puntoDeVenta = new PuntoDeVenta(sem);
		
	}

	
	@Test
	void testIniciarEstacionamientoYSEMRegistraElEstacionamiento() {
		
		puntoDeVenta.iniciarEstacionamiento("1s1-223", 2);	
		
		verify(sem).registrarInicioEstacionamientoCompraPuntual("1s1-223",2);
	}
	
	@Test
	void testCargarCreditoYSEMRegistrarLaCargaDeCredito() {
		
		puntoDeVenta.cargarCredito(43141321, 500d);
		
		verify(sem).registrarCargaDeCredito(43141321, 500d);
	}

	

}
