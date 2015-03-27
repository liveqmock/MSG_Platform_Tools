package com.neusoft.web.handler; 

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
@RequestMapping(value = "/{orgi}")
public class IndexController extends Handler{
	@RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request ,@PathVariable String orgi, @ModelAttribute("data") RequestData data) {
		ResponseData responseData = new ResponseData("/pages/index"  ) ; 
		responseData.setValueList(super.getTypeCategoryList(orgi)) ;
		return request(responseData) ;
    }
	
	@RequestMapping(value = "/agent")
    public ModelAndView agent(HttpServletRequest request ,@PathVariable String orgi, @ModelAttribute("data") RequestData data) {
		ResponseData responseData = new ResponseData("/pages/agent"  ) ; 
		return request(responseData) ;
    }
}
