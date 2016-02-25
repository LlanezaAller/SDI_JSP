package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.util.Asserts;
import uo.sdi.model.Message;
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
		String login = request.getParameter("user");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("repeatPassword");
		
		//Comprobaciones
		state = Asserts.assertCampos(name,surname
				,email,login,password,rePassword);
		state = Asserts.isEmail(email);
		state = password.equals(rePassword);
		
		HttpSession session = request.getSession();
		if (state) {
			UserDao dao = PersistenceFactory.newUserDao();
			User newUser = dao.findByLogin(login);
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
				Message error = new Message(Message.OK, "Bienvenido,  " + name);
				request.setAttribute("message", error);
			} else {
				Message error = new Message(Message.ERROR, "El nombre de usuario " + name + " ya está en uso.");
				request.setAttribute("message", error);
				Log.info("Fallo en registro, login [%s] en uso", name);
				resultado = "FRACASO";
			}
		} else {
			Log.info("Fallo en el registro, parametros erroneos");
			Message error = new Message(Message.ERROR, "No ha sido posible crear tu cuenta, revisa los campos.");
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