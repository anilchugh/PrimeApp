package com.rbs.prime.service;

import com.rbs.prime.PrimeNumberAlgorithm;
import com.rbs.prime.model.PrimeResponse;

public interface PrimeGenService {

	/**
	 * Generate prime numbers up to and including max
	 * 
	 * @param max
	 * @return PrimeResponse instance
	 */
	PrimeResponse generatePrimesUptoMax(long max);
	
	/**
	 * Set algorithm to check for prime number
	 * @param primeNumberAlgorithm
	 */
	void setPrimeNumberAlgorithm(PrimeNumberAlgorithm primeNumberAlgorithm);

}
