package arg.edu.unq.po2.tpfinal.compras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.compras.CompraPuntual;
import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;

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
	void testGetterYSetterDeLaVariableNumeroDeControl() {
		
		compraPuntual.setNumeroControl(4);
		
		assertEquals(4, compraPuntual.getNumeroControl());	
	}
	
	@Test
	void testGetterYSetterDeLaVariableFechaDeCompra() {
		
		otraFechaDeCompra = LocalDate.of(2021, 5, 2);
		
		compraPuntual.setFecha(otraFechaDeCompra);
		
		assertEquals(otraFechaDeCompra, compraPuntual.getFecha());	
	}
	
	@Test
	void testGetterYSetterDeLaVariableHoraDeCompra() {
		
		otroHoraDeCompra = LocalTime.of(18, 12, 01);
		
		compraPuntual.setHora(otroHoraDeCompra);
		
		assertEquals(otroHoraDeCompra, compraPuntual.getHora());	
	}
	
	@Test
	void testGetterYSetterDeLaVariablePuntoDeVenta() {
		
		otroPuntoDeVenta = mock(PuntoDeVenta.class);
		
		compraPuntual.setPuntoVenta(otroPuntoDeVenta);
		
		assertEquals(otroPuntoDeVenta, compraPuntual.getPuntoVenta());	
	}
	
	@Test
	void testGetterYSetterDeLaVariableCantidadDeHoras() {
		
		compraPuntual.setCantidadHoras(6);
		
		assertEquals(6, compraPuntual.getCantidadHoras());	
	}
}
