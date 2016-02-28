package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Rating;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ListarRatingsAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Rating> ratings = null;

		try {
			HttpSession s = request.getSession();
			User u = (User) s.getAttribute("user");
			ratings = Factories.persistence.createRatingGateway()
					.findRatingsByUserAboutId(u.getId());
			request.setAttribute("listaRatings", ratings);
			Log.debug("Obtenida lista de viajes" + " conteniendo [%d] viajes",
					ratings.size());
		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
