package com.rbs.prime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbs.prime.model.PrimeResponse;
import com.rbs.prime.service.PrimeGenService;
import com.rbs.prime.service.PrimeGenServiceImpl;

@RunWith(SpringRunner.class)
public class PrimeGenServiceUnitTest {

	@Autowired
	private PrimeGenService service;

	@MockBean
	private DefaultPrimeNumberAlgorithm algorithm;

	@Before
	public void setup() {
		service.setPrimeNumberAlgorithm(algorithm);

	}

	@TestConfiguration
	static class PrimeGenServiceTestContextConfiguration {
		@Bean
		public PrimeGenService employeeService() {
			return new PrimeGenServiceImpl();
		}
	}

	@Test
	public void getPrimesUptoMaxTest() throws Exception {
		PrimeResponse primeResponse = new PrimeResponse();
		primeResponse.setInitial(3l);
		primeResponse.setPrimes(Arrays.asList(1L, 2L, 3L));

		when(algorithm.checkPrime(3)).thenReturn(true);

		assertEquals("List of primes does not match", primeResponse, service.generatePrimesUptoMax(3L));
	}

}
