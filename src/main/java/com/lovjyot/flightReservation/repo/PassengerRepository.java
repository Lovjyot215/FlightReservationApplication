package com.lovjyot.flightReservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovjyot.flightReservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
