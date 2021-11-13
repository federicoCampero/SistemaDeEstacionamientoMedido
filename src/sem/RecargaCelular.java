package sem;

import java.time.LocalDate;
import java.time.LocalTime;

public class RecargaCelular extends Compra {
	private double monto;
	
	//Constructor
	private String numeroCel;
	public RecargaCelular(int numeroControl, PuntoDeVenta puntoVenta, LocalDate fecha, LocalTime hora, double monto,
			String numeroCel) {
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
	public String getNumeroCel() {
		return numeroCel;
	}
	public void setNumeroCel(String numeroCel) {
		this.numeroCel = numeroCel;
	}
}
