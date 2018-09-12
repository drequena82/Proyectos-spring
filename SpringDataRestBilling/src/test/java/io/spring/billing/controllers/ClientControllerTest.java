package io.spring.billing.controllers;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.spring.billing.controller.ClientController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ClientControllerTest {
	
	@InjectMocks
	private ClientController controller;
	
	@Mock
	private ModelMapper mockModelMapper;
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	
}
