package com.writekun.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;
    private final AccountLogoutSuccessHandler accountLogoutSuccessHandler;
    private final AuthEntryPoint authEntryPoint;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                          AuthSuccessHandler authSuccessHandler,
                          AuthFailureHandler authFailureHandler,
                          AccountLogoutSuccessHandler accountLogoutSuccessHandler,
                          AuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authSuccessHandler = authSuccessHandler;
        this.authFailureHandler = authFailureHandler;
        this.accountLogoutSuccessHandler = accountLogoutSuccessHandler;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("xy17u");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http相关的配置，包括登入登出、异常处理、会话管理等
        // 设置 csrf token，防止 post 请求拦截
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers("/api/user/**", "/api/note/**");
        http
                .authorizeRequests()
                    .antMatchers("/api/note/**").hasAuthority("note")
                    .antMatchers("/api/user/register").permitAll()
                    .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                    .loginProcessingUrl("/api/user/login")
                    .successHandler(authSuccessHandler)
                    .failureHandler(authFailureHandler)
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/api/user/logout")
                    .logoutSuccessHandler(accountLogoutSuccessHandler)
                    .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint);
    }
}
