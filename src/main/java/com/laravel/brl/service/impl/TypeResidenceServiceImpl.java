package com.laravel.brl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laravel.brl.dto.TypeResidenceDTO;
import com.laravel.brl.models.TypeResidence;
import com.laravel.brl.repository.TypeResidenceRepository;
import com.laravel.brl.service.TypeResidenceService;

@Service
public class TypeResidenceServiceImpl implements TypeResidenceService{
	
	@Autowired
	TypeResidenceRepository typeResidenceRepository;
	
	@Autowired
	ModelMapper modelMapper;

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
		return convertEntityToDto(typeResidenceRepository.findById(id).get());
	}

	@Override
	public List<TypeResidenceDTO> getAllTypeResidences() {
		return typeResidenceRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public TypeResidenceDTO convertEntityToDto(TypeResidence t) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE); // pour retrouver des champs en declarer jdans la class principal (faire perdre en precision)
		TypeResidenceDTO typeResidenceDTO = modelMapper.map(t, TypeResidenceDTO.class);
		return typeResidenceDTO;
	}

	@Override
	public TypeResidence convertDtoToEntity(TypeResidenceDTO t) {
		TypeResidence typeResidence = new TypeResidence();
		typeResidence = modelMapper.map(t, TypeResidence.class);
		return typeResidence;
	}

}
