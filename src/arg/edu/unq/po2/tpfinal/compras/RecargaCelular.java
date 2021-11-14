package arg.edu.unq.po2.tpfinal.compras;

import java.time.LocalDate;
import java.time.LocalTime;

import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;

public class RecargaCelular extends Compra {
	private double monto;
	private int numeroCel;
	
	//Constructor
	
	public RecargaCelular(int numeroControl, PuntoDeVenta puntoVenta, LocalDate fecha, LocalTime hora, double monto,
			int numeroCel) {
		super(numeroControl, puntoVenta, fecha, hora);
		this.monto = monto;
		this.numeroCel = numeroCel;
	}
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public int getNumeroCel() {
		return numeroCel;
	}
	public void setNumeroCel(int numeroCel) {
		this.numeroCel = numeroCel;
	}
}
