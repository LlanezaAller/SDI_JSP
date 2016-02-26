package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;

public class ModificarDatosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String nuevoEmail=request.getParameter("email");
		HttpSession session=request.getSession();
		User usuario=((User)session.getAttribute("user"));
		usuario.setEmail(nuevoEmail);
		try {
			UserFinder uF = Factories.persistence.createUserGateway();
			uF.update(usuario);
			Log.debug("Modificado email de [%s] con el valor [%s]",
					usuario.getLogin(), nuevoEmail);
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido actualizando el email de [%s]", 
					usuario.getLogin());
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
