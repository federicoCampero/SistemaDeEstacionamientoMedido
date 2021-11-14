package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntoDeVentaTestCase {

	PuntoDeVenta puntoDeVenta;
	SEM sem;
	SEM otroSem;

	@BeforeEach
	void setUp() throws Exception {

		sem = mock(SEM.class);

		puntoDeVenta = new PuntoDeVenta(sem);

	}

	@Test
	void testGetterYSetterDeLaVariableSem() {

		otroSem = mock(SEM.class);

		puntoDeVenta.setSem(otroSem);

		assertEquals(otroSem, puntoDeVenta.getSem());

	}

	@Test
	void testPuntoDeVentaIniciaElEstacionamientoYSEMRegistraElEstacionamiento() {

		puntoDeVenta.iniciarEstacionamiento("1s1-223", 2);

		verify(sem).registrarInicioEstacionamientoCompraPuntual("1s1-223", 2, puntoDeVenta);
	}

	@Test
	void testUsarioEstaRegistradoYCargarCreditoYSEMRegistrarLaCargaDeCredito() {

		when(sem.estaRegistradoCelularApp(43141321)).thenReturn(true);

		puntoDeVenta.cargarCredito(43141321, 500d);

		verify(sem).registrarCargaDeCredito(43141321, 500d, puntoDeVenta);
	}

	@Test
	void testUsarioNoEstaRegistradoYNoPuedeCargarCreditoYSEMNoRegistraLaCargaDeCredito() {

		when(sem.estaRegistradoCelularApp(43141321)).thenReturn(false);

		puntoDeVenta.cargarCredito(43141321, 500d);

		verify(sem, never()).registrarCargaDeCredito(43141321, 500d, puntoDeVenta);

	}

}
