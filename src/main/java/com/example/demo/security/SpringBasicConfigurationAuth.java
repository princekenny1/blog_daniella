package com.example.demo.security;


import com.example.demo.exceptions.JwtAuthenticationEntryPoint;
import com.example.demo.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SpringBasicConfigurationAuth extends WebSecurityConfigurerAdapter {

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    private static class CorsConfig implements CorsConfigurationSource {
        @Override
        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.applyPermitDefaultValues();
            corsConfig.addAllowedMethod(HttpMethod.POST);
            corsConfig.addAllowedMethod(HttpMethod.GET);
            corsConfig.addAllowedMethod(HttpMethod.DELETE);
            //IRT-FRONT-END, CERT
//            corsConfig.setAllowedOrigins(List.of("https://172.16.32.37:8023", "https://197.243.3.212:2244"));
            return corsConfig;
        }
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().configurationSource(new CorsConfig()).and()
//                .authorizeRequests()
//                .antMatchers("/images/**", "/css/**", "/js/**").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/logout").permitAll()
//                .antMatchers("/contact").permitAll()
//                .antMatchers("/about").permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/path/to/allow").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/access-denied").permitAll()
//                .and().logout().permitAll();

        http.antMatcher("/resources/**").authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/path/to/allow").permitAll().anyRequest().authenticated().and()
                .formLogin().loginPage("/access-denied").permitAll().and().logout().permitAll().and()
                .cors();

    }
}
