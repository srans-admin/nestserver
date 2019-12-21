package com.srans.nestserver.model;

public class BedBookingTypeChartInfo {

	private String monthType;
	private Integer totalbookedbed;

	public BedBookingTypeChartInfo() {
		this.monthType = "";
		this.totalbookedbed = 0;

	}

	public String getMonthType() {
		return monthType;
	}

	public void setMonthType(String monthType) {
		this.monthType = monthType;
	}

	public Integer getTotalbookedbed() {
		return totalbookedbed;
	}

	public void setTotalbookedbed(Integer totalbookedbed) {
		this.totalbookedbed = totalbookedbed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BedBookingTypeChartInfo [monthType=");
		builder.append(monthType);
		builder.append(", totalbookingbed=");
		builder.append(totalbookedbed);
		builder.append("]");
		return builder.toString();
	}

}
