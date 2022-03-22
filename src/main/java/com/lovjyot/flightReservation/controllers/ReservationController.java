package com.lovjyot.flightReservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lovjyot.flightReservation.dto.ReservationRequest;
import com.lovjyot.flightReservation.entities.Flight;
import com.lovjyot.flightReservation.entities.Reservation;
import com.lovjyot.flightReservation.repo.FlightRepository;
import com.lovjyot.flightReservation.services.ReservationService;

@Controller
public class ReservationController {

	Logger LOGGER= LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepository flightRepo;

	@Autowired
	ReservationService reservationService;

	@RequestMapping("showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Flight flight = flightRepo.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";

	}

	@RequestMapping(value="completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request,ModelMap modelMap) {
		LOGGER.info("Inside completeReservation method");
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation is completed successfully and the id is "+reservation.getId());
		return "reservationConfirmation";
		
	}
}
