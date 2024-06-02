package com.laravel.brl.service;

import java.util.List;

import com.laravel.brl.dto.ReservationDTO;
import com.laravel.brl.models.Reservation;

public interface ReservationService {
	

	ReservationDTO saveReservation(ReservationDTO r);
	ReservationDTO updateReservation(ReservationDTO r);
	
	void deleteReservation(Reservation r);
	void deleteReservationById(Long id);
	ReservationDTO getReservation(Long id);
	List<ReservationDTO> getAllReservations();

	ReservationDTO convertEntityToDto(Reservation r);
	Reservation convertDtoToEntity(ReservationDTO r);


}
