package com.laravel.brl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.laravel.brl.dto.ResidenceDTO;
import com.laravel.brl.models.Residence;
import com.laravel.brl.models.TypeResidence;
import com.laravel.brl.repository.ResidenceRepository;
import com.laravel.brl.service.ResidenceService;
import com.laravel.brl.utils.Utils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResidenceServiceImpl implements ResidenceService{
	
	private final ResidenceRepository residenceRepository;
	 
	@Override
	public ResidenceDTO saveResidence(ResidenceDTO residence, MultipartFile image) {
		 ResidenceDTO r = new Utils().storageImg(residence, image);      
	     return convertEntityToDto(residenceRepository.save(convertDtoToEntity(r)));    
	}

	@Override
	public ResidenceDTO updateResidence(ResidenceDTO residence, MultipartFile image) {     
	    return saveResidence(residence, image);
	}

	@Override
	public void deleteResidence(Residence r) {
		residenceRepository.delete(r);
	}

	@Override
	public void deleteResidenceById(Long id) {
		residenceRepository.deleteById(id);
		
	}

	@Override
	public ResidenceDTO getResidence(Long id) {
		return residenceRepository.findById(id)
				.map(this::convertEntityToDto)
				.orElseThrow(() -> new EntityNotFoundException("Résidence non trouvé"));
	}

	@Override
	public List<ResidenceDTO> getAllResidences() {
		return residenceRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.toList();
	}

	@Override
	public ResidenceDTO convertEntityToDto(Residence r) {		
		return ResidenceDTO.builder()
				.idResidence(r.getIdResidence())
				.nameResidence(r.getNameResidence())
				.localisationResidence(r.getLocalisationResidence())
				.prixResidence(r.getPrixResidence())
				.imageUrl(r.getImageUrl())
				.typeResidence(
						TypeResidence.builder()
						.idTypeResidence(r.getTypeResidence().getIdTypeResidence())
						.libeleTypeResidence(r.getTypeResidence().getLibeleTypeResidence())
						.build()
						)
				.etatResidence(r.getEtatResidence())
				.build();
	}

	@Override
	public Residence convertDtoToEntity(ResidenceDTO r) {
		
		return Residence.builder()
				.idResidence(r.getIdResidence())
				.nameResidence(r.getNameResidence())
				.localisationResidence(r.getLocalisationResidence())
				.prixResidence(r.getPrixResidence())
				.imageUrl(r.getImageUrl())
				.typeResidence(
						TypeResidence.builder()
						.idTypeResidence(r.getTypeResidence().getIdTypeResidence())
						.libeleTypeResidence(r.getTypeResidence().getLibeleTypeResidence())
						.build()
						)
				.etatResidence(r.getEtatResidence())
				.build();
	}
}
