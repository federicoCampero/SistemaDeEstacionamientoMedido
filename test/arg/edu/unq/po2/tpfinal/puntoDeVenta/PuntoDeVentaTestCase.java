package arg.edu.unq.po2.tpfinal.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;
import arg.edu.unq.po2.tpfinal.sem.SEM;

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
	void testUsarioEstaRegistradoYCargarCreditoYSEMRegistrarLaCargaDeCredito() throws Exception {

		when(sem.estaRegistradoCelularApp(43141321)).thenReturn(true);

		puntoDeVenta.cargarCredito(43141321, 500d);

		verify(sem).registrarCargaDeCredito(43141321, 500d, puntoDeVenta);
	}


}
