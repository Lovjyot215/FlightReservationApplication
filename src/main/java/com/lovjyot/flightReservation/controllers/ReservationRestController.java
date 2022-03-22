package com.lovjyot.flightReservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lovjyot.flightReservation.dto.ReservationUpdateRequest;
import com.lovjyot.flightReservation.entities.Reservation;
import com.lovjyot.flightReservation.repo.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {
	
	@Autowired
	ReservationRepository reservationRepository; 
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		Reservation reservation = reservationRepository.findById(id).get();
		return reservation;
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedInFlag());
		Reservation updatedReservation = reservationRepository.save(reservation);
		return updatedReservation;
		
	}

}
