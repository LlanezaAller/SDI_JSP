package uo.sdi.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.persistence.SeatFinder;
import uo.sdi.persistence.util.Jpa;

public class SeatFinderImpl implements SeatFinder {
	@Override
	public Seat findByUserAndTrip(Long userId, Long tripId) {
		List<Seat> seats = Jpa.getManager()
				.createNamedQuery("Seat.findByUserAndTrip",
						Seat.class)
				.setParameter(1, userId)
				.setParameter(2, tripId)
				.getResultList();
		return (seats.size() > 0) ? seats.get(0) : null;
	}
	@Override
	public List<Seat> findByUser(Long userId) {
		List<Seat> seats = Jpa.getManager()
				.createNamedQuery("Seat.findByUser",
						Seat.class)
						.setParameter(1, userId)
						.getResultList();
		return (seats != null) ? seats : new ArrayList<Seat>();
	}

	@Override
	public void newSeat(Seat seat) {
		Jpa.getManager().persist(seat);
	}
	
	

}
