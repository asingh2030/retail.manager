package com.retail.manger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retail.manger.dto.LocationDto;
import com.retail.manger.dto.ShopResponseDto;
import com.retail.manger.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/retail")
@Api(value = "/api/v1/retail", description = "Manage Retail shops.")
public class RetailController {
	
	@Autowired
	private LocationService locService;
	
	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/nearest-shop/{customerLongitude}&{customerLatitude:.+}", method = RequestMethod.GET)
	@ApiOperation(value = "Get nearest shop of current position of customer.",
	notes = "Get nearest shop of current position of customer."
	, response = ShopResponseDto.class)
	public ResponseEntity<ShopResponseDto> getNearestShops(@ApiParam(value="customer location longitude.", required=true) 
								@PathVariable("customerLongitude") final double customerLongitude,
								@ApiParam(value="customer location customerLatitude.", required=true) 
								@PathVariable("customerLatitude") final double customerLatitude) {
		LocationDto dto = new LocationDto();
		dto.setLatitude(customerLatitude);
		dto.setLongitude(customerLongitude);
		ShopResponseDto shop = locService.findNearestShop(dto);
		
		return new ResponseEntity<ShopResponseDto>(shop, HttpStatus.OK);
	}
	
	/*@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ShopDto>> findAll(){
		List<ShopDto> shops = retailService.findAll();
		return new ResponseEntity<List<ShopDto>>(shops, HttpStatus.OK);
	}*/
	
}
