package arg.edu.unq.po2.tpfinal.estacionamiento;

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

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHorafin() {
		return horafin;
	}

	public void setHorafin(LocalTime horafin) {
		this.horafin = horafin;
	}

	public boolean isValidez() {
		return validez;
	}

	public void setValidez(boolean validez) {
		this.validez = validez;
	}

	public Long duracionEstacionamiento() {
		return this.getHoraInicio().until(this.getHorafin(), HOURS);
	}

	public Long costoEstacionamiento() {
		return this.duracionEstacionamiento() * 40;
	}

}
