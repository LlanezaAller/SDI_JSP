package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.SdiUtil;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Trip;

public class ConseguirViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		if(SdiUtil.assertCampos(request.getParameter("viajeID"))){
			Trip v = Factories.persistence.createTripGateway().findById(Long.valueOf(request.getParameter("viajeID")));
			request.setAttribute("viaje", v);
			return "EXITO";
		}
		return "FRACASO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
