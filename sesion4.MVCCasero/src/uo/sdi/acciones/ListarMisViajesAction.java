
package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ListarMisViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Trip> viajes = null;
		
		try {
			HttpSession s = request.getSession();
			User u = (User) s.getAttribute("user");
			viajes = Factories.persistence
					.createTripGateway().findAllTripsByPromoterId(u.getId());
			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes"
					+ " conteniendo [%d] viajes", viajes.size());
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
