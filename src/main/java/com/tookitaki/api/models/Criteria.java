package com.tookitaki.api.models;

import java.util.Date;

public class Criteria {

	private boolean lastWeek;

	private boolean lastYear;

	private Date fromDate;

	private Date toDate;
	
	public Criteria(boolean lastWeek, boolean lastYear, Date fromDate,
			Date toDate) {
		super();
		this.lastWeek = lastWeek;
		this.lastYear = lastYear;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public boolean isLastWeek() {
		return lastWeek;
	}

	public void setLastWeek(boolean lastWeek) {
		this.lastWeek = lastWeek;
	}

	public boolean isLastYear() {
		return lastYear;
	}

	public void setLastYear(boolean lastYear) {
		this.lastYear = lastYear;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Criteria [lastWeek=" + lastWeek + ", lastYear=" + lastYear
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

}
