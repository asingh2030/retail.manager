package com.retail.manger.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retail.manger.dto.ShopInputDto;
import com.retail.manger.dto.ShopResponseDto;
import com.retail.manger.service.RetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/shop")
@Api(value = "/api/v1/shop", description = "Manage Shops.")
public class ShopController {
	@Autowired
	private RetailService retailService;
	
	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "Add shop.",
					notes = "Add shop details with its location."
					, response = ShopResponseDto.class)
	public ResponseEntity<ShopResponseDto> add(@ApiParam(value="Shop details.", required=true) @ModelAttribute("shop") final ShopInputDto dto) {
		HttpStatus status = HttpStatus.OK;
		ShopResponseDto responseDto = null;
		try {
			responseDto = retailService.addShop(dto);
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			e.printStackTrace();
		}
		return new ResponseEntity<ShopResponseDto>(responseDto, status);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "Find all shops in retails management application.",
	notes = "Find all shops."
	, response = ShopResponseDto.class
	, responseContainer = "List")
	public ResponseEntity<List<ShopResponseDto>> findAll(){
		List<ShopResponseDto> shops = retailService.findAll();
		return new ResponseEntity<List<ShopResponseDto>>(shops, HttpStatus.OK);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find shop in retails management application.",
	notes = "Find shop details."
	, response = ShopResponseDto.class)
	public ResponseEntity<ShopResponseDto> find(@ApiParam(value="UUID of shop.", required=true) @PathVariable("id") final UUID id){
		ShopResponseDto shop = retailService.findOne(id);
		return new ResponseEntity<ShopResponseDto>(shop, HttpStatus.OK);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update shop details in retails management application.",
	notes = "Update shop details."
	, response = ShopResponseDto.class)
	public ResponseEntity<ShopResponseDto> update(@ApiParam(value="UUID of shop.", required=true) @PathVariable("id") UUID id, 
			@ApiParam(value="Shop details that need to be update.") @ModelAttribute("shop") final ShopInputDto dto){
		HttpStatus status = HttpStatus.OK;
		ShopResponseDto responseDto = null;
		try {
			responseDto = retailService.updateShop(id, dto);
			
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			e.printStackTrace();
		}
		return new ResponseEntity<ShopResponseDto>(responseDto, status);
	}
}
