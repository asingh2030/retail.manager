package com.retail.manger;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.retail.manger.service.LocationService;
import com.retail.manger.service.RetailService;

public abstract class ApplicationTests {
	@Autowired
	protected RetailService retailService;
	@Autowired
	protected LocationService locationService;
	@Autowired
	protected GeoApiContext context;
	
	public static final long postalcode = 412207L;
	public double longitude = 0;
	public double latitude = 0;
	
	@Before
	public void setup() {
		try {
			GeocodingResult[] mapRes = GeocodingApi.newRequest(context).address(String.valueOf(postalcode)).await();
			if(mapRes != null && mapRes.length > 0 && mapRes[0].geometry != null && mapRes[0].geometry.location != null){
				latitude = mapRes[0].geometry.location.lat;
				longitude = mapRes[0].geometry.location.lng;
			}
		} catch (Exception e) {
			fail("Setup failed.");
		}
	}
	
}
