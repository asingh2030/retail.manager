package com.retail.manger.dao.shop;

import java.io.Serializable;

public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2522028938127170961L;
	private static final double earthRadius = 3958.75;
	private double latitude;
	private double longitude;
	
	public Location(double latitude, double longitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	/**
	 * Compute the distance in meters
	 * @param Location
	 * @return distance.
	 */
    public double distanceTo(Location loc)
    {
        double dLat = Math.toRadians(latitude - loc.latitude);
        double dLng = Math.toRadians(longitude - loc.longitude);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(Math.toRadians(latitude)) * 
            Math.cos(Math.toRadians(latitude)) *
            Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
        return dist * 1609;
    }


	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
    
}
