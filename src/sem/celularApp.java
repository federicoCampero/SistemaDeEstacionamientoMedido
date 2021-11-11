package sem;

public class celularApp {
	private int numero;
	private ItipoDeModo tipoDeModo;
	private double saldo;
	private SEM sem;
	
	
	public celularApp(int numero, ItipoDeModo tipoDeModo, double saldo) {
		super();
		this.numero = numero;
		this.tipoDeModo = tipoDeModo;
		this.saldo = saldo;
	}
	
	// ASISTENCIA AL USUARIO
	public void alertaIncioEstacionamiento() {
		if(!sem.tieneEstacionamientoVigente(this.getNumero())) {
			this.informarAlertaInicioEstacionamiento();
		}
	}
	public void alertaFinEstacionamiento() {
		if(sem.tieneEstacionamientoVigente(this.getNumero())) {
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public ItipoDeModo getTipoDeModo() {
		return tipoDeModo;
	}
	public void setTipoDeModo(ItipoDeModo tipoDeModo) {
		this.tipoDeModo = tipoDeModo;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}