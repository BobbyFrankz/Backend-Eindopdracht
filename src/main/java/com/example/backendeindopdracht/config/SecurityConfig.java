package com.example.backendeindopdracht.config;

import com.example.backendeindopdracht.filter.JwtRequestFilter;
import com.example.backendeindopdracht.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*  Deze security is niet de enige manier om het te doen.
    In de andere branch van deze github repo staat een ander voorbeeld
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;


    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }



    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic().disable().cors().and()
                .authorizeRequests()
                // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
//                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/users").hasAnyRole("USER","ADMIN")

                .antMatchers(HttpMethod.POST, "/rating").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/rating").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/rating/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/rating/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/rating").hasAnyRole("USER","ADMIN")


                .antMatchers(HttpMethod.POST, "/upload").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/files").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/files/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/audiofiles/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/upload/image").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/images").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/images/**").hasAnyRole("USER","ADMIN")





                // Je mag meerdere paths tegelijk definieren
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
//                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}