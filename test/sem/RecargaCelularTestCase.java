package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecargaCelularTestCase {
	
	RecargaCelular recargaCelular;
	PuntoDeVenta puntoDeVenta;
	LocalTime horaDeCompra;
	LocalDate fechaDeCompra;
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		puntoDeVenta = mock(PuntoDeVenta.class);
		fechaDeCompra = LocalDate.of(2021, 3, 2);
		horaDeCompra = LocalTime.of(8, 1, 43);
		
		
		recargaCelular = new RecargaCelular(1, puntoDeVenta, fechaDeCompra, horaDeCompra,300d,1143244383);
		
		
	}
	
	@Test
	void testGetterYSetterDeLaVaribleMonto() {
		
		recargaCelular.setMonto(350d);
		
		assertEquals(350d, recargaCelular.getMonto());	
	}
	
	@Test
	void testGetterYSetterDeLaVaribleNumeroDeCelular() {
		
		recargaCelular.setNumeroCel(1199234350);
		
		assertEquals(1199234350, recargaCelular.getNumeroCel());	
	}

}
