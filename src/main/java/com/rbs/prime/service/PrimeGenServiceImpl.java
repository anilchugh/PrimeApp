package com.rbs.prime.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rbs.prime.DefaultPrimeNumberAlgorithm;
import com.rbs.prime.PrimeNumberAlgorithm;
import com.rbs.prime.model.PrimeResponse;

@Service
public class PrimeGenServiceImpl implements PrimeGenService {

	private PrimeNumberAlgorithm primeNumberAlgorithm = new DefaultPrimeNumberAlgorithm();

	public PrimeNumberAlgorithm getPrimeNumberAlgorithm() {
		return primeNumberAlgorithm;
	}

	public void setPrimeNumberAlgorithm(PrimeNumberAlgorithm primeNumberAlgorithm) {
		this.primeNumberAlgorithm = primeNumberAlgorithm;
	}

	private static final List<Long> PRIME_LIST_OF_SIZE_ONE = Arrays.asList(1L);
	private static final List<Long> PRIME_LIST_OF_SIZE_TWO = Arrays.asList(1L, 2L);

	@Cacheable("primes")
	public PrimeResponse generatePrimesUptoMax(long max) {
		PrimeResponse primeResponse = new PrimeResponse();
		primeResponse.setInitial(max);

		if (max == 1) {
			primeResponse.setPrimes(PRIME_LIST_OF_SIZE_ONE);
		} else if (max == 2) {
			primeResponse.setPrimes(PRIME_LIST_OF_SIZE_TWO);
		} else {
			ArrayList<Long> primeList = new ArrayList<>(PRIME_LIST_OF_SIZE_TWO);
			long potentialPrime = primeList.get(primeList.size() - 1);
			while (++potentialPrime <= max) {
				if (primeNumberAlgorithm.checkPrime(potentialPrime)) {
					primeList.add(potentialPrime);
				}
			}
			primeResponse.setPrimes(primeList);
		}
		return primeResponse;
	}

}
