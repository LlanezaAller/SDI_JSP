package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.exception.BusinessException;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.type.SeatStatus;
import uo.sdi.model.type.TripStatus;
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
				User user = (User) request.getSession().getAttribute("user");
				
				boolean hasSeatOrApplication = false;
				boolean isParticipante = false;
				boolean hasRated = false;
				Rating ratedTrip = null;

				for (Seat s : viaje.getSeats())
					if (s.getUser().getId() == user.getId()) {
						if (s.getStatus() == SeatStatus.ACCEPTED) {
							isParticipante = true;
							if (viaje.getStatus() == TripStatus.DONE) {
								ratedTrip = Factories.persistence
										.createRatingGateway()
										.findByAboutFromAndTripID(user.getId(),
												viaje.getId());
								if(ratedTrip != null)
									hasRated = true;
							}
						}
						hasSeatOrApplication = true;
					}
				if (!hasSeatOrApplication) {
					for (User u : viaje.getApplications())
						if (u.getId() == user.getId())
							hasSeatOrApplication = true;
				}

				request.setAttribute("seats", viaje.getSeats());
				request.setAttribute("applicants", viaje.getApplications().toArray(new User[viaje.getApplications().size()]));
				request.setAttribute("viaje", viaje);
				request.setAttribute("today", new Date());
				request.setAttribute("hasSeatOrApplication",
						hasSeatOrApplication);
				request.setAttribute("hasRated", hasRated);
				request.setAttribute("isParticipante", isParticipante);
				return "EXITO";
			} else {// No se ha introducido una id
				Message error = new Message(Message.OK,
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
