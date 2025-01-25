package com.laravel.brl.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laravel.brl.dto.TypeResidenceDTO;
import com.laravel.brl.service.TypeResidenceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/typeresidence")
@CrossOrigin
@RequiredArgsConstructor
public class TypeResidenceController {
	
	
	private final TypeResidenceService typeResidenceService;
	
	@GetMapping
	List<TypeResidenceDTO> getAllTypeResidences(){
		return typeResidenceService.getAllTypeResidences();
	}
	
	@GetMapping(value = "/{id}")
	public TypeResidenceDTO getTypeResidenceById(@PathVariable Long id) {
		return typeResidenceService.getTypeResidence(id);
	}
	
	@PostMapping
	public TypeResidenceDTO createTypeResidence(@RequestBody TypeResidenceDTO typeResidence) {
		return typeResidenceService.saveTypeResidence(typeResidence);
		
	}
	
	@PutMapping
	public TypeResidenceDTO updateTypeResidence(@RequestBody TypeResidenceDTO typeResidence) {
		return typeResidenceService.updateTypeResidence(typeResidence);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteTypeResidence(@PathVariable Long id) {
		typeResidenceService.deleteTypeResidenceById(id);
		
	}

}
