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

		boolean state = true;
		
		//Obtencion de datos
		
		String resultado = "EXITO";
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("repeatPassword");
		
		//Comprobaciones
		state = Asserts.assertCampos(name,surname
				,email,login,password,rePassword);
		state = Asserts.isEmail(email);
		state = password == rePassword;
		
		HttpSession session = request.getSession();
		if (state) {
			UserDao dao = PersistenceFactory.newUserDao();
			User newUser = dao.findByLogin(name);
			if (newUser == null  ) {
				
				//Definicion de caracteristicas
				newUser = new User();
				newUser.setEmail(email);
				newUser.setLogin(login);
				newUser.setName(name);
				newUser.setPassword(rePassword);
				newUser.setSurname(surname);
				newUser.setId(dao.save(newUser));
				
				//Introducimos el usuario en sesion
				session.setAttribute("user", newUser);
				Log.info("El usuario [%s] ha creado su cuenta", name);
			} else {
				session.invalidate();
				Log.info("Fallo en registro, login [%s] en uso", name);
				resultado = "FRACASO";
			}
		} else {
			Log.info("Fallo en el registro, parametros erroneos");
			resultado = "FRACASO";
		}
		return resultado;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
