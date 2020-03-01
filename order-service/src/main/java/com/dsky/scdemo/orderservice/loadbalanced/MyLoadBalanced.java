package com.dsky.scdemo.orderservice.loadbalanced;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 *
 *
 * @author colby
 * @date 2020/2/29 17:01
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface MyLoadBalanced {
}
