package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.User;
import uo.sdi.model.type.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class LogoutAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().invalidate();
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
