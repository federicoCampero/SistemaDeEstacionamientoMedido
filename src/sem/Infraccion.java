package sem;

import java.time.LocalDate;
import java.time.LocalTime;

public class Infraccion {
	
	private Inspector inspector;
	private int patente;
	private LocalDate fecha;
	private LocalTime horario;
	private ZonaDeEstacionamiento zonaDeEstacionamiento;
	
	public Infraccion(Inspector inspector, int patente, LocalDate fecha, LocalTime horario,
			ZonaDeEstacionamiento zonaDeEstacionamiento) {
		super();
		this.inspector = inspector;
		this.patente = patente;
		this.fecha = fecha;
		this.horario = horario;
		this.zonaDeEstacionamiento = zonaDeEstacionamiento;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public int getPatente() {
		return patente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public ZonaDeEstacionamiento getZonaDeEstacionamiento() {
		return zonaDeEstacionamiento;
	}
	
	

	
	

}
