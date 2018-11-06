package com.revature.batchservice.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name= "location", url = "localhost:8040/")
public interface LocationClient {
	@RequestMapping(method = RequestMethod.GET, value = "all/location/{id}" )
     public ResponseEntity <String> getLocationById(@PathVariable(value="id") Integer locationId);
		
	
}
