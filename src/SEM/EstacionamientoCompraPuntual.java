package SEM;

import java.time.LocalDate;

public class EstacionamientoCompraPuntual extends Estacionamiento{
	private LocalDate fechaFin;
	private int horasEstacionar;
	
	public int getHorasEstacionar() {
		return horasEstacionar;
	}
	public void setHorasEstacionar(int horasEstacionar) {
		this.horasEstacionar = horasEstacionar;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public EstacionamientoCompraPuntual(String patente, LocalDate fechaInicio, boolean validez,
			ZonaEstacionamiento zonaEstacimiento, LocalDate fechaFin, int horasEstacionar) {
		super(patente, fechaInicio, validez, zonaEstacimiento);
		this.fechaFin = fechaFin;
		this.horasEstacionar = horasEstacionar;
	}
	
	
}
