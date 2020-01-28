package com.rbs.prime;

public class DefaultPrimeNumberAlgorithm implements PrimeNumberAlgorithm {

	@Override
	public boolean checkPrime(long n) {
		for (long i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				// not a prime
				return false;
			}
		}
		return true;
	}

}
