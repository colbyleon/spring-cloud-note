package com.dsky.scdemo.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author colby.luo
 * @date 2020/3/10 17:08
 */
@RequestMapping("/apollo")
@RestController
public class ApolloController {

    @Value("${username:undefined}")
    private String username;

    @GetMapping("/username")
    public String username() {
        return username;
    }
}
