package arg.edu.unq.po2.tpfinal.zonaDeEstacionamiento;

import java.util.List;

import arg.edu.unq.po2.tpfinal.estacionamiento.Estacionamiento;
import arg.edu.unq.po2.tpfinal.inspector.Inspector;
import arg.edu.unq.po2.tpfinal.puntoDeVenta.PuntoDeVenta;

public class ZonaDeEstacionamiento {
	
	private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	private List<Estacionamiento> estacionamientosDeLaZona;
	
	public ZonaDeEstacionamiento(Inspector inspector, List<PuntoDeVenta> puntosDeVenta,
			List<Estacionamiento> estacionamientosDeLaZona) {
		super();
		this.inspector = inspector;
		this.puntosDeVenta = puntosDeVenta;
		this.estacionamientosDeLaZona = estacionamientosDeLaZona;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}

	public List<Estacionamiento> getEstacionamientosDeLaZona() {
		return estacionamientosDeLaZona;
	}
	
	
	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		this.getEstacionamientosDeLaZona().add(estacionamiento);
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public void setPuntosDeVenta(List<PuntoDeVenta> puntosDeVenta) {
		this.puntosDeVenta = puntosDeVenta;
	}

	public void setEstacionamientosDeLaZona(List<Estacionamiento> estacionamientosDeLaZona) {
		this.estacionamientosDeLaZona = estacionamientosDeLaZona;
	}
	
	
	
	
}
