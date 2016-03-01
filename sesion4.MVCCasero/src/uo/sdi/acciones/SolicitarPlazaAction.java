package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class SolicitarPlazaAction implements Accion {

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
			boolean hasSeatOrApplication = false;
			for (Seat s : viaje.getSeats())
				if (s.getUser().getId() == user.getId())
					hasSeatOrApplication = true;
			if (!hasSeatOrApplication) {
				for (User u : viaje.getApplications())
					if (u.getId() == user.getId())
						hasSeatOrApplication = true;
			}
			if (!hasSeatOrApplication) {// El usuario no tiene plaza pedida
				if (viaje.getAvailablePax() > 0) {// Hay hueco en el viaje
					user.aplicar(viaje);
					Message info = new Message(Message.OK,
							"Has solicitado tu plaza con éxito.");
					request.setAttribute("message", info);
					return "EXITO";
				} else {
					Message error = new Message(Message.ERROR,
							"No hay hueco en el viaje.");
					request.setAttribute("message", error);
					Log.debug("No quedan huecos en el viaje [%d]",
							viaje.getId());
					return "FRACASO";
				}
			} else {
				Message error = new Message(Message.ERROR,
						"Ya has solicitado una plaza.");
				request.setAttribute("message", error);
				Log.debug("El usuario [%s] ha vuelto a "
						+ "pedir una plaza en el viaje [%d]", user.getLogin(),
						viaje.getId());
				return "FRACASO";
			}
		} else {
			Message error = new Message(Message.ERROR,
					"Necesitar estar logueado para solicitar una plaza.");
			Log.debug("Un usuario no autorizado ha intentado pedir una plaza");
			request.setAttribute("message", error);
			return "FRACASO";
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
