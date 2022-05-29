package com.tcs.docstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter {

	@Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
    private FilterConfig filterConfig;
    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }
    public void setFilterConfig (FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter (ServletRequest request,
                          ServletResponse response,
                          FilterChain chain) {
        try {
                if (response instanceof HttpServletResponse) {
                	HttpServletResponse httpResponse = (HttpServletResponse) response;
                	httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
                	httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
                	httpResponse.setDateHeader("Expires", 0);
                   
                }
                chain.doFilter (request, response);
        } catch (IOException e) {
            System.out.println ("IOException in NoCacheFilter");
            e.printStackTrace() ;
        } catch (ServletException e) {
            System.out.println ("ServletException in NoCacheFilter");
            e.printStackTrace() ;
        }
    }
}