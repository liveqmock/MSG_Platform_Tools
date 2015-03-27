package com.neusoft.web.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.neusoft.core.EapDataContext;
import com.neusoft.core.datasource.handler.GeneraDAO;
import com.neusoft.web.model.Cube;
import com.neusoft.web.model.CubeMeasure;
import com.neusoft.web.model.CubeMetadata;
import com.neusoft.web.model.Dimension;
import com.neusoft.web.model.TypeCategory;
import com.neusoft.web.model.User;

@Controller
@SessionAttributes
public class Handler {
	
	public final static int PAGE_SIZE_TW = 20 ;
	public final static int PAGE_SIZE_FV = 50 ;
	public final static int PAGE_SIZE_HA = 100 ;
	
	public User getUser(HttpServletRequest request){
		return (User) request.getSession(true).getAttribute(EapDataContext.USER_SESSION_NAME) ;
	}
	
	public GeneraDAO getService(){
		return EapDataContext.getService() ;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public ModelAndView request(String path) {
		return new ModelAndView(path);
    }
	/**
	 * 
	 * @param data
	 * @return
	 */
	public ModelAndView request(ResponseData data) {
    	return new ModelAndView(data.getPage() , "data", data);
    }
	
	@SuppressWarnings("unchecked")
	public List<TypeCategory> getTypeCategoryList(String orgi){
		return getService().findAllByCriteria(DetachedCriteria.forClass(TypeCategory.class).add(Restrictions.and(Restrictions.eq("orgi", orgi), Restrictions.eq("catetype", EapDataContext.TypeCategoryEnum.KM.toString())))) ;
	}
	
	@SuppressWarnings("unchecked")
	public List<TypeCategory> getKeywordTypeCategoryList(String orgi){
		return getService().findAllByCriteria(DetachedCriteria.forClass(TypeCategory.class).add(Restrictions.and(Restrictions.eq("orgi", orgi), Restrictions.eq("catetype", EapDataContext.TypeCategoryEnum.WEIBO_KEYWORD.toString())))) ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cube> getCube(String orgi){
		return getService().findAllByCriteria(DetachedCriteria.forClass(Cube.class)) ;
	}
	/**
	 * 
	 * @param data
	 * @return
	 */
	public ModelAndView request(ResponseData data , Map<String, Object> dataMap) {
		dataMap.put("data", data) ;
    	return new ModelAndView(data.getPage() , dataMap);
    }
	/**
	 * 
	 * @param data
	 * @return
	 */
	public ModelAndView request(ResponseData data , RequestData rqdata) {
    	return new ModelAndView(rqdata.getQ()!=null ? new StringBuffer().append(data.getPage()).append(data.getPage().indexOf("\\?")<0 ? "?":"").append(rqdata.getQ()).toString() : data.getPage() , "data", data);
    }
	/**
	 * 
	 * @param path
	 * @param error
	 * @return
	 */
	public ModelAndView request(String path , String error) {
    	return request(new ResponseData(path , error)) ;
    }
	/**
	 * 重定向
	 * @param redirectView
	 * @return
	 */
	public ModelAndView request(RedirectView redirectView) {
    	return new ModelAndView(redirectView);
    }
}
