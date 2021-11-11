package sem;

public class SEM {

	// INFORMACION AL USUARIO EN ESTACIONAMIENTO VIA APP
	
	// Evento 1 - Inicio de Estacionamiento
	public void eventoInicioEstacionamientoViaApp(int numero, int patente) {
		if(this.saldoSuficiente(numero)) {
			this.informarInicioEstacionamiento(numero);
			this.iniciarEstacionamiento(numero, patente);
		} else {
			this.informarSaldoInsuficiente(numero);
		}
	}
	
	// Evento 2 - Fin de Estacionamiento
	public void eventoFinEstacionamiento(int numero) {
		this.informarFinEstacionamiento(numero);
		this.finalizarEstacionamiento(numero);
	}
	
	// Evento 3 - Consulta de saldo
	public double consultaDeSaldo(int numero) {
		return 2;
	}
	
	// Metodos evento 1
	private boolean saldoSuficiente(int numero) {
		// TODO Auto-generated method stub
		return false;
	}
	private void informarInicioEstacionamiento(int numero) {
		// TODO Auto-generated method stub
		
	}
	private void iniciarEstacionamiento(int numero, int patente) {
		// TODO Auto-generated method stub
		
	}
	private void informarSaldoInsuficiente(int numero) {
		// TODO Auto-generated method stub
		
	}
	
	// Metodos evento 2
	private void finalizarEstacionamiento(int numero) {
		// TODO Auto-generated method stub
		
	}
	private void informarFinEstacionamiento(int numero) {
		// TODO Auto-generated method stub
		
	}
	

	
}
