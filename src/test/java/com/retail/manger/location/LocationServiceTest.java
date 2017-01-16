package com.retail.manger.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.manger.Application;
import com.retail.manger.ApplicationTests;
import com.retail.manger.dao.shop.Location;
import com.retail.manger.dto.LocationDto;
import com.retail.manger.dto.ShopInputDto;
import com.retail.manger.dto.ShopResponseDto;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@EnableAutoConfiguration
public class LocationServiceTest extends ApplicationTests {
	
	@Test
	public void testSuccessGetLocation(){
		Location location = locationService.getLocation(postalcode);
		assertNotNull("Failed to get location.",location);
		assertTrue("Failed to get expected latitude.",latitude == location.getLatitude());
		assertTrue("Failed to get expected latitude.", longitude == location.getLongitude());
	}
	
	@Test
	public void testSuccessFindNearestShop(){
		try {
			ShopInputDto inputDto = new ShopInputDto();
			inputDto.setName("Pune");
			inputDto.setNumber(1L);
			inputDto.setPostalCode(411001L);
			retailService.addShop(inputDto);
			ShopInputDto inputDto1 = new ShopInputDto();
			inputDto1.setName("Varanasi");
			inputDto1.setNumber(2L);
			inputDto1.setPostalCode(221001L);
			retailService.addShop(inputDto1);
			ShopInputDto inputDto2 = new ShopInputDto();
			inputDto2.setName("Lucknow");
			inputDto2.setNumber(3L);
			inputDto2.setPostalCode(226001L);
			retailService.addShop(inputDto2);
		
			LocationDto dto = new LocationDto();
			dto.setLatitude(latitude);
			dto.setLongitude(longitude);
		ShopResponseDto nearestShop = locationService.findNearestShop(dto);
		assertNotNull(nearestShop);
		assertEquals("Failed to find nearest location. Location found : "+nearestShop,inputDto.getName(), nearestShop.getName());
		} catch (Exception e) {
			fail("Failed : get exception.");
		}
		
	}

}
