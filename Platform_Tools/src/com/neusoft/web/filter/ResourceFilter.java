package com.neusoft.web.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ResourceFilter implements Filter{
	@Override
	public void destroy() {
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		String path = arg0.getRealPath(((HttpServletRequest) arg0).getRequestURI() );
		if(path.toLowerCase().endsWith(".html") && ((HttpServletRequest) arg0).getRequestURI().indexOf("/js/")>=0){
			arg1.setContentType("text/html;charset=UTF-8");
			arg1.setCharacterEncoding("UTF-8");
			FileInputStream input = new FileInputStream(path) ;
			byte[] data = new byte[1024] ;
			int len = 0 ;
			OutputStream out = arg1.getOutputStream() ;
			while((len = input.read(data))>0){
				out.write(data, 0, len) ;
			}
			out.close();
			input.close();
		}else{
			chain.doFilter(arg0, arg1) ;
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
