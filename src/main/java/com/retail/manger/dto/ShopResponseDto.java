package com.retail.manger.dto;

import java.io.Serializable;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel( value = "ShopDetails", description = "Shop resource representation" )
public class ShopResponseDto implements Serializable {

	private static final long serialVersionUID = 4368638942157230362L;
	@ApiModelProperty( value = "Shop id.", required = true)
	private UUID id;
	@ApiModelProperty( value = "Shop name.", required = true )
	private String name;
	@ApiModelProperty( value = "Shop number.", required = true )
	private long number;
	@ApiModelProperty( value = "Shop postalcode.", required = true )
	private long postalCode;
	@ApiModelProperty( value = "Shop Location.", required = true)
	private LocationDto locationDto;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public long getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(long postalCode) {
		this.postalCode = postalCode;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public LocationDto getLocationDto() {
		return locationDto;
	}
	public void setLocationDto(LocationDto locationDto) {
		this.locationDto = locationDto;
	}
	@Override
	public String toString() {
		return "ShopDto [id=" + id + ", name=" + name + ", number=" + number + ", postalCode=" + postalCode
				+ ", locationDto=" + locationDto + "]";
	}
	
}
