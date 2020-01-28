package com.rbs.prime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rbs.prime.model.PrimeResponse;
import com.rbs.prime.service.PrimeGenService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PrimeGenServiceCacheUnitTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private PrimeGenService service;

	@MockBean
	private DefaultPrimeNumberAlgorithm algorithm;

	@Before
	public void setup() {
		service.setPrimeNumberAlgorithm(algorithm);

	}

	@Test
	public void getPrimesUptoMaxTest() throws Exception {
		PrimeResponse primeResponse = new PrimeResponse();
		primeResponse.setInitial(10l);
		primeResponse.setPrimes(Arrays.asList(1L, 2L, 3L, 5L, 7L));

		mvc.perform(get("/primes/10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"Initial\":10,\"Primes\":[1,2,3,5,7]}")));
		assertThat(verify(algorithm, times(1)).checkPrime(10L));

		// second call should not result in algorithm being called
		mvc.perform(get("/primes/10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"Initial\":10,\"Primes\":[1,2,3,5,7]}")));
		assertThat(verify(algorithm, times(1)).checkPrime(10L));

	}

}
