package com.laravel.brl.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.laravel.brl.dto.BilanDTO;
import com.laravel.brl.dto.ReservationDTO;
import com.laravel.brl.models.Bilan;
import com.laravel.brl.models.Client;
import com.laravel.brl.models.Reservation;
import com.laravel.brl.models.Residence;
import com.laravel.brl.models.TypeResidence;
import com.laravel.brl.repository.ReservationRepository;
import com.laravel.brl.repository.ResidenceRepository;
import com.laravel.brl.service.ReservationService;
import com.laravel.brl.utils.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private Utils utils = new Utils();
		
	private final ReservationRepository reservationRepository;
	
    private final ResidenceRepository residenceRepository;

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
		return reservationRepository.findById(id)
				.map(this::convertEntityToDto)
				.orElseThrow(() -> new EntityNotFoundException("Réservation  non trouvé"));
	}

	@Override
	public List<ReservationDTO> getAllReservations() {
		return reservationRepository.findAll().stream()
				.map(this::etatReservation)
				.map(this::updateResidenceState)
				.map(this::convertEntityToDto)
				.toList();
	}

	@Override
	public ReservationDTO convertEntityToDto(Reservation r) {
	
		return ReservationDTO.builder()
				.idReservation(r.getIdReservation())
				.client(
						Client.builder()
						.idClient(r.getClient().getIdClient())
						.nameClient(r.getClient().getNameClient())
						.pieceClient(r.getClient().getPieceClient())
						.adresseClient(r.getClient().getAdresseClient())
						.contactClient(r.getClient().getContactClient())
						.reservations(r.getClient().getReservations())
						.build()
						)
				.dateEntrer(r.getDateEntrer())
				.dateReservation(r.getDateReservation())
				.dateSortie(r.getDateSortie())
				.total(r.getTotal())
				.etatReservation(r.getEtatReservation())
				.residence(
						Residence.builder()
						.idResidence(r.getResidence().getIdResidence())
						.nameResidence(r.getResidence().getNameResidence())
						.localisationResidence(r.getResidence().getLocalisationResidence())
						.prixResidence(r.getResidence().getPrixResidence())
						.etatResidence(r.getResidence().getEtatResidence())
						.imageUrl(r.getResidence().getImageUrl())
						.typeResidence(
								TypeResidence.builder()
								.idTypeResidence(r.getResidence().getTypeResidence().getIdTypeResidence())
								.libeleTypeResidence(r.getResidence().getTypeResidence().getLibeleTypeResidence())
								.build()
								)
						.build()
						)
				.build();
	}

	@Override
	public Reservation convertDtoToEntity(ReservationDTO r) {
	
		return Reservation.builder()
				.idReservation(r.getIdReservation())
				.client(
						Client.builder()
						.idClient(r.getClient().getIdClient())
						.nameClient(r.getClient().getNameClient())
						.pieceClient(r.getClient().getPieceClient())
						.adresseClient(r.getClient().getAdresseClient())
						.contactClient(r.getClient().getContactClient())
						.reservations(r.getClient().getReservations())
						.build()
						)
				.dateEntrer(r.getDateEntrer())
				.dateReservation(r.getDateReservation())
				.dateSortie(r.getDateSortie())
				.total(r.getTotal())
				.etatReservation(r.getEtatReservation())
				.residence(
						Residence.builder()
						.idResidence(r.getResidence().getIdResidence())
						.nameResidence(r.getResidence().getNameResidence())
						.localisationResidence(r.getResidence().getLocalisationResidence())
						.prixResidence(r.getResidence().getPrixResidence())
						.etatResidence(r.getResidence().getEtatResidence())
						.imageUrl(r.getResidence().getImageUrl())
						.typeResidence(
								TypeResidence.builder()
								.idTypeResidence(r.getResidence().getTypeResidence().getIdTypeResidence())
								.libeleTypeResidence(r.getResidence().getTypeResidence().getLibeleTypeResidence())
								.build()
								)
						.build()
						)
				.build();
	}
	
	public Reservation etatReservation(Reservation reservation) {
			
			if (LocalDate.now().isBefore(reservation.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
				reservation.setEtatReservation("En attente");
			}else if (((reservation.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())) || (reservation.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(LocalDate.now())) ) && 
					(LocalDate.now().isBefore(reservation.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) || (LocalDate.now().isEqual(reservation.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) ) {
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
		 
		return results.stream()
                 .map(row -> new BilanDTO((Residence) row[0], (int) row[1], (String) row[2], (Double) row[3], (Double) row[4]))
                 .toList();
	}

	@Override
	public BilanDTO convertEntityToDtoBilan(Bilan r) {
		return BilanDTO.builder()
				.residence(
						Residence.builder()
						.idResidence(r.getResidence().getIdResidence())
						.nameResidence(r.getResidence().getNameResidence())
						.localisationResidence(r.getResidence().getLocalisationResidence())
						.prixResidence(r.getResidence().getPrixResidence())
						.etatResidence(r.getResidence().getEtatResidence())
						.build()
						)
				.depenses(r.getDepenses())
				.year(r.getYear())
				.month(r.getMonth())
				.recette(r.getRecette())
				.build();
	}

	
	
}
