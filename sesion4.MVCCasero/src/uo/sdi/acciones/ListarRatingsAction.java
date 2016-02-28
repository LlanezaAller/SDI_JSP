package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Rating;
import uo.sdi.model.User;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class ListarRatingsAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Rating> ratings = null;

		if (request.getParameter("userLogin") != null) {
			User u = Factories.persistence.createUserGateway().findByLogin(
					request.getParameter("userLogin"));
			ratings = Factories.persistence.createRatingGateway()
					.findRatingsByUserAboutId(u.getId());
			request.setAttribute("listaRatings", ratings);
			Log.debug(
					"Obtenida lista de valoraciones de [%s] conteniendo [%d] valoraciones",
					u.getName(), ratings.size());
		} else {
			Message m = new Message(Message.ERROR,
					"No se ha especificado ningún usuario");
			request.setAttribute("message", m);
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
