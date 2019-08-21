package com.revature.batchservice.feign.dev;

import com.revature.batchservice.feign.base.LocationClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;

/**
 * @author William Gentry
 * 8/21/2019
 */
@Profile("dev && !prod")
@FeignClient(url = "http://location.caliber-2-dev")
public interface LocationClientDev extends LocationClient {
}
