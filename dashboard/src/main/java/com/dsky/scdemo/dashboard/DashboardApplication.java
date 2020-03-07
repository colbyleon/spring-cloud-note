package com.dsky.scdemo.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * http://localhost:9011/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9011%2Fturbine.stream
 *
 * @author colby.luo
 */
@EnableTurbine
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class DashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }

}
