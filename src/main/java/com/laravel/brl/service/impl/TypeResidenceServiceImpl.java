package com.laravel.brl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laravel.brl.dto.TypeResidenceDTO;
import com.laravel.brl.models.TypeResidence;
import com.laravel.brl.repository.TypeResidenceRepository;
import com.laravel.brl.service.TypeResidenceService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeResidenceServiceImpl implements TypeResidenceService{
	
	private final TypeResidenceRepository typeResidenceRepository;

	@Override
	public TypeResidenceDTO saveTypeResidence(TypeResidenceDTO t) {
		return convertEntityToDto(typeResidenceRepository.save(convertDtoToEntity(t)));
	}

	@Override
	public TypeResidenceDTO updateTypeResidence(TypeResidenceDTO t) {
		return convertEntityToDto(typeResidenceRepository.save(convertDtoToEntity(t)));
	}

	@Override
	public void deleteTypeResidence(TypeResidence t) {
		typeResidenceRepository.delete(t);
		
	}

	@Override
	public void deleteTypeResidenceById(Long id) {
		typeResidenceRepository.deleteById(id);
		
	}

	@Override
	public TypeResidenceDTO getTypeResidence(Long id) {
		return typeResidenceRepository.findById(id)
				.map(this::convertEntityToDto)
				.orElseThrow(() -> new EntityNotFoundException("Type de résidence non trouvé"));
	}

	@Override
	public List<TypeResidenceDTO> getAllTypeResidences() {
		return typeResidenceRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.toList();
	}

	@Override
	public TypeResidenceDTO convertEntityToDto(TypeResidence t) {
		
		return TypeResidenceDTO.builder()
				.idTypeResidence(t.getIdTypeResidence())
				.libeleTypeResidence(t.getLibeleTypeResidence())
				.build();
	}

	@Override
	public TypeResidence convertDtoToEntity(TypeResidenceDTO t) {
		
		return TypeResidence.builder()
				.idTypeResidence(t.getIdTypeResidence())
				.libeleTypeResidence(t.getLibeleTypeResidence())
				.build();
	}

}
