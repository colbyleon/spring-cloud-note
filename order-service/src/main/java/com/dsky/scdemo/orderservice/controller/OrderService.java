package com.dsky.scdemo.orderservice.controller;

import com.dsky.scdemo.orderservice.feign.client.UserServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author colby.luo
 * @date 2020/2/28 17:38
 */
@RestController
@RequestMapping("/order")
public class OrderService {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserServiceClient userServiceClient;

    @GetMapping("/hello")
    public String hello() {
        return restTemplate.getForObject("http://user-service/user/hello", String.class);
    }

    @GetMapping("/hello/feign")
    public String helloFeign(){
        return userServiceClient.hello();
    }

}
