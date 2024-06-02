package com.laravel.brl.service;

import java.util.List;

import com.laravel.brl.dto.ResidenceDTO;
import com.laravel.brl.models.Residence;

public interface ResidenceService {
	
	ResidenceDTO saveResidence(ResidenceDTO r);
	ResidenceDTO updateResidence(ResidenceDTO r);
	
	void deleteResidence(Residence r);
	void deleteResidenceById(Long id);
	ResidenceDTO getResidence(Long id);
	List<ResidenceDTO> getAllResidences();

	ResidenceDTO convertEntityToDto(Residence r);
	Residence convertDtoToEntity(ResidenceDTO r);

}
