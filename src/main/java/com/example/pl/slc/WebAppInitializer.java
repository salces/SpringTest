package com.example.pl.slc;

import com.example.pl.slc.aspect.AspectConfiguration;
import com.example.pl.slc.controller.ControllerConfig;
import com.example.pl.slc.repository.JpaConfig;
import com.example.pl.slc.security.SecurityConfig;
import com.example.pl.slc.service.ServiceConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;



@Order(1)
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(
                SecurityConfig.class,
                ControllerConfig.class,
                ServiceConfig.class,
                JpaConfig.class,
                AspectConfiguration.class
                );
        container.addListener(new ContextLoaderListener(rootContext));
        ServletRegistration.Dynamic dispatcherServlet =
                container.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }

}
