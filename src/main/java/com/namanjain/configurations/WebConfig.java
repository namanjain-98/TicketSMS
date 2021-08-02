package com.namanjain.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


       @Override
        protected Class<?>[] getRootConfigClasses() {
    	   return new Class<?>[] { AppContext.class , SecurityConfig.class };
        }
 
        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class<?>[] { AppConfig.class };
        }
 
        @Override
        protected String[] getServletMappings() {
             return new String[] { "/" };
        }
}