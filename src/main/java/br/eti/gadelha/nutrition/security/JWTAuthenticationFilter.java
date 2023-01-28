package br.eti.gadelha.nutrition.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTGenerator tokenGenerator;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJWTFromRequest(request);
        if(StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
            String username = tokenGenerator.getUsernameFromJWT(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
    private String getJWTFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7, token.length());
        }
        return null;
    }
}