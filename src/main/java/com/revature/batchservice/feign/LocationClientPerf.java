package com.revature.batchservice.feign;

import com.revature.batchservice.feign.base.LocationClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 */
@Profile("perf")
@FeignClient(name="location-service", url = "http://location.caliber-2-perf")
public interface LocationClientPerf extends LocationClient {
}
