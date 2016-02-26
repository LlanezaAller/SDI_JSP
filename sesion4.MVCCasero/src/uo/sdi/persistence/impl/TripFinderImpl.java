package uo.sdi.persistence.impl;

import java.util.Date;
import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.persistence.TripFinder;
import uo.sdi.persistence.util.Jpa;

public class TripFinderImpl implements TripFinder {

	@Override
	public Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate) {
		List<Trip> trips = Jpa.getManager()
				.createNamedQuery("Trip.findByPromoterIdAndArrivalDate",
						Trip.class)
				.setParameter(1, id)
				.setParameter(2, arrivalDate)
				.getResultList();
		return (trips.size() > 0) ? trips.get(0) : null;
	}
	
	
	
	@Override
	public List<Trip> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Trip.findAll", Trip.class)
				.getResultList();
	}



	@Override
	public Trip findById(Long id) {
		List<Trip> trips = Jpa.getManager()
				.createNamedQuery("Trip.findById",
						Trip.class)
				.setParameter(1, id)
				.getResultList();
		return (trips.size() > 0) ? trips.get(0) : null;
	}
	
	@Override
	public void newTrip(Trip trip){
		Jpa.getManager().persist(trip);
	}
	
	@Override
	public void updateTrip(Trip trip){
		Jpa.getManager().merge(trip);
	}

}
