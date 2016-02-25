package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class RegistrarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String resultado = "EXITO";
		String nombreUsuario = request.getParameter("user");
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			UserDao dao = PersistenceFactory.newUserDao();
			User newUser = dao.findByLogin(nombreUsuario);
			if (newUser == null) {
				
				
				
				Log.info("El usuario [%s] ha creado su cuenta", nombreUsuario);
			} else {
				session.invalidate();
				Log.info("El usuario [%s] no está registrado", nombreUsuario);
				resultado = "FRACASO";
			}
		} else if (!nombreUsuario.equals(session.getAttribute("user"))) {
			Log.info(
					"Login [%s] en uso.",
					((User) session.getAttribute("user")).getLogin());
			resultado = "FRACASO";
		}
		return resultado;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
