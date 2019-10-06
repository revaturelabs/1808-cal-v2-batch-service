package com.revature.batchservice.feign.test;

import com.revature.batchservice.feign.base.LocationClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 */
@Profile("test")
@FeignClient(name="location-service", url = "http://127.0.0.1:80")
public interface LocationClientTest extends LocationClient {
}
