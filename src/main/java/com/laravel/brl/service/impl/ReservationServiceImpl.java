package com.laravel.brl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laravel.brl.dto.ReservationDTO;
import com.laravel.brl.models.Reservation;
import com.laravel.brl.repository.ReservationRepository;
import com.laravel.brl.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ReservationDTO saveReservation(ReservationDTO r) {
		return convertEntityToDto(reservationRepository.save(convertDtoToEntity(r)));
	}

	@Override
	public ReservationDTO updateReservation(ReservationDTO r) {
		return convertEntityToDto(reservationRepository.save(convertDtoToEntity(r)));
	}

	@Override
	public void deleteReservation(Reservation r) {
		reservationRepository.delete(r);
		
	}

	@Override
	public void deleteReservationById(Long id) {
		reservationRepository.deleteById(id);
		
	}

	@Override
	public ReservationDTO getReservation(Long id) {
		return convertEntityToDto(reservationRepository.findById(id).get());
	}

	@Override
	public List<ReservationDTO> getAllReservations() {
		return reservationRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public ReservationDTO convertEntityToDto(Reservation r) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ReservationDTO reservationDTO = modelMapper.map(r, ReservationDTO.class);
		return reservationDTO;
	}

	@Override
	public Reservation convertDtoToEntity(ReservationDTO r) {
		Reservation reservation = new Reservation();
		reservation = modelMapper.map(r, Reservation.class);
		return reservation;
	}

}
