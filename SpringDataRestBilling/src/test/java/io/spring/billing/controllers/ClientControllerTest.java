package io.spring.billing.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.spring.billing.controller.impl.ClientControllerImpl;
import io.spring.billing.managers.ClientManager;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
/**
 * Clase de test que comprueba la conectividad del controller (sólo la conectividad)
 * @author drequena
 *
 */
public class ClientControllerTest {
	
	@InjectMocks
	private ClientControllerImpl controller;
	
	@Mock
	private ModelMapper mockModelMapper;
	
	@Mock
	private ClientManager manager;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void findByIdTest() {
		Mockito.when(manager.findOne(1L)).thenReturn(null);
		
		ResultActions result;
		try {
			result = mockMvc.perform(get("/client/2"));
			result.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//Probar JSONASSERT
}
