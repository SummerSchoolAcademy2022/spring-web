package com.example.springwebtraining.filter_intercept;

import com.example.springwebtraining.error.ApiException;
import com.example.springwebtraining.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    private static final String AUTH_HEADER_KEY = "x-api-key";
    private static final String KEY = "123456"; // Btw, this should not be here ;)

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Logging Request: {} {}", request.getMethod(), request.getRequestURI());
        LOGGER.info("Logging Response: {}", response.getOutputStream());
        if (!KEY.equals(request.getHeader(AUTH_HEADER_KEY))) {
            throw new ApiException(ErrorCode.UNAUTHORIZED);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
