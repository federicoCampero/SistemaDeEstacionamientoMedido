package sem;

import java.time.LocalTime;

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
	

	
	
	
	
	
}
