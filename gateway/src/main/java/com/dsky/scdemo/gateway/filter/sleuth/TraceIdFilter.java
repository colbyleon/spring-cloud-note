package com.dsky.scdemo.gateway.filter.sleuth;

import brave.Span;
import brave.Tracer;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author colby
 * @date 2020/3/15 11:46
 */
@Component
public class TraceIdFilter implements WebFilter {

    @Resource
    private Tracer tracer;

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        exchange.getResponse().beforeCommit(() -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                String traceId = currentSpan.context().traceIdString();
                exchange.getResponse().getHeaders().add("TRACE-ID", traceId);
            }
            return Mono.empty();
        });
        return chain.filter(exchange);
    }
}
