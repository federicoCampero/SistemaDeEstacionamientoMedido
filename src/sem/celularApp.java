package sem;

public class celularApp {
	private int numero;
	private ItipoDeModo tipoDeModo;
	private double saldo;
	private boolean estacionamientoActivo;
	
	
	public celularApp(int numero, ItipoDeModo tipoDeModo, double saldo) {
		super();
		this.numero = numero;
		this.tipoDeModo = tipoDeModo;
		this.saldo = saldo;
		this.estacionamientoActivo = false;
	}
	
	// ASISTENCIA AL USUARIO
	public void activarDeteccionDesplazamiento() {}
	
	public void alertaIncioEstacionamiento() {
		if(!this.isEstacionamientoActivo()) {
			this.informarAlertaInicioEstacionamiento();
		}
	}
	public void alertaFinEstacionamiento() {
		if(this.isEstacionamientoActivo()) {
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
	public boolean isEstacionamientoActivo() {
		return estacionamientoActivo;
	}

	public void setEstacionamientoActivo(boolean estacionamientoActivo) {
		this.estacionamientoActivo = estacionamientoActivo;
	}
}
