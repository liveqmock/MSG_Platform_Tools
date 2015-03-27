package com.neusoft.web.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.neusoft.core.EapDataContext;
import com.neusoft.util.PlatformMsgTools;
import com.neusoft.web.model.Database;
import com.neusoft.web.model.SearchResultTemplet;
import com.neusoft.web.model.TypeCategory;
import com.neusoft.web.model.User;

@Controller
@SessionAttributes
public class UserController extends Handler{
	/**
	 * 
	 * @param request
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/{orgi}/login")
    public ModelAndView login(HttpServletRequest request ,HttpServletResponse response ,@PathVariable String orgi, @ModelAttribute("user") User data) {
		return data != null && (!StringUtils.isEmpty(data.getEmail()) || !StringUtils.isEmpty(data.getUsername())) && !StringUtils.isEmpty(data.getPassword())  ? logindo(request, orgi, data) : request(super.getUser(request)==null ? "/login" : "redirect:/{orgi}/index.html") ;
	}
	/**
	 * 
	 * @param request
	 * @param data
	 * @return
	 */
    public ModelAndView logindo(HttpServletRequest request ,String orgi, User data) {
  
    	if(data!=null && !(data.getUsername()==null || data.getUsername().length()==0 || data.getPassword()==null || data.getPassword().length()==0)){
			if(request.getSession().getAttribute(EapDataContext.USER_SESSION_NAME)!=null){
				request.getSession().removeAttribute(EapDataContext.USER_SESSION_NAME) ;
			}
			List<User> userList = new ArrayList<User>();
			if(data.getUsername().matches("[\\S\\s]*@[\\S\\s]*")){
				userList = super.getService().findAllByCriteria(DetachedCriteria.forClass(User.class).add(Restrictions.and(Restrictions.eq("orgi", orgi) , Restrictions.eq("email", data.getUsername()))))  ;
			}else{
				userList = super.getService().findAllByCriteria(DetachedCriteria.forClass(User.class).add(Restrictions.and(Restrictions.eq("username", data.getUsername()),Restrictions.eq("orgi", orgi))))  ;
			}
			User user = null ;
			if(userList.size()>0){
				user = userList.get(0) ;
				if(!user.getPassword().equals(PlatformMsgTools.md5(data.getPassword()))){
					ResponseData res =	new ResponseData("/pages/public/message");
					res.setMessage("密码错误，请重新输入！");
					return request(res);
				}else{
					data = user;
				}
			}else{
				ResponseData res =	new ResponseData("/pages/public/message");
				res.setMessage("用户名错误，请确认！");
				return request(res);
			}
    	}else{
    		ResponseData res =	new ResponseData("/pages/public/message");
			res.setMessage("请输入用户名和密码！");
			return request(res);
    	}
    	
    	if(request.getSession().getAttribute(EapDataContext.USER_SESSION_NAME)==null){
    		request.getSession().removeAttribute(EapDataContext.USER_SESSION_NAME) ;
    	}
    	request.getSession().setAttribute(EapDataContext.USER_SESSION_NAME, data) ;
    	return request(new ResponseData("redirect:/"+orgi+"/index.html")) ;
    }
    /**
	 * 
	 * @param request
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/{orgi}/logout")
    public ModelAndView logout(HttpServletRequest request ,@PathVariable String orgi, @ModelAttribute("user") User data) {
		request.getSession().removeAttribute(EapDataContext.USER_SESSION_NAME) ;
		return request(new ResponseData("redirect:/"+orgi+"/login.html")) ;
	}
	
	@RequestMapping(value = "/{orgi}/userlist")
    public ModelAndView userlist(HttpServletRequest request ,   @PathVariable String orgi, @ModelAttribute("user") RequestData data) {
		List<User> dataList = super.getService().findPageByCriteria(DetachedCriteria.forClass(User.class)) ;
		return request(new ResponseData("/pages/manage/system/userlist" , dataList)) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/add")
    public ModelAndView add(HttpServletRequest request ,   @PathVariable String orgi, @ModelAttribute("user") RequestData data) {
		return request(new ResponseData("/pages/manage/system/useradd")) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/edit/{id}")
    public ModelAndView edit(HttpServletRequest request ,   @PathVariable String orgi,  @PathVariable String id, @ModelAttribute("user") User data) {
		return request(new ResponseData("/pages/manage/system/useredit"  ,null, super.getService().getIObjectByPK(User.class, id))) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/info/{id}")
    public ModelAndView info(HttpServletRequest request ,   @PathVariable String orgi,  @PathVariable String id,  @ModelAttribute("user") User data) {
		return request(new ResponseData("/pages/manage/system/userinfo"  ,  super.getService().getIObjectByPK(User.class, id))) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/userinfoedit/{id}")
    public ModelAndView infoedit(HttpServletRequest request ,   @PathVariable String orgi,  @PathVariable String id,  @ModelAttribute("user") User data) {
		return request(new ResponseData("/pages/manage/system/userinfoedit"  , super.getService().getIObjectByPK(User.class, id))) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/userinfoeditdo")
    public ModelAndView userinfoeditdo(HttpServletRequest request ,   @PathVariable String orgi, @ModelAttribute("user") User data) {
		super.getService().updateIObject(data) ;
		return request(new ResponseData(new StringBuffer("redirect:/{orgi}/user/info/").append(data.getId()).append(".html").toString())) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/changepasswd/{id}")
    public ModelAndView changepasswd(HttpServletRequest request ,   @PathVariable String orgi,  @PathVariable String id, @ModelAttribute("user") User data) {
		return request(new ResponseData("/pages/manage/system/changepasswd"  ,null, super.getService().getIObjectByPK(User.class, id))) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/editdo")
    public ModelAndView editdo(HttpServletRequest request ,   @PathVariable String orgi, @ModelAttribute("user") User data) {
		User temp = (User) super.getService().getIObjectByPK(User.class, data.getId()) ;
		data.setOrgi(orgi);
		data.setPassword(temp.getPassword());
		super.getService().updateIObject(data) ;
		return request(new ResponseData("redirect:/{orgi}/userlist.html")) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/changepasswddo")
    public ModelAndView changepasswddo(HttpServletRequest request ,   @PathVariable String orgi, @ModelAttribute("user") User data) {
		User user = (User) request.getSession().getAttribute(EapDataContext.USER_SESSION_NAME);
		data.setOrgi(orgi);
		user.setPassword(PlatformMsgTools.md5(data.getNewpwd()));
		super.getService().updateIObject(user) ;
		return request(new ResponseData("/pages/public/success" , "修改成功！",true,null)) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/checkPassword/{passwd}")
    public ModelAndView checkPassword(HttpServletRequest request ,   @PathVariable String orgi, @PathVariable String passwd, @ModelAttribute("user") User data) {
		ResponseData res = new ResponseData("/pages/public/message");
		User user =(User)request.getSession().getAttribute(EapDataContext.USER_SESSION_NAME);
		if(!user.getPassword().equals(PlatformMsgTools.md5(passwd))){
			res.setMessage("密码错误，请重新输入！");
		}
		return request(res);
    }

	@RequestMapping(value = "/{orgi}/user/adddo")
    public ModelAndView adddo(HttpServletRequest request ,   @PathVariable String orgi, @ModelAttribute("user") User data) {
		data.setOrgi(orgi) ;
		data.setPassword(PlatformMsgTools.md5(data.getPassword()));
		super.getService().saveIObject(data);
		return request(new ResponseData("redirect:/{orgi}/userlist.html")) ;
    }
	
	@RequestMapping(value = "/{orgi}/user/rm/{id}")
    public ModelAndView rm(HttpServletRequest request ,   @PathVariable String orgi, @PathVariable String id, @ModelAttribute("user") User data) {
		if(id.equalsIgnoreCase("402880e52835b654012835b6ab720001")){
			//return request(new ResponseData("redirect:/{orgi}/userlist.html","不支持此操作：系统账户，禁止删除",true,null)) ;
			return null;
		}else{
			data.setId(id) ;
			super.getService().deleteIObject(data) ;
		}
		return request(new ResponseData("redirect:/{orgi}/userlist.html")) ;
    }
	
}