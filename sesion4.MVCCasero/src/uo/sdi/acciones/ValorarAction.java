package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.type.SeatStatus;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class ValorarAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		if (request.getSession().getAttribute("user") != null) {
			if (request.getParameter("viajeID") != null) {
				User user = (User) request.getSession().getAttribute("user");
				Trip viaje = Factories.persistence
						.createTripGateway()
						.findById(
								Long.valueOf(request.getParameter("viajeID")));
				boolean canRate = false;
				Seat mySeat = null;
				for (Seat s : viaje.getSeats())
					if (s.getUser().getId() == user.getId()
							&& s.getStatus() == SeatStatus.ACCEPTED) {
						if (s.getRatingsFrom() == null
								|| s.getRatingsFrom().isEmpty()) {
							canRate = true;
							mySeat = s;
						}
					}
				if (canRate) {// Valoremos pues
					for (Seat s : viaje.getSeats()) {
						if (s.getUser().getId() != user.getId()) {
							String comment = request
									.getParameter("comment-user-"
											+ s.getUser().getId());
							String ratingString = request
									.getParameter("rating-user-"
											+ s.getUser().getId());
							if (ratingString == null) {
								Message error = new Message(Message.ERROR,
										"Necesitas valorar"
										+ " a toda la gente del viaje");
								request.setAttribute("message", error);
								return "FRACASO";
							}
							int ratingValue = Integer.valueOf(ratingString);
							Factories.persistence.createRatingGateway()
							.newRating(new Rating(mySeat, s, comment,
									ratingValue));
						}
					}
					Message error = new Message(Message.OK,
							"Has valorado este viaje");
					request.setAttribute("message", error);
					return "EXITO";
				} else {
					Message error = new Message(Message.ERROR,
							"Ya has valorado a los miembros de este viaje");
					request.setAttribute("message", error);
					return "FRACASO";
				}
			} else {
				Message error = new Message(Message.ERROR,
						"Error con la id del viaje");
				request.setAttribute("message", error);
				return "FRACASO";
			}
		} else {
			Message error = new Message(Message.ERROR,
					"Necesitar estar logueado para valorar un viaje.");
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
