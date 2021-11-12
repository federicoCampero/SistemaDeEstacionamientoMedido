package sem;

import java.time.LocalDate;
import java.time.LocalTime;

public class Infraccion {
	
	private Inspector inspector;
	private String patente;
	private LocalDate fecha;
	private LocalTime horario;
	private ZonaDeEstacionamiento zonaDeEstacionamiento;
	
	public Infraccion(Inspector inspector, String patente, LocalDate fecha, LocalTime horario,
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

	public String getPatente() {
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