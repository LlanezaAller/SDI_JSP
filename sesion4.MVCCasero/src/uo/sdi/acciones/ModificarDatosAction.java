package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.util.Asserts;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User usuario = Factories.persistence.createUserGateway().findByLogin(
				((User) session.getAttribute("user")).getLogin());

		// Recogida de parámetros
		boolean status = true;

		String newName = request.getParameter("name");
		String newSurname = request.getParameter("surname");
		String newEmail = request.getParameter("email");

		// Password
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String reNewPassword = request.getParameter("reNewPassword");

		status = Asserts.assertCampos(oldPassword, newPassword, reNewPassword);


		try {
			if (status && usuario.getPassword().equals(oldPassword)
					&& newPassword.equals(reNewPassword)) {
				
				usuario.setPassword(newPassword);
				
			}
			if(Asserts.isEmail(newEmail))
				usuario.setEmail(newEmail);
			usuario.setName(newName);
			usuario.setSurname(newSurname);
			
			// Factories.persistence.createUserGateway().update(usuario);
			Log.debug("Modificado email de [%s] con el valor [%s]",
					usuario.getLogin(), newEmail);
		} catch (Exception e) {
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
