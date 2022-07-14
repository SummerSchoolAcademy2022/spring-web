package com.example.springwebtraining.filter_intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestLoggingFilter implements Filter {

    //TODO: OncePerRequestFilter

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);

    private static final String AUTH_HEADER_KEY = "x-api-key";
    private static final String KEY = "123456"; // Btw, this should not be here ;)

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating Request Logging Filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!KEY.equals(request.getHeader(AUTH_HEADER_KEY))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The api key provided is not valid.");
        }
        chain.doFilter(request, response);
    }

}
