package com.dsky.scdemo.zuul.config;

import com.dsky.scdemo.zuul.filter.ErrorFilter;
import com.dsky.scdemo.zuul.filter.IpFilter;
import com.dsky.scdemo.zuul.filter.TestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author colby.luo
 * @date 2020/3/4 16:37
 */
@Configuration
public class FilterConfig {
    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public TestFilter testFilter(){
        return new TestFilter();
    }
}
