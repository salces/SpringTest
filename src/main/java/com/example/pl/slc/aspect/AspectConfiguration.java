package com.example.pl.slc.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by slc on 14.07.16.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.example.pl.slc.aspect")
public class AspectConfiguration {
}
