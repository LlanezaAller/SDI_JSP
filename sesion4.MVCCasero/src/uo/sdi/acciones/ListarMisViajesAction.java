package uo.sdi.acciones;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.view.Message;

public class ListarMisViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Seat> seats = null;
		List<Trip> applications = null;

		HttpSession s = request.getSession();
		if (s.getAttribute("user") != null) {
			User u = (User) s.getAttribute("user");

			seats = Factories.persistence.createSeatGateway().findByUser(
					u.getId());
			applications = Factories.persistence.createTripGateway()
					.findAllAplicantsByUserId(u.getId());
		
			request.setAttribute("today", new Date());
			request.setAttribute("listaSeats", seats);
			request.setAttribute("listaApplications", applications);
			return "EXITO";
		} else {
			Message m = new Message(Message.ERROR,
					"No puedes ver viajes sin estar logueado.");
			request.setAttribute("message", m);
			return "FRACASO";
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
