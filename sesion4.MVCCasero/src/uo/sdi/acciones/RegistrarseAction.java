package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.util.Asserts;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class RegistrarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String resultado = "EXITO";
		String nombreUsuario = request.getParameter("name");
		String apellidosUsuario = request.getParameter("surname");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("repeatPassword");
		
		
		HttpSession session = request.getSession();
		if (Asserts.assertCampos(nombreUsuario,apellidosUsuario
				,email,login,password,rePassword)) {
			UserDao dao = PersistenceFactory.newUserDao();
			User newUser = dao.findByLogin(nombreUsuario);
			if (newUser == null  ) {
				newUser = new User();
				
				
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
