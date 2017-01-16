package com.retail.manger.util;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.retail.manger.dao.shop.ShopDao;

public class StoreUtil {
	
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static ConcurrentMap <UUID, ShopDao> shopMap = new ConcurrentHashMap<>();
	
}
