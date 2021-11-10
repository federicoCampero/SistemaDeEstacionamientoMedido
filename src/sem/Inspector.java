package sem;

import java.util.List;

public class Inspector {
	
	private SEM sem;
	private ZonaDeEstacionamiento asignadoAZonaEstacionamiento;
	
	public Inspector(SEM sem, ZonaDeEstacionamiento asignacionesDeZonaEstacionamiento) {
		super();
		this.sem = sem;
		this.asignadoAZonaEstacionamiento = asignacionesDeZonaEstacionamiento;
	}

	public SEM getSem() {
		return sem;
	}

	public ZonaDeEstacionamiento getAsignadoAZonaEstacionamiento() {
		return asignadoAZonaEstacionamiento;
	}

	public boolean consultaEstacionamientoVigente(String patente) {
		
		return sem.tieneEstacionamientoVigente(patente);
	}
	
	
	public void altaDeInflaccion(String patente) {
		sem.realizarAltaDeInflaccion(patente);
		
	}
	
	public void recorrerZonasDeEstacionamiento() {
		
		List<Estacionamiento> estacionamientosDeLaZona = this.getAsignadoAZonaEstacionamiento().getEstacionamientosDeLaZona();
		
		for(Estacionamiento estacionamiento : estacionamientosDeLaZona) {
			
			String patenteDelEstacionamiento = estacionamiento.getPatente();
			
			if (this.consultaEstacionamientoVigente(patenteDelEstacionamiento)) {
				this.altaDeInflaccion(patenteDelEstacionamiento);
			}
		}
	}

}
