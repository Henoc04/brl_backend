package com.laravel.brl.service;

import java.util.List;

import com.laravel.brl.dto.DepenseDTO;
import com.laravel.brl.models.Depense;

public interface DepenseService {
	

	DepenseDTO saveDepense(DepenseDTO d);
	DepenseDTO updateDepense(DepenseDTO d);
	
	void deleteDepense(Depense d);
	void deleteDepenseById(Long id);
	DepenseDTO getDepense(Long id);
	List<DepenseDTO> getAllDepenses();	

	DepenseDTO convertEntityToDto(Depense d);
	Depense convertDtoToEntity(DepenseDTO d);

}
