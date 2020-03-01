package com.dsky.scdemo.orderservice.feign.client;

import com.dsky.scdemo.orderservice.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author colby
 * @date 2020/3/1 10:45
 */
@FeignClient(value = "user-service", configuration = FeignConfiguration.class)
public interface UserServiceClient {

    @GetMapping("/user/hello/feign")
    String hello();
}
