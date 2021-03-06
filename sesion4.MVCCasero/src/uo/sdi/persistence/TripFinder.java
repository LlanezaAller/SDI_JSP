package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.model.type.TripStatus;

public interface TripFinder {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);

	Trip findById(Long id);

	List<Trip> findAll();

	List<Trip> findAllTripsByPromoterId(Long id);

	void newTrip(Trip trip);

	void updateTrip(Trip trip);

	List<Trip> findAllStatus(TripStatus status);

	List<Trip> findAllAplicantsByUserId(Long id);

}
