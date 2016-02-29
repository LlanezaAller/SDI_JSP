package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.util.Asserts;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.type.SeatStatus;
import uo.sdi.model.type.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class ExcluirUsuarioAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession().getAttribute("user") != null) {
			if (Asserts.assertCampos(request.getParameter("viajeID"), request.getParameter("userLogin"))) {
				User user = (User) request.getSession().getAttribute("user");
				Long viajeID = Long.valueOf(request.getParameter("viajeID"));
				String userLogin = request.getParameter("userLogin");
				
				User applicant = Factories.persistence.createUserGateway().findByLogin(userLogin);
				Trip viaje = Factories.persistence.createTripGateway().findById(viajeID);
				Seat seat = Factories.persistence.createSeatGateway().findByUserAndTrip(applicant.getId(), viajeID);
				
				if(seat==null){
					if(viaje.getApplications().contains(applicant)){//El usuario es un aplicante y no tiene seats creado
						seat = new Seat(applicant, viaje);							
						Factories.persistence.createSeatGateway().newSeat(seat);
						applicant.finAplicacion(viaje);
					} else {//El usuario no ha solicitado plaza
						Message error = new Message(Message.ERROR,
								"Has intentado excluir un usuario que no ha solicitado plaza");
						request.setAttribute("message", error);
						return "FRACASO";
					}
				}else if (seat.getStatus()==SeatStatus.EXCLUDED){
					Message error = new Message(Message.WARNING,
							"El usuario " + user.getName() + "ya está excluido.");
					request.setAttribute("message", error);
					return "FRACASO";
				}
				seat.setStatus(SeatStatus.EXCLUDED);
				viaje.setAvailablePax(viaje.getAvailablePax()+1);
				Message error = new Message(Message.OK,
						"El usuario " + user.getName() + " ha sido excluido del viaje.");
				request.setAttribute("message", error);
				return "EXITO";
			} else {
				Message error = new Message(Message.ERROR,
						"Error con la id del viaje o del usuario");
				request.setAttribute("message", error);
				return "FRACASO";
			}
		} else {
			Message error = new Message(Message.ERROR,
					"Necesitas estar logueado para excluir gente de un viaje.");
			Log.debug("Un usuario no autorizado ha intentado excluir a gente.");
			request.setAttribute("message", error);
			return "FRACASO";
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
