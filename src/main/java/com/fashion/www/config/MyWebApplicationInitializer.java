package com.fashion.www.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletCxt) throws ServletException{
		
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(com.fashion.www.config.RootConfig.class);
        ac.register(com.fashion.www.config.WebConfig.class);
		servletCxt.addListener(new ContextLoaderListener(ac));

        // Load Spring web application configuration
        // AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();


        
        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        registration.setMultipartConfig(new MultipartConfigElement("E:/project/fashion/temp"));
        
        CharacterEncodingFilter cef = new CharacterEncodingFilter(); 
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		FilterRegistration.Dynamic cefilter = servletCxt.addFilter("encodingFilter", CharacterEncodingFilter.class);
		cefilter.addMappingForUrlPatterns(null, false, "/");

	    }

}