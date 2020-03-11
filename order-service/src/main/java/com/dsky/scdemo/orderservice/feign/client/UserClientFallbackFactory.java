package com.dsky.scdemo.orderservice.feign.client;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author colby.luo
 * @date 2020/3/3 10:56
 */
@Slf4j
@Component
public class UserClientFallbackFactory implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable throwable) {
        log.error("user-service 回退：", throwable);
        return new UserServiceClientFallback();
    }
}
