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

import com.laravel.brl.dto.ClientDTO;
import com.laravel.brl.service.ClientService;

@RestController
@RequestMapping("/api/client")
@CrossOrigin
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@GetMapping
	List<ClientDTO> getAllClients(){
		return clientService.getAllClients();
	}
	
	@GetMapping(value = "/{id}")
	public ClientDTO getClientById(@PathVariable("id") Long id) {
		return clientService.getClient(id);
	}
	
	@PostMapping
	public ClientDTO createClient(@RequestBody ClientDTO client) {
		return clientService.saveClient(client);
		
	}
	
	@PutMapping
	public ClientDTO updateClient(@RequestBody ClientDTO client) {
		return clientService.updateClient(client);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteClient(@PathVariable("id") Long id) {
		 clientService.deleteClientById(id);
		
	}
	
	@GetMapping(value = "/client/{name}")
	public List<ClientDTO> getClientByName(@PathVariable("name") String name) {
		 return clientService.findByNameClient(name);		
	}
	
}
