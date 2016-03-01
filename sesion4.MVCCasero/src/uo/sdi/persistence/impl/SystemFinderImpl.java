package uo.sdi.persistence.impl;

import javax.persistence.EntityManager;

import uo.sdi.persistence.SystemFinder;
import uo.sdi.persistence.util.Jpa;

public class SystemFinderImpl implements SystemFinder {

	@Override
	public void deleteAll() {
		EntityManager em = Jpa.getManager();
		em.createNamedQuery("System.dropRatings");
		em.createNamedQuery("System.dropSeats");
		em.createNamedQuery("System.dropUsers");
		em.createNamedQuery("System.dropTrips");
	}
}