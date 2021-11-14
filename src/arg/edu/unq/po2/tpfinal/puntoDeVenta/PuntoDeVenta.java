package arg.edu.unq.po2.tpfinal.puntoDeVenta;

import arg.edu.unq.po2.tpfinal.sem.SEM;

public class PuntoDeVenta {

	private SEM sem;

	public PuntoDeVenta(SEM sem) {
		super();
		this.sem = sem;
	}

	public SEM getSem() {
		return sem;
	}

	public void setSem(SEM sem) {
		this.sem = sem;
	}

	/**
	 * envia un mensaje al sem para que registre el inicio del estacionamiento
	 * mediante compra puntual
	 * 
	 * @param patente
	 * @param cantidadHoras
	 */
	public void iniciarEstacionamiento(String patente, int cantidadHoras) {
		this.getSem().registrarInicioEstacionamientoCompraPuntual(patente, cantidadHoras, this);
	}

	/**
	 * envia un mensaje al sem para que le carge credito al usuario, el credito
	 * quedara asocidado al numero del celular. para poder realizar la carga de
	 * credito el usuario tiene que haber registrado el celular con su numero en el
	 * sem
	 * 
	 * @param numeroDeCelular
	 * @param cantidad
	 * @throws Exception 
	 */
	public void cargarCredito(int numeroCelular, double cantidad) throws Exception {
			sem.registrarCargaDeCredito(numeroCelular, cantidad, this);
	}
}
