package com.example.pl.slc.apect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by slc on 14.07.16.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.example.pl.slc.apect")
public class AspectConfiguration {
}
