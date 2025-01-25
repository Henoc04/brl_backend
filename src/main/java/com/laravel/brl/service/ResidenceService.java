package com.laravel.brl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.laravel.brl.dto.ResidenceDTO;
import com.laravel.brl.models.Residence;

public interface ResidenceService {
	
	ResidenceDTO saveResidence(ResidenceDTO r, MultipartFile image);
	ResidenceDTO updateResidence(ResidenceDTO r, MultipartFile image);
	
	void deleteResidence(Residence r);
	void deleteResidenceById(Long id);
	ResidenceDTO getResidence(Long id);
	List<ResidenceDTO> getAllResidences();

	ResidenceDTO convertEntityToDto(Residence r);
	Residence convertDtoToEntity(ResidenceDTO r);

}
