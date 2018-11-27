package com.tookitaki.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tookitaki.api.exceptions.BitcoinPricingServiceException;

/**
 * 
 * Date utility class
 * 
 * @author RAVIKISHORE
 *
 */
public class DateUtil {

	private static final String PATTERN = "yyyy-MM-dd";
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static Date toDate(String in) throws BitcoinPricingServiceException {
		Date out = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
			out = formatter.parse(in);
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new BitcoinPricingServiceException("Date format should be yyyy-MM-dd");
		}
		return out;
	}
}
