package io.spring.billing.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.billing.dtos.ClientDTO;
import io.spring.billing.entities.Client;

@RequestMapping("/client")
public interface ClientController {
	
	@GetMapping("{id}")
	public ResponseEntity<ClientDTO> getClientById(String id);
	
	@GetMapping("/find")
	public ResponseEntity<List<ClientDTO>> getClients(String name,String surname);
		
	@PostMapping("/save")
	public ResponseEntity<Client> save(ClientDTO dto);
}
