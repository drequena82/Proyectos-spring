package io.spring.billing;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import io.spring.billing.dtos.ClientDTO;
import io.spring.billing.entities.Client;
import io.spring.billing.managers.ClientManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientControllerTestIT {
	
	@Autowired
	private ClientManager manager;
	
	@Autowired
	private TestRestTemplate template;
	
	@Autowired
	private ModelMapper mapper;
	
	@Test
	public void findByIdTest() {
		ResponseEntity<ClientDTO> response = template.
				exchange("/client/2",HttpMethod.GET,new HttpEntity<>(new HttpHeaders()),ClientDTO.class);
		
		Assert.assertEquals("No es el cliente correcto", response.getBody().getName(), "paul");
	}
	
	@Test
	public void findById2Test() {
		ResponseEntity<ClientDTO> response = template.
				exchange("/client/2",HttpMethod.GET,new HttpEntity<>(new HttpHeaders()),ClientDTO.class);
		
		Assert.assertEquals("No es el cliente correcto", response.getBody().getSurname(), "mccartney");
	}
	
	@Test
	public void save() {
		ClientDTO dto = new ClientDTO();
		Client client = new Client();
		dto.setName("David");
		dto.setSurname("Requena");
		
		client = mapper.map(dto, Client.class);
		
		//client.getAudit().setCreateOn(new Date(System.currentTimeMillis()));
		
		client = manager.save(client);
		
		ResponseEntity<ClientDTO> response = template.
				exchange("/client/" + client.getId(),HttpMethod.GET,new HttpEntity<>(new HttpHeaders()),ClientDTO.class);
		
		Assert.assertEquals("No es el cliente correcto", response.getBody().getName(), "David");
	}
	
	
}
