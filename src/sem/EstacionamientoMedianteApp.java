package sem;

import java.time.LocalTime;

public class EstacionamientoMedianteApp extends Estacionamiento {
	
	private int numeroCelular;

	public EstacionamientoMedianteApp(String patente, LocalTime horaInicio, LocalTime horafin, boolean validez,
			int numeroCelular) {
		super(patente, horaInicio, horafin, validez);
		this.numeroCelular = numeroCelular;
	}

	public int getNumeroCelular() {
		return numeroCelular;

	}
	
	public void finalizarVigencia() {
		this.setValidez(false);

	}
}
