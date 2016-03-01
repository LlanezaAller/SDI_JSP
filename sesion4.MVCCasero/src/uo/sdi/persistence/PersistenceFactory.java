package uo.sdi.persistence;

public interface PersistenceFactory {

	UserFinder createUserGateway();

	TripFinder createTripGateway();

	SeatFinder createSeatGateway();

	RatingFinder createRatingGateway();

	SystemFinder createSystemGateway();
}
