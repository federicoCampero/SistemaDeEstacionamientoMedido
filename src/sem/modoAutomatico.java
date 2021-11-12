package sem;

public class modoAutomatico implements TipoDeModo {
	
	/**
	 * el modoAutomatica es el encargado de disparar los mensajes de inicio y finalizacion del estacionamiento cuando el 
	 * usuario ingresa a la zona de estacionamiento 
	 *  
	 */

	@Override
	public void entrarAlEstacionamiento(CelularApp celularApp) {
		celularApp.iniciarEstacionamiento();
		
	}

	@Override
	public void salirDelEstacionamiento(CelularApp celularApp) {
		celularApp.finalizarEstacionamiento();
		
	}

}
