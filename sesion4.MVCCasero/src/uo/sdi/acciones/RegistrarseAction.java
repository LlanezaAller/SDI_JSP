package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.util.SdiUtil;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.User;
import uo.sdi.model.type.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.view.Message;
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
		state = SdiUtil.assertCampos(name,surname
				,email,login,password,rePassword);
		state = SdiUtil.isEmail(email);
		state = password.equals(rePassword);
		
		HttpSession session = request.getSession();
		if (state) {
//			UserDao dao = PersistenceFactory.newUserDao();
			UserFinder uf = Factories.persistence.createUserGateway();
			User newUser = uf.findByLogin(login);//dao.findByLogin(login);
			if (newUser == null  ) {
				
				//Definicion de caracteristicas
				newUser = new User();
				newUser.setEmail(email);
				newUser.setLogin(login);
				newUser.setName(name);
				newUser.setStatus(UserStatus.ACTIVE);
				newUser.setPassword(rePassword);
				newUser.setSurname(surname);
				
				uf.newUser(newUser);
				
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
