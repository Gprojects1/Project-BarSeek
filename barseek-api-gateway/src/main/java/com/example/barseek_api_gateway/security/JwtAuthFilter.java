package com.example.barseek_api_gateway.security;

import com.example.barseek_api_gateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GlobalFilter {

    private final JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = jwtService.extractToken(exchange);

        if(token == null || token.isEmpty()) return chain.filter(exchange);

        if(!jwtService.validateToken(token)){
            System.out.println("validation failed");
            return unauthorizedResponse(exchange);
        }

        String userId = jwtService.extractUserId(token);
        List<String> rolesLst = jwtService.extractRoles(token);
        String roles = String.join(",",rolesLst);

        logger.info("Adding headers: ={}, X-User-Roles={}", userId, roles);

        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("X-User-Id", userId)
                .header("X-User-Roles", roles)
                .build();

        ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();

        return chain.filter(mutatedExchange);
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        System.out.println("unora");
        return exchange.getResponse().setComplete();
    }

}

