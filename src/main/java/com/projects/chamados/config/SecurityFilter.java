package com.projects.chamados.config;

import com.projects.chamados.services.TechnicianService;
import com.projects.chamados.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService  tokenService;
    @Autowired
    private TechnicianService technicianService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        var token = this.tokenService.getFormattedToken(request);

        if(token != null){
            var technicianEmail = this.tokenService.validateAccessToken(token);

            if(technicianEmail != null){
                var technician = this.technicianService.loadUserByUsername(technicianEmail);

                var authentication = new UsernamePasswordAuthenticationToken(technician, null, technician.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
