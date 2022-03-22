package com.lovjyot.flightReservation.services;

import com.lovjyot.flightReservation.dto.ReservationRequest;
import com.lovjyot.flightReservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
