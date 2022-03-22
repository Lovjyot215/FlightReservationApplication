package com.lovjyot.flightReservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovjyot.flightReservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
