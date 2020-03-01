package com.dsky.scdemo.userservice.endpoint;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author colby
 * @date 2020/2/29 12:15
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (Math.random() < 0) {
            builder.down().withDetail("status", false);
        }  else {
            builder.up().withDetail("status", true);
        }
    }
}
