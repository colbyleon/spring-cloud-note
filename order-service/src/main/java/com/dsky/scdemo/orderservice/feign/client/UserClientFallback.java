package com.dsky.scdemo.orderservice.feign.client;

import org.springframework.stereotype.Component;

/**
 * feign-hystrix 的回退方法
 * 结合 org.springframework.cloud.openfeign.FeignClient#fallback() 使用
 *
 * @author colby.luo
 * @date 2020/3/3 10:46
 */
@Component
public class UserClientFallback implements UserServiceClient {
    @Override
    public String helloFeign() {
        return "fallback hello feign";
    }

    @Override
    public String helloHystrix() {
        return "fallback hello hystrix";
    }
}
