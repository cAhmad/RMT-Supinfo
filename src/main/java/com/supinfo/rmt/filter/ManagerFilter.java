package com.supinfo.rmt.filter;

import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ahmad
 * @version $Id$
 */
@WebFilter(urlPatterns = "/faces/manager/*")
public class ManagerFilter implements Filter {

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

        User user = (User) httpRequest.getSession().getAttribute("user");
        if (user == null || !(user instanceof Manager)) {
            httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/faces/login.jsf");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
