package arg.edu.unq.po2.tpfinal.compras;

import java.time.LocalDate;
import java.time.LocalTime;

import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;

public abstract class Compra {
	private int numeroControl;
	private PuntoDeVenta puntoVenta;
	private LocalDate fecha;
	private LocalTime hora;
	
	// Constructor
	public Compra(int numeroControl, PuntoDeVenta puntoVenta, LocalDate fecha, LocalTime hora) {
		super();
		this.numeroControl = numeroControl;
		this.puntoVenta = puntoVenta;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	// Get and Set
	public int getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(int numeroControl) {
		this.numeroControl = numeroControl;
	}

	public PuntoDeVenta getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(PuntoDeVenta puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
