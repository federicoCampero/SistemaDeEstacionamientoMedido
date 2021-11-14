package sem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SEM {

	private List<Estacionamiento> estacionamientosApp = new ArrayList<Estacionamiento>();
	private List<Estacionamiento> estacionamientosCompraPuntual = new ArrayList<Estacionamiento>();
	private List<Compra> comprasRealizadas = new ArrayList<Compra>();
	private List<ZonaDeEstacionamiento> zonasDeEstacionamientos = new ArrayList<ZonaDeEstacionamiento>();
	private List<Infraccion> infracionesLabradas = new ArrayList<Infraccion>();
	private Map<Integer, Double> celularesCredito = new HashMap<Integer, Double>();
	private List<IEntidad> entidadesSubscritas = new ArrayList<IEntidad>();

	// CREACION Y FINILAZACION DE ESTACIONAMIENTOS VIA APP
	/**
	 * registrar inicio de Estacionamiento via app, crea una instancia de
	 * EstacionamientoMedianteApp y lo guarda en la lista de estacionamientos .
	 * tambien lo guarda en la lista de ZonaDeEstacionamientos(en la primera
	 * posicion), pero tendria que guardarla depende la locacion(GPS).
	 * 
	 * luego informa al celularApp el inicio del Estacionamiento
	 * 
	 * @param numeroCelular
	 * @param patente
	 */
	public void registrarInicioEstacionamientoViaApp(CelularApp celularApp, String patente) {
		if (this.estacionamientoActivo(patente)) {
			this.informarEstacionamientoActivo();
		}
		if (!this.saldoSuficiente(celularApp.getNumero())) {
			this.informarSaldoInsuficiente();
		} else {
			EstacionamientoMedianteApp nuevoEstacionamientoViaApp = this
					.crearEstacionamientoMedianteApp(celularApp.getNumero(), patente);
			this.agregarEstacionamiento(nuevoEstacionamientoViaApp);
			this.getZonasDeEstacionamientos().get(0).registrarEstacionamiento(nuevoEstacionamientoViaApp);
			this.eventoInicioEstacionamientoViaApp(nuevoEstacionamientoViaApp);
		}
	}

	/**
	 * crea una instancia de Estacionamiento Mediante App y la retorna
	 * 
	 * @param numeroCelular
	 * @param patente
	 * @return
	 */
	private EstacionamientoMedianteApp crearEstacionamientoMedianteApp(int numeroCelular, String patente) {
		LocalTime horaInicio = LocalTime.now();
		return new EstacionamientoMedianteApp(patente, horaInicio, this.calcularHoraMaximaAEstacionarSegunCredito(),
				true, numeroCelular);
	}

	// CALCULA HORA MAX
	private LocalTime calcularHoraMaximaAEstacionarSegunCredito() {
		return null;
	}

	// AGREGA LOS ESTACIONAMIENTOS APP
	public void agregarEstacionamiento(Estacionamiento estacionamiento) {
		this.getEstacionamientos().add(estacionamiento);
	}

	// Finalizar estacionamiento VIA APP
	public void registrarFinEstacionamientoViaApp(CelularApp celularApp) {
		if (this.estacionamientoActivo(celularApp.getPatente())) {
			this.finalizarEstacionamientoEnSEM(celularApp.getPatente());
			this.eventoFinEstacionamientoViaApp(celularApp.getPatente());
		}
	}

	private String informarEstacionamientoActivo() {
		return "Ya hay un estacionamiento activo";
	}

	private void finalizarEstacionamientoEnSEM(String patente) {
		Estacionamiento est = this.buscarEstacionamiento(patente);
		this.getEstacionamientos().remove(est);
	}

	private Estacionamiento buscarEstacionamiento(String patente) {
		return this.getEstacionamientos().stream().filter(estacionamiento -> estacionamiento.getPatente() == patente)
				.findFirst().get();
	}

	public boolean estacionamientoActivo(String patente) {
		return this.getEstacionamientos().stream().anyMatch(estacionamiento -> estacionamiento.getPatente() == patente);
	}

	// X FRANJA HORARIA
	public void finalizarEstacionamientosPorFinDeFranjaHoraria() {
	}

	// COMPRA PUNTUAL
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
	public void registrarInicioEstacionamientoCompraPuntual(String patente, int cantidadDeHoras,
			PuntoDeVenta puntoDeVenta) {
		EstacionamientoCompraPuntual nuevoEstacionamientoCompraPuntual = this.crearEstacionamientoCompraPuntual(patente,
				cantidadDeHoras);
		this.agregarEstacionamiento(nuevoEstacionamientoCompraPuntual);
		this.getZonasDeEstacionamientos().get(0).registrarEstacionamiento(nuevoEstacionamientoCompraPuntual);

		CompraPuntual nuevaCompraPuntual = this.crearCompraPuntoDeVenta(puntoDeVenta, cantidadDeHoras);

		this.agregarCompra(nuevaCompraPuntual);
	}

	private CompraPuntual crearCompraPuntoDeVenta(PuntoDeVenta puntoDeVenta, int cantidadDeHoras) {
		LocalTime HoraDeCompra = LocalTime.now();
		LocalDate fechaDeCompra = LocalDate.now();

		return new CompraPuntual(this.encontrarSiguienteNumeroDeControl(), puntoDeVenta, fechaDeCompra, HoraDeCompra,
				cantidadDeHoras);
	}

	private int encontrarSiguienteNumeroDeControl() {

		return this.getComprasRealizadas().size();
	}

	/**
	 * crea una instancia de EstacionamientoCompraPuntual y la retorna
	 * 
	 * @param patente
	 * @param cantidadHoras
	 * @return
	 */
	// CREAR UNA COMPRA PUNTUAL
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

	// CARGAR CELULAR
	private void registrarCelularApp(int numeroCelular) {
		this.getCelularesCredito().put(numeroCelular, 0.0);
	}

	public void registrarCargaDeCredito(int numeroCelular, double cantidad, PuntoDeVenta puntoDeVenta) {
			if(!this.getCelularesCredito().containsKey(numeroCelular)) {
				this.registrarCelularApp(numeroCelular);
			} else {
				double creditoAnterior = this.getCelularesCredito().get(numeroCelular);
				this.getCelularesCredito().put(numeroCelular, creditoAnterior + cantidad);
			}
			
			
			RecargaCelular nuevaRecargarDeCelular = this.crearCompraRecargaCelular(puntoDeVenta, cantidad,numeroCelular);
			
		}

	private RecargaCelular crearCompraRecargaCelular(PuntoDeVenta puntoDeVenta, double cantidad, int numeroCelular) {
		
		LocalDate fechaDeRecarga = LocalDate.now();
		LocalTime horaDeRecarga = LocalTime.now();
		
		RecargaCelular recargaCelular = new RecargaCelular(this.encontrarSiguienteNumeroDeControl(),puntoDeVenta,fechaDeRecarga,horaDeRecarga,cantidad,numeroCelular);
		
		return recargaCelular;
	}

	// INFRACCIONES
	public void registrarAltaDeInfraccion(String patente, Inspector inspector,
			ZonaDeEstacionamiento zonaDeEstacionamiento) {
		Infraccion nuevaInflaccion = new Infraccion(inspector, patente, LocalDate.now(), LocalTime.now(),
				zonaDeEstacionamiento);
		this.agregarInfraccion(nuevaInflaccion);
	}

	private void agregarInfraccion(Infraccion infraccion) {
		this.getInfracionesLabradas().add(infraccion);

	}

	// EVENTOS
	// Evento 1
	public void eventoInicioEstacionamientoViaApp(EstacionamientoMedianteApp nuevoEstacionamientoViaApp) {
		nuevoEstacionamientoViaApp.getHoraInicio();
		nuevoEstacionamientoViaApp.getHorafin();
		
	}

	// Evento 2
	private void eventoFinEstacionamientoViaApp(String patente) {
		Estacionamiento est = this.buscarEstacionamiento(patente);
		est.getHoraInicio();
		est.getHorafin();
		est.duracionEstacionamiento();
		est.costoEstacionamiento();
	}

	// Evento 3 - Consulta de saldo
	public void consultaDeSaldoViaApp(int numero) {
		this.getCelularesCredito().get(numero);
	}

	// SUSCRIPCION ENTIDAD
	public void suscripcionEntidad(IEntidad entidad) {
		this.getEntidadesSubscritas().add(entidad);
	}

	public void desuscripcionEntidad(IEntidad entidad) {
		this.getEntidadesSubscritas().remove(entidad);
	}

	public void informarEntidadesCantidadEstacionamientosActivos() {
		this.getEntidadesSubscritas().stream().forEach(entidad -> entidad.cantidadEstacionamientosActivos());
	}

	public void informarEntidadesUsuarioConPocoSaldoIniciaEstacionamiento(int numeroCelular) {
		this.getEntidadesSubscritas().stream()
				.forEach(entidad -> entidad.usuarioConPocoSaldoIniciaEstacionamiento(numeroCelular));
	};

	// FUNCIONES AUX
	/**
	 * buscar al estacionamiento con una determinada patente y le pregunta si esta
	 * valido el estacionamiento
	 * 
	 * @param patente
	 * @return
	 */
	public boolean tieneEstacionamientoVigente(String patente) {
		return this.buscarEstacionamiento(patente).isValidez();
	}

	private boolean saldoSuficiente(int numeroCelular) {
		return this.getCelularesCredito().get(numeroCelular) > 20;
	}

	private String informarSaldoInsuficiente() {
		return "Saldo insuficiente para realizar el inicio del Estacionamiento";
	}

	private void agregarCompra(Compra compra) {
		this.getComprasRealizadas().add(compra);
	}

	// Getters Setters
	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientosApp;
	}

	public void setEstacionamientos(List<Estacionamiento> estacionamientos) {
		this.estacionamientosApp = estacionamientos;
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

	public Map<Integer, Double> getCelularesCredito() {
		return celularesCredito;
	}

	public List<IEntidad> getEntidadesSubscritas() {
		return entidadesSubscritas;
	}

	public void setEntidadesSubscritas(List<IEntidad> entidadesSubscritas) {
		this.entidadesSubscritas = entidadesSubscritas;
	}

	public List<Estacionamiento> getEstacionamientosCompraPuntual() {
		return estacionamientosCompraPuntual;
	}

	public void setEstacionamientosCompraPuntual(List<Estacionamiento> estacionamientosCompraPuntual) {
		this.estacionamientosCompraPuntual = estacionamientosCompraPuntual;
	}

	public List<Compra> getComprasRealizadas() {
		return comprasRealizadas;
	}

	public void setComprasRealizadas(List<Compra> comprasRealizadas) {
		this.comprasRealizadas = comprasRealizadas;
	}
}