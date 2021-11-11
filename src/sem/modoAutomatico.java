package sem;

public class modoAutomatico implements TipoDeModo {

	@Override
	public void entrarAlEstacionamiento(CelularApp celularApp, String pantente) {
		celularApp.iniciarEstacionamiento(pantente);
		
	}

	@Override
	public void salirDelEstacionamiento(CelularApp celularApp) {
		celularApp.finalizarEstacionamiento();
		
	}

}
