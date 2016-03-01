package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.User;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class ListarAplicacionesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Long tripId = Long.parseLong(request.getParameter("viajeId"));

		if (tripId != null) {
			List<User> aplicantes = Factories.persistence.createUserGateway()
					.findUsersByTrip(tripId);

			request.setAttribute("listaAplicantes", aplicantes);
			Log.debug(
					"Obtenida lista de usuarios aplicantes al trip con id [%s]",
					tripId);
		} else {
			Message m = new Message(Message.ERROR, "No existe trip para ese Id");
			request.setAttribute("message", m);
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
