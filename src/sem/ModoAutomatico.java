package sem;

public class ModoAutomatico implements TipoDeModo {
	
	
	
	/**
	 * el modoAutomatica es el encargado de disparar los mensajes de inicio y finalizacion del estacionamiento cuando el 
	 * usuario ingresa a la zona de estacionamiento 
	 *  
	 */

	@Override
	public void entrarAlEstacionamiento(CelularApp celularApp) {
		try {
			celularApp.iniciarEstacionamiento();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void salirDelEstacionamiento(CelularApp celularApp) {
		try {
			celularApp.finalizarEstacionamiento();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
