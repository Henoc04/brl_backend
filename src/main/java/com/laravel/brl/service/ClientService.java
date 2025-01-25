package com.laravel.brl.service;

import java.util.List;

import com.laravel.brl.dto.ClientDTO;
import com.laravel.brl.models.Client;

public interface ClientService {
	
	ClientDTO saveClient(ClientDTO c);
	ClientDTO updateClient(ClientDTO c);
	
	void deleteClient(Client c);
	void deleteClientById(Long id);
	ClientDTO getClient(Long id);
	List<ClientDTO> getAllClients();
	
	List<ClientDTO> findByNameClient(String name);
	
	ClientDTO convertEntityToDto(Client c);
	Client convertDtoToEntity(ClientDTO c);
}
