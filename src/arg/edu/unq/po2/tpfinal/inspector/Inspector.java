package arg.edu.unq.po2.tpfinal.inspector;

import java.util.List;

import arg.edu.unq.po2.tpfinal.estacionamiento.Estacionamiento;
import arg.edu.unq.po2.tpfinal.sem.SEM;
import arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento.ZonaDeEstacionamiento;

public class Inspector {

	private SEM sem;
	private ZonaDeEstacionamiento asignadoAZonaEstacionamiento;

	public Inspector(SEM sem, ZonaDeEstacionamiento asignacionesDeZonaEstacionamiento) {
		super();
		this.sem = sem;
		this.asignadoAZonaEstacionamiento = asignacionesDeZonaEstacionamiento;
	}

	public SEM getSem() {
		return sem;
	}

	public void setSem(SEM sem) {
		this.sem = sem;
	}

	public ZonaDeEstacionamiento getAsignadoAZonaEstacionamiento() {
		return asignadoAZonaEstacionamiento;
	}

	public void setAsignadoAZonaEstacionamiento(ZonaDeEstacionamiento asignadoAZonaEstacionamiento) {
		this.asignadoAZonaEstacionamiento = asignadoAZonaEstacionamiento;
	}

	/**
	 * envia un mensaje al sem preguntando la vigencia del estacionamiento con una determinada patente
	 * @param patente
	 * @return
	 */
	public boolean consultaEstacionamientoVigente(String patente) {

		return sem.tieneEstacionamientoVigente(patente);
	}
	
	/**
	 * envia un mensaje al sem para que registre una alta de infraccion
	 * @param patente
	 */
	public void altaDeInfraccion(String patente) {
		sem.registrarAltaDeInfraccion(patente, this ,this.getAsignadoAZonaEstacionamiento());

	}

	public void recorrerZonasDeEstacionamiento() {

		List<Estacionamiento> estacionamientosDeLaZona = this.getAsignadoAZonaEstacionamiento()
				.getEstacionamientosDeLaZona();

		for (Estacionamiento estacionamiento : estacionamientosDeLaZona) {

			String patenteDelEstacionamiento = estacionamiento.getPatente();

			if (this.consultaEstacionamientoVigente(patenteDelEstacionamiento)) {
				this.altaDeInfraccion(patenteDelEstacionamiento);
			}
		}
	}

}
