package com.example.pl.slc;

import com.example.pl.slc.apect.AspectConfiguration;
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

/**
 * Created by slc on 11.07.16.
 */

@Order(1)
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        /**
         * First we create a {@link org.springframework.web.context.WebApplicationContext}.
         */
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        /**
         * Than we register our {@link org.springframework.context.annotation.Configuration} classes.
         * Alternatively we can use {@link AnnotationConfigWebApplicationContext#scan(String...)} method.
         */
        rootContext.register(
                SecurityConfig.class,
                ControllerConfig.class,
                ServiceConfig.class,
                JpaConfig.class,
                AspectConfiguration.class
                );
        /**
         * Next we register a {@link ContextLoaderListener} to hook to the servlet lifecycle and load the Spring context.
         */
        container.addListener(new ContextLoaderListener(rootContext));
        /**
         * Next we register the Spring {@link javax.servlet.Servlet} implementation that will handle all the requests.
         * We pass to it the applicationContext we created earlier - this is optional, as {@link DispatcherServlet}
         * will anyway find it in a place where {@link ContextLoaderListener} publishes it, but this way it's more
         * explicit.
         */
        ServletRegistration.Dynamic dispatcherServlet =
                container.addServlet("dispatcher", new DispatcherServlet(rootContext));

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }

}
