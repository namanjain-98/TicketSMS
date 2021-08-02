package com.namanjain.configurations;

import java.util.Properties;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan({"com.namanjain"})
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver resolver(){
             InternalResourceViewResolver resolver = new InternalResourceViewResolver();
             resolver.setViewClass(JstlView.class);
             resolver.setPrefix("/WEB-INF/views/");
             resolver.setSuffix(".jsp");
             return resolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resource/**")
            .addResourceLocations("/resource/");
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }
    
    @Bean
    public JavaMailSender configureJavaMailSender(){
    	JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
    	javaMailSenderImpl.setHost("smtp.gmail.com");
    	javaMailSenderImpl.setUsername("ticketsmsnamanjain@gmail.com");
    	javaMailSenderImpl.setPassword("Naman@3007");
    	javaMailSenderImpl.setPort(587);
    	
    	Properties mailProperties = new Properties();
    	mailProperties.put("mail.smtp.starttls.enable",true);
    	mailProperties.put("mail.smtp.ssl.trust","smtp.gmail.com");
    	mailProperties.put("mail.smtp.auth", "true");
    	
    	javaMailSenderImpl.setJavaMailProperties(mailProperties);
    	
    	return javaMailSenderImpl;
    	
    }
   
}