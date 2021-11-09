package sem;

import java.time.LocalDate;
import java.time.LocalTime;

public class EstacionamientoMedianteApp extends Estacionamiento {
	private String numeroCelular;
	private LocalTime horaMaximaSegunCredito;

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public LocalTime getHoraMaximaSegunCredito() {
		return horaMaximaSegunCredito;
	}

	public void setHoraMaximaSegunCredito(LocalTime horaMaximaSegunCredito) {
		this.horaMaximaSegunCredito = horaMaximaSegunCredito;
	}

	public EstacionamientoMedianteApp(String patente, LocalDate fechaInicio, boolean validez,
			ZonaEstacionamiento zonaEstacimiento, String numeroCelular, LocalTime horaMaximaSegunCredito) {
		super(patente, fechaInicio, validez, zonaEstacimiento);
		this.numeroCelular = numeroCelular;
		this.horaMaximaSegunCredito = horaMaximaSegunCredito;
	}

	

}
