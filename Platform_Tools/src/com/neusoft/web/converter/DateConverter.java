package com.neusoft.web.converter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class DateConverter implements WebBindingInitializer {  
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //可以設定任意的日期格式  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class,   
            new CustomDateEditor(dateFormat, true));  
    }  
}
