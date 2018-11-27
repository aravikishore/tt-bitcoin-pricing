package com.tookitaki.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tookitaki.api.models.Bitcoin;
import com.tookitaki.api.models.Price;

/**
 * this utility class is used to load json file and provide utility method to
 * parse json data
 * 
 * @author RAVIKISHORE
 *
 */
public final class JsonUtil {

	private static final String DATA_FILENAME = "data.json";

	/**
	 * load the json data
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Bitcoin load() throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream in = JsonUtil.class.getClassLoader().getResourceAsStream(
				DATA_FILENAME);
		Bitcoin btc = mapper.readValue(in, Bitcoin.class);
		return btc;
	}

	/**
	 * 
	 * return the price list from data
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<Price> prices() throws JsonParseException,
			JsonMappingException, IOException {
		return load().getPrices();
	}

	/**
	 * filter data by two dates
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<Price> getPriceBetweenDates(Date from, Date to)
			throws JsonParseException, JsonMappingException, IOException {
		List<Price> prices = prices();
		Predicate<? super Price> datesPredicate = p -> p.getTime().after(from)
				&& p.getTime().before(to);
		List<Price> priceData = prices.stream().filter(datesPredicate)
				.collect(Collectors.toList());
		return priceData;
	}

}
