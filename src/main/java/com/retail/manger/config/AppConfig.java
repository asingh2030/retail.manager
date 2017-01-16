package com.retail.manger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;

@Configuration
public class AppConfig {
	
	@Value("${google.map.key}")
	private String googleKey;
	
	@Bean
	public GeoApiContext getGeoContext(){
		GeoApiContext context = new GeoApiContext().setApiKey(googleKey);
		return context;
	}
}
