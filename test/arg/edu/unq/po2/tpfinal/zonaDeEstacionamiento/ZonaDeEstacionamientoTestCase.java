package arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arg.edu.unq.po2.tpfinal.estacionamiento.Estacionamiento;
import arg.edu.unq.po2.tpfinal.inspector.Inspector;
import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;
import arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento.ZonaDeEstacionamiento;

class ZonaDeEstacionamientoTestCase {
	
	ZonaDeEstacionamiento zonaDeEstacionamiento;
	Inspector inspector;
	Inspector otroInspector;
	List<PuntoDeVenta> listaDePuntoDeVentas;
	List<Estacionamiento> listaDeEstacionamientos;
	List<PuntoDeVenta> otraListaDePuntoDeVentas;
	List<Estacionamiento> otraListaDeEstacionamientos;
	Estacionamiento nuevoEstacionamiento;

	@BeforeEach
	void setUp() throws Exception {
		
		listaDePuntoDeVentas = new ArrayList<PuntoDeVenta>();
		listaDeEstacionamientos = new ArrayList<Estacionamiento>();
		
		zonaDeEstacionamiento = new ZonaDeEstacionamiento(inspector, listaDePuntoDeVentas, listaDeEstacionamientos);
	}

	@Test
	void testGetterYSetterDeLaVariableInspector() {
		
		otroInspector = mock(Inspector.class);
		
		zonaDeEstacionamiento.setInspector(otroInspector);
		
		assertEquals(otroInspector, zonaDeEstacionamiento.getInspector());		
	}
	
	@Test
	void testGetterYSetterDeLaVariableListaDePuntoDeVentas() {
		
		otraListaDePuntoDeVentas = new ArrayList<PuntoDeVenta>();
		
		zonaDeEstacionamiento.setPuntosDeVenta(otraListaDePuntoDeVentas);
		
		assertEquals(otraListaDePuntoDeVentas, zonaDeEstacionamiento.getPuntosDeVenta());		
	}
	
	@Test
	void testGetterYSetterDeLaVariableListaDePuntoDeEstacionamientos() {
		
		otraListaDeEstacionamientos = new ArrayList<Estacionamiento>();
		
		zonaDeEstacionamiento.setEstacionamientosDeLaZona(otraListaDeEstacionamientos);
		
		assertEquals(otraListaDeEstacionamientos, zonaDeEstacionamiento.getEstacionamientosDeLaZona());		
	}
	
	@Test
	void testRegistrarEstacionamiento() {
		
		nuevoEstacionamiento = mock(Estacionamiento.class);
		
		zonaDeEstacionamiento.registrarEstacionamiento(nuevoEstacionamiento);
		
		assertEquals(1,zonaDeEstacionamiento.getEstacionamientosDeLaZona().size());
		
	}

}
