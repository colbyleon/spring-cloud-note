package com.dsky.scdemo.orderservice.config;

import com.dsky.scdemo.orderservice.loadbalanced.MyLoadBalanced;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author colby.luo
 * @date 2020/2/28 17:41
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @MyLoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .requestFactory(OkHttp3ClientHttpRequestFactory::new)
                .setConnectTimeout(Duration.ofSeconds(1))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }
}
