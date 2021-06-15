package com.gladias.royalbet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthFilter extends BasicAuthenticationFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final String secret;

    public JwtAuthFilter(AuthenticationManager authenticationManager,
                         CustomUserDetailsService userDetailsService,
                         String secret) {
        super(authenticationManager);
        this.customUserDetailsService = userDetailsService;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }


        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        try {
            Cookie cookie = Arrays
                .stream(request.getCookies())
                .filter(c -> c.getName().equals("token"))
                .findFirst()
                .get();

            String token = cookie.getValue();

            String login = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();

            UserPrincipal userDetails = (UserPrincipal) customUserDetailsService.loadUserByUsername(login);
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        } catch (Exception e) {
            return null;
        }
    }
}