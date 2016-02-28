package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.util.Asserts;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.User;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("user") != null) {
			User usuario = Factories.persistence.createUserGateway()
					.findByLogin(
							((User) session.getAttribute("user")).getLogin());

			// Recogida de parámetros
			boolean status = true;

			String newName = request.getParameter("name");
			String newSurname = request.getParameter("surname");
			String newEmail = request.getParameter("email");

			// Password
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("password");
			String reNewPassword = request.getParameter("repeatPassword");

			status = Asserts.assertCampos(oldPassword, newPassword,
					reNewPassword);

			try {
				
				if (status){
					if( usuario.getPassword().equals(oldPassword)
						&& newPassword.equals(reNewPassword)) {
					usuario.setPassword(newPassword);
					}else {
						Message m = new Message(Message.ERROR, "Debes introducir tu contraseña vieja, y confirmar la nueva.");
						request.setAttribute("message", m);
						return "FRACASO";
					}	
				}
				if (Asserts.isEmail(newEmail)){
					usuario.setEmail(newEmail);
				}else {
					Message m = new Message(Message.ERROR, "Email no válido.");
					request.setAttribute("message", m);
					return "FRACASO";
				}
				usuario.setName(newName);
				usuario.setSurname(newSurname);

				// Factories.persistence.createUserGateway().update(usuario);
				Log.debug("Modificado email de [%s] con el valor [%s]",
						usuario.getLogin(), newEmail);
				Message m = new Message(Message.OK, "Perfil editado con éxito");
				request.setAttribute("message", m);
				return "EXITO";
			} catch (Exception e) {
				Log.error("Algo ha ocurrido actualizando el usuario de [%s]",
						usuario.getLogin());
				return "FRACASO";
			}
		} else {
			Message m = new Message(Message.ERROR,
					"No puedes editar tu usuario sin estar logueado.");
			request.setAttribute("message", m);
			return "FRACASO";
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
