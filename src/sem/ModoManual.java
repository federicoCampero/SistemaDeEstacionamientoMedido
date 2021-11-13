package sem;

public class ModoManual implements TipoDeModo{

	/**
	 * cuando el celularApp activa el modo manual, este no debe tener comportamiento ya que el 
	 * encargado de iniciar y finalizar el estacionamiento cuando se ingresa a la zona de estacionamiento 
	 * es el usuario 
	 */
	
	@Override
	public void entrarAlEstacionamiento(CelularApp celularApp) {
		celularApp.alertaInicioEstacionamiento();
		
	}

	@Override
	public void salirDelEstacionamiento(CelularApp celularApp) {
		celularApp.alertaFinEstacionamiento();
	}


	

}
