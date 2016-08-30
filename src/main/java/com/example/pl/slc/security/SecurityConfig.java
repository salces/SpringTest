package com.example.pl.slc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.example.pl.slc.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CustomLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LogoutHandler logoutHandler = new CookieClearingLogoutHandler();

        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/register").permitAll().
                    and().
                logout().logoutUrl("/logout").logoutSuccessUrl("/login").logoutSuccessHandler(logoutSuccessHandler).invalidateHttpSession(true).
                addLogoutHandler(logoutHandler).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll().
                    and().

                authorizeRequests().antMatchers("/admins/**").hasAuthority("ADMIN").
                    and().
                authorizeRequests().anyRequest().authenticated().
                    and().
                formLogin().successHandler(authenticationSuccessHandler);
    }


}
