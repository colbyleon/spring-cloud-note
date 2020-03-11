package com.dsky.scdemo.gateway.filter.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义Spring Cloud Gateway过滤器工厂需要继承AbstractGatewayFilterFactory类，重写apply方法的逻辑。
 * 命名需要以GatewayFilterFactory结尾，比如CheckAuthGatewayFilterFactory，那么在使用的时候CheckAuth就是这个过滤器工厂的名称。
 * 如果你的配置是Key、Value这种形式的，那么可以不用自己定义配置类，直接继承AbstractNameValueGatewayFilterFactory类即可。
 *
 * @author colby
 * @date 2020/3/7 21:47
 */
@Slf4j
@Component
public class CheckAuth2GatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuth2GatewayFilterFactory.Config> {

    public CheckAuth2GatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("进入自定义GatewayFilter {} | uri: {}", config.getName(), exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String name;
    }
}
