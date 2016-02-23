package uo.sdi.persistence.impl;

import java.util.List;

import uo.sdi.model.Application;
import uo.sdi.persistence.ApplicationFinder;
import uo.sdi.persistence.util.Jpa;

public class ApplicationFinderImpl implements ApplicationFinder {

	@Override
	public List<Application> findByUserId(Long userId) {
		List<Application> aplicaciones = Jpa.getManager()
				.createNamedQuery("Application.findByUserId",
						Application.class)
				.setParameter(1, userId).getResultList();
		return aplicaciones;
	}

	@Override
	public List<Application> findByTripId(Long tripId) {
		List<Application> aplicaciones = Jpa.getManager()
				.createNamedQuery("Application.findByTripId",
						Application.class)
				.setParameter(1, tripId).getResultList();
		return aplicaciones;
	}

}
