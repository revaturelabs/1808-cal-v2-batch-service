package com.revature.batchservice.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.batchservice.entity.BatchEntity;

/**
 * A FeignClient for sending HTTP Requests to the QualityAudit Microservice.
 * 
 * Note that the url and endpoints are hardcoded.
 * 
 * @author Ian Barney
 *
 */
@FeignClient(name = "audit-service", url = "localhost:9075/")
public interface QualityAuditClient {
	/**
	 * Sends a HTTP Get request to the getLocationById endpoint in the
	 * LocationService Controller. Uses the given id to specify which Location to
	 * get.
	 * 
	 * @param locationId
	 *            The Location id to search for.
	 * @return A BatchEntity<String> from the LocationService. Should contain a
	 *         Location address within its body of the form (LocationId, Location
	 *         Name, Street Address City State ZipCode)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "note/create-batch-notes\"")
	public ResponseEntity sendBatch(@RequestBody BatchEntity be);

}