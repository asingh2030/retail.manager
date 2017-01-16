package com.retail.manger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.retail.manger.dao.shop.Address;
import com.retail.manger.dao.shop.Location;
import com.retail.manger.dao.shop.ShopDao;
import com.retail.manger.dto.LocationDto;
import com.retail.manger.dto.ShopInputDto;
import com.retail.manger.dto.ShopResponseDto;
import com.retail.manger.util.StoreUtil;

@Service
public class RetailService {
	
	@Autowired
	LocationService locationService;
	
	/**
	 * Add Shop details.
	 * @param dto
	 * @return {@link ShopResponseDto}
	 * @throws Exception
	 */
	public ShopResponseDto addShop(ShopInputDto dto) throws Exception{
		ShopResponseDto resDto = new ShopResponseDto();
		validate(dto);
		Location location = locationService.getLocation(dto.getPostalCode());
		if(location != null){
			ShopDao dao = new ShopDao();
			dao.setLocation(location);
			dao.setName(dto.getName());
			Address address = new Address();
			address.setNumber(dto.getNumber());
			address.setPostalCode(dto.getPostalCode());
			dao.setAddress(address);
			StoreUtil.shopMap.put(dao.getId(), dao);
			convertDaoToDto(dao, resDto);
		}
		return resDto;
	}

	private void validate(ShopInputDto dto) throws IllegalArgumentException {
		if(dto == null || StringUtils.isEmpty(dto.getName()) 
				|| dto.getNumber() == 0L){
			throw new IllegalArgumentException("Shop details must not be null. "+dto);
		}
	}
	/**
	 * Update Shop details.
	 * @param id
	 * @param dto
	 * @return {@link ShopResponseDto}
	 * @throws Exception
	 */
	public ShopResponseDto updateShop(UUID id, ShopInputDto dto) throws Exception{
		if(StoreUtil.shopMap.containsKey(id)){
			ShopDao shopDao = StoreUtil.shopMap.get(id);
			Address address = shopDao.getAddress();
			if(!StringUtils.isEmpty(String.valueOf(dto.getPostalCode()))){
				Location location = locationService.getLocation(dto.getPostalCode());
				if(location != null){
					shopDao.setLocation(location);
					address.setPostalCode(dto.getPostalCode());
				}else{
					throw new Exception("Shop location not found. postalcode = "+dto.getPostalCode()+" and name = "+dto.getName());
				}
			}
			if(!StringUtils.isEmpty(String.valueOf(dto.getNumber()))){
				address.setNumber(dto.getNumber());
			}
			if(!StringUtils.isEmpty(dto.getName())){
				shopDao.setName(dto.getName());
			}
			ShopResponseDto responseDto = new ShopResponseDto();
			convertDaoToDto(shopDao, responseDto);
			return responseDto;
		}else{
			throw new IllegalArgumentException("Given shop details not exist.");
		}
	}
	/**
	 * Remove shop details.
	 * @param uuid
	 */
	public void removeShop(UUID uuid){
		StoreUtil.shopMap.remove(uuid);
	}
	/**
	 * Find Shop response details.
	 * @param id
	 * @return {@link ShopResponseDto}
	 */
	public ShopResponseDto findOne(UUID id){
		ShopResponseDto dto = new ShopResponseDto();
		if(StoreUtil.shopMap.containsKey(id)){
			convertDaoToDto(StoreUtil.shopMap.get(id), dto);
		}
		return dto;
	}
	/**
	 * Convert shop entity details to response DTO.
	 * @param ShopDao
	 * @param ShopResponseDto
	 */
	public void convertDaoToDto(ShopDao dao, ShopResponseDto dto) {
		dto.setId(dao.getId());
		dto.setName(dao.getName());
		Address address = dao.getAddress();
		dto.setNumber(address.getNumber());
		dto.setPostalCode(address.getPostalCode());
		Location location = dao.getLocation();
		if(location != null){
			LocationDto locDto = new LocationDto();
			locDto.setLatitude(location.getLatitude());
			locDto.setLongitude(location.getLongitude());
			dto.setLocationDto(locDto);
		}
	}

	public List<ShopResponseDto> findAll(){
		List<ShopResponseDto> dtoList = new ArrayList<>();
		StoreUtil.shopMap.values().forEach( dao -> {
			ShopResponseDto dto = new ShopResponseDto();
			convertDaoToDto(dao, dto);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
