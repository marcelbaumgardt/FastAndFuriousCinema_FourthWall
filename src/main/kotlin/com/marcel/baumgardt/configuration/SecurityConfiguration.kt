package com.marcel.baumgardt.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(builder: AuthenticationManagerBuilder) {
        builder.inMemoryAuthentication()
            .withUser("user")
            .password("{noop}user")
            .roles("USER")
            .and()
            .withUser("admin")
            .password("{noop}admin")
            .roles("ADMIN")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http
            .authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .formLogin().permitAll()
    }
}