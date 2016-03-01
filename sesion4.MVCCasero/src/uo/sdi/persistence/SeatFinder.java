package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.Seat;

public interface SeatFinder {

	Seat findByUserAndTrip(Long userId, Long tripId);

	void newSeat(Seat seat);

	List<Seat> findByUser(Long userId);

}
