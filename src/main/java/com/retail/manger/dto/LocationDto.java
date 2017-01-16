package com.retail.manger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel( value = "Location Details.", description = "Shop location representation" )
public class LocationDto {
	@ApiModelProperty( value = "Shop logitude name.", required = true )
	private double longitude;
	@ApiModelProperty( value = "Shop latitude name.", required = true )
	private double latitude;
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
