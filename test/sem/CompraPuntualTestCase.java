package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompraPuntualTestCase {
	
	CompraPuntual compraPuntual;
	PuntoDeVenta puntoDeVenta;
	PuntoDeVenta otroPuntoDeVenta;
	LocalTime horaDeCompra;
	LocalTime otroHoraDeCompra;
	LocalDate fechaDeCompra;
	LocalDate otraFechaDeCompra;
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		puntoDeVenta = mock(PuntoDeVenta.class);
		fechaDeCompra = LocalDate.of(2021, 11, 1);
		horaDeCompra = LocalTime.of(15, 4, 43);
		
		
		compraPuntual = new CompraPuntual(0, puntoDeVenta, fechaDeCompra, horaDeCompra, 4);
		
		
	}
	
	@Test
	void testGetterYSetterDeLaVaribleNumeroDeControl() {
		
		compraPuntual.setNumeroControl(4);
		
		assertEquals(4, compraPuntual.getNumeroControl());	
	}
	
	@Test
	void testGetterYSetterDeLaVaribleFechaDeCompra() {
		
		otraFechaDeCompra = LocalDate.of(2021, 5, 2);
		
		compraPuntual.setFecha(otraFechaDeCompra);
		
		assertEquals(otraFechaDeCompra, compraPuntual.getFecha());	
	}
	
	@Test
	void testGetterYSetterDeLaVaribleHoraDeCompra() {
		
		otroHoraDeCompra = LocalTime.of(18, 12, 01);
		
		compraPuntual.setHora(otroHoraDeCompra);
		
		assertEquals(otroHoraDeCompra, compraPuntual.getHora());	
	}
	
	@Test
	void testGetterYSetterDeLaVariblePuntoDeVenta() {
		
		otroPuntoDeVenta = mock(PuntoDeVenta.class);
		
		compraPuntual.setPuntoVenta(otroPuntoDeVenta);
		
		assertEquals(otroPuntoDeVenta, compraPuntual.getPuntoVenta());	
	}
	
	@Test
	void testGetterYSetterDeLaVaribleCantidadDeHoras() {
		
		compraPuntual.setCantidadHoras(6);
		
		assertEquals(6, compraPuntual.getCantidadHoras());	
	}
}
