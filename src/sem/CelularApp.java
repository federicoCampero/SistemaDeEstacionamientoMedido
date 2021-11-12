package sem;

import java.time.LocalTime;

public class CelularApp implements MovementSensor {

	private SEM sem;
	private int numero;
	private TipoDeModo modo;
	private String patente;

	public CelularApp(SEM sem, int numero, TipoDeModo modo, String patente) {
		super();
		this.sem = sem;
		this.numero = numero;
		this.modo = modo;
		this.patente = patente;
	}

	public SEM getSem() {
		return sem;
	}

	public void setSem(SEM sem) {
		this.sem = sem;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoDeModo getModo() {
		return modo;
	}

	public void setModo(TipoDeModo modo) {
		this.modo = modo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	/**
	 * envia un mensaje al sem para que registre un inicio del estacionamiento, el
	 * sem debe validar el mismo dependiendo el saldo del usuario
	 * 
	 * @param patente
	 */
	public void iniciarEstacionamiento() {

		sem.registrarInicioEstacionamientoViaApp(this.getNumero(), this.getPatente());

	}

	/**
	 * envia un mensaje al sem para que termine la vigencia del estacionamiento
	 */
	public void finalizarEstacionamiento() {
		sem.registrarFinEstacionamientoViaApp(this.getNumero());
	}

	@Override
	public void driving() {
		this.getModo().salirDelEstacionamiento(this);

	}

	@Override
	public void walking() {
		this.getModo().entrarAlEstacionamiento(this);

	}

}
