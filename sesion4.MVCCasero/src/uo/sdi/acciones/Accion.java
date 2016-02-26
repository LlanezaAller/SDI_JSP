package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.exception.BusinessException;

public interface Accion {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws BusinessException;

}
