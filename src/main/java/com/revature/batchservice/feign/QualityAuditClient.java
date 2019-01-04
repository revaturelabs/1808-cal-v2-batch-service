package com.revature.batchservice.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(method = RequestMethod.POST, value = "audit/note/create-batch-notes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity sendBatch(@RequestBody BatchEntity be);

}