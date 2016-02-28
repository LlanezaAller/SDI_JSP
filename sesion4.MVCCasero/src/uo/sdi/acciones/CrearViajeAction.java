package uo.sdi.acciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.exception.BusinessException;
import uo.sdi.acciones.util.Asserts;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.type.AddressPoint;
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
			boolean hasAllData = Asserts.assertCampos(
					request.getParameter("freeSeats"),
					request.getParameter("totalCost"),
					request.getParameter("limitDatetime"),
					request.getParameter("departureDatetime"),
					request.getParameter("arrivalDatetime"));
			if (!hasAllData)
				return faltanCampos(request);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");

			int freeSeats = Integer.parseInt(request.getParameter("freeSeats"));
			double totalCost = Float.parseFloat(request
					.getParameter("totalCost"));
			Log.debug(request.getParameter("limitDatetime"));
			Date limitDatetime = null;
			try {
				limitDatetime = simpleDateFormat.parse(request.getParameter(
						"limitDatetime").replace('T', ' '));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Log.debug(limitDatetime.toString());
			// Departure
			String departureCountry = request.getParameter("departureCountry");
			String departureState = request.getParameter("departureState");
			String departureCity = request.getParameter("departureCity");
			String departureZip = request.getParameter("departureZip");
			String departureStreet = request.getParameter("departureStreet");

			double departureLat, departureLon;
			departureLat = departureLon = -1;
			if (Asserts.assertCampos(request.getParameter("departureLat"),
					request.getParameter("departureLon"))) {
				departureLat = Float.parseFloat(request
						.getParameter("departureLat"));
				departureLon = Float.parseFloat(request
						.getParameter("departureLon"));
			}
			Date departureDatetime = null;
			try {
				departureDatetime = simpleDateFormat.parse(request
						.getParameter("departureDatetime").replace('T', ' '));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// Destination
			String arrivalCountry = request.getParameter("arrivalCountry");
			String arrivalState = request.getParameter("arrivalState");
			String arrivalCity = request.getParameter("arrivalCity");
			String arrivalZip = request.getParameter("arrivalZip");
			String arrivalStreet = request.getParameter("arrivalStreet");
			double arrivalLat, arrivalLon;
			arrivalLat = arrivalLon = -1;
			if (Asserts.assertCampos(request.getParameter("arrivalLat"),
					request.getParameter("arrivalLon"))) {
				arrivalLat = Float.parseFloat(request
						.getParameter("arrivalLat"));
				arrivalLon = Float.parseFloat(request
						.getParameter("arrivalLon"));
			}
			Date arrivalDatetime = null;
			try {
				arrivalDatetime = simpleDateFormat.parse(request.getParameter(
						"arrivalDatetime").replace('T', ' '));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// Description
			String description = request.getParameter("description");

			hasAllData = Asserts.assertCampos(departureCountry, departureState,
					departureCity, departureZip, departureStreet,
					arrivalCountry, arrivalState, arrivalCity, arrivalZip,
					arrivalStreet);
			if (hasAllData) {
				// TODO Pala db
				AddressPoint departure = new AddressPoint(departureStreet,
						departureCity, departureState, departureCountry,
						departureZip, new Waypoint(departureLat, departureLon));
				AddressPoint arrival = new AddressPoint(arrivalStreet,
						arrivalCity, arrivalState, arrivalCountry, arrivalZip,
						new Waypoint(arrivalLat, arrivalLon));

				Trip viaje = new Trip(departure, arrival, arrivalDatetime,
						departureDatetime, limitDatetime, freeSeats, freeSeats,
						totalCost, description, TripStatus.OPEN, user);

				Message ok = new Message(Message.OK, "Viaje creado con éxito");
				request.setAttribute("message", ok);
				return "EXITO";
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
