package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class CancelarSolicitudAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession().getAttribute("user") != null) {// Usuario en
																// sesión
			User user = (User) request.getSession().getAttribute("user");
			user = Factories.persistence.createUserGateway().findByLogin(
					user.getLogin());
			Trip viaje = Factories.persistence.createTripGateway().findById(
					Long.valueOf(request.getParameter("viajeID")));
			user.finAplicacion(viaje);
			Message error = new Message(Message.OK,
					"Has cancelado tu solicitud.");
			Log.debug("El usuario [%s] ha cancelado su solicitud de plaza.",
					user.getLogin());
			request.setAttribute("message", error);
			return "EXITO";
		} else {
			Message error = new Message(Message.ERROR,
					"Necesitar estar logueado para solicitar una plaza.");
			Log.debug("Un usuario no autorizado ha intentado cancelar una plaza");
			request.setAttribute("message", error);
			return "FRACASO";
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
