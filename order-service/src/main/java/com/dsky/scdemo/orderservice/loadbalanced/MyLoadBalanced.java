package com.dsky.scdemo.orderservice.loadbalanced;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import java.lang.annotation.*;

/**
 *  模拟 {@link LoadBalanced} 注解
 *  被注解的 RestTemplate 会被添加一个拦截器 {@link MyLoadBalancerInterceptor}
 *  拦截器里是 RibbonClient 的逻辑
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
