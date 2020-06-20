package com.galina.coupons.filters;


import com.galina.coupons.beans.PostLoginData;
import com.galina.coupons.logic.CacheController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class LoginFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

    @Autowired
    private CacheController cacheController;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURL().toString();

        if (url.endsWith("/register")) {
            chain.doFilter(httpRequest, response);
            return;
        }

        if (url.endsWith("/login")) {
            chain.doFilter(httpRequest, response);
            return;
        }

        String token = httpRequest.getHeader("token");

        PostLoginData postLoginData = (PostLoginData) cacheController.get(token);
        if (postLoginData != null) {
            request.setAttribute("userData", postLoginData);
            chain.doFilter(request, response);
            return;
        }
    }
}
