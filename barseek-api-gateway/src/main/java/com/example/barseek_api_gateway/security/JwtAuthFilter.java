package com.example.barseek_api_gateway.security;

import com.example.barseek_api_gateway.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GlobalFilter {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = jwtService.extractToken(exchange);

        if(token == null || token.isEmpty()) return chain.filter(exchange);

        if(!jwtService.validateToken(token)) return unauthorizedResponse(exchange);

        exchange.getRequest().mutate()
                .header("X-User-Id", jwtService.extractUserId(token))
                .header("X-User-Roles", String.join(",", jwtService.extractRoles(token)))
                .build();

        return chain.filter(exchange);
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        System.out.println("unora");
        return exchange.getResponse().setComplete();
    }

}

