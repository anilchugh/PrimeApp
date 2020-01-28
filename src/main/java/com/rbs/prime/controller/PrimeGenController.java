package com.rbs.prime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.prime.exception.ValidationException;
import com.rbs.prime.model.PrimeResponse;
import com.rbs.prime.service.PrimeGenService;

@RestController
@RequestMapping(path = "/primes")
public class PrimeGenController {

	@Autowired
	private PrimeGenService primeGenService;

	@GetMapping(path = "/{max}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
	public ResponseEntity<PrimeResponse> getPrimesUptoMax(@PathVariable long max) throws ValidationException {
		if (max <= 0) {
			throw new ValidationException(max);
		}
		PrimeResponse primeResponse = primeGenService.generatePrimesUptoMax(max);
		return new ResponseEntity<PrimeResponse>(primeResponse, HttpStatus.OK);

	}

}
