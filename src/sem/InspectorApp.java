package sem;

import java.util.List;


public class InspectorApp {
	
	private List<ZonaDeEstacionamiento> asignacionesDeZonaEstacionamiento;
	private SEM sem;

	public InspectorApp(List<ZonaDeEstacionamiento> asignacionesDeZonaEstacionamiento) {
		super();
		this.asignacionesDeZonaEstacionamiento = asignacionesDeZonaEstacionamiento;
	}

	public List<ZonaDeEstacionamiento> getAsignacionesDeZonaEstacionamiento() {
		return asignacionesDeZonaEstacionamiento;
	}

	public void setAsignacionesDeZonaEstacionamiento(List<ZonaDeEstacionamiento> asignacionesDeZonaEstacionamiento) {
		this.asignacionesDeZonaEstacionamiento = asignacionesDeZonaEstacionamiento;
	}
	
	public boolean consultaEstacionamientoVigente(int patente) {
		
		//return sem.tieneEstacionamientoVigente(patente);
		return false;
	}
	
	
	public void altaDeInflaccion(int patente) {
		//sem.realizarAltaDeInflaccion(patente);
		
	}
	
	
	/*
	 * cuando el inspector realiza el recorrido , por cada zona de estacionamiento deberia haber Estacionamientos
	 */
	public void recorrerZonasDeEstacionamiento() {
		
		/*
		asignacionesDeZonaEstacionamiento.stream().forEach(zona -> zona.get);
		*/
	}

}