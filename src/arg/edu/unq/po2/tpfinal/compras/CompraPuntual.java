package arg.edu.unq.po2.tpfinal.compras;

import java.time.LocalDate;
import java.time.LocalTime;

import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;

public class CompraPuntual extends Compra {
	private int cantidadHoras;
	
	// Constructor
	public CompraPuntual(int numeroControl, PuntoDeVenta puntoVenta, LocalDate fecha, LocalTime hora,
			int cantidadHoras) {
		super(numeroControl, puntoVenta, fecha, hora);
		this.setCantidadHoras(cantidadHoras);
	}
	
	// Get and Set
	public int getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(int cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	
}
