package com.bookstore.configuration;

import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

    @Configuration
    @Order(1)
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter{
        public UserConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/user*")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("USER")

                    .and()
                    .formLogin()
                    .loginPage("/loginUser")
                    .loginProcessingUrl("/user_login")
                    .failureUrl("/loginUser?error=loginError")
                    .defaultSuccessUrl("/user_page")

                    .and()
                    .logout()
                    .logoutUrl("/user_logout")
                    .logoutSuccessUrl("/protectedLinks")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();
        }

    }

    @Configuration
    @Order(2)
    public static class PublisherConfigurationAdapter extends WebSecurityConfigurerAdapter {

        public PublisherConfigurationAdapter() {
            super();
        }

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/publisher*")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("PUBLISHER")

                    .and()
                    .formLogin()
                    .loginPage("/loginPublisher")
                    .loginProcessingUrl("/publisher_login")
                    .failureUrl("/loginPublisher?error=loginError")
                    .defaultSuccessUrl("/publisherPage")

                    .and()
                    .logout()
                    .logoutUrl("/publisher_logout")
                    .logoutSuccessUrl("/protectedLinks")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();
        }
    }

}
