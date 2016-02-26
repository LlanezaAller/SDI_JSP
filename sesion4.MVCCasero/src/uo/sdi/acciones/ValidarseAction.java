package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.view.Message;

public class ValidarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String resultado = "EXITO";
		String nombreUsuario = request.getParameter("user");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
//			UserDao dao = PersistenceFactory.newUserDao();
			UserFinder uf = Factories.persistence.createUserGateway();
			User userByLogin = uf.findByLogin(nombreUsuario);///dao.findByLogin(nombreUsuario);
			if (userByLogin != null
					&& userByLogin.getPassword().equals(password)) {
				session.setAttribute("user", userByLogin);
				Message error = new Message(Message.OK, "¡Bienvenido de nuevo, " + nombreUsuario + "!");
				request.setAttribute("message", error);
				Log.info("El usuario [%s] ha iniciado sesión", nombreUsuario);
			} else {
				session.invalidate();
				Message error = new Message(Message.ERROR, "El usuario y/o contraseña son incorrectos.");
				request.setAttribute("message", error);
				Log.info("El usuario [%s] no está registrado", nombreUsuario);
				resultado = "FRACASO";
			}
		} else if (!nombreUsuario.equals(session.getAttribute("user"))) {
			String userNick = ((User)session.getAttribute("user")).getLogin();
			Log.info(
					"Se ha intentado iniciar sesión como [%s] teniendo la "
					+ "sesión iniciada como [%s]",
					nombreUsuario, userNick);
			
			session.invalidate();
			Message error = new Message(Message.ERROR, "Has intentado iniciar sesión teniendo una sesión ya iniciada.");
			request.setAttribute("message", error);
			resultado = "FRACASO";
		}
		return resultado;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
