package com.dsky.scdemo.orderservice.loadbalanced;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author colby
 * @date 2020/2/29 21:00
 */
@Configuration
public class RibbonUserServiceConfiguration {

    @Bean
    public IRule rule() {
//        return new MyRule();
        return new RetryRule(new MyRule());
    }
}
