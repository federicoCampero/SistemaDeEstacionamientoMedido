package sem;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.HOURS;

public abstract class Estacionamiento {
	
	private String patente;
	private LocalTime horaInicio;
	private LocalTime horafin;
	private boolean validez;
	
	public Estacionamiento(String patente, LocalTime horaInicio, LocalTime horafin, boolean validez) {
		super();
		this.patente = patente;
		this.horaInicio = horaInicio;
		this.horafin = horafin;
		this.validez = validez;
	}

	public String getPatente() {
		return patente;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHorafin() {
		return horafin;
	}

	public boolean isValidez() {
		return validez;
	}

	protected void setValidez(boolean validez) {
		this.validez = validez;
	}

	public Long duracionEstacionamiento() {
		return this.getHorafin().until(this.getHoraInicio(), HOURS);
	}
	public Long costoEstacionamiento() {
		return this.duracionEstacionamiento() * 40;
	}
	

	
	
	
	
	
}
