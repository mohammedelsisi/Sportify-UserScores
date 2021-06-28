package com.example.SportifyUserScores.config;

import com.example.SportifyUserScores.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {


    final JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfigurer(JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/predictions").hasAuthority("USER_ROLE")
//                .antMatchers(HttpMethod.POST,"/predictions/check").hasAuthority("USER_ROLE")
//                .antMatchers(HttpMethod.GET,"/predictions/*").hasAuthority("ADMIN_ROLE")
//                .antMatchers(HttpMethod.GET,"/predictions").hasAuthority("ADMIN_ROLE")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

