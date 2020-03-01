package com.dsky.scdemo.orderservice.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author colby
 * @date 2020/2/29 17:33
 */
@RestController
@RequestMapping("/lb")
public class LBController {

    @Resource
    private LoadBalancerClient loadBalancer;

    @GetMapping("/choose")
    public Object chooseUrl(@RequestParam String service){
        return loadBalancer.choose(service);
    }
}
