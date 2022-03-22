package com.lovjyot.flightReservation.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lovjyot.flightReservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	//public List<Flight> findByDepartureCityAndArrivalCityAndDateOfDeparture(String fromCity, String toCity, Date departureDate);
	
	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String fromCity, @Param("arrivalCity") String toCity,
			@Param("dateOfDeparture") Date departureDate);
}
