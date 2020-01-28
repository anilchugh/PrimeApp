package com.rbs.prime.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class PrimeResponse {

	@JsonProperty("Initial")
	private Long initial;

	@JsonProperty("Primes")
	private List<Long> primes;

	public List<Long> getPrimes() {
		return primes;
	}

	public void setPrimes(List<Long> primes) {
		this.primes = primes;
	}

	public Long getInitial() {
		return initial;
	}

	public void setInitial(Long initial) {
		this.initial = initial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((initial == null) ? 0 : initial.hashCode());
		result = prime * result + ((primes == null) ? 0 : primes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrimeResponse other = (PrimeResponse) obj;
		if (initial == null) {
			if (other.initial != null)
				return false;
		} else if (!initial.equals(other.initial))
			return false;
		if (primes == null) {
			if (other.primes != null)
				return false;
		} else if (!primes.equals(other.primes))
			return false;
		return true;
	}

}
