package com.example.SportifyUserScores.filters;

import com.example.SportifyUserScores.model.dto.UserDto;
import com.example.SportifyUserScores.model.enums.UserRole;
import com.example.SportifyUserScores.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JWTAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            System.err.println(jwt);
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validationToken(jwt)) {
                var dto = new UserDto(jwtUtil.extractEmail(jwt), jwtUtil.extractUsername(jwt), UserRole.valueOf(jwtUtil.extractRole(jwt)));
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto, null, List.of(new SimpleGrantedAuthority(dto.getUserRole().toString())));
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
