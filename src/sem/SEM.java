package sem;

public class SEM {

	// INFORMACION AL USUARIO EN ESTACIONAMIENTO VIA APP
	
	// Evento 1 - Inicio de Estacionamiento
	public void registrarInicioEstacionamientoViaApp(int numero, int patente) {
		if(this.saldoSuficiente(numero)) {
			this.eventoInicioEstacionamientoViaApp(numero);
			this.iniciarEstacionamientoEnSEM(numero, patente);
		} else {
			this.informarSaldoInsuficiente(numero);
		}
	}
	
	// Evento 2 - Fin de Estacionamiento
	public void registrarFinEstacionamientoViaApp(int numero) {
		this.eventoFinEstacionamientoViaApp(numero);
		this.finalizarEstacionamientoEnSEM(numero);
	}
	
	// Evento 3 - Consulta de saldo
	public double consultaDeSaldoViaApp(int numero) {
		return 2;
	}
	
	// Metodos evento 1
	private boolean saldoSuficiente(int numero) {
		// TODO Auto-generated method stub
		return false;
	}
	private void eventoInicioEstacionamientoViaApp(int numero) {
		// TODO Auto-generated method stub
		
	}
	private void iniciarEstacionamientoEnSEM(int numero, int patente) {
		// TODO Auto-generated method stub
		
	}
	private void informarSaldoInsuficiente(int numero) {
		// TODO Auto-generated method stub
		
	}
	
	// Metodos evento 2
	private void finalizarEstacionamientoEnSEM(int numero) {
		// TODO Auto-generated method stub
		
	}
	private void eventoFinEstacionamientoViaApp(int numero) {
		// TODO Auto-generated method stub
		
	}

	public boolean estacionamientoActivo(int numero) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean tieneEstacionamientoVigente(int numero) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
}
