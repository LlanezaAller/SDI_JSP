package uo.sdi.persistence;

import uo.sdi.model.Seat;

public interface SeatFinder {

	Seat findByUserAndTrip(Long userId, Long tripId);

}
