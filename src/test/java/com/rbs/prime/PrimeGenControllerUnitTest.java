package com.rbs.prime;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rbs.prime.model.PrimeResponse;
import com.rbs.prime.service.PrimeGenService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PrimeGenControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PrimeGenService service;

	@Test
	public void getPrimesUptoMaxTest() throws Exception {
		PrimeResponse primeResponse = new PrimeResponse();
		primeResponse.setInitial(10l);
		primeResponse.setPrimes(Arrays.asList(1L, 2L, 3L, 5L, 7L));

		when(service.generatePrimesUptoMax(10)).thenReturn(primeResponse);

		mvc.perform(get("/primes/10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"Initial\":10,\"Primes\":[1,2,3,5,7]}")));
	}

	@Test
	public void getPrimesUptoMaxWhereMaxIsZero() throws Exception {

		mvc.perform(get("/primes/0").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andExpect(content().string(
						containsString("{\"status\":400,\"message\":\"Prime number max limit less than zero: 0\"}")));
	}

}
