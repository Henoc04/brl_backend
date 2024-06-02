package com.laravel.brl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.laravel.brl.models.Reservation;


@RepositoryRestResource(path = "reserv")
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
