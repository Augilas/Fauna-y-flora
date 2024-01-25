package com.example.semestral.Auth;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.semestral.Auth.Constans.*;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private Claims parseTokenFromHeader(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY);
        if (jwtToken != null) {
            return validateToken(jwtToken);
        }
        return null;
    }

    private Claims parseTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return validateToken(cookie.getValue());
                }
            }
        }
        return null;
    }

    private Claims validateToken(String jwtToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(SUPER_SECRET_KEY))
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Claims claims = parseTokenFromHeader(request);

            // Si el token no se encuentra en el encabezado, intenta buscarlo en las cookies
            if (claims == null) {
                claims = parseTokenFromCookie(request);
            }

            if (claims != null && claims.get("authorities") != null) {
                setAuthentication(claims);
            } else {
                SecurityContextHolder.clearContext();
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

	
    private void setAuthentication(Claims claims) {
        if (claims.get("authorities") != null) {
            List<String> authorities = (List<String>) claims.get("authorities");

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }
    }
}
