package com.dsky.scdemo.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author colby.luo
 * @date 2020/2/28 17:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public ResponseEntity<?> hello() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(10);
//        return ResponseEntity
//                .status(HttpStatus.BAD_GATEWAY)
//                .body("502");

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/hello/feign")
    public ResponseEntity<String> helloFeign() {
        return ResponseEntity.ok("Hello feign");
    }

}
