package com.tookitaki.api.models;

import java.util.List;

/**
 * 
 * Data model object for Bitcoin pricing.
 * 
 * @author RAVIKISHORE
 *
 */
public class Bitcoin {

	private String base;

	private String currency;

	private List<Price> prices;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

}
