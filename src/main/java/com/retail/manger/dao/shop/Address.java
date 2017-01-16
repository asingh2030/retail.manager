package com.retail.manger.dao.shop;

import java.io.Serializable;

public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3651065691771035625L;
	
	private long number;
	private long postalCode;
	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (number ^ (number >>> 32));
		result = prime * result + (int) (postalCode ^ (postalCode >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (number != other.number)
			return false;
		if (postalCode != other.postalCode)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [number=" + number + ", postalCode=" + postalCode + "]";
	}
}
