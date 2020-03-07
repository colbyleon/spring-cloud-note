package com.dsky.scdemo.userservice.controller;

import com.dsky.scdemo.userservice.feign.OrderServiceClient;
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
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private OrderServiceClient orderServiceClient;

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

    @GetMapping("/hello/hystrix")
    public ResponseEntity<String> helloHystrix() {
        return ResponseEntity.ok("hello hystrix");
    }

    @GetMapping("/lb/choose")
    public ResponseEntity<?> choose(){
        return ResponseEntity.ok(orderServiceClient.chooseUrl("order-service"));
    }
}
