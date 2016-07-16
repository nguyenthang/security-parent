package com.security.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thangnguyen-imac on 7/16/16.
 */
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    private DefaultRedirectStrategy strategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()){
            logger.info("Cannot redirect");
            return;
        }

        strategy.sendRedirect(request, response, targetUrl);
    }


    protected String determineTargetUrl(Authentication authentication) {
        String url;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Set<String> roles = new HashSet<>();

        for (GrantedAuthority role : authorities){
            roles.add(role.getAuthority());
        }

        if(isAdmin(roles)){
            url = "/admin";
        }else if(isDba(roles)){
            url = "/dba";
        }else if(isUser(roles)){
            url = "/user";
        }else {
            url = "/access_denied";
        }

        return url;
    }

    private boolean isUser(Set<String> roles) {
        boolean isUser = roles.contains("ROLE_USER");
        if(isUser) return true;
        return false;
    }

    private boolean isDba(Set<String> roles) {
        boolean isDba = roles.contains("ROLE_DBA");
        if(isDba) return true;
        return false;
    }

    private boolean isAdmin(Set<String> roles) {
        boolean isAdmin = roles.contains("ROLE_ADMIN");
        if(isAdmin) return true;
        return false;

    }


}
