package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.Rating;

public interface RatingFinder{

	Rating findByAboutFrom(
			Long aboutUserId, 
			Long aboutTripId, 
			Long fromUserId, 
			Long fromTripId
		); 
	
	List<Rating> findRatingsByUserAboutId (Long id);
	
}
