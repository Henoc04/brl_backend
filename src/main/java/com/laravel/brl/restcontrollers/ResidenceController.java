package com.laravel.brl.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.laravel.brl.dto.ResidenceDTO;
import com.laravel.brl.models.TypeResidence;
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
	public ResidenceDTO createResidence(@RequestParam("name") String name,
										@RequestParam("localisation") String localisation,
										@RequestParam("prix") float prix,
										@RequestParam("idtype") Long idType,
										@RequestParam("libeletype") String libeleType,
										@RequestParam("image") MultipartFile image) {
		
		ResidenceDTO residenceDTO = new ResidenceDTO();
		TypeResidence typeResidence = new TypeResidence();
				
		typeResidence.setIdTypeResidence(idType);
		typeResidence.setLibeleTypeResidence(libeleType);
		
		residenceDTO.setNameResidence(name);
		residenceDTO.setLocalisationResidence(localisation);
		residenceDTO.setPrixResidence(prix);
		residenceDTO.setTypeResidence(typeResidence);
		
		return residenceService.saveResidence(residenceDTO, image);
		
	}
	
	@PutMapping
	public ResidenceDTO updateResidence(@RequestParam("id") Long id,
										@RequestParam("name") String name,
										@RequestParam("localisation") String localisation,
										@RequestParam("prix") float prix,
										@RequestParam("idtype") Long idType,
										@RequestParam("libeletype") String libeleType,
										@RequestParam("etat") String etat,
										@RequestParam("image") MultipartFile image) {
		
		ResidenceDTO residenceDTO = new ResidenceDTO();
		TypeResidence typeResidence = new TypeResidence();
				
		typeResidence.setIdTypeResidence(idType);
		typeResidence.setLibeleTypeResidence(libeleType);
		
		residenceDTO.setIdResidence(id);
		residenceDTO.setNameResidence(name);
		residenceDTO.setLocalisationResidence(localisation);
		residenceDTO.setPrixResidence(prix);
		residenceDTO.setTypeResidence(typeResidence);
		residenceDTO.setEtatResidence(etat);
		
		return residenceService.updateResidence(residenceDTO, image);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteResidence(@PathVariable Long id) {
		residenceService.deleteResidenceById(id);
		
	}
	

}
