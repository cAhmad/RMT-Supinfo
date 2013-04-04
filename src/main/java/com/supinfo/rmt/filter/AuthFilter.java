package com.supinfo.rmt.filter;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ahmad
 * @version $Id$
 */
//@WebFilter(urlPatterns = {"/faces/manager/*", "/faces/employee/*"})
public class AuthFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String requestedUrl = httpRequest.getRequestURL().toString();
        User user = (User) httpRequest.getSession().getAttribute("user");

        if (user != null) {
            if (requestedUrl.contains("/faces/manager/") && user instanceof Manager) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (requestedUrl.contains("/faces/employee/") && user instanceof Employee) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/faces/login.jsf");
            }
        } else {
            httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/faces/login.jsf");
        }
    }

    @Override
    public void destroy() {
    }
}
