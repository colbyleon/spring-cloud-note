package com.dsky.scdemo.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author colby.luo
 * @date 2020/3/4 14:32
 */
@FeignClient("order-service")
public interface OrderServiceClient {

    @GetMapping("/lb/choose")
    Object chooseUrl(@RequestParam String service);

}
