package com.tookitaki.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tookitaki.api.exceptions.BitcoinPricingServiceException;
import com.tookitaki.api.models.Criteria;
import com.tookitaki.api.models.Price;
import com.tookitaki.api.services.BitcoinPricingService;
import com.tookitaki.api.util.DateUtil;

@RestController
@RequestMapping("/api/bitcoin/pricing/")
@Api(value = "All about Bitcoin pricing", tags = "Bitcoin")
public class BitcoinPricingController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BitcoinPricingService bitcoinPricingService;

	@ApiOperation(value = "price movemnet API", tags = { "Bitcoin" })
	@RequestMapping(value = "/movement", method = RequestMethod.GET)
	public List<Price> priceMovement(
			@RequestParam(name = "lastWeek", defaultValue = "false") String lastWeek,
			@RequestParam(name = "lastYear", defaultValue = "false") String lastYear,
			@ApiParam(value = "yyyy-MM-dd") @RequestParam String from,
			@ApiParam(value = "yyyy-MM-dd") @RequestParam String to)
			throws BitcoinPricingServiceException {
		Criteria criteria = new Criteria(Boolean.valueOf(lastWeek),
				Boolean.valueOf(lastYear), DateUtil.toDate(from),
				DateUtil.toDate(to));
		logger.debug("priceMovement input criteria -> {}", criteria);
		List<Price> prices = bitcoinPricingService.priceMovement(criteria);
		logger.debug("priceMovement output -> ", Optional.ofNullable(prices)
				.isPresent());
		return prices;
	}

	@ApiOperation(value = "X days price rolling API", tags = { "Bitcoin" })
	@RequestMapping(value = "/rolling", method = RequestMethod.GET)
	public String averageRollingByDatesForXDays(
			@ApiParam(value = "yyyy-MM-dd") @RequestParam String from,
			@ApiParam(value = "yyyy-MM-dd") @RequestParam String to,
			@RequestParam int days) throws BitcoinPricingServiceException {
		logger.debug("priceMovement input from, to, and days -> {}, {} and {}",
				from, to, days);
		return bitcoinPricingService.averageRollingByDatesForXDays(
				DateUtil.toDate(from), DateUtil.toDate(to), days);
	}

}
