package com.lovjyot.flightReservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovjyot.flightReservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
