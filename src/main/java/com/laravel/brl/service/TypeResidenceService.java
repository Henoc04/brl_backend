package com.laravel.brl.service;

import java.util.List;

import com.laravel.brl.dto.TypeResidenceDTO;
import com.laravel.brl.models.TypeResidence;

public interface TypeResidenceService {
	
	
	TypeResidenceDTO saveTypeResidence(TypeResidenceDTO t);
	TypeResidenceDTO updateTypeResidence(TypeResidenceDTO t);
	
	void deleteTypeResidence(TypeResidence t);
	void deleteTypeResidenceById(Long id);
	TypeResidenceDTO getTypeResidence(Long id);
	List<TypeResidenceDTO> getAllTypeResidences();

	TypeResidenceDTO convertEntityToDto(TypeResidence t);
	TypeResidence convertDtoToEntity(TypeResidenceDTO t);

}
