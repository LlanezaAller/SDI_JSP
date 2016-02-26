package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.exception.BusinessException;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Trip;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class VerViaje implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		
		long viajeID = (long) request.getAttribute("viajeID");
		
		if(request.getAttribute("user")!=null){
			Trip viaje = Factories.persistence.createTripGateway().findById(viajeID);
			request.setAttribute("viaje", viaje);
		}
		Message error = new Message(Message.ERROR, "Necesitas estar logueado para poder ver viajes");
		Log.debug("Un visitante no autorizado ha intentado acceder a un viaje");
		return "FRACASO";
		
		
	}

}
