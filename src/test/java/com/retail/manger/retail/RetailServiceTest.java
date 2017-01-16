package com.retail.manger.retail;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.manger.Application;
import com.retail.manger.ApplicationTests;
import com.retail.manger.dto.LocationDto;
import com.retail.manger.dto.ShopInputDto;
import com.retail.manger.dto.ShopResponseDto;
import com.retail.manger.util.StoreUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@EnableAutoConfiguration
public class RetailServiceTest extends ApplicationTests {
	private ShopInputDto dto = null;
	private ShopResponseDto shop = null;
	@Before
	public void setup(){
		dto = new ShopInputDto();
		dto.setName("A");
		dto.setNumber(1L);
		dto.setPostalCode(221001L);
		try {
			shop = retailService.addShop(dto);
		} catch (Exception e) {
			fail("Failed to add shop.");
		}
	}
	@Test
	public void testSuccesAddShop(){
		try {
			assertNotNull("Failed to add shop.",shop);
			assertNotNull("Failed to create UUID.", shop.getId());
			assertTrue("Failed to store new shop.", StoreUtil.shopMap.containsKey(shop.getId()));
		} catch (Exception e) {
			fail("Failed to add shop.");
		}
	}
	@Test
	public void testSuccessFindShop(){
		ShopResponseDto responseDto = retailService.findOne(shop.getId());
		assertNotNull(responseDto);
		assertTrue("Failed to find Shop details.",dto.getName() == responseDto.getName());
	}
	@Test
	public void testSuccessFindAll(){
		List<ShopResponseDto> responseDto = retailService.findAll();
		assertNotNull(responseDto);
		assertTrue("Failed to fetch all shops details.", !responseDto.isEmpty());
		assertTrue("Failed to fetch all shops details.",StoreUtil.shopMap.size() == responseDto.size());
	}
	@Test
	public void testSuccessShopUpdate(){
		dto.setPostalCode(411001L);
		dto.setName("AA");
		try {
			ShopResponseDto updateShop = retailService.updateShop(shop.getId(), dto);
			LocationDto locationDto = updateShop.getLocationDto();
			assertNotNull("Failed to update shop details.",locationDto);
			assertTrue("Failed to update location latitude.",locationDto.getLatitude() == 18.5296029);
			assertTrue("Failed to update location longitude.",locationDto.getLongitude() == 73.87601529999999);
			assertTrue("Failed to update shop name.",updateShop.getName() == dto.getName());
		} catch (Exception e) {
			fail("Failed to update shop details.");
		}
	}
	@Test(expected=IllegalArgumentException.class)
	public void testFailureShopAdd() throws Exception{
		ShopInputDto inputDto = new ShopInputDto();
		retailService.addShop(inputDto);
	}
}
