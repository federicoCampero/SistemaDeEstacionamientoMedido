package sem;

import java.time.LocalTime;

public class CelularApp {
	
	private SEM sem;
	private int numero;
	private TipoDeModo modo;
	private double saldo;
	
	public CelularApp(SEM sem, int numero, TipoDeModo modo, double saldo) {
		super();
		this.sem = sem;
		this.numero = numero;
		this.modo = modo;
		this.saldo = saldo;
	}

	public int getNumero() {
		return numero;
	}

	public TipoDeModo getModo() {
		return modo;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public SEM getSem() {
		return sem;
	}

	public void iniciarEstacionamiento(String patente) {
		
		sem.registrarEstacionamiento(this.crearEstacionamientoMedianteApp(patente));
		
	}
	
	private EstacionamientoMedianteApp crearEstacionamientoMedianteApp(String patente) {
		LocalTime horaInicio = LocalTime.now();

		return new EstacionamientoMedianteApp(patente,horaInicio,null,true,this.getNumero());
	}
	
	public void finalizarEstacionamiento() {
		sem.terminarVigenciaDelEstacionamiento(this.getNumero());
	}
	
	

}
