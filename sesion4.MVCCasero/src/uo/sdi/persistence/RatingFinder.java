package uo.sdi.persistence;

import uo.sdi.model.Rating;

public interface RatingFinder{

	Rating findByAboutFrom(
			Long aboutUserId, 
			Long aboutTripId, 
			Long fromUserId, 
			Long fromTripId
		); 
	
}
