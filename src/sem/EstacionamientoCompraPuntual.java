package sem;


import java.time.LocalDate;

import java.time.LocalTime;

public class EstacionamientoCompraPuntual extends Estacionamiento{
	
	private int horasEstacionar;

	public EstacionamientoCompraPuntual(String patente, LocalTime horaInicio, LocalTime horafin, boolean validez,
			int horasEstacionar) {
		super(patente, horaInicio, horafin, validez);
		this.horasEstacionar = horasEstacionar;
	}

	public int getHorasEstacionar() {
		return horasEstacionar;
	}
	
	
}