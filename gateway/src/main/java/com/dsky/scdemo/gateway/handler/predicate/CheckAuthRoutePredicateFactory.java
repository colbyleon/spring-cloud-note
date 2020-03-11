package com.dsky.scdemo.gateway.handler.predicate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * 自定义路由断言工厂需要继承AbstractRoutePredicateFactory类，重写apply方法的逻辑。
 * 命名需要以RoutePredicateFactory结尾，比如CheckAuthRoutePredicateFactory，那么在使用的时候 CheckAuth 就是这个路由断言工厂的名称
 * 配置的是就是 CheckAuth=xxx
 *
 * @author colby
 * @date 2020/3/7 20:17
 */
@Slf4j
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {


    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            log.info("进入自定义路由断言器, uri:{}", exchange.getRequest().getURI());
            return true;
        };
    }

    @Data
    public static class Config {
        private String name;
    }
}
