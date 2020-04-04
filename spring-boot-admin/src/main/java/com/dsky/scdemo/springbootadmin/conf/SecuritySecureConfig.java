package com.dsky.scdemo.springbootadmin.conf;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/**
 * @author colby
 * @date 2020/3/28 19:07
 */
@Configuration(proxyBeanMethods = false)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminServer;

    public SecuritySecureConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

        http
                .authorizeRequests(aq -> aq
                        // 放行静态资源
                        .antMatchers(adminServer.path("/assets/**")).permitAll()
                        .antMatchers(adminServer.path("/login")).permitAll()
                        // 其他请求都需要授权
                        .anyRequest().authenticated()
                )
                // 配置登录和登出
                .formLogin(fl -> fl
                        .loginPage(adminServer.path("/login"))
                        .successHandler(successHandler)
                )
                .logout(logout -> logout
                        .logoutUrl(adminServer.path("/logout"))
                )
                // Enables HTTP-Basic support. 客户端注册的时候需要
                .httpBasic().and()
                // csrf保护
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        // 客户端注册(销)，actuator端点 解除csrf保护
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.path("/instances"), HttpMethod.POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/instances/*"), HttpMethod.DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/actuator/**"))
                        )
                )
                .rememberMe(rm -> rm
                        .key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600)
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER");
    }
}
