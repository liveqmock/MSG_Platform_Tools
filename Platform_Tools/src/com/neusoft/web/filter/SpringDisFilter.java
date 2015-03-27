package com.neusoft.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringDisFilter implements Filter {
	private FilterConfig filterConfig;

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse=(HttpServletResponse)response;  
        HttpServletRequest httpRequest=(HttpServletRequest)request;  
        String uri = httpRequest.getRequestURI() ;
        if(uri!=null && uri.matches("/[a-zA-Z0-9/]*?")){
        	httpResponse.sendRedirect(new StringBuffer().append(uri).append(uri.endsWith("/")?"":"/").append("login.html").toString()) ;
        }else{
        	chain.doFilter(request, response);// 把处理发送到下一个过滤器
        }
	}
}
