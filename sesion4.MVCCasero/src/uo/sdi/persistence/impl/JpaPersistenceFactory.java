package uo.sdi.persistence.impl;

import uo.sdi.persistence.ApplicationFinder;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingFinder;
import uo.sdi.persistence.SeatFinder;
import uo.sdi.persistence.TripFinder;
import uo.sdi.persistence.UserFinder;

public class JpaPersistenceFactory implements PersistenceFactory{
	@Override
	public TripFinder createTripGateway() {
		return new TripFinderImpl();
	}
	@Override
	public SeatFinder createSeatGateway() {
		return new SeatFinderImpl();
	}
	@Override
	public RatingFinder createRatingGateway() {
		return new RatingFinderImpl();
	}
	@Override
	public UserFinder createUserGateway() {
		return new UserFinderImpl();
	}
	@Override
	public ApplicationFinder createApplicationDao() {
		return new ApplicationFinderImpl();
	}
}
