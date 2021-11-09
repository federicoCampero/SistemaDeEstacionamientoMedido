package sem;

import java.time.LocalDate;

public abstract class Estacionamiento {
	
	private String patente;
	private LocalDate fechaInicio;
	private boolean validez;
	private ZonaEstacionamiento zonaEstacimiento;
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public Estacionamiento(String patente, LocalDate fechaInicio, boolean validez,
			ZonaEstacionamiento zonaEstacimiento) {
		this.patente = patente;
		this.fechaInicio = fechaInicio;
		this.validez = validez;
		this.zonaEstacimiento = zonaEstacimiento;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public boolean esValido() {
		return validez;
	}
	public void setValidez(boolean validez) {
		this.validez = validez;
	}
	public ZonaEstacionamiento getZonaEstacimiento() {
		return zonaEstacimiento;
	}
	public void setZonaEstacimiento(ZonaEstacionamiento zonaEstacimiento) {
		this.zonaEstacimiento = zonaEstacimiento;
	}
	
	
	
	
	
}
