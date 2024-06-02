package com.laravel.brl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laravel.brl.dto.ResidenceDTO;
import com.laravel.brl.models.Residence;
import com.laravel.brl.repository.ResidenceRepository;
import com.laravel.brl.service.ResidenceService;

@Service
public class ResidenceServiceImpl implements ResidenceService{
	
	@Autowired
	ResidenceRepository residenceRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ResidenceDTO saveResidence(ResidenceDTO r) {
		return convertEntityToDto(residenceRepository.save(convertDtoToEntity(r)));
	}

	@Override
	public ResidenceDTO updateResidence(ResidenceDTO r) {
		return convertEntityToDto(residenceRepository.save(convertDtoToEntity(r)));
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
		return convertEntityToDto(residenceRepository.findById(id).get());
	}

	@Override
	public List<ResidenceDTO> getAllResidences() {
		return residenceRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public ResidenceDTO convertEntityToDto(Residence r) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE); // pour retrouver des champs en declarer jdans la class principal (faire perdre en precision)
		ResidenceDTO residenceDTO = modelMapper.map(r, ResidenceDTO.class);
		return residenceDTO;
	}

	@Override
	public Residence convertDtoToEntity(ResidenceDTO r) {
		Residence residence = new Residence();
		residence = modelMapper.map(r, Residence.class);
		return residence;
	}

}
