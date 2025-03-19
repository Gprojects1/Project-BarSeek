package com.example.barseek_rating_mservice.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class RequestFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val userId = request.getHeader("X-User-Id")

        if (userId == null || userId.isEmpty()) {
            response.status = HttpStatus.UNAUTHORIZED.value()
            return
        }

        filterChain.doFilter(request, response)
    }

}