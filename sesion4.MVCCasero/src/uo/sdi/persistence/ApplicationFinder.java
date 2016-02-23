package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.Application;

public interface ApplicationFinder {

	List<Application> findByUserId( Long userId );
	List<Application> findByTripId( Long tripId );
	
}
