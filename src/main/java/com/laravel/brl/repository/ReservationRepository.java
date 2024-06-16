package com.laravel.brl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laravel.brl.models.Reservation;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
