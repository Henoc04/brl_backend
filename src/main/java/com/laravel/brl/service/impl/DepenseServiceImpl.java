package com.laravel.brl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laravel.brl.dto.DepenseDTO;
import com.laravel.brl.models.Depense;
import com.laravel.brl.repository.DepenseRepository;
import com.laravel.brl.service.DepenseService;

@Service
public class DepenseServiceImpl implements DepenseService{
	
	@Autowired
	DepenseRepository depenseRepository;
	
	@Autowired
	ModelMapper modelMapper;

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
		return convertEntityToDto(depenseRepository.findById(id).get());
	}

	@Override
	public List<DepenseDTO> getAllDepenses() {
		return depenseRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public DepenseDTO convertEntityToDto(Depense d) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE); 
		DepenseDTO depenseDTO = modelMapper.map(d, DepenseDTO.class);
		return depenseDTO;
	}

	@Override
	public Depense convertDtoToEntity(DepenseDTO d) {
		Depense depense = new Depense();
		depense = modelMapper.map(d, Depense.class);
		return depense;
	}

}
