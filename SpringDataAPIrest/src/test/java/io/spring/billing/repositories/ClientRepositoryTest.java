package io.spring.billing.repositories;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import io.spring.billing.entities.Client;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(
		{
			DependencyInjectionTestExecutionListener.class,
			DirtiesContextTestExecutionListener.class,
			TransactionalTestExecutionListener.class,
			DbUnitTestExecutionListener.class
		}
		)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository repo;
	
	@Test
	public void findBySurnameIgnoreCaseOrderByNameDescTest() {
		List<Client> result = repo.findBySurnameIgnoreCaseOrderByNameDesc("harrison");
		
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void findByNameIgnoreCaseAndSurnameIgnoreCaseTest() {
		List<Client> result = repo.findByNameIgnoreCaseAndSurnameIgnoreCase("ringo","starr");
		
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	@DatabaseSetup("/db/clientTest.xml")
	public void findById() {
		Client client = repo.findById(25L).get();
		
		Assert.assertEquals("David", client.getName());
	}
}
