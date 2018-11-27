package com.tookitaki.api.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tookitaki.api.exceptions.BitcoinPricingServiceException;
import com.tookitaki.api.models.Criteria;
import com.tookitaki.api.models.Price;
import com.tookitaki.api.util.JsonUtil;

@Service
public class BitcoinPricingServiceImpl implements BitcoinPricingService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Price> priceMovement(Criteria criteria)
			throws BitcoinPricingServiceException {
		logger.debug("Start:: priceMovement");
		List<Price> prices = null;
		try {
			final ZonedDateTime now = ZonedDateTime.now();
			// Last one week pricing data
			if (criteria.isLastWeek()) {
				prices = JsonUtil.getPriceBetweenDates(new Date(),
						Date.from(now.minusWeeks(1).toInstant()));
			} else if (criteria.isLastYear()) {
				// last one year pricing data
				prices = JsonUtil.getPriceBetweenDates(new Date(),
						Date.from(now.minusYears(1).toInstant()));
			} else if (!Objects.isNull(criteria.getFromDate())
					&& !Objects.isNull(criteria.getToDate())
					&& criteria.getToDate().after(criteria.getFromDate())) {
				// pricing data between two custom dates
				prices = JsonUtil.getPriceBetweenDates(criteria.getFromDate(),
						criteria.getToDate());
			}
		} catch (IOException e) {
			throw new BitcoinPricingServiceException(
					"Service exception occured, please contact administrator.",
					e);
		}
		logger.debug("Completed:: priceMovement");
		return prices;
	}

	@Override
	public String averageRollingByDatesForXDays(Date from, Date to, int days)
			throws BitcoinPricingServiceException {
		logger.debug("Start:: averageRollingByDatesForXDays");
		String average = null;
		try {
			List<Price> prices = JsonUtil.getPriceBetweenDates(from, to);
			Comparator<? super Price> comparator = (p1, p2) -> p1.getTime()
					.compareTo(p2.getTime());
			BigDecimal total = prices.stream().sorted(comparator).limit(days)
					.map(p -> p.getPrice())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			average = total.divide(new BigDecimal(days)).toString();
		} catch (IOException e) {
			throw new BitcoinPricingServiceException(
					"Service exception occured, please contact administrator.",
					e);
		}
		return days + " days rolling price was " + average + " for given dates";
	}

}
