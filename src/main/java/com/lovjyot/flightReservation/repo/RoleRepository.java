package com.lovjyot.flightReservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovjyot.flightReservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
