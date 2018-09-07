package io.spring.billing.repositories;

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

import io.spring.billing.entities.Bill;

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
public class BillRepositoryTest {
	
	@Autowired
	private BillRepository repo;
	
	@Test
	public void fetchByIdWithClientWithLineWithProduct() {
		
		Bill bill = this.repo.fetchByIdWithClientWithLineWithProduct(1L);

		Assert.assertEquals("sgt peppers" , bill.getDescription());
	}
}
