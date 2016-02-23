package uo.sdi.persistence.impl;

import java.util.List;

import uo.sdi.model.Rating;
import uo.sdi.persistence.RatingFinder;
import uo.sdi.persistence.util.Jpa;

public class RatingFinderImpl implements RatingFinder {

	@Override
	public Rating findByAboutFrom(Long aboutUserId, Long aboutTripId,
			Long fromUserId, Long fromTripId) {
		List<Rating> ratings = Jpa.getManager()
				.createNamedQuery("User.findUserByLogin",
						Rating.class)
				.setParameter(1, aboutUserId)
				.setParameter(2, aboutTripId)
				.getResultList();
		return (ratings.size() > 0) ? ratings.get(0) : null;
	}

}