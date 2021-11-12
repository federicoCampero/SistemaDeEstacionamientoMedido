package sem;

import java.time.LocalTime;

public class SEM {
	
	
	/*
	 * registrar inicio de Estacionamiento via app, crea una instancia de EstacionamientoMedianteApp y la almacena
	 */
	
	public void registrarInicioEstacionamientoViaApp(int numeroCelular, String patente) {
		
		EstacionamientoMedianteApp nuevoEstacionamiento = this.crearEstacionamientoMedianteApp(numeroCelular,patente);
		
	}
	
	private EstacionamientoMedianteApp crearEstacionamientoMedianteApp(int numeroCelular, String patente) {
		LocalTime horaInicio = LocalTime.now();

		return new EstacionamientoMedianteApp(patente,horaInicio,this.calcularHoraMaximaAEstacionarSegunCredito(),true,numeroCelular);
	}
	
	
	private LocalTime calcularHoraMaximaAEstacionarSegunCredito() {
		return null;
	}


	public void registrarFinEstacionamientoViaApp(int numero) {
		
	}
	
	/**
	 * registra el inicio del Estacionamiento por Compra puntual,  crea una instancia de EstacionamientoCompraPuntual y la guarda
	 * @param patente patente del choche
	 * @param cantidadDeHoras cantidad de horas que le usuario va a permanecer en el Estacionamiento
	 */
	public void registrarInicioEstacionamientoCompraPuntual(String patente, int cantidadDeHoras) {
		
		EstacionamientoCompraPuntual nuevoEstacionamiento = this.crearEstacionamientoCompraPuntual(patente,cantidadDeHoras);
		
	}
	
	/**
	 * crea la instancia de EstacionamientoCompraPuntual y la retorna
	 * @param patente
	 * @param cantidadHoras
	 * @return
	 */
	
	private EstacionamientoCompraPuntual crearEstacionamientoCompraPuntual(String patente, int cantidadHoras) {
		LocalTime horaInicio = LocalTime.now();

		return new EstacionamientoCompraPuntual(patente,horaInicio,this.calcularHoraFinDelEstacionamiento(horaInicio,cantidadHoras),true,cantidadHoras);
	}
	
	/**
	 * dado la hora de inicio y la cantidad de horas calcula la hora(LocalTime) resultante despues de sumarle esa cantidad de horas
	 * @param horaInicio hora de inicio del estacionamiento
	 * @param cantidadHoras 
	 * @return
	 */

	private LocalTime calcularHoraFinDelEstacionamiento(LocalTime horaInicio, int cantidadHoras) {			
		return horaInicio.plusHours(cantidadHoras);
	}

	public boolean tieneEstacionamientoVigente(String patente) {
		return false;
	}

	public void registrarAltaDeInfraccion(String patente) {
		
	}

	public void registrarCargaDeCredito(int numeroDeCelular, double cantidad) {
		
	}

}
