package com.tookitaki.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tookitaki.api.models.Criteria;
import com.tookitaki.api.services.BitcoinPricingService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BitcoinPricingControllerTest {

	private static final String X_DAYS_ROLLING = "/api/bitcoin/pricing/rolling?days=3&from=2018-11-01&to=2018-11-30";

	private static final String BW_DATES_PRICES = "/api/bitcoin/pricing/movement?lastWeek=false&lastYear=false&from=2018-11-01&to=2018-11-30";

	private static final String LASTYEAR_PRICES = "/api/bitcoin/pricing/movement?lastWeek=false&lastYear=true&from=2018-11-01&to=2018-11-30";

	private static final String LASTWEEK_PRICES = "/api/bitcoin/pricing/movement?lastWeek=true&lastYear=false&from=2018-11-01&to=2018-11-30";

	@Autowired
	private MockMvc mvc;

	@MockBean
	BitcoinPricingService btcService;

	@Test
	public void priceMovementLastWeek() throws Exception {
		given(btcService.priceMovement(any(Criteria.class))).willReturn(
				Collections.emptyList());
		mvc.perform(
				MockMvcRequestBuilders.get(LASTWEEK_PRICES).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void priceMovementLastYear() throws Exception {
		given(btcService.priceMovement(any(Criteria.class))).willReturn(
				Collections.emptyList());
		mvc.perform(
				MockMvcRequestBuilders.get(LASTYEAR_PRICES).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void priceMovementTwoDates() throws Exception {
		given(btcService.priceMovement(any(Criteria.class))).willReturn(
				Collections.emptyList());
		mvc.perform(
				MockMvcRequestBuilders.get(BW_DATES_PRICES).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	};

	@Test
	public void averageRollingByDatesForXDays() throws Exception {
		given(
				btcService.averageRollingByDatesForXDays(any(Date.class),
						any(Date.class), any(Integer.class))).willReturn(
				"Rolling price 11.0");
		mvc.perform(MockMvcRequestBuilders.get(X_DAYS_ROLLING)).andExpect(
				status().isOk());
	}
}
