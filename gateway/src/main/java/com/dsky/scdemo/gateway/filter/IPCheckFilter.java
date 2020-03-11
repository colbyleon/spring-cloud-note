package com.dsky.scdemo.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * 全局过滤器用它来实现很多统一化处理的业务需求，比如权限认证、IP访问限制等。
 *
 * @author colby
 * @date 2020/3/8 11:11
 */
@Slf4j
//@Component
public class IPCheckFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        log.info("ip:{}", remoteAddress);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
