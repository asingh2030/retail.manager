/**
 * 
 */
package com.retail.manger.dao.shop;

import java.util.UUID;

import com.retail.manger.dao.DAO;

/**
 * @author Ashutosh
 *
 */
public class ShopDao implements DAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8284439646145226229L;
	private UUID id;
	private String name;
	private Address address;
	private Location location;
	
	public ShopDao() {
		id = UUID.randomUUID();
	}
	/* (non-Javadoc)
	 * @see com.retail.manger.dto.DTO#getId()
	 */
	@Override
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "ShopDao [id=" + id + ", name=" + name + ", address=" + address + ", location=" + location + "]";
	}
	
}
