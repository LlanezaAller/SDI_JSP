package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.exception.BusinessException;
import uo.sdi.acciones.util.SdiUtil;
import uo.sdi.infraestructure.factories.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.type.AddressPoint;
import uo.sdi.model.type.SeatStatus;
import uo.sdi.model.type.TripStatus;
import uo.sdi.model.type.Waypoint;
import uo.sdi.view.Message;
import alb.util.log.Log;

public class CrearViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			boolean hasAllData = SdiUtil.assertCampos(
					request.getParameter("freeSeats"),
					request.getParameter("totalCost"),
					request.getParameter("limitDatetime"),
					request.getParameter("departureDatetime"),
					request.getParameter("arrivalDatetime"));
			if (!hasAllData)
				return faltanCampos(request);

			String viajeID = request.getParameter("viajeID");
			Trip viaje = null;
			if (SdiUtil.assertCampos(viajeID))
				viaje = Factories.persistence.createTripGateway().findById(
						Long.valueOf(viajeID));

			int freeSeats = Integer.parseInt(request.getParameter("freeSeats"));
			double estimatedCost = Float.parseFloat(request
					.getParameter("totalCost"));
			Date closingDatetime = SdiUtil.getDate(request
					.getParameter("limitDatetime"));
			// Departure
			String departureCountry = request.getParameter("departureCountry");
			String departureState = request.getParameter("departureState");
			String departureCity = request.getParameter("departureCity");
			String departureZip = request.getParameter("departureZip");
			String departureStreet = request.getParameter("departureStreet");

			double departureLat, departureLon;
			departureLat = departureLon = -1;
			if (SdiUtil.assertCampos(request.getParameter("departureLat"),
					request.getParameter("departureLon"))) {
				departureLat = Float.parseFloat(request
						.getParameter("departureLat"));
				departureLon = Float.parseFloat(request
						.getParameter("departureLon"));
			}
			Date departureDatetime = SdiUtil.getDate(request
					.getParameter("departureDatetime"));
			// Destination
			String arrivalCountry = request.getParameter("arrivalCountry");
			String arrivalState = request.getParameter("arrivalState");
			String arrivalCity = request.getParameter("arrivalCity");
			String arrivalZip = request.getParameter("arrivalZip");
			String arrivalStreet = request.getParameter("arrivalStreet");
			double arrivalLat, arrivalLon;
			arrivalLat = arrivalLon = -1;
			if (SdiUtil.assertCampos(request.getParameter("arrivalLat"),
					request.getParameter("arrivalLon"))) {
				arrivalLat = Float.parseFloat(request
						.getParameter("arrivalLat"));
				arrivalLon = Float.parseFloat(request
						.getParameter("arrivalLon"));
			}
			Date arrivalDatetime = SdiUtil.getDate(request
					.getParameter("arrivalDatetime"));
			// Description
			String comments = request.getParameter("description");

			hasAllData = SdiUtil.assertCampos(departureCountry, departureState,
					departureCity, departureZip, departureStreet,
					arrivalCountry, arrivalState, arrivalCity, arrivalZip,
					arrivalStreet);
			hasAllData &= departureDatetime.before(arrivalDatetime)
					&& closingDatetime.before(departureDatetime);
			if (hasAllData) {
				user = Factories.persistence.createUserGateway().findByLogin(
						user.getLogin());
				AddressPoint departure = new AddressPoint(departureStreet,
						departureCity, departureState, departureCountry,
						departureZip, new Waypoint(departureLat, departureLon));
				AddressPoint destination = new AddressPoint(arrivalStreet,
						arrivalCity, arrivalState, arrivalCountry, arrivalZip,
						new Waypoint(arrivalLat, arrivalLon));
				if (viaje == null) {
					viaje = new Trip(departure, destination, arrivalDatetime,
							departureDatetime, closingDatetime, freeSeats,
							freeSeats + 1, estimatedCost, comments,
							TripStatus.OPEN, user);
					Factories.persistence.createTripGateway().newTrip(viaje);
					Seat seat = new Seat(user, viaje);
					seat.setStatus(SeatStatus.ACCEPTED);

					Factories.persistence.createSeatGateway().newSeat(seat);

					Message ok = new Message(Message.OK, "Viaje creado con éxito");
					request.setAttribute("message", ok);
					return "EXITO";
				} else {
										
					viaje.setArrivalDate(arrivalDatetime);
					viaje.setClosingDate(closingDatetime);
					viaje.setComments(comments);
					viaje.setDeparture(departure);
					viaje.setDestination(destination);
					viaje.setEstimatedCost(estimatedCost);
					
					Message ok = new Message(Message.OK, "Viaje modificado con éxito");
					request.setAttribute("message", ok);
					return "EXITO";
				}

				
			} else {
				return faltanCampos(request);
			}

		}
		Message error = new Message(Message.ERROR,
				"Necesitas estar logueado para poder crear viajes");
		request.setAttribute("message", error);
		Log.debug("Un visitante no autorizado ha intentado crear un viaje");
		return "FRACASO";

	}

	private String faltanCampos(HttpServletRequest request) {
		Message error = new Message(Message.ERROR,
				"Rellena todos los campos necesarios");
		request.setAttribute("message", error);
		return "FRACASO";
	}
}
