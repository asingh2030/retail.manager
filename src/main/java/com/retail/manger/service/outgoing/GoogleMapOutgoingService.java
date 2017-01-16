package com.retail.manger.service.outgoing;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.retail.manger.util.StoreUtil;

@Service("mapService")
public class GoogleMapOutgoingService implements OutgoingService {
	
	@Autowired
	private GeoApiContext context;

	@Override
	public Map<String, Object> getResponse(Object... args) throws Exception {
		Map<String, Object> respose = new HashMap<>();
		Object[] inputArr = args;
		if(inputArr == null || inputArr.length <= 0 ){
			throw new IllegalArgumentException("Input parameter should contain postal code.");
		}
		long postalcode = (long) inputArr[0];
		GeocodingResult[] mapRes = GeocodingApi.newRequest(context).address(String.valueOf(postalcode)).await();
		if(mapRes != null && mapRes.length > 0 && mapRes[0].geometry != null && mapRes[0].geometry.location != null){
			respose.put(StoreUtil.LATITUDE, mapRes[0].geometry.location.lat);
			respose.put(StoreUtil.LONGITUDE, mapRes[0].geometry.location.lng);
		}
		
		return respose;
	}

}
