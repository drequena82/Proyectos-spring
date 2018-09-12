package io.spring.billing.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.spring.billing.controller.ClientController;
import io.spring.billing.dtos.ClientDTO;
import io.spring.billing.entities.Client;
import io.spring.billing.managers.ClientManager;

@RestController
public class ClientControllerImpl implements ClientController {

	@Autowired
	public ClientManager manager;

	@Autowired
	public ModelMapper mapper;

	public ResponseEntity<ClientDTO> getClientById(@PathVariable String id) {

		Optional<Client> client = manager.findOne(Long.valueOf(id));
		ClientDTO dto = null;

		if (client != null) {
			dto = mapper.map(client.get(), ClientDTO.class);

			return new ResponseEntity<ClientDTO>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ClientDTO>(dto, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Client> save(ClientDTO dto) {
		Client client = mapper.map(dto, Client.class);
		ResponseEntity<Client> response = null;
		try {
			client = manager.save(client);
			response = new ResponseEntity<Client>(client, HttpStatus.OK);
		} catch (Exception ex) {
			response = new ResponseEntity<Client>(client, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	public ResponseEntity<List<ClientDTO>> getClients(@RequestParam("name")String name,@RequestParam("surname")String surname) {

		Iterable<Client> clients = manager.findByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);
		ClientDTO dto = null;
		List<ClientDTO> lista = new ArrayList<ClientDTO>();
		
		if (clients != null) {
			while( clients.iterator().hasNext()) {
				Client client = clients.iterator().next();
				
				dto = mapper.map(client, ClientDTO.class);
				
				lista.add(dto);
			}
			return new ResponseEntity<List<ClientDTO>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ClientDTO>>(lista, HttpStatus.NOT_FOUND);
		}
	}
}
