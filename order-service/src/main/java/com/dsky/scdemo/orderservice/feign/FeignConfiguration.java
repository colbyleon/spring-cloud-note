package com.dsky.scdemo.orderservice.feign;

import com.dsky.scdemo.orderservice.feign.client.UserServiceClient;
import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配合 {@link UserServiceClient} 使用
 *
 * @author colby
 * @date 2020/3/1 11:13
 */
@Configuration
public class FeignConfiguration {

    /**
     * 日志配置
     * <p>
     * NONE：不输出日志。
     * BASIC：只输出请求方法的URL和响应的状态码以及接口执行的时间。
     * HEADERS：将BASIC信息和请求头信息输出。
     * FULL：输出完整的请求信息。
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 契约配置
     * <p>
     * Spring Cloud在Feign的基础上做了扩展，可以让Feign支持Spring MVC的注解来调用。
     * 原生的Feign是不支持Spring MVC注解的。
     * 如果想在SpringCloud中使用原生的注解方式来定义客户端也是可以的，通过配置契约来改变这个配置，
     * SpringCloud中默认的是SpringMvcContract
     */
    @Bean
    public Contract feignContract() {
//        return new Contract.Default();
        return new SpringMvcContract();
    }

    /**
     * 通常我们调用的接口都是有权限控制的，很多时候可能认证的值是通过参数去传递的，还有就是通过请求头去传递认证信息，
     * 比如Basic认证方式。在Feign中我们可以直接配置Basic认证
     * 不过是一个拦截器而以，我们可以自定义更复杂的功能
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthenticationInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

    /**
     * 通过Options可以配置连接超时时间和读取超时时间
     * 第一个参数是连接超时时间（ms），默认值是10×1000；
     * 第二个是取超时时间（ms），默认值是60×1000。
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(1000, 60000);
    }
}
