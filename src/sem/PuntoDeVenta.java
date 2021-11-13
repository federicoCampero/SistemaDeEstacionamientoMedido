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
	
	/**
	 * envia un mensaje al sem para que registre el inicio del estacionamiento mediante compra puntual 
	 * @param patente
	 * @param cantidadHoras
	 */
	public void iniciarEstacionamiento(String patente, int cantidadHoras) {
		
		this.getSem().registrarInicioEstacionamientoCompraPuntual(patente,cantidadHoras,this);

	}



	/**
	 * envia un mensaje al sem para que le carge credito al usuario, el credito quedara asocidado al numero del celular 
	 * @param numeroDeCelular
	 * @param cantidad
	 */
	public void cargarCredito(int numeroDeCelular, double cantidad) {
		
		sem.registrarCargaDeCredito(numeroDeCelular,cantidad);
	}
	
	

}
