package sem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SEM {

	private List<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	private List<ZonaDeEstacionamiento> zonasDeEstacionamientos = new ArrayList<ZonaDeEstacionamiento>();
	private List<Infraccion> infracionesLabradas = new ArrayList<Infraccion>();
	private List<Compra> comprasRealizadas = new ArrayList<Compra>();
	
	


	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}

	public void setEstacionamientos(List<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	public List<ZonaDeEstacionamiento> getZonasDeEstacionamientos() {
		return zonasDeEstacionamientos;
	}

	public void setZonasDeEstacionamientos(List<ZonaDeEstacionamiento> zonasDeEstacionamientos) {
		this.zonasDeEstacionamientos = zonasDeEstacionamientos;
	}

	public List<Infraccion> getInfracionesLabradas() {
		return infracionesLabradas;
	}

	public void setInfracionesLabradas(List<Infraccion> infracionesLabradas) {
		this.infracionesLabradas = infracionesLabradas;
	}
	
	public List<Compra> getComprasRealizadas() {
		return comprasRealizadas;
	}

	public void setComprasRealizadas(List<Compra> comprasRealizadas) {
		this.comprasRealizadas = comprasRealizadas;
	}


	/**
	 * registrar inicio de Estacionamiento via app, crea una instancia de
	 * EstacionamientoMedianteApp y lo guarda en la lista de estacionamientos .
	 * tambien lo guarda en la lista de ZonaDeEstacionamientos(en la primera
	 * posicion), pero tendria que guardarla depende la locacion(GPS).
	 * 
	 * @param numeroCelular
	 * @param patente
	 */
	public void registrarInicioEstacionamientoViaApp(int numeroCelular, String patente) {

		EstacionamientoMedianteApp nuevoEstacionamientoViaApp = this.crearEstacionamientoMedianteApp(numeroCelular,
				patente);

		this.agregarEstacionamiento(nuevoEstacionamientoViaApp);

		this.getZonasDeEstacionamientos().get(0).registrarEstacionamiento(nuevoEstacionamientoViaApp);
		
		
		
		

	}

	/**
	 * crea una instancia de Estacionamiento Mediante App y la retorna
	 * @param numeroCelular
	 * @param patente
	 * @return
	 */
	private EstacionamientoMedianteApp crearEstacionamientoMedianteApp(int numeroCelular, String patente) {
		LocalTime horaInicio = LocalTime.now();

		return new EstacionamientoMedianteApp(patente, horaInicio, this.calcularHoraMaximaAEstacionarSegunCredito(),
				true, numeroCelular);
	}

	private LocalTime calcularHoraMaximaAEstacionarSegunCredito() {
		return null;
	}

	public void registrarFinEstacionamientoViaApp(int numero) {

	}

	/**
	 * registrar inicio de Estacionamiento Compra Puntual, crea una instancia de
	 * EstacionamientoCompraPuntual y lo guarda en la lista de estacionamientos .
	 * tambien lo guarda en la lista de ZonaDeEstacionamientos(en la primera
	 * posicion), pero tendria que guardarla depende la locacion(GPS).
	 * 
	 * @param patente         patente del choche
	 * @param cantidadDeHoras cantidad de horas que le usuario va a permanecer en el
	 *                        Estacionamiento
	 */
	public void registrarInicioEstacionamientoCompraPuntual(String patente, int cantidadDeHoras, PuntoDeVenta puntoDeVenta) {

		EstacionamientoCompraPuntual nuevoEstacionamientoCompraPuntual = this.crearEstacionamientoCompraPuntual(patente,
				cantidadDeHoras);

		this.agregarEstacionamiento(nuevoEstacionamientoCompraPuntual);

		this.getZonasDeEstacionamientos().get(0).registrarEstacionamiento(nuevoEstacionamientoCompraPuntual);
		
		
		CompraPuntual nuevaCompraPuntual = this.crearCompraPuntoDeVenta(puntoDeVenta,cantidadDeHoras);
		
		this.agregarCompra(nuevaCompraPuntual);

	}

	private CompraPuntual crearCompraPuntoDeVenta(PuntoDeVenta puntoDeVenta, int cantidadDeHoras) {
		LocalTime HoraDeCompra = LocalTime.now();
		LocalDate fechaDeCompra = LocalDate.now();
		
		return new CompraPuntual(0,puntoDeVenta,fechaDeCompra,HoraDeCompra,cantidadDeHoras);
	}

	/**
	 * crea una instancia de EstacionamientoCompraPuntual y la retorna
	 * 
	 * @param patente
	 * @param cantidadHoras
	 * @return
	 */

	private EstacionamientoCompraPuntual crearEstacionamientoCompraPuntual(String patente, int cantidadHoras) {
		LocalTime horaInicio = LocalTime.now();

		return new EstacionamientoCompraPuntual(patente, horaInicio,
				this.calcularHoraFinDelEstacionamiento(horaInicio, cantidadHoras), true, cantidadHoras);
		
	}

	/**
	 * dado la hora de inicio y la cantidad de horas calcula la hora(LocalTime)
	 * resultante despues de sumarle esa cantidad de horas
	 * 
	 * @param horaInicio    hora de inicio del estacionamiento
	 * @param cantidadHoras
	 * @return
	 */

	private LocalTime calcularHoraFinDelEstacionamiento(LocalTime horaInicio, int cantidadHoras) {
		return horaInicio.plusHours(cantidadHoras);
	}

	/**
	 * buscar al estacionamiento con una determinada patente y le pregunta si esta valido el estacionamiento
	 * @param patente
	 * @return
	 */
	public boolean tieneEstacionamientoVigente(String patente) {
		
		return this.buscarEstacionamiento(patente).isValidez();
	}
	
	public Estacionamiento buscarEstacionamiento(String patente) {
		return null;		
	}

	public void registrarAltaDeInfraccion(String patente, Inspector inspector,ZonaDeEstacionamiento zonaDeEstacionamiento) {
		
		Infraccion nuevaInflaccion = new Infraccion(inspector,patente , LocalDate.now(), LocalTime.now(), zonaDeEstacionamiento);	
		
		this.agregarInfraccion(nuevaInflaccion);

	}

	private void agregarInfraccion(Infraccion infraccion) {
		
		this.getInfracionesLabradas().add(infraccion);
		
	}

	public void registrarCargaDeCredito(int numeroDeCelular, double cantidad) {
		
		

	}

	public void agregarEstacionamiento(Estacionamiento estacionamiento) {
		this.getEstacionamientos().add(estacionamiento);
	}

	public void agregarCompra(Compra compra) {
		this.getComprasRealizadas().add(compra);
	}

	
}
