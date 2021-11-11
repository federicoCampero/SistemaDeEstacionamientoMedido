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
	public void activarDeteccionDesplazamiento() {}
	
	
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
	
	private void informarAlertaInicioEstacionamiento() {
		// TODO Auto-generated method stub
		
	}
	private void informarAlertaFinEstacionamiento() {
		// TODO Auto-generated method stub
		
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
