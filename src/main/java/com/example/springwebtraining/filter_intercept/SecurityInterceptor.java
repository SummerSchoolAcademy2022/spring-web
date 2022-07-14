package com.example.springwebtraining.filter_intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Logging Request: {} {}", request.getMethod(), request.getRequestURI());
        LOGGER.info("Logging Response: {}", response.getOutputStream());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
