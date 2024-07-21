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

import com.laravel.brl.dto.ClientDTO;
import com.laravel.brl.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/client")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;
	
	@GetMapping
	List<ClientDTO> getAllClients(){
		return clientService.getAllClients();
	}
	
	@GetMapping(value = "/{id}")
	public ClientDTO getClientById(@PathVariable Long id) {
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
	public void deleteClient(@PathVariable Long id) {
		 clientService.deleteClientById(id);
		
	}
	
	@GetMapping(value = "/client/{name}")
	public List<ClientDTO> getClientByName(@PathVariable String name) {
		 return clientService.findByNameClient(name);		
	}
	
}
