package uo.sdi.acciones;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.type.TripStatus;
import alb.util.log.Log;

public class ListarViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Trip> viajes;

		try {
			viajes = Factories.persistence.createTripGateway().findAllStatus(
					TripStatus.OPEN);
			Date today = new Date();
			for (int i = 0; i < viajes.size(); i++) {
				if (viajes.get(i).getClosingDate().before(today)
						|| viajes.get(i).getAvailablePax() < 0
						|| viajes.get(i).getStatus() == TripStatus.CANCELLED)
					viajes.remove(i);
			}
			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
