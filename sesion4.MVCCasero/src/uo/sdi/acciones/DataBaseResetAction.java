package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infraestructure.factories.Factories;

public class DataBaseResetAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Factories.persistence.createSystemGateway().deleteAll();
		Factories.persistence.createSystemGateway().createElements();
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
