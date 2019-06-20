package com.revature.batchservice.feign;

import org.springframework.http.ResponseEntity;

public class LocationClientFallback implements LocationClient{

	@Override
	public ResponseEntity<String> getLocationById(Integer locationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
