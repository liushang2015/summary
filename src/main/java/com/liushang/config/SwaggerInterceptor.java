package com.liushang.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class SwaggerInterceptor implements HandlerInterceptor {
    @Value("${swagger.disabled}")
    private Boolean disabledSwagger;

    @Value("${swagger.redirect-uri}")
    private String redirectUri;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!disabledSwagger) return Boolean.TRUE;
        String uri = redirectUri;
        if(uri == null || uri.trim().length() == 0)
            uri = "/";
        try {
            response.sendRedirect(uri);
        } catch (IOException e) {
            log.error(String.format("Redirect to '%s' for swagger throw an exception : %s", uri, e.getMessage()), e);
        }
        return Boolean.FALSE;
    }
}
