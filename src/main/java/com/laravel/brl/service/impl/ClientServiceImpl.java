package com.laravel.brl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laravel.brl.dto.ClientDTO;
import com.laravel.brl.models.Client;
import com.laravel.brl.repository.ClientRepository;
import com.laravel.brl.service.ClientService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	
	private final ClientRepository clientRepository;
	
	@Override
	public ClientDTO saveClient(ClientDTO c) {
		return convertEntityToDto(clientRepository.save(convertDtoToEntity(c)));
	}

	@Override
	public ClientDTO updateClient(ClientDTO c) {
		return convertEntityToDto(clientRepository.save(convertDtoToEntity(c)));
	}

	@Override
	public void deleteClient(Client c) {
		clientRepository.delete(c);
		
	}

	@Override
	public void deleteClientById(Long id) {
		clientRepository.deleteById(id);
		
	}

	@Override
	public ClientDTO getClient(Long id) {
		
		return clientRepository.findById(id)
				.map(this::convertEntityToDto)
				.orElseThrow(() -> new EntityNotFoundException("Client non trouvé"));
		
	}

	@Override
	public List<ClientDTO> getAllClients() {
		return clientRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.toList();
	}

	@Override
	public List<ClientDTO> findByNameClient(String name) {
		return clientRepository.findByNameClient(name).stream()
				.map(this::convertEntityToDto)
				.toList();
	}

	@Override
	public List<ClientDTO> findByName(String name) {
		return clientRepository.findByName(name).stream()
				.map(this::convertEntityToDto)
				.toList();
	}

	@Override
	public ClientDTO convertEntityToDto(Client c) {
		
		return ClientDTO.builder()
				.idClient(c.getIdClient())
				.nameClient(c.getNameClient())
				.pieceClient(c.getPieceClient())
				.contactClient(c.getContactClient())
				.adresseClient(c.getAdresseClient())
				.build();
		
		
	}

	@Override
	public Client convertDtoToEntity(ClientDTO c) {
		
		return Client.builder()
				.idClient(c.getIdClient())
				.nameClient(c.getNameClient())
				.pieceClient(c.getPieceClient())
				.contactClient(c.getContactClient())
				.adresseClient(c.getAdresseClient())
				.build();
		
		
	}

}
