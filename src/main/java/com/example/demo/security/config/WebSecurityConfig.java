package com.example.demo.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {


//    private JsonWebTokenAuthenticationProvider authenticationProvider;
//
//    private JsonWebTokenAuthenticationFilter jsonWebTokenFilter;
//
//    @Autowired
//    public void setAuthenticationProvider(JsonWebTokenAuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Lazy
//    @Autowired
//    public void setJsonWebTokenFilter(JsonWebTokenAuthenticationFilter jsonWebTokenFilter) {
//        this.jsonWebTokenFilter = jsonWebTokenFilter;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return new ProviderManager(authenticationProvider);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
//
//        http
//// disable CSRF, http basic, form login
//                .csrf().disable() //
//                .httpBasic().disable() //
//                .formLogin().disable()
//
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
//                .and().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
//
//// Let child classes set up authorization paths
//        setupAuthorization(http);
//
//        http.addFilterBefore(jsonWebTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

}
