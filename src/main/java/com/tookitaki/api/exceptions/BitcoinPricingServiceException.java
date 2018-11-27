package com.tookitaki.api.exceptions;

public class BitcoinPricingServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public BitcoinPricingServiceException() {
		super();
	}

	public BitcoinPricingServiceException(String message) {
		super(message);
	}

	public BitcoinPricingServiceException(String message, Throwable th) {
		super(message, th);
	}
}
