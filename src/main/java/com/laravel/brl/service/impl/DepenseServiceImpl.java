package com.laravel.brl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laravel.brl.dto.DepenseDTO;
import com.laravel.brl.models.Depense;
import com.laravel.brl.models.Residence;
import com.laravel.brl.models.TypeResidence;
import com.laravel.brl.repository.DepenseRepository;
import com.laravel.brl.service.DepenseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepenseServiceImpl implements DepenseService{
	
	private final DepenseRepository depenseRepository;

	@Override
	public DepenseDTO saveDepense(DepenseDTO d) {
		return convertEntityToDto(depenseRepository.save(convertDtoToEntity(d)));
	}

	@Override
	public DepenseDTO updateDepense(DepenseDTO d) {
		return convertEntityToDto(depenseRepository.save(convertDtoToEntity(d)));
	}

	@Override
	public void deleteDepense(Depense d) {
		depenseRepository.delete(d);
		
	}

	@Override
	public void deleteDepenseById(Long id) {
		depenseRepository.deleteById(id);
		
	}

	@Override
	public DepenseDTO getDepense(Long id) {
		return depenseRepository.findById(id)
				.map(this::convertEntityToDto)
				.orElseThrow(() -> new EntityNotFoundException("Dépense non trouvé"));
	}

	@Override
	public List<DepenseDTO> getAllDepenses() {
		return depenseRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.toList();
	}

	@Override
	public DepenseDTO convertEntityToDto(Depense d) {
		
		return DepenseDTO.builder()
				.idDepense(d.getIdDepense())
				.libeleDepense(d.getLibeleDepense())
				.dateDepense(d.getDateDepense())
				.descriptionDepense(d.getDescriptionDepense())
				.montantDepense(d.getMontantDepense())
				.residence(
						Residence.builder()
						.idResidence(d.getResidence().getIdResidence())
						.nameResidence(d.getResidence().getNameResidence())
						.localisationResidence(d.getResidence().getLocalisationResidence())
						.prixResidence(d.getResidence().getPrixResidence())
						.etatResidence(d.getResidence().getEtatResidence())
						.imageUrl(d.getResidence().getImageUrl())
						.typeResidence(
								TypeResidence.builder()
								.idTypeResidence(d.getResidence().getTypeResidence().getIdTypeResidence())
								.libeleTypeResidence(d.getResidence().getTypeResidence().getLibeleTypeResidence())
								.build())
						.build())
				.build();
	}

	@Override
	public Depense convertDtoToEntity(DepenseDTO d) {
		
		return Depense.builder()
				.idDepense(d.getIdDepense())
				.libeleDepense(d.getLibeleDepense())
				.dateDepense(d.getDateDepense())
				.descriptionDepense(d.getDescriptionDepense())
				.montantDepense(d.getMontantDepense())
				.residence(
						Residence.builder()
						.idResidence(d.getResidence().getIdResidence())
						.nameResidence(d.getResidence().getNameResidence())
						.localisationResidence(d.getResidence().getLocalisationResidence())
						.prixResidence(d.getResidence().getPrixResidence())
						.etatResidence(d.getResidence().getEtatResidence())
						.imageUrl(d.getResidence().getImageUrl())
						.typeResidence(
								TypeResidence.builder()
								.idTypeResidence(d.getResidence().getTypeResidence().getIdTypeResidence())
								.libeleTypeResidence(d.getResidence().getTypeResidence().getLibeleTypeResidence())
								.build())
						.build())
				.build();
	}

}
