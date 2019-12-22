package com.srans.nestserver.model;

public class BedBookingBarChartInfo {

	private String monthType;
	private Integer totalBookedbed;

	public BedBookingBarChartInfo() {
		this.monthType = "";
		this.totalBookedbed = 0;

	}

	public String getMonthType() {
		return monthType;
	}

	public void setMonthType(String monthType) {
		this.monthType = monthType;
	}

	public Integer getTotalBookedbed() {
		return totalBookedbed;
	}

	public void setTotalBookedbed(Integer totalBookedbed) {
		this.totalBookedbed = totalBookedbed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BedBookingTypeChartInfo [monthType=");
		builder.append(monthType);
		builder.append(", totalbookingbed=");
		builder.append(totalBookedbed);
		builder.append("]");
		return builder.toString();
	}

}
