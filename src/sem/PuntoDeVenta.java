package sem;

import java.time.LocalTime;

public class PuntoDeVenta {
	
	private SEM sem;

	public PuntoDeVenta(SEM sem) {
		super();
		this.sem = sem;
	}

	private SEM getSem() {
		return sem;
	}
	
	public void iniciarEstacionamiento(String patente, int cantidadHoras) {
		
		this.getSem().registrarEstacionamiento(this.crearEstacionamientoCompraPuntual(patente,cantidadHoras));

	}
	
	

	private EstacionamientoCompraPuntual crearEstacionamientoCompraPuntual(String patente, int cantidadHoras) {
		LocalTime horaInicio = LocalTime.now();

		return new EstacionamientoCompraPuntual(patente,horaInicio,this.calcularHoraFinDelEstacionamiento(horaInicio,cantidadHoras),true,cantidadHoras);
	}
	

	private LocalTime calcularHoraFinDelEstacionamiento(LocalTime horaInicio, int cantidadHoras) {			
		return horaInicio.plusHours(cantidadHoras);
	}

	public void cargarCredito(CelularApp celularApp, double cantidad) {
		
		
	}

}
