package sem;

import java.time.LocalDate;
import java.time.LocalTime;

public class Inflaccion {
	
	private InspectorApp inspector;
	private int patente;
	private LocalDate fecha;
	private LocalTime horario;
	private ZonaDeEstacionamiento zonaDeEstacionamiento;
	
	public Inflaccion(InspectorApp inspector, int patente, LocalDate fecha, LocalTime horario,
			ZonaDeEstacionamiento zonaDeEstacionamiento) {
		super();
		this.inspector = inspector;
		this.patente = patente;
		this.fecha = fecha;
		this.horario = horario;
		this.zonaDeEstacionamiento = zonaDeEstacionamiento;
	}

	public InspectorApp getInspector() {
		return inspector;
	}

	public void setInspector(InspectorApp inspector) {
		this.inspector = inspector;
	}

	public int getPatente() {
		return patente;
	}

	public void setPatente(int patente) {
		this.patente = patente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public ZonaDeEstacionamiento getZonaDeEstacionamiento() {
		return zonaDeEstacionamiento;
	}

	public void setZonaDeEstacionamiento(ZonaDeEstacionamiento zonaDeEstacionamiento) {
		this.zonaDeEstacionamiento = zonaDeEstacionamiento;
	}
	
	
	

}
