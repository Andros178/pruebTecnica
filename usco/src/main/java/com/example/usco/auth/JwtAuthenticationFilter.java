package com.example.usco.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Claims claims = jwtUtil.validateAndGetClaims(token);
                String nombre = claims.getSubject();
                Long id = claims.get("id", Long.class);
                java.util.List<String> roles = claims.get("roles", java.util.List.class);

                java.util.List<org.springframework.security.core.GrantedAuthority> authorities = java.util.Collections.emptyList();
                if (roles != null && !roles.isEmpty()) {
                    authorities = roles.stream()
                        .filter(r -> r != null)
                        .map(r -> r.toString().trim().toUpperCase())
                        .map(r -> (org.springframework.security.core.GrantedAuthority)
                            new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + r))
                        .collect(java.util.stream.Collectors.toList());
                }

                if (nombre != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            nombre, null, authorities);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ex) {
                // token invalid - ignore and continue unauthenticated
            }
        }

        filterChain.doFilter(request, response);
    }

}
