package arg.edu.unq.po2.tpfinal.tiposDeModos;

import arg.edu.unq.po2.tpfinal.celularApp.CelularApp;

public class ModoManual implements TipoDeModo {

	/**
	 * cuando el celularApp activa el modo manual, este no debe tener dispara ningun
	 * mensaje de inicio o finalizacion de estacionamiento (estas las dispara el
	 * usuario). en cambio dispara los alertas por si el usuario olvide dispararlos
	 */

	@Override
	public void entrarAlEstacionamiento(CelularApp celularApp) {
		celularApp.alertaIncioEstacionamiento();

	}

	@Override
	public void salirDelEstacionamiento(CelularApp celularApp) {
		celularApp.alertaFinEstacionamiento();
	}

}
