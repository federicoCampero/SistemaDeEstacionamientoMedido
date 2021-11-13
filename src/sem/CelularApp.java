package sem;

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
		if(sem.estacionamientoActivo(this.getPatente())) {
			this.getModo().salirDelEstacionamiento(this);
		}
	}

	@Override
	public void walking() {
		if(!sem.estacionamientoActivo(this.getPatente())) {
		this.getModo().entrarAlEstacionamiento(this);
		}

	}

	// ASISTENCIA AL USUARIO
	public void alertaIncioEstacionamiento() {
		if(!sem.estacionamientoActivo(this.getPatente())) {
			this.informarAlertaInicioEstacionamiento();
		}
	}
	public void alertaFinEstacionamiento() {
		if(sem.estacionamientoActivo(this.getPatente())) {
			this.informarAlertaFinEstacionamiento();
		}
	}
	
	private String informarAlertaInicioEstacionamiento() {
		return "Asegurese de inicar su estacionamiento";
	}
	private String informarAlertaFinEstacionamiento() {
		return "Asegurese de finalizar su estacionamiento";
	}
	
	
	// Getters Setters


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
}
