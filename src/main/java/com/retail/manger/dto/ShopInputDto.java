package com.retail.manger.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel( value = "ShopDetails", description = "Shop resource representation" )
public class ShopInputDto implements Serializable {

	private static final long serialVersionUID = -7729553282230831405L;

	@ApiModelProperty( value = "Shop name.", required = true )
	private String name;
	@ApiModelProperty( value = "Shop number.", required = true )
	private long number;
	@ApiModelProperty( value = "Shop postalcode.", required = true )
	private long postalCode;
	
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
	@Override
	public String toString() {
		return "ShopDto [" +", name=" + name + ", number=" + number + ", postalCode=" + postalCode
				+ "]";
	}
	
}
