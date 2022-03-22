package com.lovjyot.flightReservation.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lovjyot.flightReservation.entities.Flight;
import com.lovjyot.flightReservation.repo.FlightRepository;

@Controller
public class FlightController {
	
	@Autowired
	FlightRepository flightRepo;
	
	@RequestMapping("findFlights")
	public String flindFlights(@RequestParam("fromCity")String fromCity ,@RequestParam("toCity") String toCity, @RequestParam("departureDate") @DateTimeFormat(pattern="MM-dd-yyyy") Date departureDate,ModelMap modelMap) {
		List<Flight> flights = flightRepo.findFlights(fromCity,toCity,departureDate);
		modelMap.addAttribute("flights", flights);
		return "displayFlights";
		
	}
	
	@RequestMapping("/admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
		
	}
	
}
