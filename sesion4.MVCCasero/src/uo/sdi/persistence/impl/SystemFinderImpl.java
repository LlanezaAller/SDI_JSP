package uo.sdi.persistence.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.type.AddressPoint;
import uo.sdi.model.type.SeatStatus;
import uo.sdi.model.type.TripStatus;
import uo.sdi.model.type.UserStatus;
import uo.sdi.model.type.Waypoint;
import uo.sdi.persistence.SystemFinder;
import uo.sdi.persistence.util.Jpa;

public class SystemFinderImpl implements SystemFinder {

	@Override
	public void deleteAll() {
		EntityManager em = Jpa.getManager();

		em.createNamedQuery("System.dropRatings").executeUpdate();
		em.createNamedQuery("System.dropSeats").executeUpdate();
		em.createNamedQuery("System.dropTrips").executeUpdate();
		em.createNamedQuery("System.dropUsers").executeUpdate();
	}

	@Override
	public void createElements() {
		// Creacion User
		User usuario1 = new User("usuario1", "usuario1", "usuario1Nombre",
				"usuario1Apellido", "usuario1@users.com", UserStatus.ACTIVE);
		User usuario2 = new User("usuario2", "usuario2", "usuario2Nombre",
				"usuario2Apellido", "usuario2@users.com", UserStatus.ACTIVE);
		User usuario3 = new User("usuario3", "usuario3", "usuario3Nombre",
				"usuario3Apellido", "usuario3@users.com", UserStatus.ACTIVE);
		Factories.persistence.createUserGateway().newUser(usuario1);
		Factories.persistence.createUserGateway().newUser(usuario2);
		Factories.persistence.createUserGateway().newUser(usuario3);

		// Creacion Trip
		Long d1 = (long) (24 * 60 * 60 * 1000);

		Trip trip1 = new Trip(new AddressPoint("direccion11", "ciudad11",
				"state11", "country11", "1111", new Waypoint(11.0, 11.0)),
				new AddressPoint("direccion12", "ciudad12", "state12",
						"country12", "1122", new Waypoint(12.0, 12.0)),
				new Date(System.currentTimeMillis() - d1), new Date(
						System.currentTimeMillis() - d1 * 2), new Date(
						System.currentTimeMillis() - d1 * 3), 1, 2, 10.0,
				"comment1", TripStatus.DONE, usuario1);
		Trip trip2 = new Trip(new AddressPoint("direccion21", "ciudad21",
				"state21", "country21", "2211", new Waypoint(21.0, 21.0)),
				new AddressPoint("direccion22", "ciudad22", "state22",
						"country22", "2222", new Waypoint(22.0, 22.0)),
				new Date(System.currentTimeMillis() + d1 * 3), new Date(
						System.currentTimeMillis() + d1 * 2), new Date(
						System.currentTimeMillis()+ d1), 2, 3, 10.0, "comment1",
				TripStatus.OPEN, usuario2);
		Trip trip3 = new Trip(new AddressPoint("direccion31", "ciudad31",
				"state31", "country31", "3311", new Waypoint(31.0, 31.0)),
				new AddressPoint("direccion32", "ciudad32", "state32",
						"country32", "3322", new Waypoint(32.0, 32.0)),
				new Date(System.currentTimeMillis() + d1 * 3), new Date(
						System.currentTimeMillis() + d1 * 2), new Date(
						System.currentTimeMillis()+ d1), 3, 4, 10.0, "comment1",
				TripStatus.CANCELLED, usuario3);

		Factories.persistence.createTripGateway().newTrip(trip1);
		Factories.persistence.createTripGateway().newTrip(trip2);
		Factories.persistence.createTripGateway().newTrip(trip3);

		// Creacion Aplicantes

		usuario1.aplicar(trip2);
		usuario2.aplicar(trip3);
		usuario3.aplicar(trip1);

		// Creacion Seats
		ArrayList<Seat> seats = new ArrayList<>();

		Seat seat11 = new Seat(usuario1, trip1);
		Seat seat12 = new Seat(usuario2, trip1);

		Seat seat22 = new Seat(usuario2, trip2);
		Seat seat23 = new Seat(usuario3, trip2);

		Seat seat31 = new Seat(usuario1, trip3);
		Seat seat33 = new Seat(usuario3, trip3);

		seats.add(seat11);
		seats.add(seat12);
		seats.add(seat22);
		seats.add(seat23);
		seats.add(seat31);
		seats.add(seat33);

		for (Seat s : seats) {
			s.setStatus(SeatStatus.ACCEPTED);
			if (s.equals(seat22))
				s.setStatus(SeatStatus.EXCLUDED);
			Factories.persistence.createSeatGateway().newSeat(s);

		}

		// Creacion de Ratings

		Rating rating1 = new Rating(seat11, seat12, "ComentarioPromotor", 5);
		Rating rating2 = new Rating(seat12, seat11, "ComentarioAPromotor", 5);

		Factories.persistence.createRatingGateway().newRating(rating1);
		Factories.persistence.createRatingGateway().newRating(rating2);
	}

}