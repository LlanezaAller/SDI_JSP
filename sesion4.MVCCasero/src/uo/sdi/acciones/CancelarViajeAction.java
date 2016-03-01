package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.SdiUtil;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.type.TripStatus;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class CancelarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession().getAttribute("user") != null) {
			if (SdiUtil.assertCampos(request.getParameter("viajeID"))) {
				User user = (User) request.getSession().getAttribute("user");
				Long viajeID = Long.valueOf(request.getParameter("viajeID"));
				Trip viaje = Factories.persistence.createTripGateway().findById(viajeID);
				
				if(viaje.getPromoter().getId() == user.getId()){
					viaje.setStatus(TripStatus.CANCELLED);
					Message error = new Message(Message.OK,
							"Viaje cancelado");
					request.setAttribute("message", error);
					return "EXITO";
				} else {
					Message error = new Message(Message.ERROR,
							"No estás autorizado a cancelar el viaje");
					request.setAttribute("message", error);
					return "FRACASO";
				}		
			} else {
				Message error = new Message(Message.ERROR,
						"Error con la id del viaje o del usuario");
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
