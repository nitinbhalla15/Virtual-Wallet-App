package com.project.paytm.authentication.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTFilterService jwtService;

    @Autowired
    private UserDetailsService usrDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws
            ServletException, IOException {
        try{
            //Checking if request is having auth token
            final String authToken = request.getHeader("Authorization");
            if(authToken==null || authToken.isBlank() || authToken.isEmpty()){
                filterChain.doFilter(request,response);
                return;
            }
            final String jwtToken = authToken.substring(7);
            //Check if token can be parsed
            final String userName = jwtService.parseUserNameFromToken(jwtToken);
            if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails usrInfo = usrDetailService.loadUserByUsername(userName);
                if(usrInfo.getUsername()!=null && !jwtService.isTokenExpired(jwtToken)){
                    UsernamePasswordAuthenticationToken authenticationTokenToken = new UsernamePasswordAuthenticationToken(
                            usrInfo,
                            null,
                            null
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationTokenToken);
                }else if(usrInfo == null || jwtService.isTokenExpired(jwtToken)){
                    response.setStatus(403);
                    response.getWriter().write("Invalid token");
                    return;
                }
            }
            filterChain.doFilter(request,response);
        }catch (Exception e){
            return;
        }
    }
}
