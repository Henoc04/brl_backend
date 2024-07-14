package com.laravel.brl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laravel.brl.dto.ClientDTO;
import com.laravel.brl.models.Client;
import com.laravel.brl.repository.ClientRepository;
import com.laravel.brl.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
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
		return convertEntityToDto(clientRepository.findById(id).get());
	}

	@Override
	public List<ClientDTO> getAllClients() {
		//convertir les element de la liste automatique avec la programmation fonctionnelle (ça marche pour tout type)
		return clientRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ClientDTO> findByNameClient(String name) {
		return clientRepository.findByNameClient(name).stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ClientDTO> findByName(String name) {
		return clientRepository.findByName(name).stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public ClientDTO convertEntityToDto(Client c) {
		
		//avec la bibliothèque ModelMapper
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE); // pour retrouver des champs en declarer jdans la class principal (faire perdre en precision)
		ClientDTO clientDTO = modelMapper.map(c, ClientDTO.class);
		return clientDTO;
		
		/*
		//la manière classique
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setIdClient(c.getIdClient());
		clientDTO.setNameClient(c.getNameClient());
		clientDTO.setBithdateClient(c.getBithdateClient());
		return clientDTO
		*/
		
		//affectation avec builder (programmation fonctionnelle)
		/*
		return ClientDTO.builder()
				.idClient(c.getIdClient())
				.nameClient(c.getNameClient())
				.bithdateClient(c.getBithdateClient())
				.build();
				*/
	}

	@Override
	public Client convertDtoToEntity(ClientDTO c) {
		
		Client client = new Client();
		client = modelMapper.map(c, Client.class);
		return client;
		/*
		Client client = new Client();
		client.setIdClient(c.getIdClient());
		client.setNameClient(c.getNameClient());
		client.setBithdateClient(c.getBithdateClient());
		return client;
		*/
	}

}
