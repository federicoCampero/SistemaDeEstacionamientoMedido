package arg.edu.unq.po2.tpfinal.sem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import arg.edu.unq.po2.tpfinal.celularApp.CelularApp;
import arg.edu.unq.po2.tpfinal.compras.Compra;
import arg.edu.unq.po2.tpfinal.compras.CompraPuntual;
import arg.edu.unq.po2.tpfinal.compras.RecargaCelular;
import arg.edu.unq.po2.tpfinal.estacionamiento.Estacionamiento;
import arg.edu.unq.po2.tpfinal.estacionamiento.EstacionamientoCompraPuntual;
import arg.edu.unq.po2.tpfinal.estacionamiento.EstacionamientoMedianteApp;
import arg.edu.unq.po2.tpfinal.infraccion.Infraccion;
import arg.edu.unq.po2.tpfinal.inspector.Inspector;
import arg.edu.unq.po2.tpfinal.otros.IEntidad;
import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;
import arg.edu.unq.po2.tpfinal.tiposDeModos.TipoDeModo;
import arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento.ZonaDeEstacionamiento;

// El punto de zonas de estacionamientos lo desarrollamos pero no lo testeamos, entonces no lo agregamos a la lista del sem.

public class SEM {

	private List<CelularApp> celularesApp = new ArrayList<CelularApp>();

	private Map<Integer, Double> celularesCredito = new HashMap<Integer, Double>();

	private List<EstacionamientoMedianteApp> estacionamientosApp = new ArrayList<EstacionamientoMedianteApp>();
	private List<EstacionamientoCompraPuntual> estacionamientosCompraPuntual = new ArrayList<EstacionamientoCompraPuntual>();
	private List<ZonaDeEstacionamiento> zonasDeEstacionamientos = new ArrayList<ZonaDeEstacionamiento>();

	private List<Compra> comprasRealizadas = new ArrayList<Compra>();
	private List<Infraccion> infracionesLabradas = new ArrayList<Infraccion>();
	private List<IEntidad> entidadesSubscritas = new ArrayList<IEntidad>();

	private Map<CelularApp, TipoDeModo> celularTipoDeModo = new HashMap<CelularApp, TipoDeModo>();

	// CREACION Y FINILAZACION DE ESTACIONAMIENTOS VIA APP

	/**
	 * registrar inicio de Estacionamiento via app, crea una instancia de
	 * EstacionamientoMedianteApp y lo guarda en la lista de estacionamientos .
	 * 
	 * Luego informa al celularApp el inicio del evento del Estacionamiento
	 * 
	 * si el celular no se encuentra registrado o tiene estacionamiento activo o no
	 * tiene saldo suficiente realiza su correspondiente excepcion
	 * 
	 * @param numeroCelular
	 * @param patente
	 * @throws Exception
	 */

	public void registrarInicioEstacionamientoViaApp(CelularApp celularApp, String patente) throws Exception {

		this.verificarRegistroCelularApp(celularApp);
		this.verificarEstacionamientoActivo(patente);
		this.verificarSaldoSuficienteParaIniciarEstacionamientoViaApp(celularApp);
		this.verificarYNotificarSobreUsuarioConPocoSaldo(celularApp);
		EstacionamientoMedianteApp nuevoEstacionamientoViaApp = this
				.crearEstacionamientoMedianteApp(celularApp.getNumero(), patente);
		this.agregarEstacionamientoViaApp(nuevoEstacionamientoViaApp);
		// this.getZonasDeEstacionamientos().get(0).registrarEstacionamiento(nuevoEstacionamientoViaApp);
		this.eventoInicioEstacionamientoViaApp(celularApp, nuevoEstacionamientoViaApp);
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

	/**
	 * Deberia calcular la hora maxima a estacionar, en cambio retorna las 20:00 como hora maxima
	 * @return
	 */
	private LocalTime calcularHoraMaximaAEstacionarSegunCredito() {
		return LocalTime.of(20, 0);
	}
	
	/**
	 * Agrega los estacionamientos app a la lista de estacionamientos
	 * @param estacionamiento
	 */
	public void agregarEstacionamientoViaApp(EstacionamientoMedianteApp estacionamiento) {
		this.getEstacionamientosViaApp().add(estacionamiento);
	}

	/**
	 * Registar el fin de un estacionamiento via app, si el estacionamiento no esta activo realiza una excepcion
	 * @param celularApp
	 * @throws Exception
	 */
	public void registrarFinEstacionamientoViaApp(CelularApp celularApp) throws Exception {
		if (!this.estacionamientoActivo(celularApp.getPatente())) {
			throw new Exception("No tienes un estacionamiento activo");
		}
		this.finalizarEstacionamientoViaAppEnSEM(celularApp.getPatente());
		this.eventoFinEstacionamientoViaApp(celularApp);
	}

	private void finalizarEstacionamientoViaAppEnSEM(String patente) {
		EstacionamientoMedianteApp est = this.buscarEstacionamientoViaApp(patente);
		est.setValidez(false);
	}

	EstacionamientoMedianteApp buscarEstacionamientoViaApp(String patente) {
		return this.getEstacionamientosViaApp().stream()
				.filter(estacionamiento -> estacionamiento.getPatente() == patente).findFirst().get();
	}

	public boolean estacionamientoActivo(String patente) {
		return this.getEstacionamientosViaApp().stream()
				.anyMatch(estacionamiento -> estacionamiento.getPatente() == patente && estacionamiento.isValidez());
	}

	/**
	 * Finaliza por franja horaria todos los tipos de estacionamientos
	 */
	public void finalizarEstacionamientosPorFinDeFranjaHoraria() {
		this.finalizarTotalidadEstacionamientosViaApp();
		this.finalizarTotalidadEstacionamientoCompraPuntual();

	}
	
	/**
	 * Finaliza la totalidad de estacionamientos via app (cambia el atributo valides de los estacionamientos a false)
	 */
	void finalizarTotalidadEstacionamientosViaApp() {
		List<EstacionamientoMedianteApp> estacionamientosActivos = this.totalidadDeEstacionamientosActivosViaApp();

		estacionamientosActivos.stream().forEach(estacionamiento -> {
			try {
				this.registrarFinEstacionamientoViaApp(
						this.encontarCelularAppMedianteNumeroCelular(estacionamiento.getNumeroCelular()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	List<EstacionamientoMedianteApp> totalidadDeEstacionamientosActivosViaApp() {
		return this.getEstacionamientosViaApp().stream().filter(est -> est.isValidez()).collect(Collectors.toList());
	}

	List<EstacionamientoCompraPuntual> totalidadDeEstacionamientosActivosCompraPuntual() {
		return this.getEstacionamientosCompraPuntual().stream().filter(est -> est.isValidez())
				.collect(Collectors.toList());
	}

	private CelularApp encontarCelularAppMedianteNumeroCelular(int numeroCelular) {
		return this.getCelularApp().stream().filter(celularApp -> celularApp.getNumero() == numeroCelular).findFirst()
				.get();
	}
	
	/**
	 * Finaliza la totalidad de los estacionamientos por compra puntual
	 */
	void finalizarTotalidadEstacionamientoCompraPuntual() {
		Stream<EstacionamientoCompraPuntual> estacionamientosActivos = this.getEstacionamientosCompraPuntual().stream()
				.filter(compraPuntual -> compraPuntual.isValidez() == true);
		estacionamientosActivos.forEach(estacionamiento -> this
				.finalizarEstacionamientoPorFinDeHorarioCompraPuntal(estacionamiento.getPatente()));
	}

	/**
	 * Finaliza un estacionamiento por compra puntal
	 * @param patente
	 */
	public void finalizarEstacionamientoPorFinDeHorarioCompraPuntal(String patente) {
		Estacionamiento estacionamiento = this.buscarEstacionamientoPorCompraPuntual(patente);
		estacionamiento.setValidez(false);
	}

	EstacionamientoCompraPuntual buscarEstacionamientoPorCompraPuntual(String patente) {
		return this.getEstacionamientosCompraPuntual().stream()
				.filter(compraPuntal -> compraPuntal.getPatente() == patente).findFirst().get();
	}

	/**
	 * registrar inicio de Estacionamiento Compra Puntual, crea una instancia de
	 * EstacionamientoCompraPuntual y lo guarda en la lista de estacionamientos .
	 * 
	 * @param patente         patente del choche
	 * @param cantidadDeHoras cantidad de horas que le usuario va a permanecer en el
	 *                        Estacionamiento
	 */
	public void registrarInicioEstacionamientoCompraPuntual(String patente, int cantidadDeHoras,
			PuntoDeVenta puntoDeVenta) {

		EstacionamientoCompraPuntual nuevoEstacionamientoCompraPuntual = this.crearEstacionamientoCompraPuntual(patente,
				cantidadDeHoras);
		this.agregarEstacionamientoCompraPuntual(nuevoEstacionamientoCompraPuntual);
		// this.getZonasDeEstacionamientos().get(0).registrarEstacionamiento(nuevoEstacionamientoCompraPuntual);

		CompraPuntual nuevaCompraPuntual = this.crearCompraPuntoDeVenta(puntoDeVenta, cantidadDeHoras);
		this.agregarCompra(nuevaCompraPuntual);
	}

	/**
	 * Agrega un estacionamiento compra puntal a la lista correspondiente
	 * @param nuevoEstacionamientoCompraPuntual
	 */
	private void agregarEstacionamientoCompraPuntual(EstacionamientoCompraPuntual nuevoEstacionamientoCompraPuntual) {
		this.getEstacionamientosCompraPuntual().add(nuevoEstacionamientoCompraPuntual);
	}
	
	/**
	 * Crea una instancia de compra puntual y la retorna
	 * @param puntoDeVenta
	 * @param cantidadDeHoras
	 * @return
	 */
	private CompraPuntual crearCompraPuntoDeVenta(PuntoDeVenta puntoDeVenta, int cantidadDeHoras) {
		LocalTime HoraDeCompra = LocalTime.now();
		LocalDate fechaDeCompra = LocalDate.now();

		return new CompraPuntual(this.encontrarSiguienteNumeroDeControl(), puntoDeVenta, fechaDeCompra, HoraDeCompra,
				cantidadDeHoras);
	}
	
	/**
	 * Describe el siguente numero de control 
	 * @return
	 */
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
	 * Registra un celular. Para que un usuario inicie un estacionamoiento debe estar registrado en el sem
	 * @param celularApp
	 */
	public void registrarCelularApp(CelularApp celularApp) {
		this.getCelularApp().add(celularApp);
		this.getCelularTipoDeModo().put(celularApp, celularApp.getModo());
		this.registrarNumeroCelularYSaldo(celularApp.getNumero());
	}
	
	public boolean estaRegistradoCelularApp(int numeroCelular) {
		return this.getCelularApp().stream().anyMatch(celularApp -> celularApp.getNumero() == numeroCelular);
	}

	/**
	 * Carga credito a un numero de celular especifico. Debe estar debidamente registrado
	 * @param numeroCelular
	 * @param cantidad
	 * @param puntoDeVenta
	 * @throws Exception
	 */
	public void registrarCargaDeCredito(int numeroCelular, double cantidad, PuntoDeVenta puntoDeVenta)
			throws Exception {
		if (!this.estaRegistradoCelularApp(numeroCelular)) {
			throw new Exception("Debe registrar su celular para cargar credito");
		}
		double creditoAnterior = this.getCelularesCredito().get(numeroCelular);
		this.getCelularesCredito().put(numeroCelular, creditoAnterior + cantidad);
		RecargaCelular nuevaRecargarDeCelular = this.crearCompraRecargaCelular(puntoDeVenta, cantidad, numeroCelular);
		this.agregarCompra(nuevaRecargarDeCelular);
	}
	
	/**
	 * Registra un numero de celular y su correspondiente saldo a la lista celularesCredito
	 * @param numeroCelular
	 */
	private void registrarNumeroCelularYSaldo(int numeroCelular) {
		this.getCelularesCredito().put(numeroCelular, 0.0);
	}
	
	/**
	 * Crea una nueva compra de recarga de celular
	 * @param puntoDeVenta
	 * @param cantidad
	 * @param numeroCelular
	 * @return
	 */
	private RecargaCelular crearCompraRecargaCelular(PuntoDeVenta puntoDeVenta, double cantidad, int numeroCelular) {

		LocalDate fechaDeRecarga = LocalDate.now();
		LocalTime horaDeRecarga = LocalTime.now();

		RecargaCelular recargaCelular = new RecargaCelular(this.encontrarSiguienteNumeroDeControl(), puntoDeVenta,
				fechaDeRecarga, horaDeRecarga, cantidad, numeroCelular);

		return recargaCelular;
	}

	/**
	 * Registra una nueva alta de infraccion y lo guarda en la lista correspondiente
	 * @param patente
	 * @param inspector
	 * @param zonaDeEstacionamiento
	 */
	public void registrarAltaDeInfraccion(String patente, Inspector inspector,
			ZonaDeEstacionamiento zonaDeEstacionamiento) {
		Infraccion nuevaInflaccion = new Infraccion(inspector, patente, LocalDate.now(), LocalTime.now(),
				zonaDeEstacionamiento);
		this.agregarInfraccion(nuevaInflaccion);
	}

	private void agregarInfraccion(Infraccion infraccion) {
		this.getInfracionesLabradas().add(infraccion);
	}

	/**
	 * Notifica al celularApp el inicio de un estacionamiento via app
	 * @param celularApp
	 * @param nuevoEstacionamientoViaApp
	 */
	void eventoInicioEstacionamientoViaApp(CelularApp celularApp,
			EstacionamientoMedianteApp nuevoEstacionamientoViaApp) {
		String horaInicio = nuevoEstacionamientoViaApp.getHoraInicio().toString();
		String horaMaxima = nuevoEstacionamientoViaApp.getHorafin().toString();
		celularApp.notificarEventoEstacionamiento(
				"Estacionamiento realizado. Hora de inicio: " + horaInicio + "; Hora maxima: " + horaMaxima);
	}

	/**
	 * Notifica al celularApp el fin de un estacionamiento via app
	 * @param celularApp
	 */
	void eventoFinEstacionamientoViaApp(CelularApp celularApp) {
		Estacionamiento estacionamiento = this.buscarEstacionamientoViaApp(celularApp.getPatente());
		String horaInicio = estacionamiento.getHoraInicio().toString();
		String horaFin = estacionamiento.getHorafin().toString();
		String duracion = estacionamiento.duracionEstacionamiento().toString();
		String costo = estacionamiento.costoEstacionamiento().toString();
		celularApp.notificarEventoEstacionamiento("Estacionamiento finalizado. Hora de inicio: " + horaInicio
				+ "; Hora de fin: " + horaFin + "; Duracion: " + duracion + "; Costo: " + costo);
	}

	/**
	 * Notifica al celularApp su saldo 
	 * @param numeroCelular
	 * @return
	 */
	public double consultaDeSaldoViaApp(int numeroCelular) {
		return this.getCelularesCredito().get(numeroCelular);
	}

	/**
	 * Suscribe una nueva entidad
	 * @param entidad
	 */
	public void suscripcionEntidad(IEntidad entidad) {
		this.getEntidadesSubscritas().add(entidad);
	}

	public void desuscripcionEntidad(IEntidad entidad) {
		this.getEntidadesSubscritas().remove(entidad);
	}

	void verificarYNotificarSobreUsuarioConPocoSaldo(CelularApp celularApp) {
		if (this.consultaDeSaldoViaApp(celularApp.getNumero()) == 40d) {
			this.informarEntidadesUsuarioConPocoSaldoIniciaEstacionamiento(celularApp.getNumero());
		}
	}

	public void informarEntidadesCantidadEstacionamientosActivos() {
		int cantidadEstacionamientos = this.totalidadDeEstacionamientosActivosViaApp().size()
				+ this.totalidadDeEstacionamientosActivosCompraPuntual().size();
		this.getEntidadesSubscritas().stream()
				.forEach(entidad -> entidad.cantidadEstacionamientosActivos(cantidadEstacionamientos));
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
	private void verificarSaldoSuficienteParaIniciarEstacionamientoViaApp(CelularApp celularApp) throws Exception {
		if (!this.saldoSuficiente(celularApp)) {
			throw new Exception("Su saldo es insuficiente para iniciar el estacionamiento");
		}
	}

	private void verificarEstacionamientoActivo(String patente) throws Exception {
		if (this.estacionamientoActivo(patente)) {
			throw new Exception("Ya tiene un estacionamiento activo");
		}
	}

	private void verificarRegistroCelularApp(CelularApp celularApp) throws Exception {
		if (!this.estaRegistradoCelularApp(celularApp.getNumero())) {
			throw new Exception("Su celular no esta registrado");
		}
	}

	public boolean tieneEstacionamientoVigente(String patente) {
		return this.buscarEstacionamientoViaApp(patente).isValidez();
	}

	private double saldoDisponible(int numeroCelular) {
		return this.getCelularesCredito().get(numeroCelular);
	}

	private boolean saldoSuficiente(CelularApp celularApp) {
		return this.saldoDisponible(celularApp.getNumero()) >= 40d;
	}

	private void agregarCompra(Compra compra) {
		this.getComprasRealizadas().add(compra);
	}

	public void cambiarModoCelular(CelularApp celularApp) {
		this.getCelularTipoDeModo().put(celularApp, celularApp.getModo());
	}

	// Getters Setters
	public List<CelularApp> getCelularesApp() {
		return celularesApp;
	}

	public void setCelularesApp(List<CelularApp> celularesApp) {
		this.celularesApp = celularesApp;
	}

	public List<EstacionamientoMedianteApp> getEstacionamientosViaApp() {
		return estacionamientosApp;
	}

	public void setEstacionamientos(List<EstacionamientoMedianteApp> estacionamientos) {
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

	public List<EstacionamientoCompraPuntual> getEstacionamientosCompraPuntual() {
		return estacionamientosCompraPuntual;
	}

	public void setEstacionamientosCompraPuntual(List<EstacionamientoCompraPuntual> estacionamientosCompraPuntual) {
		this.estacionamientosCompraPuntual = estacionamientosCompraPuntual;
	}

	public List<Compra> getComprasRealizadas() {
		return comprasRealizadas;
	}

	public void setComprasRealizadas(List<Compra> comprasRealizadas) {
		this.comprasRealizadas = comprasRealizadas;
	}

	private List<CelularApp> getCelularApp() {
		return celularesApp;
	}

	public Map<CelularApp, TipoDeModo> getCelularTipoDeModo() {
		return celularTipoDeModo;
	}

	public void setCelularTipoDeModo(Map<CelularApp, TipoDeModo> celularTipoDeModo) {
		this.celularTipoDeModo = celularTipoDeModo;
	}
}