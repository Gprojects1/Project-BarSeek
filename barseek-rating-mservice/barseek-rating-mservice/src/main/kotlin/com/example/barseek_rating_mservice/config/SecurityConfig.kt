package com.example.barseek_rating_mservice.config

import com.example.barseek_rating_mservice.security.JwtTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(private val jwtTokenFilter: JwtTokenFilter) {

    private val allowedURLS : Array<String> = arrayOf("/h2-console/**","/rating-service-api/v1/ratings")

    @Bean
    fun securityFilterChain(http : HttpSecurity) : SecurityFilterChain {
        
        http
            .csrf { it.disable() }
            .sessionManagement{ session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .authorizeHttpRequests{ auth ->
                auth
                    .requestMatchers(*allowedURLS).permitAll()
                    .anyRequest().authenticated()
            }
            .headers { headers ->
                headers.frameOptions { frameOptions ->
                    frameOptions.sameOrigin()
                }
            }
            .addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter :: class.java)
        
        return http.build();
    }
}