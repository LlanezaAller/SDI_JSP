package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.exception.BusinessException;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class VerViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {

		if (request.getSession().getAttribute("user") != null) {
			if (request.getParameter("viajeID") != null) {
				long viajeID = Long.valueOf(request.getParameter("viajeID"));
				Trip viaje = Factories.persistence.createTripGateway()
						.findById(viajeID);

				if (viaje == null) {
					Message error = new Message(Message.ERROR,
							"No existe el viaje selecionado.");
					request.setAttribute("message", error);
					return "FRACASO";
				}
				List<User> users = Factories
						.persistence.createUserGateway()
						.findUsersByTrip(viaje.getId());
				request.setAttribute("users", users);
				request.setAttribute("viaje", viaje);
				return "EXITO";
			} else {// No se ha introducido una id
				Message error = new Message(Message.ERROR,
						"No hay ningún viaje especificado");
				request.setAttribute("message", error);
				return "FRACASO";
			}
		}
		Message error = new Message(Message.ERROR,
				"Necesitas estar logueado para poder ver viajes");
		request.setAttribute("message", error);
		Log.debug("Un visitante no autorizado"
				+ " ha intentado acceder a un viaje");
		return "FRACASO";

	}
}
