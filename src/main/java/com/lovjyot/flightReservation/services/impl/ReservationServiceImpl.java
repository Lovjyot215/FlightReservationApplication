package com.lovjyot.flightReservation.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovjyot.flightReservation.controllers.ReservationController;
import com.lovjyot.flightReservation.dto.ReservationRequest;
import com.lovjyot.flightReservation.entities.Flight;
import com.lovjyot.flightReservation.entities.Passenger;
import com.lovjyot.flightReservation.entities.Reservation;
import com.lovjyot.flightReservation.repo.FlightRepository;
import com.lovjyot.flightReservation.repo.PassengerRepository;
import com.lovjyot.flightReservation.repo.ReservationRepository;
import com.lovjyot.flightReservation.services.ReservationService;
import com.lovjyot.flightReservation.util.EmailUtil;
import com.lovjyot.flightReservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	Logger LOGGER= LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		// Make Payment
		
		LOGGER.info("Inside bookFlight method");
		
		long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger=new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving Passenger Data..");
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving Reservation Data..");
		Reservation savedReservation = reservationRepository.save(reservation);
		String filePath = "C:\\Reservations\\reservation"+savedReservation.getId()+".pdf";
		
		LOGGER.info("Generating Itinerary..");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Emailing Itinerary..");
		emailUtil.sendItinerary(passenger.getEmail(),filePath);
		
		return savedReservation;
	}

}
