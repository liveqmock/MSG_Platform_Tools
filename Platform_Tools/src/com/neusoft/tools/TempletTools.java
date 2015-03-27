package com.neusoft.tools;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import com.neusoft.web.model.SearchResultTemplet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 邮件发送
 * @author Administrator
 *
 */
public class TempletTools {
	/**
     * 
     * @param templetid
     * @throws IOException 
     * @throws TemplateException 
     */
    public static String getTemplet(SearchResultTemplet resultTemplet , Map<String , Object> values) throws IOException, TemplateException{
    	StringWriter writer = new StringWriter(); 
		
		Configuration cfg = null;
		Template template = null ;
		if(resultTemplet!=null){
			cfg = new Configuration();
			TempletLoader loader = new TempletLoader(resultTemplet.getTemplettext()) ;
			cfg.setTemplateLoader(loader);   
			cfg.setDefaultEncoding("UTF-8");  
			template = cfg.getTemplate("");
			template.process(values, writer);  
		}
		return writer.toString() ;
    }
}
