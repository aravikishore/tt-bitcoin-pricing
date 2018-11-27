package com.tookitaki.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BitcoinPricingExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(BitcoinPricingServiceException.class)
	public String handleNotFoundException(Exception ex) {
		logger.error(ex.getMessage());
		return ex.getMessage();
	}
}
