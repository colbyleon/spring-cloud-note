package com.dsky.scdemo.userservice.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author colby
 * @date 2020/2/29 11:20
 */
@RestController
@RequestMapping("/user")
public class EurekaClientController {

    @Resource
    private EurekaClient eurekaClient;

    @GetMapping("/infos")
    public Object serviceUrl(){
        return eurekaClient.getInstancesByVipAddress("user-service", false);
    }
}
