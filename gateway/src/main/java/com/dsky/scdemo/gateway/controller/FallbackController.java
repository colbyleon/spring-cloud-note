package com.dsky.scdemo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author colby
 * @date 2020/3/8 20:41
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback(){
        return "fallback";
    }

}
