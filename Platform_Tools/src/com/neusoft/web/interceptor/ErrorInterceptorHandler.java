package com.neusoft.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.neusoft.web.handler.ResponseData;

public class ErrorInterceptorHandler implements org.springframework.web.servlet.HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView mv) throws Exception {
		if(mv!=null &&  mv.getModelMap()!=null &&  mv.getModelMap().get("data")!=null && mv.getModelMap().get("data") instanceof ResponseData){
			ResponseData data = (ResponseData) mv.getModelMap().get("data") ;
			if(data.getError()!=null){
				StringBuffer strb = new StringBuffer() ;
				for(int i=0 ; i<data.getError().length() ; i++){
					if(data.getError().charAt(i)>=19968 && data.getError().charAt(i)<=40959){
						strb.append("&#").append((int)data.getError().charAt(i)).append(";") ;
					}else{
						strb.append("&#").append((int)data.getError().charAt(i)).append(";") ;
					}
				}
				response.addHeader("emsg", strb.toString()) ;
				response.setCharacterEncoding("UTF-8") ;
				response.setContentType("text/html,charset=UTF-8") ;
				if(data.isThrowError()){
					response.sendError(400 , data.getError());
					mv.setViewName("/pages/public/error");
				}
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		return true;
	}
}
