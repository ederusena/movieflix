package com.movieflix.config;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());

            Optional<JWTUserData> userData = tokenService.validateToken(token);
            if (userData.isPresent()) {
                // In a real application, you would set the user details in the security context
                // here
                // For example:
                // UsernamePasswordAuthenticationToken auth = new
                // UsernamePasswordAuthenticationToken(
                // userData.get(), null, List.of());
                // SecurityContextHolder.getContext().setAuthentication(auth);
                JWTUserData jwtUser = userData.get();
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        jwtUser, null, null);

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
            filterChain.doFilter(request, response);

        } else {
            filterChain.doFilter(request, response);
        }
    }

}
