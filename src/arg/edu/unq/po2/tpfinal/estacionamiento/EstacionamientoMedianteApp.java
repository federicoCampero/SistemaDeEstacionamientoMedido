package arg.edu.unq.po2.tpfinal.estacionamiento;

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

	public void setNumeroCelular(int numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	
	
}
