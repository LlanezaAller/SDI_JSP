package uo.sdi.acciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.exception.BusinessException;
import uo.sdi.persistence.PersistenceException;
import uo.sdi.persistence.util.Jpa;

public class ActionExecutor {
	public String execute(Accion accion, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();

		try {
			String res = accion.execute(request, response);
			trx.commit();
			return res;
		} catch (PersistenceException pex) {
			if (trx.isActive())
				trx.rollback();
			throw pex;
		} finally {
			if (em.isOpen())
				em.close();
		}
	}
}

