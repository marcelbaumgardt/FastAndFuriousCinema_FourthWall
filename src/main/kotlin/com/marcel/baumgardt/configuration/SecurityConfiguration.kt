package com.marcel.baumgardt.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    val dataSource: DataSource,
    val objectMapper: ObjectMapper,
    val successHandler: RestAuthenticationSuccessHandler,
    val failureHandler: RestAuthenticationFailureHandler,
    @param:Value("\${jwt.secret}") private val secret: String,
) : WebSecurityConfigurerAdapter() {

    private val userAuth = "USER"
    private val adminAuth = "ADMIN"

    @Throws(Exception::class)
    override fun configure(builder: AuthenticationManagerBuilder) {
        builder.jdbcAuthentication().dataSource(dataSource)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/login/**").permitAll()
            .antMatchers("/api/movie/**").authenticated()
            .antMatchers("/api/showing/price/*").hasAuthority(adminAuth)
            .antMatchers(HttpMethod.PUT, "/api/showing/dates/**").hasAuthority(adminAuth)
            .antMatchers(HttpMethod.GET, "/api/showing/dates/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(authenticationFilter())
            .addFilter(JwtAuthorizationFilter(authenticationManager(), userDetailsManager(), secret))
            .exceptionHandling()
            .authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            .and()
            .headers().frameOptions().disable()
    }


    @Throws(java.lang.Exception::class)
    fun authenticationFilter(): JsonObjectAuthenticationFilter {
        val authenticationFilter = JsonObjectAuthenticationFilter(objectMapper)
        authenticationFilter.setAuthenticationSuccessHandler(successHandler)
        authenticationFilter.setAuthenticationFailureHandler(failureHandler)
        authenticationFilter.setAuthenticationManager(super.authenticationManager())
        return authenticationFilter
    }

    @Bean
    fun userDetailsManager(): UserDetailsManager {
        return JdbcUserDetailsManager(dataSource)
    }
}