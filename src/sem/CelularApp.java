package sem;

import java.time.LocalTime;

public class CelularApp implements MovementSensor {
	
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

		return new EstacionamientoMedianteApp(patente,horaInicio,this.calcularHoraMaximaAEstacionarSegunCredito(),true,this.getNumero());
	}
	
	private LocalTime calcularHoraMaximaAEstacionarSegunCredito() {
		return null;
	}

	public void finalizarEstacionamiento() {
		sem.terminarVigenciaDelEstacionamiento(this.getNumero());
	}

	@Override
	public void driving() {
		this.getModo().salirDelEstacionamiento(this);
		
	}

	
	@Override
	public void walking() {
		this.getModo().entrarAlEstacionamiento(this, patente);
		
	}
	
	

}
