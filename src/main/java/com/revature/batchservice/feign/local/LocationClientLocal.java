package com.revature.batchservice.feign.local;

import com.revature.batchservice.feign.base.LocationClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 * 8/21/2019
 */
@Profile("local")
@FeignClient(name= "location-service", fallback= LocationClientFallbackLocal.class)
public interface LocationClientLocal extends LocationClient {
}
