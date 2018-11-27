package com.tookitaki.api.services;

import java.util.Date;
import java.util.List;

import com.tookitaki.api.exceptions.BitcoinPricingServiceException;
import com.tookitaki.api.models.Criteria;
import com.tookitaki.api.models.Price;

/**
 * 
 * this interface is used publish the price movement and avergae rollong prices
 * for between dates
 * 
 * @author RAVIKISHORE
 *
 */
public interface BitcoinPricingService {

	/**
	 * 
	 * find the price movement by using specified criteria.
	 * 
	 * Criteria may be Last week, month, year or two custom dates.
	 * 
	 * @param criteria
	 * @return
	 * @throws BitcoinPricingServiceException
	 */
	public List<Price> priceMovement(Criteria criteria)
			throws BitcoinPricingServiceException;

	/**
	 * 
	 * this method is used to calculate average rolling between dates for
	 * specified days
	 * 
	 * @param from
	 * @param to
	 * @param days
	 * @return
	 * @throws BitcoinPricingServiceException
	 */
	public String averageRollingByDatesForXDays(Date from, Date to,
			int days) throws BitcoinPricingServiceException;

}
