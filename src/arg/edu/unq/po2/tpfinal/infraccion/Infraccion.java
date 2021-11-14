package arg.edu.unq.po2.tpfinal.infraccion;

import java.time.LocalDate;
import java.time.LocalTime;

import arg.edu.unq.po2.tpfinal.inspector.Inspector;
import arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento.ZonaDeEstacionamiento;

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

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public void setZonaDeEstacionamiento(ZonaDeEstacionamiento zonaDeEstacionamiento) {
		this.zonaDeEstacionamiento = zonaDeEstacionamiento;
	}
	
	

	
	

}
