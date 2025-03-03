package com.example.barseek_rating_mservice.service

import com.example.barseek_rating_mservice.exception.custom.InvalidDataException
import com.example.barseek_rating_mservice.security.UserPrincipal
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class JwtService {

    companion object {
        const val BEARER_PREFIX = "Bearer "
        const val HEADER_NAME = "Authorization"
    }

    @Value("\${security.jwt.token.secret-key}")
    private lateinit var jwtSigningKey: String

    fun createAuthentication(token: String): Authentication {
        val claims: Claims = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtSigningKey.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload

        val userId = claims.subject
        val role = claims.get("role", String::class.java)
        val principal = UserPrincipal(userId, role)

        return UsernamePasswordAuthenticationToken(principal, null, principal.authorities)
    }

    fun extractToken(request: HttpServletRequest): String {
        val header = request.getHeader(HEADER_NAME)
        return if (header != null && header.startsWith(BEARER_PREFIX)) {
            header.substring(BEARER_PREFIX.length)
        } else {
            throw InvalidDataException("jwt is incorrect")
        }
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSigningKey.toByteArray()))
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }


}