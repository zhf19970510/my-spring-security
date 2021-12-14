package com.zhf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: 曾鸿发
 * @create: 2021-11-19 05:34
 * @description：security配置类
 **/
@Configuration
// 如果是SpringBoot项目开发，@EnableWebSecurity可以省略，因为自动配置类 SecurityAutoConfiguration 类已经 @Import 了 WebSecurityEnablerConfiguration类，该类已经加上了@EnableWebSecurity注解
// @EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()    // 表单登录
            .loginPage("/login.html")   // 指定登录路径
                .loginProcessingUrl("/user/login")   // 对应 action="/user/login"
                .defaultSuccessUrl("/main.html");
                // .failureHandler();
        // 授权
        http.authorizeRequests()
                // 不用认证
                .antMatchers( "/login.html", "/user/login").permitAll()
                // 认证
                .anyRequest().authenticated();

        // 会话管理，设置失效session
        http.sessionManagement().invalidSessionUrl("/session/invalid")
        // 用户在这个手机登录后，他又在另一个手机登录相同账号，对于之前登录的账号是否需要被挤兑，或者说在第二次登录时限制它登录，
        // maximumSessions 最大会话数量，设置为1表示一个用户只能有一个会话
        // expiredSessionStrategy: 会话过期策略
        .maximumSessions(1)
        .expiredSessionStrategy(new MyExpiredSessionStrategy())
        // 阻止用户第二次登录
        .maxSessionsPreventsLogin(true);

        http.csrf().disable();  // csrf 关闭

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 但是这种方式写死了用户名和密码，也不建议
        // auth.inMemoryAuthentication()
        //         .withUser("fox")
        //         .password(passwordEncoder().encode("123456"))
        //         .authorities("admin")
        //         .and()
        //         .withUser("fox2")
        //         .password(passwordEncoder().encode("123456"))
        //         .authorities("admin");
        auth.userDetailsService(myUserDetailsService);
    }
}
