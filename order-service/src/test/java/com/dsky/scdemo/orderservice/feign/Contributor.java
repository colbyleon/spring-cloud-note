package com.dsky.scdemo.orderservice.feign;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author colby
 * @date 2020/3/1 16:53
 */
@Data
@Accessors(chain = true)
public class Contributor {
    String login;
    int contributions;
}
