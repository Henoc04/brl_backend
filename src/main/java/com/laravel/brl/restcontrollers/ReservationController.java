package com.laravel.brl.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laravel.brl.dto.BilanDTO;
import com.laravel.brl.dto.ReservationDTO;
import com.laravel.brl.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin
public class ReservationController {
	

	@Autowired
	ReservationService reservationService;
	
	@GetMapping
	List<ReservationDTO> getAllReservations(){
		return reservationService.getAllReservations();
	}
	
	@GetMapping(value = "/{id}")
	public ReservationDTO getReservationById(@PathVariable("id") Long id) {
		return reservationService.getReservation(id);
	}
	
	@PostMapping
	public ReservationDTO createReservation(@RequestBody ReservationDTO reservation) {
		return reservationService.saveReservation(reservation);
		
	}
	
	@PutMapping
	public ReservationDTO updateReservation(@RequestBody ReservationDTO reservation) {
		return reservationService.updateReservation(reservation);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteReservation(@PathVariable("id") Long id) {
		reservationService.deleteReservationById(id);
		
	}
	
	@GetMapping("/bilan")
    public List<BilanDTO> getMonthlySummaries() {
		return reservationService.getMonthlySummaries();
       
    }

}
