package com.laravel.brl.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/typeresidence")
@CrossOrigin
public class TypeResidenceController {
	
	
	@Autowired
	TypeResidenceService typeResidenceService;
	
	@GetMapping
	List<TypeResidenceDTO> getAllTypeResidences(){
		return typeResidenceService.getAllTypeResidences();
	}
	
	@GetMapping(value = "/{id}")
	public TypeResidenceDTO getTypeResidenceById(@PathVariable("id") Long id) {
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
	public void deleteTypeResidence(@PathVariable("id") Long id) {
		typeResidenceService.deleteTypeResidenceById(id);
		
	}

}
