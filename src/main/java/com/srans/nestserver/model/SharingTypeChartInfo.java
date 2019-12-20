/**
 * 
 */
package com.srans.nestserver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manish
 *
 */
public class SharingTypeChartInfo {

	private String sharingType;
	private List<Integer> bedsAvailibilty;// Order of Reserved, Filled, Vaccant

	public SharingTypeChartInfo() {
		this.sharingType = "Single";
		this.bedsAvailibilty = new ArrayList<Integer>();

	}

	public String getSharingType() {
		return sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}

	public List<Integer> getBedsAvailibilty() {
		return bedsAvailibilty;
	}

	public void setBedsAvailibilty(List<Integer> bedsAvailibilty) {
		this.bedsAvailibilty = bedsAvailibilty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SharingTypeChartInfo [sharingType=");
		builder.append(sharingType);
		builder.append(", bedsAvailibilty=");
		builder.append(bedsAvailibilty);
		builder.append("]");
		return builder.toString();
	}

}
