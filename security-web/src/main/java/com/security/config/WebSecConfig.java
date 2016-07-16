package com.security.config;

import com.security.service.auth.CustomAccessDeniedHandler;
import com.security.service.auth.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



/**
 * Created by thangnguyen-imac on 7/16/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("thang").password("thang").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("thang1").password("thang1").roles("USER");
        auth.inMemoryAuthentication().withUser("thang2").password("thang2").roles("ADMIN", "DBA");
    }

    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler(){
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/access_denied");
        return accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
                .antMatchers("/", "/user").access("hasRole('USER')")
                .antMatchers("/admin").access("hasRole('ADMIN')")
                .antMatchers("/dba").access("hasRole('ADMIN') or hasRole('DBA')")
                .and()
                .formLogin().loginPage("/login")
                .successHandler(customSuccessHandler)
                .usernameParameter("ssoId")
                .passwordParameter("password")
                .and()
                .csrf()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied")
                .accessDeniedHandler(customAccessDeniedHandler);
    }

}
