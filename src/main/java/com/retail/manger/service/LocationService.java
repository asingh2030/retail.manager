package com.retail.manger.service;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.manger.dao.shop.Location;
import com.retail.manger.dao.shop.ShopDao;
import com.retail.manger.dto.LocationDto;
import com.retail.manger.dto.ShopResponseDto;
import com.retail.manger.service.outgoing.OutgoingService;
import com.retail.manger.util.StoreUtil;
@Service
public class LocationService {

	@Autowired
	RetailService retailService;
	
	@Autowired(required=true)
	OutgoingService mapService;
	/**
	 * Return longitude and latitude as per given postal code.
	 * @param postalcode
	 * @return {@link Location}
	 */
	public Location getLocation(long postalcode){
		Location location = null;
		try {
			Map<String, Object> response = mapService.getResponse(postalcode);
			if(response != null){
				double latitude = 0;
				double longitude = 0;
				Set<Entry<String,Object>> entrySet = response.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					if(StoreUtil.LATITUDE.equals(entry.getKey())){
						latitude = (double) entry.getValue();
					}
					if(StoreUtil.LONGITUDE.equals(entry.getKey())){
						longitude = (double) entry.getValue();
					}
				}
				location = new Location(latitude, longitude);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}
	
	/**
	 * Return nearest shop as per given customer location: longitude and latitude.
	 * @param LocationDto
	 * @return {@link ShopResponseDto}
	 */
	public ShopResponseDto findNearestShop(final LocationDto dto){
		Collection<ShopDao> shops = StoreUtil.shopMap.values();
		Location givenLocation = new Location(dto.getLatitude(), dto.getLongitude());
		ShopResponseDto response = new ShopResponseDto();
		if(!shops.isEmpty()){
			ShopDao nearestShop = null;
			double distance = -1;
			for (ShopDao shopDao : shops) {
				Location location = shopDao.getLocation();
				double d = location.distanceTo(givenLocation);
				if(d <= distance || distance < 0){
					nearestShop = shopDao;
					distance = d;
				}
			}
			
			System.out.println("Nearest shop :"+nearestShop);
			System.out.println("Distance : "+distance);
			retailService.convertDaoToDto(nearestShop, response);;
		}
		return response;
	}
	
}
