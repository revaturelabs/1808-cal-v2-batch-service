package com.revature.batchservice.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A FeignClient for sending HTTP Requests to the Location Microservice.
 * 
 * Note that the url and endpoints are hardcoded.
 * @author Justin Tu, Bita Mahbod
 *
 */
@FeignClient(name= "location", url = "localhost:9090/")
public interface LocationClient {
	/**
	 * Sends a HTTP Get request to the getLocationById endpoint in the LocationService
	 * Controller. Uses the given id to specify which Location to get.
	 * 
	 * @param locationId The Location id to search for.
	 * @return A BatchEntity<String> from the LocationService. Should contain a Location
	 * address within its body of the form (LocationId, Location Name, Street Address City State ZipCode)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "all/location/{id}" )
     public ResponseEntity <String> getLocationById(@PathVariable(value="id") Integer locationId);
		
	
}
