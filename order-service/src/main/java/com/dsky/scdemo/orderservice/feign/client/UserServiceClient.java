package com.dsky.scdemo.orderservice.feign.client;

import com.dsky.scdemo.orderservice.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 可将此接口单独放到公共 jar 包中 如 user-server-api
 * provider、consumer 均引入此包
 * provider 提供实现
 * consumer 进行消费
 *
 * @author colby
 * @date 2020/3/1 10:45
 */
@FeignClient(value = "user-service", configuration = FeignConfiguration.class)
public interface UserServiceClient {

    @GetMapping("/user/hello/feign")
    String hello();
}
