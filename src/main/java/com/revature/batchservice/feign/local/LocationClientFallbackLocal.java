package com.revature.batchservice.feign.local;

import com.revature.batchservice.feign.base.LocationClient;
import org.springframework.http.ResponseEntity;

public class LocationClientFallbackLocal implements LocationClient {

	@Override
	public ResponseEntity<String> getLocationById(Integer locationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
