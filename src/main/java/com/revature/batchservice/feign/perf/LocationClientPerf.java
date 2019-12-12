package com.revature.batchservice.feign.perf;

import com.revature.batchservice.feign.base.LocationClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 */
@Profile("perf")
@FeignClient(name="location-service", url = "${client.url}")
public interface LocationClientPerf extends LocationClient {
}
