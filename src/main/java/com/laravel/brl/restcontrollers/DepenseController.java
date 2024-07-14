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

import com.laravel.brl.dto.DepenseDTO;
import com.laravel.brl.service.DepenseService;

@RestController
@RequestMapping("/api/depense")
@CrossOrigin
public class DepenseController {
	
	@Autowired
	DepenseService depenseService;
	
	@GetMapping
	List<DepenseDTO> getAllDepenses(){
		return depenseService.getAllDepenses();
	}
	
	@GetMapping(value = "/{id}")
	public DepenseDTO getDepenseById(@PathVariable("id") Long id) {
		return depenseService.getDepense(id);
	}
	
	@PostMapping
	public DepenseDTO createDepense(@RequestBody DepenseDTO depense) {
		return depenseService.saveDepense(depense);
		
	}
	
	@PutMapping
	public DepenseDTO updateClient(@RequestBody DepenseDTO depense) {
		return depenseService.updateDepense(depense);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteClient(@PathVariable("id") Long id) {
		depenseService.deleteDepenseById(id);
		
	}

}
