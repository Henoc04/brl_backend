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

import com.laravel.brl.dto.ResidenceDTO;
import com.laravel.brl.service.ResidenceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/residence")
@CrossOrigin
@RequiredArgsConstructor
public class ResidenceController {
	
	
	private final ResidenceService residenceService;
	
	@GetMapping
	List<ResidenceDTO> getAllResidences(){
		return residenceService.getAllResidences();
	}
	
	@GetMapping(value = "/{id}")
	public ResidenceDTO getResidenceById(@PathVariable Long id) {
		return residenceService.getResidence(id);
	}
	
	@PostMapping
	public ResidenceDTO createResidence(@RequestBody ResidenceDTO residence) {
		return residenceService.saveResidence(residence);
		
	}
	
	@PutMapping
	public ResidenceDTO updateResidence(@RequestBody ResidenceDTO residence) {
		return residenceService.updateResidence(residence);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteResidence(@PathVariable Long id) {
		residenceService.deleteResidenceById(id);
		
	}

}
