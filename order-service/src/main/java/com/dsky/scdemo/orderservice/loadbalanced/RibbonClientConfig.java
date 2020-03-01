package com.dsky.scdemo.orderservice.loadbalanced;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @author colby
 * @date 2020/2/29 21:03
 */
@RibbonClients({
        @RibbonClient(name = "user-service", configuration = RibbonUserServiceConfiguration.class)
})
public class RibbonClientConfig {
}
