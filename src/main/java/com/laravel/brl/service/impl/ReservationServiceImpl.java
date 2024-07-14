package com.laravel.brl.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laravel.brl.dto.BilanDTO;
import com.laravel.brl.dto.ReservationDTO;
import com.laravel.brl.models.Bilan;
import com.laravel.brl.models.Reservation;
import com.laravel.brl.models.Residence;
import com.laravel.brl.repository.ReservationRepository;
import com.laravel.brl.repository.ResidenceRepository;
import com.laravel.brl.service.ReservationService;
import com.laravel.brl.utils.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	Utils utils = new Utils();
		
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
    private ResidenceRepository residenceRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ReservationDTO saveReservation(ReservationDTO r) {
		utils.calculateTotal(r);
		return convertEntityToDto(reservationRepository.save(convertDtoToEntity(r)));
	}

	@Override
	public ReservationDTO updateReservation(ReservationDTO r) {
		utils.calculateTotal(r);
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
				.map(this::etatReservation)
				.map(this::updateResidenceState)
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
	
	public Reservation etatReservation(Reservation reservation) {
			
			if (LocalDate.now().isBefore(reservation.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
				reservation.setEtatReservation("En attente");
			}else if (((reservation.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())) || (reservation.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(LocalDate.now())) ) && 
					((LocalDate.now().isBefore(reservation.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))) || (LocalDate.now().isEqual(reservation.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) ) {
				reservation.setEtatReservation("En cours");
			}else {
				reservation.setEtatReservation("Terminer");
			}
			return reservation;
	}
	
	
	private  Reservation updateResidenceState(Reservation reservation) {
		
        List<Reservation> reservations = reservation.getResidence().getReservations();
        
        boolean hasConfirmedReservations = reservations.stream()
                .anyMatch(reserv -> !("Terminer".equals(reserv.getEtatReservation())));

        if (hasConfirmedReservations) {
        	reservation.getResidence().setEtatResidence("Occuper / Reserver");
        } else {
        	reservation.getResidence().setEtatResidence("Disponible");
        }
        
        Residence residence = reservation.getResidence();
        residenceRepository.save(residence);
        
        return reservation;
    }
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<BilanDTO> getMonthlySummaries() {
		
		 String jpqlQuery = "SELECT r.residence, YEAR(r.dateEntrer), DATE_FORMAT(r.dateEntrer, '%M'), SUM(r.total), " +
                 "(SELECT COALESCE(SUM(d.montantDepense), 0) " +
                 " FROM Depense d " +
                 " WHERE r.residence = d.residence AND YEAR(r.dateEntrer) = YEAR(d.dateDepense) AND MONTH(r.dateEntrer) = MONTH(d.dateDepense)) " +
                 "FROM Reservation r " +
                 "GROUP BY r.residence, YEAR(r.dateEntrer), MONTH(r.dateEntrer)";
		 
		 List<Object[]> results = entityManager.createQuery(jpqlQuery, Object[].class)
                 .getResultList();
		 
		 List<BilanDTO> bilans = results.stream()
                 .map(row -> new BilanDTO((Residence) row[0], (int) row[1], (String) row[2], (Double) row[3], (Double) row[4]))
                 .collect(Collectors.toList());
		 
		return bilans;
	}

	@Override
	public BilanDTO convertEntityToDtoBilan(Bilan r) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		BilanDTO bilanDTO = modelMapper.map(r, BilanDTO.class);
		return bilanDTO;
	}

	
	
}
