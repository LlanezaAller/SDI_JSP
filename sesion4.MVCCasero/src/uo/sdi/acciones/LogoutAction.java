package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
